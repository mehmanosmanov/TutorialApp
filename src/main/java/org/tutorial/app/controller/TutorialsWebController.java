package org.tutorial.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tutorial.app.dto.request.TutorialRequest;
import org.tutorial.app.dto.response.TutorialResponse;
import org.tutorial.app.entity.Tutorial;
import org.tutorial.app.mapper.TutorialMapper;
import org.tutorial.app.service.TutorialService;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Mehman on 01-07-2023
 * @project tutorialApp
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/web")
public class TutorialsWebController {

    private final TutorialService tutorialService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewHomePage(Model model) {
        List<TutorialResponse> dtos = tutorialService.getAll();
        model.addAttribute("tutorials", dtos);
        return "index";
    }

    @GetMapping("/addnew")
    public String addNewTutorial(Model model) {
        TutorialRequest tutorial = new TutorialRequest();
        model.addAttribute("tutorial", tutorial);
        return "newtutorial";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("tutorial") TutorialRequest tutorial) {
        tutorialService.create(tutorial);
        return "redirect:/web/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model) {
        TutorialResponse tutorialResponse = tutorialService.getById(id);
        model.addAttribute("tutorial", tutorialResponse);
        return "update";
    }

    @GetMapping("/deleteStudent/{id}")
    public String deleteThroughId(@PathVariable(value = "id") long id) {
        tutorialService.removeById(id);
        return "redirect:/web/";
    }
}
