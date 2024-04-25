package com.las.lasbackenduser3000;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 用户模块
 * @author martin
 */
@SpringBootApplication
public class LasBackendUser3000Application {

    public static void main(String[] args) {
        SpringApplication.run(LasBackendUser3000Application.class, args);
    }

}
