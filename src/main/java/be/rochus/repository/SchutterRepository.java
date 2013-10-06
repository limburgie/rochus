package be.rochus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import be.rochus.domain.Schutter;

public interface SchutterRepository extends JpaRepository<Schutter, Long> {

	@Query("FROM Schutter ORDER BY joinYear ASC")
	List<Schutter> findSchutters();
	
	@Query("FROM Schutter WHERE active=1 ORDER BY joinYear ASC")
	List<Schutter> findActive();

	@Query("FROM Schutter WHERE male=:male AND active=1 ORDER BY joinYear ASC")
	List<Schutter> findMaleOrFemale(@Param("male") boolean male);

	Schutter findByUrlTitle(String urlTitle);
	
}
