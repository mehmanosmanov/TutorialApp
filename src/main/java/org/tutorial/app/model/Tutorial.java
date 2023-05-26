package org.tutorial.app.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tutorial {
    private Integer id;
    private String title;
    private String name;
    private String subject;
    private Boolean published;

}