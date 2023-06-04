package org.tutorial.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tutorial.app.entity.Tutorial;


@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
}
