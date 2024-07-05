package com.las.lasbackendbulletin5000.service.api;

import com.alibaba.fastjson.JSONObject;
import result.Result;

/**
 * @author sunyinuo
 */
public interface UploadService {

    /**
     * 上传
     * @param token token
     * @param jsonObject json
     * @return 统一
     */
    Result upload(String token,JSONObject jsonObject);
}
