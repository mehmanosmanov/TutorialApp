package org.tutorial.app.dto.internal;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
/**
 * @author Mehman on 07-07-2023
 * @project tutorialApp
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TutorialCost {
    private Long id;
    private String nameTutorial;
    @Max(100)
    @Positive
    private double cost;
}