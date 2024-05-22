package com.training.demo;

import com.training.demo.repository.TutorialRepository;
import com.training.demo.repository.entity.Tutorial;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class TutorialApplication {

    public static void main(String[] args) {
        SpringApplication.run(TutorialApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(TutorialRepository tutorialRepository) {
        return args -> {
            Tutorial tutorial1 = Tutorial
                    .builder()
                    .title("Tutorial 1")
                    .content("Contenu 1")
                    .description("Description 2")
                    .createAt(LocalDateTime.now())
                    .build();

            Tutorial tutorial2 = Tutorial
                    .builder()
                    .title("Tutorial 2")
                    .content("Contenu 2")
                    .description("Description 2")
                    .createAt(LocalDateTime.now().minusYears(1))
                    .build();

            tutorialRepository.save(tutorial1);
            tutorialRepository.save(tutorial2);
        };
    }

}
