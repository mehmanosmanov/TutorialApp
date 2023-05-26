package org.tutorial.app.repository;

import org.tutorial.app.model.Tutorial;

import java.util.List;
public interface TutorialRepository {
    int save(Tutorial book);

    int update(Tutorial book, Long id);

    Tutorial findById(Long id);

    int deleteById(Long id);

    List<Tutorial> findAll();

    List<Tutorial> findByPublished(boolean published);

    List<Tutorial> findByTitleContaining(String title);

    int deleteAll();


}
