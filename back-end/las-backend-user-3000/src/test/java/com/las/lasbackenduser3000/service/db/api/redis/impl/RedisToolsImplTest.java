package com.las.lasbackenduser3000.service.db.api.redis.impl;

import com.las.lasbackenduser3000.model.User;
import com.las.lasbackenduser3000.service.db.redis.impl.RedisToolsImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisToolsImplTest {

    @Autowired
    public RedisToolsImpl redisTools;

    @Test
    void insert() {
        User user = new User(0,100,"Martin","Martin","苦逼开发","developer","1222","1",1,"0",1,-100);
        redisTools.insert("111.111.111.111",user);

    }

    @Test
    void getByKey(){
        User byKey = (User) redisTools.getByKey("111.111.111.111");
        System.out.println(byKey);
    }
}