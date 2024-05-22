package com.training.demo;

import com.training.demo.entity.Tutorial;
import com.training.demo.repository.TutorialRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TutorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(TutorialApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(TutorialRepository tutorialRepository){
		return args -> {
            Tutorial tutorial1 = Tutorial
                    .builder()
                    .title("Tutorial1")
                    .content("Contenu 1")
                    .description("Description 2")
                    .build();

            Tutorial tutorial2 = Tutorial
                    .builder()
                    .title("Tutorial 2")
                    .content("Contenu 2")
                    .description("Description 2")
                    .build();

            tutorialRepository.save(tutorial1);
            tutorialRepository.save(tutorial2);
        };
	}

}
