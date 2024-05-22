package com.training.demo.service;

import com.training.demo.entity.Tutorial;
import com.training.demo.repository.TutorialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TutorialService {

    private final TutorialRepository tutorialRepository;

    public List<Tutorial> fetchTutorials() {
        return (List<Tutorial>) tutorialRepository.findAll();
    }

    public Optional<Tutorial> fetchTutorial(Long id) {
        return tutorialRepository.findById(id);
    }
}
