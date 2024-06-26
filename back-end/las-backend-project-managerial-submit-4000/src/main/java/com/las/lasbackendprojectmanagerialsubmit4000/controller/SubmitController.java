package com.las.lasbackendprojectmanagerialsubmit4000.controller;

import com.alibaba.fastjson.JSONObject;
import com.las.lasbackendprojectmanagerialsubmit4000.aop.privilegeControl.PrivilegeControl;
import com.las.lasbackendprojectmanagerialsubmit4000.service.api.impl.SubmitServiceImpl;
import org.springframework.web.bind.annotation.*;
import result.Result;



/**
 * @author sunyinuo
 */
@RestController
@RequestMapping("/projectmanagerial/")
public class SubmitController {
    public final SubmitServiceImpl submitService;

    public SubmitController(SubmitServiceImpl submitService) {
        this.submitService = submitService;
    }


    @PostMapping("/submit")
    @PrivilegeControl(roleIdList = {1})
    public Result report(@RequestHeader(value = "token") String token, @RequestBody JSONObject jsonParam){
        return submitService.submit(jsonParam);
    }

}
