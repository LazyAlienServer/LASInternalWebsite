package com.las.lasbackenduser3000.service.api;

import result.Result;

import java.io.IOException;

/**
 * @author sunyinuo
 */
public interface MinecraftAvatarGeneratorService {

    /**
     * 根据minecraft id获取头像地址
     * @param userName userName
     * @return Result
     */
    Result getMinecraftAvatar(String userName) throws IOException;

}
