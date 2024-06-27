package com.las.lasbackendprojectmanagerialaudits4500.service.api;

import com.alibaba.fastjson.JSONObject;
import result.Result;

/**
 * @author sunyinuo
 */
public interface AuditService {


    /**
     * 提交审核信息
     * @param token token
     * @param jsonParam json
     * @return result
     */
    Result audit(String token,JSONObject jsonParam);

}
