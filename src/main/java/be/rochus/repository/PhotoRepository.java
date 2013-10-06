package be.rochus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import be.rochus.domain.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

}
