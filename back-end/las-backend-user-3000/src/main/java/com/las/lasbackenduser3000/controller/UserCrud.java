package com.las.lasbackenduser3000.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.las.lasbackenduser3000.aop.privilegeControl.PrivilegeControl;
import com.las.lasbackenduser3000.model.User;
import com.las.lasbackenduser3000.service.db.impl.UserServiceImpl;
import com.las.lasbackenduser3000.service.db.redis.impl.RedisToolsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import result.Result;
import result.ResultEnum;
import result.ResultUtil;



/**
 * @author sunyinuo
 */
@RestController
@RequestMapping("/usersignin/crud")
public class UserCrud {

    public final RedisToolsImpl redisTools;
    public final UserServiceImpl userService;

    public UserCrud(RedisToolsImpl redisTools, UserServiceImpl userService) {
        this.redisTools = redisTools;
        this.userService = userService;
    }



    /**
     * 获取所有用户
     * @return 用户实体类List
     */
    @GetMapping("/getUserList")
    @PrivilegeControl(roleIdList = {2})
    public Result getUserList(@RequestHeader(value = "token") String token) {
        return ResultUtil.result(ResultEnum.SUCCESS.getCode(), userService.getUserList(),null);
    }

    /**
     * 根据id查询所有用户
     * @param id id
     * @return 用户实体类
     */
    @PostMapping("/getUserById")
    @PrivilegeControl(roleIdList = {2})
    public Result getUserById(@RequestHeader(value = "token") String token,int id) {
        return ResultUtil.result(ResultEnum.SUCCESS.getCode(), userService.getUserById(id),null);
    }

    /**
     * 根据userName查询所有用户
     * @param userName 用户名
     * @return 用户实体类
     */
    @PostMapping("/getUserByName")
    @PrivilegeControl(roleIdList = {2})
    public Result getUserByName(@RequestHeader(value = "token") String token,String userName) {
        return ResultUtil.result(ResultEnum.SUCCESS.getCode(), userService.getUserByName(userName),null);

    }


    /**
     * 根据白名单获取用户
     * @param whiteList 白名单
     * @return 用户实体类
     */
    @PostMapping("/getUserByWhiteList")
    @PrivilegeControl(roleIdList = {2})
    public Result getUserByWhiteList(@RequestHeader(value = "token") String token,String whiteList) {
        return ResultUtil.result(ResultEnum.SUCCESS.getCode(), userService.getUserByWhiteList(whiteList),null);
    }

    /**
     * 根据是否正版获取用户
     * @param online 正版状态 1=正版 0=离线
     * @return 用户实体类
     */
    @PostMapping("/getUserByOnline")
    @PrivilegeControl(roleIdList = {2})
    public Result getUserByOnline(@RequestHeader(value = "token") String token,int online) {
        return ResultUtil.result(ResultEnum.SUCCESS.getCode(), userService.getUserByOnline(online),null);
    }



    /**
     * 根据qq号获取用户
     * @param qq qq
     * @return 用户实体类
     */
    @PostMapping("/getUserByQq")
    @PrivilegeControl(roleIdList = {2})
    public Result getUserByQq(@RequestHeader(value = "token") String token,String qq) {
        return ResultUtil.result(ResultEnum.SUCCESS.getCode(), userService.getUserByQq(qq),null);
    }



    /**
     * 根据userName删除用户
     * @param userName 用户名
     * @return 是否成功
     */
    @PostMapping("/deleteUserByName")
    @PrivilegeControl(roleIdList = {2})
    public Result deleteUserByName(@RequestHeader(value = "token") String token,String userName) {
        return ResultUtil.result(ResultEnum.SUCCESS.getCode(), userService.deleteUserByName(userName),null);

    }

    /**
     * 根据是否管理获取用户
     * @param administrator 是否管理 1=是 0=否
     * @return 用户实体类
     */
    @PostMapping("/getUserByAdministrator")
    @PrivilegeControl(roleIdList = {2})
    public Result getUserByAdministrator(@RequestHeader(value = "token") String token,int administrator) {
        return ResultUtil.result(ResultEnum.SUCCESS.getCode(), userService.getUserByAdministrator(administrator),null);
    }

    @PostMapping("/getUserByToken")
    public Result getUserByToken(String token){
        //根据token查redis
        String byKeyString = (String) redisTools.getByKey(token);
        JSONObject userJson = JSONObject.parseObject(byKeyString);
        User byKey = JSON.toJavaObject(userJson,User.class);
        return ResultUtil.result(ResultEnum.SUCCESS.getCode(), byKey,null);
    }

    @PostMapping("/getLoginState")
    public boolean getLoginState(String token){
        return redisTools.getByKey(token) != null;
    }

    @PostMapping("/getUserIdByToken")
    public int getUserIdByToken(String token){
        //根据token查redis
        String byKeyString = (String) redisTools.getByKey(token);
        JSONObject userJson = JSONObject.parseObject(byKeyString);
        User byKey = JSON.toJavaObject(userJson,User.class);

        return byKey.getId();
    }

    @PostMapping("/getUserNameByToken")
    public String getUserNameByToken(String token){
        //根据token查redis
        String byKeyString = (String) redisTools.getByKey(token);
        JSONObject userJson = JSONObject.parseObject(byKeyString);
        User byKey = JSON.toJavaObject(userJson,User.class);

        return byKey.getUserName();
    }

    @PostMapping("/getAvatarByToken")
    public String getAvatarByToken(String token){
        //根据token查redis
        String byKeyString = (String) redisTools.getByKey(token);
        JSONObject userJson = JSONObject.parseObject(byKeyString);
        User byKey = JSON.toJavaObject(userJson,User.class);

        return byKey.getAvatar();
    }
}
