package org.tutorial.app.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tutorial.app.dto.TutorialDto;
import org.tutorial.app.model.Tutorial;
import org.tutorial.app.repository.TutorialRepository;
import org.tutorial.app.service.TutorialService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TutorialServiceImpl implements TutorialService {

    private final TutorialRepository tutorialRepository;


    @Override
    public int create(TutorialDto tutorialDto) {
        log.info("Started to create a new tutorial.");
        Tutorial book = convertToTutorial(tutorialDto);
        int num = tutorialRepository.save(book);
        log.info("Created a new tutorial 'id={}' database.", num);
        System.out.println(book);
        return num;
    }

    @Override
    public int update(TutorialDto tutorialDto, Long id) {
        if (tutorialRepository.findById(id) == null) {
            log.error("Tutorial does not found by 'id:{}'", id);
            return 0;
        }
        log.info("Start to update tutorial by 'id {}' ", id);
        Tutorial book = convertToTutorial(tutorialDto);
        int n = tutorialRepository.update(book, id);
        log.info("Tutorial 'id={}' is updated", id);
        return n;
    }

    @Override
    public TutorialDto getById(Long id) {
        log.info("Starting to search the tutorials in DB");
        Tutorial tutorial = tutorialRepository.findById(id);
        TutorialDto tutorialDto = convertToTutorialDto(tutorial);
        if (tutorialDto == null)
            log.warn("Tutorial 'id={}' isn't found", id);
        log.info("Tutorial 'id={}' is found ", id);
        return tutorialDto;
    }

    @Override
    public int removeById(Long id) {
        log.warn("Deleting 'id={}' tutorial", id);
        return tutorialRepository.deleteById(id);
    }

    @Override
    public List<TutorialDto> getAll() {
        log.info("Getting all tutorials...");
        List<TutorialDto> tutorialDtos = new ArrayList<>();
        for (Tutorial tutorial : tutorialRepository.findAll()) {
            tutorialDtos.add(convertToTutorialDto(tutorial));
        }
        return tutorialDtos;
    }

    @Override
    public List<TutorialDto> getByPublished(boolean published) {
        log.info("Searching all tutorials 'published={}'", published);
        List<TutorialDto> tutorialDtos =
                tutorialRepository.findByPublished(published).stream().map(this::convertToTutorialDto).collect(Collectors.toList());
        if (tutorialDtos.isEmpty()) log.error("Not found published 'status = {}' tutorials", published);
        else log.info("There is no published 'status={}' tutorials", published);
        return tutorialDtos;
    }

    @Override
    public List<TutorialDto> getByTitleContaining(String title) {
        log.info("Searching tutorial title is '{}'", title);
        List<Tutorial> tutorials = tutorialRepository.findByTitleContaining(title);
        List<TutorialDto> tutorialDtos = tutorials.stream().map(this::convertToTutorialDto).collect(Collectors.toList());
        if (tutorialDtos.isEmpty())
            log.error("There is no '{}' titled tutorials!", title);
        else log.info("Getting tutorial title is '{}'", title);
        return tutorialDtos;
    }

    @Override
    public int removeAll() {
        log.warn("Deleting all tutorials");
        int num = tutorialRepository.deleteAll();
        if (num > 0) log.warn("All tutorials were deleted!");
        return num;
    }

    private TutorialDto convertToTutorialDto(Tutorial tutorial) {
        return TutorialDto.builder()
                .title(tutorial.getTitle())
                .name(tutorial.getName())
                .subject(tutorial.getSubject())
                .published(tutorial.getPublished()).build();
    }

    private Tutorial convertToTutorial(TutorialDto tutorialDto) {
        return Tutorial.builder()
                .title(tutorialDto.getTitle())
                .name(tutorialDto.getName())
                .subject(tutorialDto.getSubject())
                .published(tutorialDto.getPublished()).build();
    }
}
