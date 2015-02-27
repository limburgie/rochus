package be.rochus.repository;

import be.rochus.domain.DynamicText;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DynamicTextRepository extends JpaRepository<DynamicText, Long> {

	DynamicText findByKey(String key);

}
