package com.vedha.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class SchedulerService {

    // if the scheduler runs with 1 thread and if the task takes more time to complete,
    // then the next task will be delayed until the current task is completed.
    // then increase the number of threads in the scheduler pool.
    @Scheduled(initialDelay = 1000, fixedRate = 3000)
    public void scheduleTask() {

        try {

            TimeUnit.SECONDS.sleep(10);
            log.info("scheduleTask called!");
        } catch (Exception e) {

            log.error("Error occurred while sleeping: ", e);
        }
    }

    @Scheduled(initialDelay = 1000, fixedRate = 3000)
    public void scheduleTask2() {

        log.info("scheduleTask2 called!");
    }
}
