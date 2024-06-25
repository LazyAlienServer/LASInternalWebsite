package com.las.lasbackenduser3000.service.api.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.las.lasbackenduser3000.model.User;
import com.las.lasbackenduser3000.model.bot.getGroupMemberInfo.request.GetGroupMemberInfo;
import com.las.lasbackenduser3000.model.bot.getGroupMemberInfo.request.Params;
import com.las.lasbackenduser3000.service.api.RegistrationService;
import com.las.lasbackenduser3000.service.db.impl.UserServiceImpl;
import com.las.lasbackenduser3000.service.db.redis.impl.RedisToolsImpl;
import com.las.lasbackenduser3000.utils.md5.Md5Slat;
import com.las.lasbackenduser3000.utils.md5.Slat;
import com.las.lasbackenduser3000.websocket.MyWebSocketClient;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import result.Result;
import result.ResultEnum;
import result.ResultUtil;

import javax.annotation.Resource;
import java.util.Calendar;

/**
 * @author sunyinuo
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Resource
    private MyWebSocketClient webSocketClient;

    public final RedisToolsImpl redisTools;
    public final UserServiceImpl userService;

    public RegistrationServiceImpl(RedisToolsImpl redisTools, UserServiceImpl userService) {
        this.redisTools = redisTools;
        this.userService = userService;
    }


    /**
     * 注册
     * @param jsonParam json
     * @return 统一返回
     */

    @Override
    public Result registration(JSONObject jsonParam) throws InterruptedException {
        //get userName,原始密码,qq号,验证码
        String userName = (String) jsonParam.get("userName");
        String originalPassword = (String) jsonParam.get("password");
        String qq = (String) jsonParam.get("qq");
        String verificationCode = (String) jsonParam.get("verificationCode");

        //用qq号为key查询redis,获取验证码
        String getVerificationCodeByQq = (String) redisTools.getByKey(qq);


        Params params = new Params(826055741,Long.parseLong(qq));
        GetGroupMemberInfo getGroupMemberInfo = new GetGroupMemberInfo("get_group_member_info",params);
        webSocketClient.send(JSON.toJSONString(getGroupMemberInfo));



        //加盐
        Integer slat = Slat.slat();
        //加盐后密码
        String password = Md5Slat.md5Slat(originalPassword,slat);



        //校验验证码
        if (getVerificationCodeByQq != null && getVerificationCodeByQq.equals(verificationCode)){
            //校验用户名是否重复
            if (userService.getUserByName(userName) == null){


                //获取用户信息
                String role = (String)  redisTools.getByKey("INFO_" + qq);

                User user = new User();
                user.setId(0);
                user.setTime(Calendar.getInstance().getTimeInMillis());
                user.setUserName(userName);
                user.setUserPassword(password);
                user.setUserSignature("这个人很懒,什么也不写");
                user.setUserTag(null);
                user.setLastLogin(null);
                user.setWhiteList(null);
                //setOnline(null);
                user.setProbation("0");
                switch (role) {
                    case "member" -> user.setAdministrator(0);
                    case "admin" -> user.setAdministrator(1);
                    case "owner" -> user.setAdministrator(1);
                }
                user.setSlat(slat);
                user.setQq(qq);

                //add
                userService.addUser(user);

                //删除redis
                redisTools.delete("INFO_" + qq);


                return ResultUtil.result(ResultEnum.SUCCESS.getCode(), "注册成功!");
            }else {
                Thread.sleep(300);
                //删除redis
                redisTools.delete("INFO_" + qq);
                return ResultUtil.result(ResultEnum.SERVER_ERROR.getCode(),"用户名重复");
            }
        }else {
            Thread.sleep(300);
            //删除redis
            redisTools.delete("INFO_" + qq);
            return ResultUtil.result(ResultEnum.SERVER_ERROR.getCode(),"验证码错误");
        }
    }
}
