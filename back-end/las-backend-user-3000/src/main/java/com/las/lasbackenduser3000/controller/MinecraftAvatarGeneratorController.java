package com.las.lasbackenduser3000.controller;

import com.las.lasbackenduser3000.service.api.impl.MinecraftAvatarGeneratorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.Result;

import java.io.IOException;

/**
 * @author sunyinuo
 */
@RestController
@RequestMapping("/usersignin/minecraft")
public class MinecraftAvatarGeneratorController {


    public final MinecraftAvatarGeneratorServiceImpl minecraftAvatarGeneratorService;

    public MinecraftAvatarGeneratorController(MinecraftAvatarGeneratorServiceImpl minecraftAvatarGeneratorService) {
        this.minecraftAvatarGeneratorService = minecraftAvatarGeneratorService;
    }

    @GetMapping("/getMinecraftAvatar")
    public Result getMinecraftAvatar(String userName) throws IOException {
        return minecraftAvatarGeneratorService.getMinecraftAvatar(userName);
    }
}
