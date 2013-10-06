package be.rochus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import be.rochus.domain.Schutterij;
import be.rochus.domain.type.Series;

public interface SchutterijRepository extends JpaRepository<Schutterij, Long> {

	List<Schutterij> findBySeries(Series series);

	Schutterij findByUrlTitle(String urlTitle);
	
}
