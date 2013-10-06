package be.rochus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import be.rochus.domain.VlasQuestion;

public interface VlasQuestionRepository extends JpaRepository<VlasQuestion, Long> {

}
