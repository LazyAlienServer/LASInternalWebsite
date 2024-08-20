package com.las.lasbackendprojectmanagerialaudits4500.controller;

import com.las.lasbackendprojectmanagerialaudits4500.service.api.impl.ProjectCRUDServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.Result;
import result.ResultEnum;
import result.ResultUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunyinuo
 */
@RestController
@RequestMapping("/project-managerial-audits/api")
public class ProjectCRUDController {


    public final ProjectCRUDServiceImpl projectCRUDService;

    public ProjectCRUDController(ProjectCRUDServiceImpl projectCRUDService) {
        this.projectCRUDService = projectCRUDService;
    }

    @PostMapping("/getProjectByType")
    public Result getProjectByType(String type){
        List<String> types = new ArrayList<>();
        types.add(type);
        return ResultUtil.result(ResultEnum.SUCCESS.getCode(),projectCRUDService.getProjectByType(types),null);
    }

}
