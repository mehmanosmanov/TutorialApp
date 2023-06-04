package org.tutorial.app.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tutorial.app.dto.request.TutorialRequest;
import org.tutorial.app.dto.response.TutorialResponse;
import org.tutorial.app.exceptions.NotFoundException;
import org.tutorial.app.exceptions.TutorialAlreadyExistsException;
import org.tutorial.app.entity.Tutorial;
import org.tutorial.app.repository.TutorialRepository;
import org.tutorial.app.service.TutorialService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TutorialServiceImpl implements TutorialService {

    private final TutorialRepository tutorialRepository;

    @Override
    public String create(TutorialRequest request) {
        log.info("Started to create a new tutorial.");
        Tutorial book = tutorialRepository.save(convertToTutorial(request));
        log.info("Created a new tutorial 'id={}' database.", book);
        System.out.println(book);
        return "Tutorial created successfully";
    }

    @Override
    public String update(TutorialRequest tutorialRequest, Long id) {
        getById(id);
        log.info("Start to update tutorial by 'id {}' ", id);
        Tutorial oldTutorial = tutorialRepository.findById(id).orElseThrow(
                () -> {
                    log.warn("Tutorial is not found with id={} ", id);
                    return new NotFoundException("No such tutorial with id=" + id);
                });
        oldTutorial.setName(tutorialRequest.getName());
        oldTutorial.setSubject(tutorialRequest.getSubject());
        oldTutorial.setTitle(tutorialRequest.getTitle());
        oldTutorial.setPublished(tutorialRequest.getPublished());
        tutorialRepository.save(oldTutorial);
        log.info("Tutorial 'id={}' is updated", id);
        return "Tutorial updated successfully";
    }

    @Override
    public TutorialResponse getById(Long id) {
        log.info("Starting to search the tutorial in DB");
        Tutorial tutorial = tutorialRepository.findById(id).orElseThrow(
                () -> {
                    log.warn("Tutorial is not found with id={} ", id);
                    return new NotFoundException("No such tutorial with id=" + id);
                });
        TutorialResponse tutorialResponse = convertToTutorialResponse(tutorial);
        log.info("Tutorial is found with id={} ", id);
        return tutorialResponse;
    }

    @Override
    public String removeById(Long id) {
        log.info("Starting to search the tutorial in DB");
        Tutorial tutorial = tutorialRepository.findById(id).orElseThrow(
                () -> {
                    log.warn("Tutorial is not found with id={} ", id);
                    return new NotFoundException("No such tutorial with id=" + id);
                });
        log.warn("Deleting 'id={}' tutorial", id);
        tutorialRepository.deleteById(id);
        return "Tutorial with id=" + id + " deleted successfully";
    }

    @Override
    public List<TutorialResponse> getAll() {
        log.info("Starting to get all tutorials...");
        List<TutorialResponse> tutorialResponses = tutorialRepository.findAll().stream()
                .map(this::convertToTutorialResponse).collect(Collectors.toList());
        log.info("All tutorials were fetched");
        return tutorialResponses;
    }

    @Override
    public List<TutorialResponse> getByPublished(boolean published) {
        log.info("Searching all tutorials 'published={}'", published);
        List<TutorialResponse> tutorialResponses = tutorialRepository.findAll().stream().
                filter(t -> t.getPublished() == published).map(this::convertToTutorialResponse).toList();
        if (tutorialResponses.isEmpty()) {
            log.error("Not found published 'status = {}' tutorials", published);
            throw new NotFoundException("Not found published 'status=" + published + "' tutorials");
        } else log.info("Published 'status={}' tutorials", published);
        return tutorialResponses;
    }

    @Override
    public List<TutorialResponse> getByTitleContaining(String title) {
        log.info("Searching tutorial title is '{}'", title);
        List<TutorialResponse> tutorialResponses = tutorialRepository.findAll().stream().
                filter(t -> t.getTitle().equals(title)).map(this::convertToTutorialResponse).collect(Collectors.toList());
        if (tutorialResponses.isEmpty()) {
            log.error("There is no '{}' titled tutorials!", title);
            throw new NotFoundException("Not found published 'status " + title + "' tutorials");
        } else log.info("Getting tutorial title is '{}'", title);
        return tutorialResponses;
    }

    @Override
    public long removeAll() {
        log.warn("Deleting all tutorials");
        long count = tutorialRepository.count();
        tutorialRepository.deleteAll();
        log.warn("All tutorials were deleted!");
        return count;
    }

    private TutorialResponse convertToTutorialResponse(Tutorial tutorial) {
        return TutorialResponse.builder()
                .title(tutorial.getTitle())
                .name(tutorial.getName())
                .subject(tutorial.getSubject())
                .published(tutorial.getPublished()).build();
    }

    private Tutorial convertToTutorial(TutorialRequest request) {
        return Tutorial.builder()
                .title(request.getTitle())
                .name(request.getName())
                .subject(request.getSubject())
                .published(request.getPublished()).build();
    }

}
