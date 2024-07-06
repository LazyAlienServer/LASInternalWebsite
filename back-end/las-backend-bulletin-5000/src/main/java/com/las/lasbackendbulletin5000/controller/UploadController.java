package com.las.lasbackendbulletin5000.controller;

import com.alibaba.fastjson.JSONObject;
import com.las.lasbackendbulletin5000.aop.privilegeControl.PrivilegeControl;
import com.las.lasbackendbulletin5000.service.api.impl.UploadServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import result.Result;

/**
 * @author sunyinuo
 */
@RestController
@RequestMapping("/bulletin/")
@Slf4j
public class UploadController {
    public final UploadServiceImpl uploadService;

    public UploadController(UploadServiceImpl uploadService) {
        this.uploadService = uploadService;
    }


    @PostMapping("/upload")
    @PrivilegeControl(roleIdList = {2})
    public Result upload (@RequestHeader(value = "token") String token, @RequestBody JSONObject jsonParam){
        return uploadService.upload(token,jsonParam);
    }
}
