package org.tutorial.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.tutorial.app.dto.request.TutorialRequest;
import org.tutorial.app.dto.response.TutorialResponse;
import org.tutorial.app.entity.Tutorial;

@Mapper(componentModel = "spring")
public interface TutorialMapper {

    Tutorial dtoToTutorial(TutorialRequest tutorialRequest);

    TutorialResponse tutorialToDto(Tutorial tutorial);

}
