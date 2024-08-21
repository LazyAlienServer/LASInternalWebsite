package com.las.lasbackenduser3000.service.api.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MinecraftAvatarGeneratorServiceImplTest {
    @Autowired
    public MinecraftAvatarGeneratorServiceImpl minecraftAvatarGeneratorService;

    @Test
    void getMinecraftAvatar() throws IOException {
        System.out.println(minecraftAvatarGeneratorService.getMinecraftAvatar("Fanzhitianyu"));
    }
}