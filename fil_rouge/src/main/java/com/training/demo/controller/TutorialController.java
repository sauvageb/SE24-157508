package com.training.demo.controller;


import com.training.demo.entity.Tutorial;
import com.training.demo.service.TutorialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/tutorials")
@RequiredArgsConstructor
public class TutorialController {

    private final TutorialService tutorialService;

    @GetMapping("/list")
    public ModelAndView displayTutorials() {
        List<Tutorial> tutorialList = tutorialService.fetchTutorials();
        return new ModelAndView("tutorial-list", "tutorials", tutorialList);
    }


}
