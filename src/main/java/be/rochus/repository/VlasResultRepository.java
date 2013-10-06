package be.rochus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import be.rochus.domain.VlasResult;

public interface VlasResultRepository extends JpaRepository<VlasResult, Long> {

	@Query("FROM VlasResult ORDER BY date DESC")
	List<VlasResult> getResults();
	
}
