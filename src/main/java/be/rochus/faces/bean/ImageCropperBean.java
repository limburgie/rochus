package be.rochus.faces.bean;

import be.rochus.domain.DynamicImage;
import be.rochus.service.DynamicImageService;
import org.primefaces.model.CroppedImage;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named @Scope("view")
public class ImageCropperBean implements Serializable {

	@Inject private DynamicImageService service;

	private CroppedImage croppedImage;

	public void crop(String key) {
		DynamicImage image = service.getImage(key);
		image.setContent(croppedImage.getBytes());
		service.save(image);
	}

	public CroppedImage getCroppedImage() {
		return croppedImage;
	}

	public void setCroppedImage(CroppedImage croppedImage) {
		this.croppedImage = croppedImage;
	}

}
