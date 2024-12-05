package com.example.shop.batch.config;

import com.example.shop.batch.DeliveryOrderReader;
import com.example.shop.batch.OrderUpdateSenderWriter;
import com.example.shop.admin.dto.OrderDeliveryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class EmailNotificationJobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final DeliveryOrderReader deliveryOrderReader;
    private final OrderUpdateSenderWriter orderUpdateSenderWriter;

    @Bean
    public Job deliveryNotificationJob(Step sendEmailStep) {
        System.out.println("deliveryNotificationJob 실행됨");
        return new JobBuilder("deliveryNotificationJob", jobRepository)
                .start(sendEmailStep)
                .build();
    }

    @Bean
    public Step sendEmailStep() {
        return new StepBuilder("sendEmailStep", jobRepository)
                .<OrderDeliveryRequest, OrderDeliveryRequest>chunk(100, transactionManager)
                .reader(deliveryOrderReader)
                .writer(orderUpdateSenderWriter)
                .build();
    }
}
