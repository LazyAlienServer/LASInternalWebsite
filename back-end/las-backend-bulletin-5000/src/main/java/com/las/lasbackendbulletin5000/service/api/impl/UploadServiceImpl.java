package com.las.lasbackendbulletin5000.service.api.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.las.lasbackendbulletin5000.model.Bulletin;
import com.las.lasbackendbulletin5000.aop.privilegeControl.User;
import com.las.lasbackendbulletin5000.service.api.UploadService;
import com.las.lasbackendbulletin5000.service.db.impl.BulletinServiceImpl;
import com.las.lasbackendbulletin5000.service.db.redis.impl.RedisToolsImpl;
import org.springframework.stereotype.Service;
import result.Result;
import result.ResultEnum;
import result.ResultUtil;

/**
 * @author sunyinuo
 */
@Service
public class UploadServiceImpl implements UploadService {

    private final RedisToolsImpl redisTools;
    private final BulletinServiceImpl bulletinService;

    public UploadServiceImpl(RedisToolsImpl redisTools, BulletinServiceImpl bulletinService) {
        this.redisTools = redisTools;
        this.bulletinService = bulletinService;
    }


    /**
     * 上传
     * @param token token
     * @param jsonObject json
     * @return 统一
     */
    @Override
    public Result upload(String token,JSONObject jsonObject) {
        //obj get
        String title = (String) jsonObject.get("title");
        String body = (String) jsonObject.get("body");
        boolean sticky = (boolean) jsonObject.get("sticky");
        int level = (int) jsonObject.get("level");

        //根据token获取用户信息
        String byKeyString = (String) redisTools.getByKey(token);
        JSONObject userJson = JSONObject.parseObject(byKeyString);
        User user = JSON.toJavaObject(userJson,User.class);


        Bulletin bulletin = new Bulletin();
        bulletin.setId(000);
        bulletin.setPublisher(user.getUserName());
        bulletin.setLevel(level);
        bulletin.setTime(String.valueOf(System.currentTimeMillis()));
        bulletin.setTitle(title);
        bulletin.setBody(body);
        bulletin.setSticky(sticky);

        int affect =  bulletinService.addBulletin(bulletin);

        if (affect == 1){
            return ResultUtil.result(ResultEnum.SUCCESS.getCode(),"发布成功");
        }else {
            return ResultUtil.result(ResultEnum.SERVER_ERROR.getCode(),"发布失败");
        }

    }
}
