package com.las.lasbackenduser3000.utils.python;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RunTest {


    @Test
    void RUN() {
        System.out.println(Run.RUN("Fanzhitianyu"));
    }
}