package be.rochus.faces.bean;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

@Named @Scope("view")
public class PhotoAlbumOldBean /*implements Serializable*/ {

//	private List<PhotoAlbum> albums;
//	private PhotoAlbum album;
//	private Photo photo;
//	
//	@Inject private PhotoService photoService;
//	@Inject private AccountBean accountBean;
//	@Inject private FacesUtil facesUtil;
//	
//	@PostConstruct
//	public void init() {
//		String albumId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("albumId");
//		if(albumId != null) {
//			album = photoService.getAlbum(Long.valueOf(albumId));
//		} else {
//			album = new PhotoAlbum();
//			album.setName("New album");
//		}
//	}
//	
//	public void handleUpload(FileUploadEvent event) {
//		if(!accountBean.isLoggedIn()) {
//			facesUtil.unauthorizedError();
//			return;
//		}
//		byte[] data = event.getFile().getContents();
//		String name = event.getFile().getFileName();
//		try {
//			photoService.createPhoto(album, name, data);
//		} catch (IOException e) {
//			facesUtil.unexpectedError(e);
//		}
//	}
//	
//	public void deletePhoto() {
//		if(!accountBean.isLoggedIn()) {
//			facesUtil.unauthorizedError();
//			return;
//		}
//		photoService.deletePhoto(photo);
//		facesUtil.info("Foto werd succesvol verwijderd");
//	}
//	
//	public PhotoAlbum getAlbum() {
//		return album;
//	}
//	
//	public List<PhotoAlbum> getAlbums() {
//		if(albums == null) {
//			albums = photoService.getAlbums();
//		}
//		return albums;
//	}
//	
//	public boolean isNewAlbum() {
//		return album.getId() == null;
//	}
//	
//	public String save() {
//		if(!accountBean.isLoggedIn()) {
//			return facesUtil.unauthorizedError();
//		}
//		album = photoService.saveAlbum(album);
//		facesUtil.info("Fotoalbum werd succesvol opgeslagen!");
//		if(isNewAlbum()) {
//			return "/pages/manage/edit-album.xhtml?albumId="+album.getId()+"&faces-redirect=true";
//		}
//		return "/pages/fotoalbum.xhtml?albumId="+album.getId()+"&faces-redirect=true";
//	}
//	
//	public void setPhoto(Photo photo) {
//		this.photo = photo;
//	}
//	
//	public Photo getPhoto() {
//		return photo;
//	}
	
}
