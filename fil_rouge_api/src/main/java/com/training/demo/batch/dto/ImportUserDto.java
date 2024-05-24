package com.training.demo.batch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImportUserDto {

    private String firstname;
    private String lastname;
    private String mail;
    private String pass;
}
