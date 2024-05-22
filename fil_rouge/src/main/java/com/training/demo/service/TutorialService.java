package com.training.demo.service;

import com.training.demo.entity.Tutorial;
import com.training.demo.repository.TutorialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TutorialService {

    private final TutorialRepository tutorialRepository;

    public List<Tutorial> fetchTutorials() {
        return (List<Tutorial>) tutorialRepository.findAll();
    }

}
