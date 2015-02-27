package be.rochus.faces.bean;

import be.rochus.domain.DynamicText;
import be.rochus.service.DynamicTextService;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Named @Scope("view")
public class DynamicTextBean implements Serializable {

	@Inject private DynamicTextService service;

	private Map<String, DynamicText> cache = new HashMap<String, DynamicText>();

	public DynamicText getText(String key) {
		if (cache.containsKey(key)) {
			return cache.get(key);
		}
		DynamicText text = service.getText(key);
		if (text != null) {
			cache.put(key, text);
			return text;
		}
		return null;
	}

	public void save(String key) {
		service.save(getText(key));
	}

}
