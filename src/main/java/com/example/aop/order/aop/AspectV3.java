package com.example.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

// 설정후 꼭 SpringBean 으로 등록 해줘야함!!!!!!!!!!!!!!!!!
@Slf4j
@Aspect
public class AspectV3 {

    //example.aop.order 패키지
    @Pointcut("execution(* com.example.aop.order..*.*(..))") // AOP 대상 설정
    private void allOrder() {}// pointcut signature

    //클래스 이름 패턴이 *Service
    @Pointcut("execution(* *..*Service.*(..))") // AOP 대상 설정
    private void allService() {}// pointcut signature


    @Around("allOrder()") // AOP 대상 설정
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature()); // join point 시그니처
        return joinPoint.proceed();
    }

    //example.aop.order 패키지 아래에 있으면서 + 클래스 이름 패턴이 *Service 포인트컷 설정
    @Around("allOrder() && allService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {

        try {
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            log.info("[트랜잭션 콜백] {}", joinPoint.getSignature());
            throw e;
        } finally {
            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
        }
    }



}
