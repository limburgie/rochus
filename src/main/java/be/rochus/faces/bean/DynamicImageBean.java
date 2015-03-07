package be.rochus.faces.bean;

import be.rochus.domain.DynamicImage;
import be.rochus.service.DynamicImageService;
import org.apache.commons.io.FileUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.CroppedImage;
import org.springframework.context.annotation.Scope;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

@Named @Scope("view")
public class DynamicImageBean implements Serializable {

	@Inject private DynamicImageService service;

	private CroppedImage croppedImage;
	private String uploadedFileName;
	private String key;

	public void crop() {
		if (croppedImage != null) {
			updateImage(key, croppedImage.getBytes());
		}
		cancel();
	}

	public void handleFileUpload(FileUploadEvent event) throws IOException {
		key = (String) event.getComponent().getAttributes().get("key");
		byte[] bytes = event.getFile().getContents();
		updateImage(key, bytes);
		uploadedFileName = event.getFile().getFileName();
		FileUtils.writeByteArrayToFile(getFile(), bytes);
	}

	public void cancel() {
		FileUtils.deleteQuietly(getFile());
		key = null;
		uploadedFileName = null;
		croppedImage = null;
	}

	private File getFile() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ServletContext sc = (ServletContext) fc.getExternalContext().getContext();
		String path = sc.getRealPath("/uploads/" + uploadedFileName);
		return new File(path);
	}

	private void updateImage(String key, byte[] bytes) {
		DynamicImage image = service.getImage(key);
		image.setContent(bytes);
		service.save(image);
	}

	public DynamicImage getImage(String key) {
		return service.getImage(key);
	}

	public String getUploadedFileName() {
		return uploadedFileName;
	}

	public CroppedImage getCroppedImage() {
		return croppedImage;
	}

	public void setCroppedImage(CroppedImage croppedImage) {
		this.croppedImage = croppedImage;
	}

}
