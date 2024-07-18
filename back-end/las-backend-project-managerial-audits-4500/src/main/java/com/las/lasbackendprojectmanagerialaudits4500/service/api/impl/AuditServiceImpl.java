package com.las.lasbackendprojectmanagerialaudits4500.service.api.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.las.lasbackendprojectmanagerialaudits4500.aop.privilegeControl.User;
import com.las.lasbackendprojectmanagerialaudits4500.model.Submit;
import com.las.lasbackendprojectmanagerialaudits4500.service.api.AuditService;
import com.las.lasbackendprojectmanagerialaudits4500.service.db.redis.impl.RedisToolsImpl;
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
        Submit submit = JSONObject.parseObject((String) redisTools.getByKey("PROJECT_" + name), Submit.class);


        submit.setPass(isPass);
        User user = JSONObject.parseObject((String) redisTools.getByKey(token), User.class);
        submit.setAuditor(user.getUserName());

        submit.setProgress(-1);

        //insert(覆盖)
        redisTools.insert("PROJECT_" + submit.getName(), JSON.toJSONString(submit));

        return ResultUtil.result(ResultEnum.SUCCESS.getCode(), JSON.toJSONString(submit),null);
    }
}
