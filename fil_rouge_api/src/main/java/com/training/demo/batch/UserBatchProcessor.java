package com.training.demo.batch;

import com.training.demo.batch.dto.ImportUserDto;
import com.training.demo.common.dto.RegisterRequest;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserBatchProcessor implements ItemProcessor<ImportUserDto, RegisterRequest> {

    @Override
    public RegisterRequest process(ImportUserDto item) throws Exception {

        // Traitements...
        return RegisterRequest
                .builder()
                .firstName(item.getFirstname())
                .lastName(item.getLastname())
                .email(item.getMail())
                .password(item.getPass())
                .build();
    }
}
