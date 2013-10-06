package be.rochus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import be.rochus.domain.PhotoAlbum;

public interface PhotoAlbumRepository extends JpaRepository<PhotoAlbum, Long> {

	@Query("FROM PhotoAlbum ORDER BY date DESC")
	List<PhotoAlbum> getAlbumsByDate();
	
	PhotoAlbum findByUrlTitle(String urlTitle);

}
