package com.las.lasbackendprojectmanagerialaudits4500.service.api.impl;

import com.alibaba.fastjson.JSON;
import com.las.lasbackendprojectmanagerialaudits4500.service.api.GetProjectListService;
import com.las.lasbackendprojectmanagerialaudits4500.service.db.redis.impl.RedisToolsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import result.Result;
import result.ResultEnum;
import result.ResultUtil;

/**
 * @author sunyinuo
 */
@Service
public class GetProjectListServiceImpl implements GetProjectListService {

    private final RedisToolsImpl redisTools;

    public GetProjectListServiceImpl(RedisToolsImpl redisTools) {
        this.redisTools = redisTools;
    }


    /**
     * 获取项目列表
     * @return result
     */
    @Override
    public Result getProjectList() {
        String toJSONString = JSON.toJSONString(redisTools.getByPrex("PROJECT_*"));
        String replaceAll = toJSONString.replaceAll("[\\\\]", "");
        return ResultUtil.result(ResultEnum.SUCCESS.getCode(), replaceAll,null);
    }
}
