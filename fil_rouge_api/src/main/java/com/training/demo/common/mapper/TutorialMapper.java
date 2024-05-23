package com.training.demo.common.mapper;

import com.training.demo.common.dto.CreateTutorial;
import com.training.demo.common.dto.TutorialDto;
import com.training.demo.common.dto.UserDto;
import com.training.demo.repository.entity.Tutorial;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class TutorialMapper {


    public Tutorial fromDto(CreateTutorial dto) {
        return Tutorial.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .content(dto.getContent())
                .createAt(LocalDateTime.now())
                .build();
    }

    public TutorialDto fromEntity(Tutorial created) {

        UserDto userDto = UserDto
                .builder()
                .id(created.getAuthor().getId())
                .email(created.getAuthor().getEmail())
                .firstName(created.getAuthor().getFirstName())
                .lastName(created.getAuthor().getLastName())
                .build();

        return TutorialDto
                .builder()
                .id(created.getId())
                .title(created.getTitle())
                .description(created.getDescription())
                .content(created.getContent())
                .createAt(created.getCreateAt())
                .author(userDto)
                .build();
    }

    public List<TutorialDto> fromEntity(List<Tutorial> tutorials) {
        return tutorials
                .stream()
                .map(this::fromEntity)
                .toList();
    }
}
