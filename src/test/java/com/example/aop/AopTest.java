package com.example.aop;

import org.apache.logging.log4j.message.AsynchronouslyFormattable;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.example.aop.order.OrderRepository;
import com.example.aop.order.OrderService;
import com.example.aop.order.aop.AspectV1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@Import(AspectV1.class)
public class AopTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;


    @Test
    void aopInfoTest() {
        log.info("isAopProxy , orderService={}", AopUtils.isAopProxy(orderService));
        log.info("isAopProxy , orderRepository={}", AopUtils.isAopProxy(orderRepository));
    }

    @Test
    void success() {
        orderService.orderItem("itemA");
    }

    @Test
    void exception() {
        Assertions.assertThatThrownBy(() -> orderService.orderItem("ex"))
            .isInstanceOf(IllegalArgumentException.class);
    }





}