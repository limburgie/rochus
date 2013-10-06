package be.rochus.faces.bean;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import be.rochus.domain.Schietstand;
import be.rochus.faces.FacesUtil;
import be.rochus.service.SchutterijService;
import be.rochus.service.exception.NoSuchSchietstandException;

@Named
public class SchietstandBean implements Converter {

	@Inject private SchutterijService schutterijService;
	@Inject private FacesUtil facesUtil;
	
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		try {
			return schutterijService.getSchietstand(Long.valueOf(value));
		} catch (NoSuchSchietstandException e) {
			facesUtil.unexpectedError(e);
			return null;
		}
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value == null) {
			return "";
		}
		return String.valueOf(((Schietstand) value).getId());
	}
	
}
