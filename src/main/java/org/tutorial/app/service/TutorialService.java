package org.tutorial.app.service;

import org.tutorial.app.dto.request.TutorialRequest;
import org.tutorial.app.dto.response.TutorialResponse;

import java.util.List;

public interface TutorialService {
    String create(TutorialRequest book);

    String update(TutorialRequest tutorialRequest, Long id);

    TutorialResponse getById(Long id);

    String removeById(Long id);

    List<TutorialResponse> getAll();

    List<TutorialResponse> getByPublished(boolean published);

    List<TutorialResponse> getByTitleContaining(String title);

    long removeAll();

}
