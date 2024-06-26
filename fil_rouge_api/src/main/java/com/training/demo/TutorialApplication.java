package com.training.demo;

import com.training.demo.repository.RoleRepository;
import com.training.demo.repository.UserRepository;
import com.training.demo.repository.entity.Role;
import com.training.demo.repository.entity.RoleEnum;
import com.training.demo.repository.entity.Tutorial;
import com.training.demo.repository.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class TutorialApplication {

    public static void main(String[] args) {
        SpringApplication.run(TutorialApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(PasswordEncoder passwordEncoder, RoleRepository roleRepository, UserRepository userRepository) {
        return args -> {

            Role roleAdmin = Role.builder().roleName(RoleEnum.ROLE_ADMIN).build();
            Role roleUser = Role.builder().roleName(RoleEnum.ROLE_USER).build();
            roleRepository.saveAll(List.of(roleAdmin, roleUser));

            User author = User
                    .builder()
                    .email("sauvageboris.pro@gmail.com")
                    .firstName("Boris")
                    .lastName("Sauvage")
                    .password(passwordEncoder.encode("qwerty"))
                    .roleList(List.of(roleAdmin, roleUser))
                    .build();

            Tutorial tutorial1 = Tutorial
                    .builder()
                    .title("Tutorial 1")
                    .content("Contenu 1")
                    .description("Description 2")
                    .createAt(LocalDateTime.now())
                    .author(author)
                    .build();

            Tutorial tutorial2 = Tutorial
                    .builder()
                    .title("Tutorial 2")
                    .content("Contenu 2")
                    .description("Description 2")
                    .createAt(LocalDateTime.now().minusYears(1))
                    .author(author)
                    .build();

            author.setTutorialList(List.of(tutorial1, tutorial2));

            userRepository.save(author);
        };
    }

}
