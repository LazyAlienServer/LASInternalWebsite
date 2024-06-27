package com.las.lasbackendprojectmanagerialaudits4500.controller;


import com.las.lasbackendprojectmanagerialaudits4500.aop.privilegeControl.PrivilegeControl;
import com.las.lasbackendprojectmanagerialaudits4500.service.api.GetProjectListService;
import com.las.lasbackendprojectmanagerialaudits4500.service.api.impl.GetProjectListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import result.Result;

/**
 * api控制器
 * @author sunyinuo
 */
@RestController
@RequestMapping("/project-managerial-audits/api")
public class ApiController {


    private final GetProjectListServiceImpl getProjectListService;

    public ApiController(GetProjectListServiceImpl getProjectListService) {
        this.getProjectListService = getProjectListService;
    }


    /**
     * 获取项目列表
     * @return 数据
     */
    @GetMapping("/getProjectList")
    @PrivilegeControl(roleIdList = {1})
    public Result getFile(@RequestHeader(value = "token") String token) {
        return getProjectListService.getProjectList();
    }


}
