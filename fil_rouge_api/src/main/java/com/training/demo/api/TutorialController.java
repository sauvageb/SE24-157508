package com.training.demo.api;

import com.training.demo.common.dto.CreateTutorial;
import com.training.demo.repository.entity.Tutorial;
import com.training.demo.service.TutorialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tutorials")
@RequiredArgsConstructor
public class TutorialController {

    private final TutorialService tutorialService;

    // GET    -> Récupérer une ressource
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TutorialDto>> fetchAll() {

        List<TutorialDto> tutorials = tutorialService.fetchTutorials();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tutorials);
    }

    // POST   -> Envoyer une ressource
    @PostMapping
    public ResponseEntity<TutorialDto> createTutorial(@RequestBody CreateTutorial dto) {

        TutorialDto createTutorial = tutorialService.addTutorial(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createTutorial);
    }

    // PUT    ->  Mise à jour/Remplacement de la ressource
    // PATCH  -> Mise à jour partielle
    // DELETE -> Suppression


}
