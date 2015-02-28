package be.rochus.service.impl;

import be.rochus.domain.DynamicImage;
import be.rochus.repository.DynamicImageRepository;
import be.rochus.service.DynamicImageService;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;

@Named @Transactional(readOnly = true)
public class DynamicImageServiceImpl implements DynamicImageService {

	@Inject private DynamicImageRepository repository;

	public DynamicImage getImage(String key) {
		return repository.findByKey(key);
	}

	@Transactional
	public void save(DynamicImage image) {
		repository.save(image);
	}

}
