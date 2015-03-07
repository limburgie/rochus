package be.rochus.service.impl;

import be.rochus.domain.Koningskoppel;
import be.rochus.domain.Photo;
import be.rochus.domain.PhotoAlbum;
import be.rochus.domain.Schutter;
import be.rochus.repository.PhotoAlbumRepository;
import be.rochus.repository.PhotoRepository;
import be.rochus.service.KoningskoppelService;
import be.rochus.service.PhotoService;
import be.rochus.service.SchutterService;
import be.rochus.service.exception.PhotoSynchronizationException;
import be.rochus.util.StringUtils;
import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.photos.*;
import com.google.gdata.util.ServiceException;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Named
public class PhotoServicePicasaImpl implements PhotoService {

	private static final String PROFIELFOTOS = "Profielfoto's";
	private static final String KONINGSKOPPELS = "Koningskoppels";
	
	private static final String USER = "sintrochuszutendaal";
	
	@Inject private PhotoRepository photoRepo;
	@Inject private PhotoAlbumRepository albumRepo;
	
	@Inject private SchutterService schutterService;
	@Inject private KoningskoppelService koningskoppelService;
	
	public List<PhotoAlbum> getAlbums() {
		return albumRepo.getAlbumsByDate();
	}

	public PhotoAlbum getAlbum(Long id) {
		return albumRepo.findOne(id);
	}
	
	@Transactional
	public void synchronize() throws PhotoSynchronizationException {
		deleteEverything();
		try {
			PicasawebService picasaService = new PicasawebService("rochus.be");
			URL feedUrl = new URL("https://picasaweb.google.com/data/feed/api/user/"+USER+"?kind=album");
			UserFeed userFeed = picasaService.getFeed(feedUrl, UserFeed.class);
			for(AlbumEntry albumEntry: userFeed.getAlbumEntries()) {
				String name = albumEntry.getTitle().getPlainText();
				AlbumFeed albumFeed = albumEntry.getFeed(PhotoData.KIND);
				if(PROFIELFOTOS.equals(name)) {
					importProfilePictures(albumFeed);
				} else if(KONINGSKOPPELS.equals(name)) {
					importKoningsKoppels(albumFeed);
				} else {
					PhotoAlbum album = new PhotoAlbum();
					album.setName(name);
					album.setDescription(albumEntry.getDescription().getPlainText());
					album.setDate(albumEntry.getDate());
					album.setThumbnailUrl(albumEntry.getMediaGroup().getThumbnails().get(0).getUrl());
					album.setPhotos(new ArrayList<Photo>());
					for(PhotoEntry photoEntry: albumFeed.getPhotoEntries()) {
						Photo photo = new Photo();
						photo.setDescription(photoEntry.getMediaGroup().getDescription().getPlainTextContent());
						photo.setThumbnailUrl(photoEntry.getMediaThumbnails().get(0).getUrl());
						photo.setUrl(photoEntry.getMediaContents().get(0).getUrl());
						photo.setAlbum(album);
						album.getPhotos().add(photo);
					}
					album.setUrlTitle(StringUtils.toUrlTitle(album.getName()));
					albumRepo.save(album);
				}
			}
		} catch (MalformedURLException e) {
			throw new PhotoSynchronizationException(e);
		} catch (IOException e) {
			throw new PhotoSynchronizationException(e);
		} catch (ServiceException e) {
			throw new PhotoSynchronizationException(e);
		}
	}

	private void importKoningsKoppels(AlbumFeed albumFeed) throws PhotoSynchronizationException {
		for(PhotoEntry photoEntry: albumFeed.getPhotoEntries()) {
			String description = photoEntry.getMediaGroup().getDescription().getPlainTextContent();
			try {
				int jaar = Integer.parseInt(description);
				Koningskoppel koppel = koningskoppelService.getKoppelInJaar(jaar);
				if(koppel != null) {
					koppel.setPictureUrl(photoEntry.getMediaContents().get(0).getUrl());
					koppel.setThumbnailUrl(photoEntry.getMediaThumbnails().get(0).getUrl());
					koningskoppelService.save(koppel);
				}
			} catch(NumberFormatException e) {
				continue;
			}
		}
	}

	private void importProfilePictures(AlbumFeed albumFeed) {
		for(PhotoEntry photoEntry: albumFeed.getPhotoEntries()) {
			String schutterName = photoEntry.getMediaGroup().getDescription().getPlainTextContent();
			Schutter schutter = schutterService.getSchutter(schutterName);
			if(schutter == null) {
				continue;
			}
//			schutter.setPictureContent(photoEntry.getMediaContents().get(0).getUrl());
			schutterService.save(schutter);
		}
	}

	private void deleteEverything() {
		photoRepo.deleteAll();
		albumRepo.deleteAll();
	}

	public PhotoAlbum getAlbum(String urlTitle) {
		return albumRepo.findByUrlTitle(urlTitle);
	}

}
