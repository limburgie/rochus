package be.rochus.service.impl;

import javax.inject.Named;

@Named
public class PhotoServiceLocalDatabaseImpl /*implements PhotoService*/ {

//	@Inject private PhotoRepository photoRepository;
//	@Inject private PhotoAlbumRepository albumRepository;
//	
//	public List<PhotoAlbum> getAlbums() {
//		return albumRepository.findAll();
//	}
//
//	public PhotoAlbum getAlbum(Long id) {
//		return albumRepository.findOne(id);
//	}
//
//	@Transactional
//	public PhotoAlbum saveAlbum(PhotoAlbum album) {
//		return albumRepository.save(album);
//	}
//
//	public Photo getPhoto(Long id) {
//		return photoRepository.findOne(id);
//	}
//
//	@Transactional
//	public Photo createPhoto(PhotoAlbum album, String name, byte[] data) throws IOException {
//		Photo photo = new Photo();
//		photo.setAlbum(album);
//		photo.setData(ImageUtils.resize(data, 640));
//		photo.setDescription(name);
//		photo.setCreateDate(new Date());
//		return photoRepository.save(photo);
//	}
//
//	@Transactional
//	public void deletePhoto(Photo photo) {
//		photoRepository.delete(photo);
//	}

}
