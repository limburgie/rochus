package be.rochus.service;

import be.rochus.domain.DynamicImage;

public interface DynamicImageService {

	DynamicImage getImage(String key);

	DynamicImage save(DynamicImage image);

}
