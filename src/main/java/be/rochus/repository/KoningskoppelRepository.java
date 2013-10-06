package be.rochus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import be.rochus.domain.Koningskoppel;
import be.rochus.domain.Schutter;

public interface KoningskoppelRepository extends JpaRepository<Koningskoppel, Long> {

	@Query("FROM Koningskoppel WHERE koning=:schutter OR koningin=:schutter ORDER BY jaar ASC")
	List<Koningskoppel> getKoningsKoppels(@Param("schutter") Schutter schutter);

	Koningskoppel findByJaar(int jaar);

}
