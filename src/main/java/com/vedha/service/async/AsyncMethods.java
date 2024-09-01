package com.vedha.service.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class AsyncMethods {

    @Async
    public void asyncMethod(String name) {

        try {

            TimeUnit.SECONDS.sleep(5);
            log.info("Hello Async, {}!", name);
        } catch (Exception e) {

            log.error("Error occurred while sleeping: ", e);
        }
    }

    @Async
    public Future<String> asyncMethodWithReturn(String name) {

        try {

            TimeUnit.SECONDS.sleep(5);
            log.info("Hello Async Return, {}!", name);
        } catch (Exception e) {

            log.error("Error occurred while sleeping: ", e);
        }

        return CompletableFuture.completedFuture("Hello, " + name + "!");
    }
}
