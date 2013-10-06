package be.rochus.faces.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolationException;

import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;

import be.rochus.domain.Koningskoppel;
import be.rochus.domain.Schutter;
import be.rochus.faces.FacesUtil;
import be.rochus.service.KoningskoppelService;

@Named @Scope("view")
public class KoningskoppelBean implements Serializable {

	private List<Koningskoppel> koppels;
	private Koningskoppel koppel;
	
	@Inject private KoningskoppelService service;
	@Inject private AccountBean accountBean;
	@Inject private FacesUtil facesUtil;
	@Inject private SchutterBean schutterBean;
	
	@PostConstruct
	public void init() {
		String jaar = facesUtil.getRequestParam("jaar");
		if (jaar != null) {
			koppel = service.getKoppelInJaar(Integer.parseInt(jaar));
		} else {
			prepareNew();
		}
	}
	
	public void toKoppel() {
		facesUtil.redirect("/koningskoppel/"+koppel.getJaar());
	}

	public void prepareNew() {
		koppel = new Koningskoppel();
		List<Schutter> maleSchutters = schutterBean.getMaleSchutters();
		Schutter defaultMale = maleSchutters.isEmpty() ? null : maleSchutters.get(0);
		List<Schutter> femaleSchutters = schutterBean.getFemaleSchutters();
		Schutter defaultFemale = femaleSchutters.isEmpty() ? null : femaleSchutters.get(0);
		koppel.setKoning(defaultMale);
		koppel.setKoningin(defaultFemale);
	}
	
	public void setCurrentKoppel(long id) {
		koppel = service.getKoppel(id);
	}
	
	public List<Koningskoppel> getKoppels() {
		if(koppels == null) {
			initKoppels();
		}
		return koppels;
	}

	private void initKoppels() {
		koppels = service.getKoningskoppels();
	}

	public Koningskoppel getKoppel() {
		return koppel;
	}

	public void setKoppel(Koningskoppel koppel) {
		this.koppel = koppel;
	}
	
	public void save() {
		if(!accountBean.isLoggedIn()) {
			facesUtil.unauthorizedError();
			return;
		}
		try {
			service.save(koppel);
			facesUtil.info("Koningskoppel werd succesvol opgeslagen!");
			initKoppels();
			facesUtil.closeDialog();
		} catch(ConstraintViolationException e) {
			facesUtil.error("Koning kan niet gelijk zijn aan koningin!");
		} catch(DataIntegrityViolationException e) {
			facesUtil.error("Er bestaat al een koningskoppel voor dit jaar!", e);
		}
	}
	
	public void delete() {
		if(!accountBean.isLoggedIn()) {
			facesUtil.unauthorizedError();
			return;
		}
		service.delete(koppel);
		facesUtil.info("Koningskoppel werd succesvol verwijderd!");
		initKoppels();
	}
	
}
