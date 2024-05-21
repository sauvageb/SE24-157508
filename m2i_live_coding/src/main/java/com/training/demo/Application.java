package com.training.demo;

import com.training.demo.repository.User;
import com.training.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner myCommandLineRunner(UserRepository userRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                System.out.println("COMMAND LINE RUNNER EXECUTED !!!");

                var u1 = new User("John", "Doe", "johndoe", LocalDate.now());
                var u2 = new User("Jane", "Dae", "janedoe", LocalDate.now());
                userRepository.saveAll(List.of(u1, u2));
            }
        };
    }
}
