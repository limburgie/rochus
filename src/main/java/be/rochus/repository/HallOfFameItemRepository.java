package be.rochus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import be.rochus.domain.HallOfFameItem;

public interface HallOfFameItemRepository extends JpaRepository<HallOfFameItem, Long> {

	@Query("FROM HallOfFameItem ORDER BY jaar DESC")
	public List<HallOfFameItem> findAllItems();

	@Query("FROM HallOfFameItem WHERE behaaldDoor=:achiever ORDER BY jaar ASC")
	public List<HallOfFameItem> findByAchiever(@Param("achiever") String achiever);
	
}
