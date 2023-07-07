package org.tutorial.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tutorial.app.client.CostClient;
import org.tutorial.app.dto.internal.TutorialCost;
import org.tutorial.app.dto.request.TutorialRequest;
import org.tutorial.app.dto.response.TutorialResponse;
import org.tutorial.app.exceptions.NotFoundException;
import org.tutorial.app.entity.Tutorial;
import org.tutorial.app.mapper.TutorialMapper;
import org.tutorial.app.repository.TutorialRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TutorialServiceImpl implements TutorialService {

    private final TutorialRepository tutorialRepository;
    private final TutorialMapper mapper;
    private final CostClient costClient;

    @Override
    public String create(TutorialRequest request) {
        log.info("Started to create a new tutorial.");
        Tutorial book = tutorialRepository.save(mapper.dtoToTutorial(request));
        costClient.postData(TutorialCost.builder().cost(request.getCost()).nameTutorial(request.getName()).id(request.getCostId()).build());
        log.info("Created a new tutorial 'id={}' database.", book);
        System.out.println(book);
        return "Tutorial created successfully";
    }

    @Override
    public String update(TutorialRequest tutorialRequest, Long id) {
        getById(id);
        log.info("Start to update tutorial by 'id {}' ", id);
        tutorialRepository.findById(id).orElseThrow(
                () -> {
                    log.warn("Tutorial is not found with id={} ", id);
                    return new NotFoundException("No such tutorial with id=" + id);
                });
        Tutorial tutorial = mapper.dtoToTutorial(tutorialRequest);
        tutorial.setId(id);
        tutorialRepository.save(tutorial);

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
        TutorialResponse tutorialResponse = mapper.tutorialToDto(tutorial);
        tutorialResponse.setCost(costClient.getData(tutorialResponse.getName()).getCost());
        tutorialResponse.setCostId(costClient.getData(tutorialResponse.getName()).getId());
        log.info("Tutorial is found with id={} ", id);
        return tutorialResponse;
    }

    @Override
    public String removeById(Long id) {
        log.info("Starting to search the tutorial in DB");
        tutorialRepository.findById(id).orElseThrow(
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
                .map(tutorial -> mapper.tutorialToDto(tutorial)).collect(Collectors.toList());

        tutorialResponses.forEach(t -> t.setCost(costClient.getData(t.getName()).getCost()));
        log.info("All tutorials were fetched");
        return tutorialResponses;
    }

    @Override
    public List<TutorialResponse> getByPublished(boolean published) {
        log.info("Searching all tutorials 'published={}'", published);
        List<Tutorial> tutorials = tutorialRepository.findAllByPublished(published);
        if (tutorials.isEmpty()) {
            log.error("Not found published 'status = {}' tutorials", published);
            throw new NotFoundException("Not found published 'status=" + published + "' tutorials");
        } else log.info("Published 'status={}' tutorials", published);
        List<TutorialResponse> responses = new ArrayList<>();
        for (Tutorial tutorial : tutorials) {
            responses.add(mapper.tutorialToDto(tutorial));
        }
        return responses;
    }

    @Override
    public List<TutorialResponse> getByTitleContaining(String title) {
        log.info("Searching tutorial title is '{}'", title);
        List<Tutorial> tutorials = tutorialRepository.findByTitle(title);
        List<TutorialResponse> responses = new ArrayList<>();
        if (tutorials.isEmpty()) {
            log.error("There is no '{}' titled tutorials!", title);
            throw new NotFoundException("Not found published 'status " + title + "' tutorials");
        } else log.info("Getting tutorial title is '{}'", title);

        for (Tutorial tutorial : tutorials) {
            responses.add(mapper.tutorialToDto(tutorial));
        }
        return responses;
    }

    @Override
    public long removeAll() {
        log.warn("Deleting all tutorials");
        long count = tutorialRepository.count();
        tutorialRepository.deleteAll();
        log.warn("All tutorials were deleted!");
        return count;
    }
}
