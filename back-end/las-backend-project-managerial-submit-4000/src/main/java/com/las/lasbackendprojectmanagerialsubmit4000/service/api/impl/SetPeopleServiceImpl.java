package com.las.lasbackendprojectmanagerialsubmit4000.service.api.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.las.lasbackendprojectmanagerialsubmit4000.model.ProjectPeople;
import com.las.lasbackendprojectmanagerialsubmit4000.model.Submit;
import com.las.lasbackendprojectmanagerialsubmit4000.service.api.SetPeopleService;
import com.las.lasbackendprojectmanagerialsubmit4000.service.db.redis.impl.RedisToolsImpl;
import org.springframework.stereotype.Service;
import result.Result;
import result.ResultEnum;
import result.ResultUtil;

import java.util.List;

/**
 * @author sunyinuo
 */
@Service
public class SetPeopleServiceImpl implements SetPeopleService {


    public RedisToolsImpl redisTools;

    public SetPeopleServiceImpl(RedisToolsImpl redisTools) {
        this.redisTools = redisTools;
    }
    @Override
    public Result setPeople(String projectName, List<String> people,int progress) {
        // 获取项目人员信息
        String projectPeopleString = (String) redisTools.getByKey("PEOPLE_PROJECT_"+projectName);
        // 解析项目人员信息
        ProjectPeople projectPeople = JSON.parseObject(projectPeopleString,ProjectPeople.class);

        // 根据进度设置人员信息
        switch (progress){
            // 设计
            case -1:
                //设置并上传项目人员信息
                projectPeople.setDesign(people);
                redisTools.insert("PEOPLE_PROJECT_"+projectName, JSONObject.toJSONString(projectPeople));
                return ResultUtil.result(ResultEnum.SUCCESS.getCode(), projectPeople, "设置成功");
            // 审核
            case 0:
                projectPeople.setAudit(people);
                redisTools.insert("PEOPLE_PROJECT_"+projectName,JSONObject.toJSONString(projectPeople));
                return ResultUtil.result(ResultEnum.SUCCESS.getCode(), projectPeople, "设置成功");
            // 测试
            case 1:
                projectPeople.setTesting(people);
                redisTools.insert("PEOPLE_PROJECT_"+projectName,JSONObject.toJSONString(projectPeople));
                return ResultUtil.result(ResultEnum.SUCCESS.getCode(), projectPeople, "设置成功");
            // 优化
            case 2:
                projectPeople.setOptimization(people);
                redisTools.insert("PEOPLE_PROJECT_"+projectName,JSONObject.toJSONString(projectPeople));
                return ResultUtil.result(ResultEnum.SUCCESS.getCode(), projectPeople, "设置成功");
            // 发布
            case 3:
                projectPeople.setRelease(people);
                redisTools.insert("PEOPLE_PROJECT_"+projectName,JSONObject.toJSONString(projectPeople));
                return ResultUtil.result(ResultEnum.SUCCESS.getCode(), projectPeople, "设置成功");
            // 实装
            case 4:
                projectPeople.setInstallation(people);
                redisTools.insert("PEOPLE_PROJECT_"+projectName,JSONObject.toJSONString(projectPeople));
                return ResultUtil.result(ResultEnum.SUCCESS.getCode(), projectPeople, "设置成功");
            // 完成
            case 5:
                projectPeople.setCompletion(people);
                redisTools.insert("PEOPLE_PROJECT_"+projectName,JSONObject.toJSONString(projectPeople));
                return ResultUtil.result(ResultEnum.SUCCESS.getCode(), projectPeople, "设置成功");
            default:
                return ResultUtil.result(ResultEnum.SERVER_ERROR.getCode(), "设置失败", "设置失败");
        }
    }
}
