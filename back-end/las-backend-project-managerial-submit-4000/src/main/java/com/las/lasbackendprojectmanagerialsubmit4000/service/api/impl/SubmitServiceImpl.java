package com.las.lasbackendprojectmanagerialsubmit4000.service.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.las.lasbackendprojectmanagerialsubmit4000.model.FileUpload;
import com.las.lasbackendprojectmanagerialsubmit4000.model.ProjectPeople;
import com.las.lasbackendprojectmanagerialsubmit4000.model.Submit;
import com.las.lasbackendprojectmanagerialsubmit4000.service.api.SubmitService;
import com.las.lasbackendprojectmanagerialsubmit4000.service.db.redis.impl.RedisToolsImpl;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import result.Result;
import result.ResultEnum;
import result.ResultUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunyinuo
 */
@Service
public class SubmitServiceImpl implements SubmitService {



    private final MongoTemplate mongoTemplate;
    private final RedisToolsImpl redisTools;

    public SubmitServiceImpl(MongoTemplate mongoTemplate, RedisToolsImpl redisTools) {
        this.mongoTemplate = mongoTemplate;
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

        //获取图片
        Query query = Query.query(Criteria.where("projectName").is(submit.getName()));
        List<FileUpload> files = mongoTemplate.find(query, FileUpload.class);

        List<String> url = new ArrayList<>();

        if (redisTools.getByKey(submit.getName()) == null){
            //设置图片url
            for (FileUpload file : files) {
                url.add("http://127.0.0.1:9000/project-managerial-submit/api/getFile/" + file.getId());
                submit.setPicture(url);
            }

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
