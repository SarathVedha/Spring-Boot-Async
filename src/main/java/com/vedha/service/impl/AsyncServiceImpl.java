package com.vedha.service.impl;

import com.vedha.service.AsyncService;
import com.vedha.service.async.AsyncMethods;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class AsyncServiceImpl implements AsyncService {

    private final AsyncMethods asyncMethods;

    private final ExecutorService executor;

    @Override
    public Map<String, String> normal(String name) {

        try {

            TimeUnit.SECONDS.sleep(3);
            log.info("Hello Normal, {}!", name);
        } catch (Exception e) {

            log.error("Error occurred while sleeping: ", e);
            return Map.of("message", "Error occurred while sleeping!");
        }

        return Map.of("message", "Hello, " + name + "!");
    }

    @Override
    public Map<String, String> multiThreading(String name) {

        executor.execute(() -> {

            try {

                TimeUnit.SECONDS.sleep(3);
                log.info("Hello Multi, {}!", name);
            } catch (Exception e) {

                log.error("Error occurred while sleeping: ", e);
            }
        });

        return Map.of("message", "Multi-threading method called!");
    }

    @Override
    public Map<String, String> async(String name) {

        // This method will be executed asynchronously, and async method needs to be in other class
        asyncMethods.asyncMethod(name);

        return Map.of("message", "Async method called!");
    }

    @Override
    public Map<String, String> asyncWithReturn(String name) {

        try {

            Future<String> stringFuture = asyncMethods.asyncMethodWithReturn(name);
            log.info("Async method with return called!");
            return Map.of("message", stringFuture.get());
        } catch (Exception e) {

            log.error("Error occurred while getting the result: ", e);
            return Map.of("message", "Error occurred while getting the result!");
        }
    }
}
