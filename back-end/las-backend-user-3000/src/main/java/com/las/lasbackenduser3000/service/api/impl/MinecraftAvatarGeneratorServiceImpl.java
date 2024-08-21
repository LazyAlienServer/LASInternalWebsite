package com.las.lasbackenduser3000.service.api.impl;

import com.las.lasbackenduser3000.model.FileUpload;
import com.las.lasbackenduser3000.service.api.MinecraftAvatarGeneratorService;
import com.las.lasbackenduser3000.task.MinecraftAvatarGenerator;
import com.las.lasbackenduser3000.utils.python.Run;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import result.Result;
import result.ResultEnum;
import result.ResultUtil;

import java.io.IOException;
import java.util.List;

/**
 * @author sunyinuo
 */
@Service
@Slf4j
public class MinecraftAvatarGeneratorServiceImpl implements MinecraftAvatarGeneratorService {
    public final MinecraftAvatarGenerator minecraftAvatarGenerator;
    private final MongoTemplate mongoTemplate;
    public MinecraftAvatarGeneratorServiceImpl(MinecraftAvatarGenerator minecraftAvatarGenerator, MongoTemplate mongoTemplate) {
        this.minecraftAvatarGenerator = minecraftAvatarGenerator;
        this.mongoTemplate = mongoTemplate;
    }


    /**
     * 根据minecraft id获取头像地址
     * @param userName userName
     * @return Result
     */
    @Override
    public Result getMinecraftAvatar(String userName) throws IOException {
        log.info("手动触发autoGetMinecraftAvatar() by "+userName);
        minecraftAvatarGenerator.autoGetMinecraftAvatar();

        Query query = Query.query(Criteria.where("token").is(userName));
        List<FileUpload> avatar = mongoTemplate.find(query, FileUpload.class);

        for (FileUpload fileUpload : avatar) {
            String URL = "http://localhost:9000/usersignin/api/getFile/"+fileUpload.getId();
            return ResultUtil.result(ResultEnum.SUCCESS.getCode(), URL,null);
        }

        return null;
    }
}
