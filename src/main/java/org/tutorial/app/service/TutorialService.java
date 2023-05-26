package org.tutorial.app.service;

import org.tutorial.app.dto.TutorialDto;

import java.util.List;

public interface TutorialService {
    int create(TutorialDto book);

    int update(TutorialDto book, Long id);

    TutorialDto getById(Long id);

    int removeById(Long id);

    List<TutorialDto> getAll();

    List<TutorialDto> getByPublished(boolean published);

    List<TutorialDto> getByTitleContaining(String title);

    int removeAll();

}
