package com.las.lasbackendprojectmanagerialsubmit4000.service.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.las.lasbackendprojectmanagerialsubmit4000.model.FileUpload;
import com.las.lasbackendprojectmanagerialsubmit4000.model.Submit;
import com.las.lasbackendprojectmanagerialsubmit4000.model.SubmitOriginal;
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
        SubmitOriginal submitOriginal = JSONObject.toJavaObject(jsonParam, SubmitOriginal.class);

        //获取图片
        Query query = Query.query(Criteria.where("projectName").is(submitOriginal.getName()));
        List<FileUpload> files = mongoTemplate.find(query, FileUpload.class);

        Submit submit = new Submit();
        List<String> url = new ArrayList<>();

        if (redisTools.getByKey(submitOriginal.getName()) == null){
            //设置图片url
            for (FileUpload file : files) {
                url.add("http://127.0.0.1:9000/projectmanagerial/api/getFile/" + file.getId());
                submit.setPicture(url);
            }



            //设置其他信息
            submit.setName(submitOriginal.getName());
            submit.setAuthors(submitOriginal.getAuthors());
            submit.setStart_time(submitOriginal.getStart_time());
            submit.setPlace(submitOriginal.getPlace());
            submit.setInnovation(submitOriginal.isInnovation());
            submit.setNote(submitOriginal.getNote());

            redisTools.insert("PROJECT_" + submit.getName(),submit);
        }else{
            return ResultUtil.result(ResultEnum.SERVER_ERROR.getCode(),"项目名重复");
        }

        return ResultUtil.result(ResultEnum.SUCCESS.getCode(), submit.toString(),"提交成功");
    }
}
