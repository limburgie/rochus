package be.rochus.service;

import be.rochus.domain.DynamicImage;

public interface DynamicImageService {

	DynamicImage getImage(String key);

	void save(DynamicImage image);

}
