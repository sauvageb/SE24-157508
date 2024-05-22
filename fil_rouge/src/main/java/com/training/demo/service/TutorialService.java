package com.training.demo.service;

import com.training.demo.common.dto.CreateTutorial;
import com.training.demo.entity.Tutorial;
import com.training.demo.common.exception.TutorialNotFoundException;
import com.training.demo.repository.TutorialRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    // ACID : Atomicité -> Transaction
    @Transactional
    public void deleteTutorial(Long idTuto) {
        if(tutorialRepository.existsById(idTuto)){
            tutorialRepository.deleteById(idTuto);
        }else {
            throw new TutorialNotFoundException(idTuto);
        }
    }

    public void addTutorial(CreateTutorial dto) {
        // conversion dto to entity
        Tutorial newTutorial = Tutorial.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .content(dto.getContent())
                .createAt(LocalDateTime.now())
                .build();

        tutorialRepository.save(newTutorial);
    }
}
