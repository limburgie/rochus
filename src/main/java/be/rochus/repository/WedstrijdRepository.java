package be.rochus.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import be.rochus.domain.Wedstrijd;

public interface WedstrijdRepository extends JpaRepository<Wedstrijd, Long> {
	
	@Query("FROM Wedstrijd WHERE date BETWEEN :from AND :to ORDER BY date ASC")
	List<Wedstrijd> getGamesBetween(@Param("from") Date from, @Param("to") Date to);

}
