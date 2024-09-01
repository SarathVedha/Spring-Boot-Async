package com.vedha.controller;

import com.vedha.service.AsyncService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/async")
@RequiredArgsConstructor
@Tag(name = "Async Controller", description = "Async Controller")
public class AsyncController {

    private final AsyncService asyncService;

    @Operation(summary = "Demo Method", description = "Demo Method Call")
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
    @PostMapping(value = "/demo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> demo(@RequestParam(value = "name", defaultValue = "Vedha") String name) {

        return ResponseEntity.ok(asyncService.demo(name));
    }

    @Operation(summary = "Normal Method", description = "Normal Method Call")
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
    @PostMapping(value = "/normal", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> normal(@RequestParam(value = "name", defaultValue = "Vedha") String name) {

        return ResponseEntity.ok(asyncService.normal(name));
    }

    @Operation(summary = "Multi-Threading Method", description = "Multi-Threading Method Call")
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
    @PostMapping(value = "/multi-threading", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> multiThreading(@RequestParam(value = "name", defaultValue = "Vedha") String name) {

        return ResponseEntity.ok(asyncService.multiThreading(name));
    }

    @Operation(summary = "Async Method", description = "Async Method Call")
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
    @PostMapping(value = "/async", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> async(@RequestParam(value = "name", defaultValue = "Vedha") String name) {

        return ResponseEntity.ok(asyncService.async(name));
    }

    @Operation(summary = "Async Method Return", description = "Async Method Return Call")
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
    @PostMapping(value = "/async-future", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> completableFuture(@RequestParam(value = "name", defaultValue = "Vedha") String name) {

        return ResponseEntity.ok(asyncService.asyncWithReturn(name));
    }
}
