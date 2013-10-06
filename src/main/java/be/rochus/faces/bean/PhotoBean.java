package be.rochus.faces.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import be.rochus.domain.PhotoAlbum;
import be.rochus.faces.FacesUtil;
import be.rochus.service.PhotoService;
import be.rochus.service.exception.PhotoSynchronizationException;

@Named @Scope("view")
public class PhotoBean implements Serializable {

	@Inject private PhotoService photoService;
	@Inject private FacesUtil facesUtil;
	
	private PhotoAlbum album;
	private List<PhotoAlbum> albums;
	
	@PostConstruct
	public void init() {
		String urlTitle = facesUtil.getRequestParam("albumId");
		if (urlTitle != null) {
			album = photoService.getAlbum(urlTitle);
		}
	}
	
	public PhotoAlbum getAlbum() {
		return album;
	}
	
	public List<PhotoAlbum> getAlbums() {
		if(albums == null) {
			initAlbums();
		}
		return albums;
	}
	
	private void initAlbums() {
		albums = photoService.getAlbums();
	}

	public void synchronize() {
		try {
			photoService.synchronize();
			initAlbums();
			facesUtil.info("Foto's van albums, koningskoppels en leden werden gesynchroniseerd.");
		} catch (PhotoSynchronizationException e) {
			facesUtil.unexpectedError(e);
		}
	}

}
