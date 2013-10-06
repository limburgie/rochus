package be.rochus.service;

import java.util.List;

import be.rochus.domain.PhotoAlbum;
import be.rochus.service.exception.PhotoSynchronizationException;

public interface PhotoService {

	List<PhotoAlbum> getAlbums();
	
	PhotoAlbum getAlbum(Long id);

//	PhotoAlbum saveAlbum(PhotoAlbum album);
//	
//	Photo getPhoto(Long id);
//
//	Photo createPhoto(PhotoAlbum album, String name, byte[] data) throws IOException;
//
//	void deletePhoto(Photo photo);
	
	void synchronize() throws PhotoSynchronizationException;

	PhotoAlbum getAlbum(String urlTitle);
	
}
