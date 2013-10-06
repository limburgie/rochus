package be.rochus.faces.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.map.MapModel;
import org.springframework.context.annotation.Scope;

import be.rochus.domain.Schutterij;
import be.rochus.domain.type.Series;
import be.rochus.faces.FacesUtil;
import be.rochus.faces.maps.MapModelFactory;
import be.rochus.service.SchutterijService;
import be.rochus.service.exception.NoSuchAddressException;

@Named @Scope("view")
public class SchutterijBean implements Serializable, Converter {

	@Inject private SchutterijService service;
	@Inject private AccountBean accountBean;
	@Inject private FacesUtil facesUtil;
	@Inject private MapModelFactory mapModelFactory;
	
	private Map<Series, List<Schutterij>> schutterijenInSeries;
	private List<Schutterij> schutterijenMetEigenSchietstand;
	private List<Schutterij> schutterijen;
	private Schutterij schutterij;
	private MapModel mapModel;
	
	private boolean eigenSchietstand;

	@PostConstruct
	public void init() {
		String urlTitle = facesUtil.getRequestParam("schutterijId");
		if (urlTitle != null) {
			schutterij = service.getSchutterij(urlTitle);
			eigenSchietstand = schutterij.isEigenSchietstand();
			mapModel = mapModelFactory.fromSchutterij(schutterij);
		} else {
			prepareNew();
		}
	}

	public void prepareNew() {
		schutterij = new Schutterij();
	}
	
	public void setCurrentSchutterij(long id) {
		schutterij = service.getSchutterij(id);
		eigenSchietstand = schutterij.isEigenSchietstand();
	}
	
	public List<Schutterij> getSchutterijen(Series series) {
		if(schutterijenInSeries == null) {
			schutterijenInSeries = new HashMap<Series, List<Schutterij>>();
		}
		if(!schutterijenInSeries.containsKey(series)) {
			schutterijenInSeries.put(series, service.getSchutterijen(series));
		}
		return schutterijenInSeries.get(series);
	}
	
	public List<Schutterij> getSchutterijen() {
		if(schutterijen == null) {
			schutterijen = service.getSchutterijen();
		}
		return schutterijen;
	}
	
	public List<Schutterij> getSchutterijenMetEigenSchietstand() {
		if(schutterijenMetEigenSchietstand == null) {
			schutterijenMetEigenSchietstand = service.getSchutterijenMetEigenSchietstand();
		}
		return schutterijenMetEigenSchietstand;
	}
	
	public void save() {
		if(!accountBean.isLoggedIn()) {
			facesUtil.unauthorizedError();
			return;
		}
		try {
			service.save(schutterij, eigenSchietstand);
			facesUtil.info("Schutterij werd succesvol opgeslagen!");
			facesUtil.closeDialog();
			schutterijenInSeries = null;
			schutterijenMetEigenSchietstand = null;
		} catch(NoSuchAddressException e) {
			facesUtil.error("Opgegeven adres kan niet worden gevonden!");
		}
	}
	
	public boolean isNewSchutterij() {
		return schutterij.getId() == null;
	}

	public Schutterij getSchutterij() {
		return schutterij;
	}

	public void setSchutterij(Schutterij schutterij) {
		this.schutterij = schutterij;
	}
	
	public MapModel getMapModel() {
		return mapModel;
	}
	
	public boolean isEigenSchietstand() {
		return eigenSchietstand;
	}

	public void setEigenSchietstand(boolean eigenSchietstand) {
		this.eigenSchietstand = eigenSchietstand;
	}

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return service.getSchutterij(Long.valueOf(value));
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value == null) {
			return "";
		}
		return String.valueOf(((Schutterij)value).getId());
	}

}
