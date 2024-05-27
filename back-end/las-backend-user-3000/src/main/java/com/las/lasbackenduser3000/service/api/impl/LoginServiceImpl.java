package com.las.lasbackenduser3000.service.api.impl;

import com.las.lasbackenduser3000.model.User;
import com.las.lasbackenduser3000.service.api.LoginService;
import com.las.lasbackenduser3000.service.db.impl.UserServiceImpl;
import com.las.lasbackenduser3000.service.db.redis.impl.RedisToolsImpl;
import com.las.lasbackenduser3000.utils.md5.Md5Slat;
import org.springframework.stereotype.Service;
import result.Result;
import result.ResultEnum;
import result.ResultUtil;

import java.util.concurrent.TimeUnit;


/**
 * @author sunyinuo
 */
@Service
public class LoginServiceImpl implements LoginService {
    public final RedisToolsImpl redisTools;
    public final UserServiceImpl userService;

    public LoginServiceImpl(RedisToolsImpl redisTools, UserServiceImpl userService) {
        this.redisTools = redisTools;
        this.userService = userService;
    }


    /**
     * login
     * @param userName userName
     * @param password password
     * @return 统一返回
     */
    @Override
    public Result login(String userName, String password) {
        //根据提供用户名查询
        User userByName = userService.getUserByName(userName);
        if (userByName != null){
            //将用户输入的密码根据数据库中slat进行加盐
            String slatPassword = Md5Slat.md5Slat(password,userByName.getSlat());
            //比对
            if (slatPassword.equals(userByName.getUserPassword())){
                //用slat为盐对用户id加密,作为token
                String token = Md5Slat.md5Slat(String.valueOf(userByName.getId()),userByName.getSlat());
                //以用户id为key，生成的token为value，缓存到redis，20天过期
                redisTools.insert(String.valueOf(userByName.getId()),token,20, TimeUnit.DAYS);
                //以生成的token为key，用户信息为value，缓存到redis，20天过期
                redisTools.insert(token,userByName,20, TimeUnit.DAYS);

                //全部正确,存入redis,返回
                return ResultUtil.result(ResultEnum.SUCCESS.getCode(), token ,"登陆成功");

            }else {
                //用户名正确(可以查询到),密码错误,result
                return ResultUtil.result(ResultEnum.SERVER_ERROR.getCode(), "用户名或密码错误");
            }
        }else {
            //无法查询到用户名,result
            return ResultUtil.result(ResultEnum.SERVER_ERROR.getCode(), "用户名或密码错误");
        }
    }
}
