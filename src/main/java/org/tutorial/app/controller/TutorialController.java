package org.tutorial.app.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.tutorial.app.dto.request.TutorialRequest;
import org.tutorial.app.dto.response.TutorialResponse;
import org.tutorial.app.service.TutorialService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TutorialController {
    private final TutorialService tutorialService;

    //1 done
    @PostMapping("/tutorials")
    public String create(@RequestBody TutorialRequest tutorialRequest) {
        return tutorialService.create(tutorialRequest);
    }


    //2 done
    @PutMapping("/tutorials/{id}")
    public String update(@PathVariable Long id, @RequestBody TutorialRequest tutorial) {
        return tutorialService.update(tutorial, id);
    }

    //3 done
    @ApiOperation(value = "Get tutorial", notes = "Get tutorial by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Getting tutorial is successful!"),
            @ApiResponse(code = 400, message = "Required id field"),
            @ApiResponse(code = 404, message = "Required tutorial not found")
    })
    @GetMapping("/tutorials/{id}")
    public TutorialResponse getById(@PathVariable("id") @ApiParam(name = "id", value = "Tutorial id", example = "1") Long id) {
        return tutorialService.getById(id);
    }

    //4 done
    @ApiOperation(value = "Remove by id", notes = "Remove tutorial by id")
    @DeleteMapping("/tutorials/{id}")
    public String removeById(@PathVariable @ApiParam(name = "id", value = "Tutorial id", example = "1") Long id) {
        return tutorialService.removeById(id);
    }

    //5 done
    @ApiOperation(value = "Get all", notes = "Get all tutorials")
    @GetMapping("/tutorials")
    public List<TutorialResponse> getAll() {
        return tutorialService.getAll();
    }

    //6 done
    @GetMapping("/tutorials/published/{published}")
    public List<TutorialResponse> getByPublished(@PathVariable @ApiParam(name = "published", value = "true/false", example = "true") boolean published) {
        return tutorialService.getByPublished(published);
    }

    //7  done
    @GetMapping("/tutorials/title")
    public List<TutorialResponse> getByTitleContain(@RequestParam String title) {
        return tutorialService.getByTitleContaining(title);
    }


    //8 done
    @DeleteMapping("/tutorials")
    public long removeAll() {
        return tutorialService.removeAll();
    }

}
