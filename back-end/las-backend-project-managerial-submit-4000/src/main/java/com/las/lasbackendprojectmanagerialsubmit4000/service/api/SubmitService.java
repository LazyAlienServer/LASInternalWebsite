package com.las.lasbackendprojectmanagerialsubmit4000.service.api;

import com.alibaba.fastjson.JSONObject;
import result.Result;

/**
 * @author sunyinuo
 */
public interface SubmitService {

    /**
     * 项目报备提交
     * @param jsonParam jsn
     * @return 统一返回
     */
    Result submit(JSONObject jsonParam);

}
