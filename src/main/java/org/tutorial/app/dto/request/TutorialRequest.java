package org.tutorial.app.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TutorialRequest {
    private Long id;
    @NotEmpty
    @ApiModelProperty(notes = "Tutorial title", example = "java-ee", required = true)
    private String title;
    @NotEmpty
    @ApiModelProperty(notes = "Tutorial name", example = "Thinking Java", required = true)
    private String name;
    @NotEmpty
    @ApiModelProperty(notes = "Tutorial subject", example = "Java", required = true)
    private String subject;
    @AssertTrue
    @ApiModelProperty(notes = "Is published?", example = "true", required = true)
    private Boolean published;
    private Long costId;
    private double cost;
}
