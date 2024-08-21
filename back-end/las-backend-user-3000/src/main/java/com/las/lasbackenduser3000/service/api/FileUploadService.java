package com.las.lasbackenduser3000.service.api;

import org.springframework.web.multipart.MultipartFile;
import result.Result;

/**
 * @author sunyinuo
 */
public interface FileUploadService {

    /**
     * 文件上传
     * @param files file
     * @param token token
     * @return 统一返回
     */
    Result fileUpload (MultipartFile[] files, String token);

    /**
     * 上传minecraft头像
     * @param files file
     * @param id id
     * @return 统一返回
     */
    Result uploadMinecraftAvatar (MultipartFile[] files,String id);
}
