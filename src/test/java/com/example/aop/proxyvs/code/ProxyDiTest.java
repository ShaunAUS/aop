package com.example.aop.proxyvs.code;

import com.example.member.MemberService;
import com.example.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest(properties = "spring.aop.proxy-target-class=false") // 스프링 부트는 기본적으로 CGLIB라 이 설정을 해줘 JDK 동적프록시로 설정된다.
@Import(ProxyDiAspect.class)
public class ProxyDiTest {

    @Autowired
    MemberServiceImpl memberServiceImpl;

    @Autowired
    MemberService memberService;

    @Test
    void go() {
        log.info("memberServiceImpl class = {}", memberServiceImpl.getClass());
        log.info("memberService class = {}", memberService.getClass());
        memberServiceImpl.hello("hello");

    }
}
