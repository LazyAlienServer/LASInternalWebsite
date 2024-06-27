package com.las.lasbackendprojectmanagerialaudits4500.controller;

import com.alibaba.fastjson.JSONObject;
import com.las.lasbackendprojectmanagerialaudits4500.aop.privilegeControl.PrivilegeControl;
import com.las.lasbackendprojectmanagerialaudits4500.service.api.impl.AuditServiceImpl;
import org.springframework.web.bind.annotation.*;
import result.Result;



/**
 * @author sunyinuo
 */
@RestController
@RequestMapping("/project-managerial-audits")
public class SubmitController {
    public final AuditServiceImpl auditService;

    public SubmitController(AuditServiceImpl auditService) {
        this.auditService = auditService;
    }


    @PostMapping("/audits")
    @PrivilegeControl(roleIdList = {2})
    public Result report(@RequestHeader(value = "token") String token, @RequestBody JSONObject jsonParam){
        return auditService.audit(token,jsonParam);
    }

}
