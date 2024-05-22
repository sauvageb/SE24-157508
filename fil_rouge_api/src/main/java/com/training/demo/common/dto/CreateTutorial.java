package com.training.demo.common.dto;

import lombok.Data;

@Data
public class CreateTutorial {
    private String title;
    private String description;
    private String content;
}
