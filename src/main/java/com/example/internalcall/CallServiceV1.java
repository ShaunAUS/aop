package com.example.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV1 {

    private CallServiceV1 callServiceV1;


    //그냥 생성자 주입하면  에러
    @Autowired
    public void setCallServiceV1(CallServiceV1 callServiceV1) {
        this.callServiceV1 = callServiceV1;
    }


    public void external() {
        log.info("call external");
        callServiceV1.internal(); // 외부메서드 호출
    }

    public void internal() {
        log.info("call internal");
    }

}
