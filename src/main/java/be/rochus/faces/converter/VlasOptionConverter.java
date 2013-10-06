package be.rochus.faces.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import be.rochus.domain.VlasOption;
import be.rochus.service.VlasService;

@Named
public class VlasOptionConverter implements Converter {

	@Inject private VlasService service;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Long optionId = Long.valueOf(value);
		return service.getOption(optionId);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		VlasOption option = (VlasOption) value;
		return String.valueOf(option.getId());
	}

}
