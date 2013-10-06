package be.rochus.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import be.rochus.domain.Gebeurtenis;

public interface GebeurtenisRepository extends JpaRepository<Gebeurtenis, Long> {

	@Query("FROM Gebeurtenis ORDER BY startDate DESC")
	List<Gebeurtenis> getEvents();

	@Query("FROM Gebeurtenis WHERE endDate > :from OR startDate < :to")
	List<Gebeurtenis> getEventsBetween(@Param("from") Date from, @Param("to") Date to);

}
