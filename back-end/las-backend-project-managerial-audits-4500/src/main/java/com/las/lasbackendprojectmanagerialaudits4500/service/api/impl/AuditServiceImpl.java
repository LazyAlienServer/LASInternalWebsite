package com.las.lasbackendprojectmanagerialaudits4500.service.api.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.las.lasbackendprojectmanagerialaudits4500.aop.privilegeControl.User;
import com.las.lasbackendprojectmanagerialaudits4500.model.Submit;
import com.las.lasbackendprojectmanagerialaudits4500.model.SubmitOriginal;
import com.las.lasbackendprojectmanagerialaudits4500.service.api.AuditService;
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
public class AuditServiceImpl implements AuditService {

    private final RedisToolsImpl redisTools;

    public AuditServiceImpl(RedisToolsImpl redisTools) {
        this.redisTools = redisTools;
    }

    /**
     * 提交审核信息
     * @param token token
     * @param jsonParam json
     * @return result
     */
    @Override
    public Result audit(String token,JSONObject jsonParam) {
        //json get
        String name = (String) jsonParam.get("name");
        boolean isPass = (boolean) jsonParam.get("pass");

        //根据工程名字查询redis
        SubmitOriginal submitOriginal = JSONObject.parseObject((String) redisTools.getByKey("PROJECT_" + name), SubmitOriginal.class);
        //创建空的Submit.class
        Submit submit = new Submit();

        //设置名字
        submit.setName(submitOriginal.getName());
        //设置进度为未开工
        submit.setProgress(-1);
        //设置负责人
        submit.setAuthors(submitOriginal.getAuthors());
        //设置开始时间
        submit.setStart_time(submitOriginal.getStart_time());
        //设置地点
        submit.setPlace(submitOriginal.getPlace());
        //设置是否原创信息
        submit.setInnovation(submitOriginal.isInnovation());
        //设置是否通过
        submit.setPass(isPass);
        //设置审核人
        User user = JSONObject.parseObject((String) redisTools.getByKey(token), User.class);
        submit.setAuditor(user.getUserName());
        //设置note
        submit.setNote(submitOriginal.getNote());
        //设置图片链接
        submit.setPicture(submitOriginal.getPicture());

        //insert(覆盖)
        redisTools.insert("PROJECT_" + submitOriginal.getName(), JSON.toJSONString(submit));

        return ResultUtil.result(ResultEnum.SUCCESS.getCode(), JSON.toJSONString(submit),null);
    }
}
