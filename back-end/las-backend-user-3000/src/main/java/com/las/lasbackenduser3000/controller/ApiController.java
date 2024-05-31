package com.las.lasbackenduser3000.controller;

import com.alibaba.fastjson.JSONObject;
import com.las.lasbackenduser3000.service.api.impl.LoginServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.Result;

/**
 * @author sunyinuo
 */
@RestController
@RequestMapping("/usersignin/")
@Slf4j
public class ApiController {
    public final LoginServiceImpl loginService;

    public ApiController(LoginServiceImpl loginService) {
        this.loginService = loginService;
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




}
