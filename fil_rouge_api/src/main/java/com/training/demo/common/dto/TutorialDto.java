package com.training.demo.api;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TutorialDto {
    private Long id;
    private String title;
    private String description;
    private String content;
    private LocalDateTime createAt;
}
