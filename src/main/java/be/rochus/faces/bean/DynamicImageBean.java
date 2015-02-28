package be.rochus.faces.bean;

import be.rochus.domain.DynamicImage;
import be.rochus.service.DynamicImageService;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.ByteArrayInputStream;
import java.io.Serializable;

@Named
public class DynamicImageBean implements Serializable {

	@Inject private DynamicImageService service;

	public StreamedContent getImageContent() {
		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		}

		String key = context.getExternalContext().getRequestParameterMap().get("key");
		return new DefaultStreamedContent(new ByteArrayInputStream(getImage(key).getContent()));
	}

	public void handleFileUpload(FileUploadEvent event) {
		String key = (String) event.getComponent().getAttributes().get("key");
		DynamicImage image = service.getImage(key);
		image.setContent(event.getFile().getContents());
		service.save(image);
	}

	public DynamicImage getImage(String key) {
		return service.getImage(key);
	}

}
