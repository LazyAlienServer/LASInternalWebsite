package com.las.lasbackendbulletin5000.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.las.lasbackendbulletin5000.aop.privilegeControl.PrivilegeControl;
import com.las.lasbackendbulletin5000.aop.privilegeControl.User;
import com.las.lasbackendbulletin5000.model.Bulletin;
import com.las.lasbackendbulletin5000.service.db.impl.BulletinServiceImpl;
import com.las.lasbackendbulletin5000.service.db.redis.impl.RedisToolsImpl;
import org.springframework.web.bind.annotation.*;
import result.Result;
import result.ResultEnum;
import result.ResultUtil;

/**
 * @author sunyinuo
 */
@RestController
@RequestMapping("/bulletin/api")
public class ApiController {

    private final RedisToolsImpl redisTools;
    public final BulletinServiceImpl bulletinService;

    public ApiController(RedisToolsImpl redisTools, BulletinServiceImpl bulletinService) {
        this.redisTools = redisTools;
        this.bulletinService = bulletinService;
    }

    @GetMapping("/getBulletinList")
    public Result getBulletinList(){
        return ResultUtil.result(ResultEnum.SUCCESS.getCode(),bulletinService.getBulletinList(),null);
    }

    @PostMapping("/getBulletinByLevel")
    public Result getBulletinByLevel(int level){
        return ResultUtil.result(ResultEnum.SUCCESS.getCode(),bulletinService.getBulletinByLevel(level),null);
    }


    @PostMapping("/getBulletinByPublisher")
    public Result getBulletinByPublisher(String publisher){
        return ResultUtil.result(ResultEnum.SUCCESS.getCode(),bulletinService.getBulletinByPublisher(publisher),null);
    }

    @PostMapping("/deleteBulletinByTitle")
    @PrivilegeControl(roleIdList = {2})
    public Result deleteBulletinByTitle(@RequestHeader(value = "token") String token,String title){
        return ResultUtil.result(ResultEnum.SUCCESS.getCode(),bulletinService.deleteBulletinByTitle(title),null);
    }

    @PostMapping("/update")
    @PrivilegeControl(roleIdList = {2})
    public Result update(@RequestHeader(value = "token") String token,@RequestBody JSONObject jsonParam){
       int id =  (int) jsonParam.get("id");

       String title = (String) jsonParam.get("title");
       String body = (String) jsonParam.get("body");
       boolean sticky = (boolean)  jsonParam.get("sticky");
       int level = (int)  jsonParam.get("level");

       Bulletin  bulletinOld = bulletinService.getBulletinById(id);

        //根据token获取用户信息
        String byKeyString = (String) redisTools.getByKey(token);
        JSONObject userJson = JSONObject.parseObject(byKeyString);
        User user = JSON.toJavaObject(userJson,User.class);

       Bulletin bulletin = new Bulletin();
       bulletin.setId(bulletinOld.getId());

       //update
       bulletin.setPublisher(user.getUserName());
       bulletin.setLevel(level);
       bulletin.setTime(String.valueOf(System.currentTimeMillis()));
       bulletin.setTitle(title);
       bulletin.setBody(body);
       bulletin.setSticky(sticky);

        return ResultUtil.result(ResultEnum.SUCCESS.getCode(),bulletinService.update(bulletin),null);

    }
}
