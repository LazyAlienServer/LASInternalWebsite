package com.las.lasbackendprojectmanagerialsubmit4000.service.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.las.lasbackendprojectmanagerialsubmit4000.model.ProjectPeople;
import com.las.lasbackendprojectmanagerialsubmit4000.model.Submit;
import com.las.lasbackendprojectmanagerialsubmit4000.service.api.SubmitService;
import com.las.lasbackendprojectmanagerialsubmit4000.service.db.redis.impl.RedisToolsImpl;
import org.springframework.stereotype.Service;
import result.Result;
import result.ResultEnum;
import result.ResultUtil;

/**
 * @author sunyinuo
 */
@Service
public class SubmitServiceImpl implements SubmitService {




    private final RedisToolsImpl redisTools;

    public SubmitServiceImpl(RedisToolsImpl redisTools) {
        this.redisTools = redisTools;
    }

    /**
     * 项目报备提交
     * @param jsonParam json
     * @return 统一返回
     */
    @Override
    public Result submit(JSONObject jsonParam) {

        Submit submit = JSONObject.toJavaObject(jsonParam,Submit.class);

        if (redisTools.getByKey(submit.getName()) == null){

            redisTools.insert("PROJECT_" + submit.getName(),JSONObject.toJSONString(submit));
            ProjectPeople projectPeople = new ProjectPeople();
            projectPeople.fill();
            redisTools.insert("PEOPLE_PROJECT_"+ submit.getName(),JSONObject.toJSONString(projectPeople));

            return ResultUtil.result(ResultEnum.SUCCESS.getCode(), submit.toString(),"提交成功");
        }else{
            return ResultUtil.result(ResultEnum.SERVER_ERROR.getCode(),"项目名重复");
        }
    }
}
