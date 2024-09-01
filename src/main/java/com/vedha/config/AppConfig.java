package com.vedha.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.task.ThreadPoolTaskExecutorBuilder;
import org.springframework.boot.task.ThreadPoolTaskSchedulerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Configuration
@EnableAsync
@EnableScheduling
public class AppConfig {

    @Bean
    ExecutorService initMultiThread() {

        int cores = Runtime.getRuntime().availableProcessors();

        log.info("System Available Cores: {}", cores);

        final AtomicInteger atomicInteger = new AtomicInteger(0);

        return Executors.newFixedThreadPool(cores, r -> {

            Thread t = new Thread(r, "multi-thread-".concat(String.valueOf(atomicInteger.incrementAndGet())));
            t.setPriority(Thread.NORM_PRIORITY);
            return t;
        });
    }

    // More than one TaskExecutor bean found within the context, and none is named 'taskExecutor'.
    // Mark one of them as primary or name it 'taskExecutor' (possibly as an alias) to use it for async processing: [initAsyncThread, initScheduledThread]
    // To resolve this issue, we need to name the beans as 'taskExecutor' and 'taskScheduler' respectively
    // Spring will use this bean for @Async annotation by default taskExecutor
    @Bean("taskExecutor")
    ThreadPoolTaskExecutor initAsyncThread() {

        // This Bean will be used for @Async annotation, this is a spring provided thread pool
        return new ThreadPoolTaskExecutorBuilder()
                .corePoolSize(2)
                .maxPoolSize(2)
                .queueCapacity(3)
                .threadNamePrefix("async-thread-")
                .build();
    }

    // Spring will use this bean for @Scheduled annotation by default taskScheduler
    @Bean("taskScheduler")
    ThreadPoolTaskScheduler initScheduledThread() {

        // This Bean will be used for @Scheduled annotation, this is a spring provided thread pool
        return new ThreadPoolTaskSchedulerBuilder()
                .poolSize(2)
                .threadNamePrefix("scheduled-thread-")
                .build();
    }

}
