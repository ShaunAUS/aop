package com.example.aop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    //example.aop.order 패키지
    @Pointcut("execution(* com.example.aop.order..*.*(..))") // AOP 대상 설정
    public void allOrder() {}// pointcut signature

    //클래스 이름 패턴이 *Service
    @Pointcut("execution(* *..*Service.*(..))") // AOP 대상 설정
    public void allService() {}// pointcut signature


    @Pointcut("allOrder() && allService()")
    public void orderServiceAndService() {}
}
