package com.training.demo.batch;

import com.training.demo.batch.dto.ImportUserDto;
import com.training.demo.common.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class UserImportBatch {

    private final UserBatchReader userBatchReader;
    private final UserBatchProcessor userBatchProcessor;
    private final UserBatchWriter userBatchWriter;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    Job userImportJob() {
        return new JobBuilder("userImportJob", jobRepository)
                .start(importUserStep(userBatchReader, userBatchProcessor, userBatchWriter))
                .build();
    }

    @Bean
    Step importUserStep(ItemReader<ImportUserDto> reader,
                        ItemProcessor<ImportUserDto, RegisterRequest> processor,
                        ItemWriter<RegisterRequest> userBatchWriter
    ) {
        return new StepBuilder("importUserStep", jobRepository)
                .<ImportUserDto, RegisterRequest>chunk(2, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(userBatchWriter)
                .build();
    }

}
