package com.vedha;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Spring Boot Async API", version = "1.0", description = "Spring Boot Async API"))
public class SpringBootAsyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAsyncApplication.class, args);
    }

}
