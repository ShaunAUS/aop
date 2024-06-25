package com.example.aop.exam;

import com.example.exam.ExamService;
import com.example.exam.aop.RetryAspect;
import com.example.exam.aop.TraceAspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

//@Import(TraceAspect.class)
@Import({TraceAspect.class, RetryAspect.class})
@SpringBootTest
public class ExamTest {

    @Autowired
    ExamService examService;

    @Test
    void test() {
        for (int i = 0; i < 5; i++) {
            examService.request("data" + i);
        }
    }


}
