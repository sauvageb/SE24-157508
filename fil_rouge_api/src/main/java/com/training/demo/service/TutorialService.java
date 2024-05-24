package com.training.demo.service;

import com.training.demo.common.dto.CreateTutorial;
import com.training.demo.common.dto.TutorialDto;
import com.training.demo.common.exception.TutorialNotFoundException;
import com.training.demo.common.mapper.TutorialMapper;
import com.training.demo.repository.TutorialRepository;
import com.training.demo.repository.UserRepository;
import com.training.demo.repository.entity.Tutorial;
import com.training.demo.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TutorialService {

    private final TutorialMapper tutorialMapper;
    private final TutorialRepository tutorialRepository;
    private final UserRepository userRepository;

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

    @Transactional
    public TutorialDto addTutorial(CreateTutorial dto) {
        Tutorial newTutorial = tutorialMapper.fromDto(dto);

        User user = userRepository.findById(dto.getAuthorId()).orElseThrow(() -> new RuntimeException("User not found"));
        newTutorial.setAuthor(user);

        Tutorial created = tutorialRepository.save(newTutorial);
        return tutorialMapper.fromEntity(created);
    }
}
