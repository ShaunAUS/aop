package com.example.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Slf4j
@Aspect
public class AspectV6 {
    @Around("com.example.aop.order.aop.Pointcuts.allOrder()") // AOP 대상 설정
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature()); // join point 시그니처
        return joinPoint.proceed();
    }

    //example.aop.order 패키지 아래에 있으면서 + 클래스 이름 패턴이 *Service 포인트컷 설정
/*
    @Around("com.example.aop.order.aop.Pointcuts.orderServiceAndService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {

        try {
            //@Before
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();

            //@AfterReturning
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            //@AfterThrowing
            log.info("[트랜잭션 콜백] {}", joinPoint.getSignature());
            throw e;
        } finally {

            //@After
            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
        }
    }
*/


    //ProceedingJoinPoint는 @Aorund에서만 사용
    //jointPoint 실행부분은 생략 가능
    //Object result = joinPoint.proceed(); 시작전 부분
    @Before("com.example.aop.order.aop.Pointcuts.orderServiceAndService()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("[Before] {}", joinPoint.getSignature());
    }


    //returning = "result" 와 Object result 값이 매칭된다.
    @AfterReturning(value = "com.example.aop.order.aop.Pointcuts.orderServiceAndService()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("[return] {} return ={}", joinPoint.getSignature(), result);
    }


    @AfterThrowing(value = "com.example.aop.order.aop.Pointcuts.orderServiceAndService()", throwing = "exception")
    public void doAfterThrowing(JoinPoint joinPoint, Exception exception) {
        log.info("[Exception] {} message ={}", joinPoint.getSignature(), exception.getMessage());
    }

    @After("com.example.aop.order.aop.Pointcuts.orderServiceAndService()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("[After] {}", joinPoint.getSignature());
    }

}
