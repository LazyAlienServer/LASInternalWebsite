package com.las.lasbackendvote6000;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 投票服务
 * @author martin
 */
@SpringBootApplication
@EnableScheduling
public class LasBackendVote6000Application {

    public static void main(String[] args) {
        SpringApplication.run(LasBackendVote6000Application.class, args);
    }

}
