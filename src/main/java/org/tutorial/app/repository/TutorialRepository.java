package org.tutorial.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tutorial.app.entity.Tutorial;

import java.util.List;


@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
    List<Tutorial> findAllByPublished(Boolean status);
    List<Tutorial> findByTitle(String title);
}
