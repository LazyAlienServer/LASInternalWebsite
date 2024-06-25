package com.las.lasbackenduser3000.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.las.lasbackenduser3000.config.RabbitmqConfig;
import com.las.lasbackenduser3000.model.bot.sendVerificationCode.SendVerificationCode;
import com.las.lasbackenduser3000.service.api.impl.LoginServiceImpl;
import com.las.lasbackenduser3000.service.api.impl.RegistrationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;
import result.Result;

/**
 * @author sunyinuo
 */
@RestController
@RequestMapping("/usersignin/")
public class ApiController {

    public final RabbitTemplate rabbitTemplate;
    public final RegistrationServiceImpl registrationService;
    public final LoginServiceImpl loginService;

    public ApiController(LoginServiceImpl loginService, RegistrationServiceImpl registrationService, RabbitTemplate rabbitTemplate) {
        this.loginService = loginService;
        this.registrationService = registrationService;
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * 登陆api,无权限控制
     * @param jsonParam json,包含输入的用户名与密码
     * @return 返回
     */
    @PostMapping("/login")
    public Result login (@RequestBody JSONObject jsonParam){
        return loginService.login((String) jsonParam.get("userName"),(String) jsonParam.get("password"));
    }


    /**
     * 发送验证码
     * @param qq qq(String)
     */
    @GetMapping("/sendVerificationCode")
    public void sendVerificationCode(String qq){
        SendVerificationCode sendVerificationCode = new SendVerificationCode();
        sendVerificationCode.setMessage("您正在使用此QQ号注册LAS_LOG");
        sendVerificationCode.setQq(qq);
        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_BOT_VERIFICATION,"bot.verification", JSON.toJSONString(sendVerificationCode));
    }

    /**
     * 注册
     * @param jsonParam json
     * @return 统一返回
     */
    @PostMapping("/register")
    public Result registration(@RequestBody JSONObject jsonParam) throws InterruptedException {
        return registrationService.registration(jsonParam);
    }

}
