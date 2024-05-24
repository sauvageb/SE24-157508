package com.training.demo.batch;

import com.training.demo.common.dto.RegisterRequest;
import com.training.demo.service.AuthenticationService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserBatchWriter implements ItemWriter<RegisterRequest> {

    private final AuthenticationService authenticationService;

    @Override
    public void write(Chunk<? extends RegisterRequest> chunk) throws Exception {
        for (RegisterRequest dto : chunk.getItems()) {
            authenticationService.register(dto);
        }
    }
}
