package be.rochus.gdata;

import java.io.IOException;
import java.net.URL;

import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.photos.AlbumEntry;
import com.google.gdata.data.photos.AlbumFeed;
import com.google.gdata.data.photos.PhotoData;
import com.google.gdata.data.photos.PhotoEntry;
import com.google.gdata.data.photos.UserFeed;
import com.google.gdata.util.ServiceException;

public class TestPicasa {

	public static void main(String[] args) throws IOException, ServiceException {
		PicasawebService picasaService = new PicasawebService("Rochus");
		URL feedUrl = new URL("https://picasaweb.google.com/data/feed/api/user/sintrochuszutendaal?kind=album");
		UserFeed userFeed = picasaService.getFeed(feedUrl, UserFeed.class);
		for(AlbumEntry albumEntry: userFeed.getAlbumEntries()) {
			System.out.println(albumEntry.getTitle().getPlainText());
			AlbumFeed album = albumEntry.getFeed(PhotoData.KIND);
			for(PhotoEntry photo: album.getPhotoEntries()) {
				System.out.println(photo.getMediaContents().get(0).getUrl());
			}
		}
	}
	
}
