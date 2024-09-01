package com.vedha.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AppProxy {

    @Around("execution(* com.vedha.controller.*.*(..)) || execution(* com.vedha.service.*.*(..))")
    public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {

        log.debug("Method: {} called with arguments: {}", joinPoint.getSignature().toShortString(), joinPoint.getArgs());

        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();

        log.debug("Time taken by {} is {} s", joinPoint.getSignature().toShortString(), (endTime - startTime) / 1000);
        log.debug("Method: {} returned: {}", joinPoint.getSignature().toShortString(), result);

        return result;
    }
}
