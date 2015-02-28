package be.rochus.service;

import be.rochus.domain.DynamicText;

public interface DynamicTextService {

	DynamicText getText(String key);

	void save(DynamicText text);

}
