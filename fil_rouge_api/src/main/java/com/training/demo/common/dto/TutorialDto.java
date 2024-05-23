package com.training.demo.common.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TutorialDto {
    private Long id;
    private String title;
    private String description;
    private String content;
    private LocalDateTime createAt;
    private UserDto author;
}
