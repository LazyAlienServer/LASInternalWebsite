package com.las.lasbackendprojectmanagerialaudits4500.service.api.impl;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.las.lasbackendprojectmanagerialaudits4500.model.Submit;
import com.las.lasbackendprojectmanagerialaudits4500.service.api.GetProjectListService;
import com.las.lasbackendprojectmanagerialaudits4500.service.db.redis.impl.RedisToolsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import result.Result;
import result.ResultEnum;
import result.ResultUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sunyinuo
 */
@Service
@Slf4j
public class GetProjectListServiceImpl implements GetProjectListService {

    private final RedisToolsImpl redisTools;

    private final static ObjectMapper objectMapper = new ObjectMapper();

    public GetProjectListServiceImpl(RedisToolsImpl redisTools) {
        this.redisTools = redisTools;
    }


    /**
     * 获取项目列表
     * @return result
     */
    @Override
    public Result getProjectList() {
        List<Submit> projects=new ArrayList<>();
        try{
            for(String project:redisTools.getByPrex("PROJECT_*")){
                projects.add(objectMapper.readValue(project, Submit.class));
            }
        }catch (IOException e){
            log.error("GetProjectListService: getProjectList",e);
        }

        String toJSONString = JSON.toJSONString(projects.toArray());
        //String replaceAll = toJSONString.replaceAll("[\\\\]", "");
        return ResultUtil.result(ResultEnum.SUCCESS.getCode(), toJSONString,null);
    }
}
