package com.example.aop.order.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import lombok.extern.slf4j.Slf4j;

// 설정후 꼭 SpringBean 으로 등록 해줘야함!!!!!!!!!!!!!!!!!
@Slf4j
@Aspect
public class AspectV1 {

    @Around("execution(* com.example.aop.order..*.*(..))") // AOP 대상 설정
    public Object doLog(ProceedingJoinPoint joinPoint) throws  Throwable{
        log.info("[log] {}", joinPoint.getSignature()); // join point 시그니처
        return joinPoint.proceed();
    }
}
