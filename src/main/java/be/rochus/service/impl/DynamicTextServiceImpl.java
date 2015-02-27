package be.rochus.service.impl;

import be.rochus.domain.DynamicText;
import be.rochus.repository.DynamicTextRepository;
import be.rochus.service.DynamicTextService;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;

@Named @Transactional(readOnly = true)
public class DynamicTextServiceImpl implements DynamicTextService {

	@Inject private DynamicTextRepository repository;

	public DynamicText getText(String key) {
		return repository.findByKey(key);
	}

	@Transactional
	public void save(DynamicText text) {
		repository.save(text);
	}

}
