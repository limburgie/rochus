package be.rochus.repository;

import be.rochus.domain.DynamicImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DynamicImageRepository extends JpaRepository<DynamicImage, Long> {

	DynamicImage findByKey(String key);

}
