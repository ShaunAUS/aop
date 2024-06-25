package com.example.aop.proxyvs;

import com.example.member.MemberService;
import com.example.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

@Slf4j
public class ProxyCastingTest {

    @Test
    void jdkProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(false); //JDK 동적 프록시

        //프록시를 인터페이스로 캐스팅 성공
        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

        //JDK 동적프록시는 인터페이스를 기반으로 하기떄문에 Impl은 뭔지도 모름, 즉 캐스팅 불가
        MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;

    }

    @Test
    void cglibProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true); //CGLIB  프록시

        //프록시를 인터페이스로 캐스팅 성공
        // 구체 -> 인터페이스 , 즉 부모의 부모이기 떄문이 타입 변환 가능
        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

        //구체클래스 기반으로 프록시 생성이라 , 캐스팅 성공
        MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;

    }

}
