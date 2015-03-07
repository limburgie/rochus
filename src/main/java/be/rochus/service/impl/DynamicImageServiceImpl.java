package be.rochus.service.impl;

import be.rochus.domain.DynamicImage;
import be.rochus.repository.DynamicImageRepository;
import be.rochus.service.DynamicImageService;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;

@Named @Transactional(readOnly = true)
public class DynamicImageServiceImpl implements DynamicImageService {

	@Inject private DynamicImageRepository repository;

	@Transactional
	public DynamicImage getImage(String key) {
		DynamicImage image = repository.findByKey(key);
		if (image == null) {
			image = new DynamicImage();
			image.setKey(key);
			image = save(image);
		}
		return image;
	}

	@Transactional
	public DynamicImage save(DynamicImage image) {
		image.setModifiedDate(new Date());
		return repository.save(image);
	}

}
