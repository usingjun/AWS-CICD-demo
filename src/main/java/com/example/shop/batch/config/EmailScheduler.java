package com.example.shop.batch.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class EmailScheduler {

    private final JobLauncher jobLauncher;
    private final Job deliveryNotificationJob;

    // 매일 14시 마다 동작
    @Scheduled(cron = "0 0 14 * * ?")
    public void runBatchJob() throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addLong("timestamp", System.currentTimeMillis())  // 고유한 값 추가
                .toJobParameters();
        jobLauncher.run(deliveryNotificationJob, params);
    }
}
