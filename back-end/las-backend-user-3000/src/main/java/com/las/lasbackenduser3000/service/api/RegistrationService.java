package com.las.lasbackenduser3000.service.api;

import com.alibaba.fastjson.JSONObject;
import result.Result;

/**
 * @author sunyinuo
 */
public interface RegistrationService {
    /**
     * 注册
     * @param jsonParam json
     * @return 统一返回
     */
    Result registration(JSONObject jsonParam) throws InterruptedException;

}
