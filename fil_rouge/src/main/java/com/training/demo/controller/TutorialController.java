package com.training.demo.controller;


import com.training.demo.entity.Tutorial;
import com.training.demo.service.TutorialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;


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

    @GetMapping("/details/{idTutorial}")
    public String displayTutorialDetails(@PathVariable("idTutorial") Long id, Model model) {
        Optional<Tutorial> tutorialOpt = tutorialService.fetchTutorial(id);
        if (tutorialOpt.isPresent()) {
            Tutorial tuto = tutorialOpt.get();
            model.addAttribute("tutorial", tuto);
            return "tutorial-details";
        } else {
            return "redirect:/tutorials/list";
        }
    }

}
