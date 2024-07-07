package com.las.lasbackenduser3000.service.api.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.las.lasbackenduser3000.model.FileUpload;
import com.las.lasbackenduser3000.model.User;
import com.las.lasbackenduser3000.service.api.FileUploadService;
import com.las.lasbackenduser3000.service.db.impl.UserServiceImpl;
import com.las.lasbackenduser3000.service.db.redis.impl.RedisToolsImpl;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import result.Result;
import result.ResultEnum;
import result.ResultUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author sunyinuo
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {
    private final UserServiceImpl userService;
    private final RedisToolsImpl redisTools;
    private final MongoTemplate mongoTemplate;


    public FileUploadServiceImpl(UserServiceImpl userService, RedisToolsImpl redisTools, MongoTemplate mongoTemplate) {
        this.userService = userService;
        this.redisTools = redisTools;
        this.mongoTemplate = mongoTemplate;
    }


    /**
     * 文件上传
     * @param files       file
     * @param token token
     * @return 统一返回
     */
    @Override
    public Result fileUpload(MultipartFile[] files, String token) {
        FileUpload fileUpload = new FileUpload();
        for (MultipartFile file : files) {

            //文件名
            fileUpload.setName(file.getOriginalFilename());
            //时间
            fileUpload.setCreatedTime(LocalDateTime.now());
            //内容
            try {
                fileUpload.setContent(new Binary(file.getBytes()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //类型
            fileUpload.setContentType(file.getContentType());
            //大小
            fileUpload.setSize(file.getSize());
            //项目名
            fileUpload.setToken(token);
        }

        //存
        mongoTemplate.save(fileUpload);

        //根据token查redis
        String byKeyString = (String) redisTools.getByKey(token);
        JSONObject userJson = JSONObject.parseObject(byKeyString);
        User userOld = JSON.toJavaObject(userJson,User.class);


        Query query = Query.query(Criteria.where("token").is(token));
        List<FileUpload> avatar = mongoTemplate.find(query, FileUpload.class);

        for (FileUpload upload : avatar) {
            userOld.setAvatar("http://localhost:9000/usersignin/api/getFile/"+upload.getId());
        }

       int result = userService.update(userOld);

        redisTools.insert(token, JSONObject.toJSONString(userOld),20, TimeUnit.DAYS);

       if (result == 1){
           return ResultUtil.result(ResultEnum.SUCCESS.getCode(), result,null);
       }else {
           return ResultUtil.result(ResultEnum.SERVER_ERROR.getCode(),null);
       }
    }
}
