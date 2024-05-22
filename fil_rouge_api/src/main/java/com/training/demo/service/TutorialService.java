package com.training.demo.service;

import com.training.demo.api.TutorialDto;
import com.training.demo.common.dto.CreateTutorial;
import com.training.demo.common.exception.TutorialNotFoundException;
import com.training.demo.common.mappeer.TutorialMapper;
import com.training.demo.repository.TutorialRepository;
import com.training.demo.repository.entity.Tutorial;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TutorialService {

    private TutorialMapper tutorialMapper;
    private final TutorialRepository tutorialRepository;

    public List<TutorialDto> fetchTutorials() {
        List<Tutorial> tutorials = (List<Tutorial>) tutorialRepository.findAll();
        return tutorialMapper.fromEntity(tutorials);
    }

    public Optional<Tutorial> fetchTutorial(Long id) {
        return tutorialRepository.findById(id);
    }

    // ACID : Atomicité -> Transaction
    @Transactional
    public void deleteTutorial(Long idTuto) {
        if (tutorialRepository.existsById(idTuto)) {
            tutorialRepository.deleteById(idTuto);
        } else {
            throw new TutorialNotFoundException(idTuto);
        }
    }

    public TutorialDto addTutorial(CreateTutorial dto) {
        Tutorial newTutorial = tutorialMapper.fromDto(dto);
        Tutorial created = tutorialRepository.save(newTutorial);
        return tutorialMapper.fromEntity(created);
    }
}
