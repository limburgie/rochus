package be.rochus.faces.bean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.CroppedImage;
import org.primefaces.model.UploadedFile;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;

import be.rochus.domain.Schutter;
import be.rochus.domain.type.Achievement;
import be.rochus.domain.type.KoningsPartner;
import be.rochus.faces.FacesUtil;
import be.rochus.service.HallOfFameItemService;
import be.rochus.service.KoningskoppelService;
import be.rochus.service.SchutterService;
import be.rochus.util.ImageUtils;

@Named @Scope("view")
public class SchutterBean implements Serializable, Converter {

	@Inject private SchutterService schutterService;
	@Inject private AccountBean accountBean;
	@Inject private FacesUtil facesUtil;
	@Inject private KoningskoppelService koningskoppelService;
	@Inject private HallOfFameItemService hallOfFameItemService;

	private List<KoningsPartner> koningsPartners;
	private List<Achievement> achievements;
	private List<Schutter> schutters;
	private List<Schutter> maleSchutters;
	private List<Schutter> femaleSchutters;
	private Schutter schutter;
	private String pictureFileName;
	private CroppedImage croppedPicture;
	private File tempPictureFile;

	@PostConstruct
	public void init() {
		String urlTitle = facesUtil.getRequestParam("schutterId");
		if (urlTitle != null) {
			schutter = schutterService.getSchutter(urlTitle);
			koningsPartners = koningskoppelService.getKoningsPartners(schutter);
			achievements = hallOfFameItemService.getAchievements(schutter);
		} else {
			prepareNew();
		}
	}
	
	public void toSchutter() {
		facesUtil.redirect("/schutter/"+schutter.getUrlTitle());
	}
	
	public void pictureUploaded(FileUploadEvent event) throws IOException {
		UploadedFile file = event.getFile();
		byte[] inputBytes = IOUtils.toByteArray(file.getInputstream());
		byte[] resizedBytes = ImageUtils.resize(inputBytes, 600);
		pictureFileName = "/pictures/temp/"+file.getFileName();
		tempPictureFile = new File(facesUtil.getRealPath(pictureFileName));
		FileUtils.writeByteArrayToFile(tempPictureFile, resizedBytes);
	}
	
	public void prepareNew() {
		schutter = new Schutter();
	}
	
	public void setCurrentSchutter(long schutterId) {
		schutter = schutterService.getSchutter(schutterId);
	}

	public void save() {
		if(!accountBean.isLoggedIn()) {
			facesUtil.unauthorizedError();
			return;
		}
		schutterService.save(schutter);
		initSchutters();
		facesUtil.info("Schutter werd succesvol opgeslagen!");
		facesUtil.closeDialog();
	}
	
	public void crop() throws IOException {
		if(!accountBean.isLoggedIn()) {
			facesUtil.unauthorizedError();
			return;
		}
		byte[] croppedBytes = croppedPicture.getBytes();
		byte[] resizedBytes = ImageUtils.resize(croppedBytes, 100);
		File finalImage = new File(facesUtil.getRealPath("/pictures/"+schutter.getId()+".jpg"));
		FileUtils.writeByteArrayToFile(finalImage, resizedBytes);
		FileUtils.deleteQuietly(tempPictureFile);
		pictureFileName = null;
		croppedPicture = null;
		tempPictureFile = null;
		facesUtil.info("Foto werd succesvol aangepast!");
		facesUtil.closeDialog("fotoDialog");
	}
	
	public void delete() {
		if(!accountBean.isLoggedIn()) {
			facesUtil.unauthorizedError();
		}
		try {
			schutterService.delete(schutter);
			initSchutters();
			facesUtil.info("Schutter werd verwijderd!");
		} catch(DataIntegrityViolationException e) {
			facesUtil.error("Schutter kan niet worden verwijderd omdat deze nog op andere plaatsen gebruikt wordt. Deactiveer de schutter of verwijder ook de referenties naar deze schutter (bv. koningskoppels)");
		}
	}
	
	public List<Schutter> getSchutters() {
		if(schutters == null) {
			initSchutters();
		}
		return schutters;
	}

	private void initSchutters() {
		schutters = schutterService.getSchutters();
	}
	
	public void setSchutter(Schutter schutter) {
		this.schutter = schutter;
	}
	
	public Schutter getSchutter() {
		return schutter;
	}
	
	public List<Schutter> getMaleSchutters() {
		if(maleSchutters == null) {
			maleSchutters = schutterService.getSchuttersMaleOrFemale(true);
		}
		return maleSchutters;
	}
	
	public List<Schutter> getFemaleSchutters() {
		if(femaleSchutters == null) {
			femaleSchutters = schutterService.getSchuttersMaleOrFemale(false);
		}
		return femaleSchutters;
	}

	public Object getAsObject(FacesContext context, UIComponent component, String schutterIdStr) {
		if(StringUtils.isEmpty(schutterIdStr)) {
			return null;
		}
		return schutterService.getSchutter(Long.valueOf(schutterIdStr));
	}

	public String getAsString(FacesContext context, UIComponent component, Object schutterObj) {
		if(schutterObj == null) {
			return "";
		}
		return String.valueOf(((Schutter)schutterObj).getId());
	}
	
	public String getPictureFileName() {
		return pictureFileName;
	}

	public CroppedImage getCroppedPicture() {
		return croppedPicture;
	}

	public void setCroppedPicture(CroppedImage croppedPicture) {
		this.croppedPicture = croppedPicture;
	}
	
	public List<KoningsPartner> getKoningsPartners() {
		return koningsPartners;
	}
	
	public List<Achievement> getAchievements() {
		return achievements;
	}
	
}
