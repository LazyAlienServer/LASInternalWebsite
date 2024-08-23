package com.las.lasbackendprojectmanagerialsubmit4000.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.las.lasbackendprojectmanagerialsubmit4000.aop.privilegeControl.PrivilegeControl;
import com.las.lasbackendprojectmanagerialsubmit4000.model.SetPeople;
import com.las.lasbackendprojectmanagerialsubmit4000.model.Submit;
import com.las.lasbackendprojectmanagerialsubmit4000.service.api.impl.SetPeopleServiceImpl;
import com.las.lasbackendprojectmanagerialsubmit4000.service.api.impl.SubmitServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import result.Result;

import java.util.List;
import java.util.Map;


/**
 * @author sunyinuo
 */
@RestController
@RequestMapping("/project-managerial-submit/")
@Slf4j
public class SubmitController {

    public final SetPeopleServiceImpl setPeopleService;
    public final SubmitServiceImpl submitService;

    public SubmitController(SetPeopleServiceImpl setPeopleService, SubmitServiceImpl submitService) {
        this.setPeopleService = setPeopleService;
        this.submitService = submitService;
    }


    @PostMapping("/submit")
    @PrivilegeControl(roleIdList = {1})
    public Result report(@RequestHeader(value = "token") String token, @RequestBody JSONObject jsonParam){
        return submitService.submit(jsonParam);
    }

    @PostMapping("/setPeople")
    @PrivilegeControl(roleIdList = {1})
    //String people,String projectName,int progress
    public Result setPeople(@RequestHeader(value = "token") String token, @RequestBody JSONObject jsonParam){
        SetPeople setPeople = JSONObject.toJavaObject(jsonParam, SetPeople.class);
        return setPeopleService.setPeople(setPeople.getProjectName(),setPeople.getPeople(),setPeople.getProgress());
    }

}
