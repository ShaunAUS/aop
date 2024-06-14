package com.example.aop.order;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class OrderRepository {

    public String save(String itemId) {
        log.info("OrderRepository 실행");

        //저장 로직
        if(itemId.equals("ex")){
            throw new IllegalArgumentException("예외 발생");
        }

        return "ok";

    }
}
