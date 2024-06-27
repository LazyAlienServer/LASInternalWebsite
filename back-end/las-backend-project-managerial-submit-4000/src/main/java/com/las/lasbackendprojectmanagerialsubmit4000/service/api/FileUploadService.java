package com.las.lasbackendprojectmanagerialsubmit4000.service.api;

import org.springframework.web.multipart.MultipartFile;
import result.Result;

/**
 * @author sunyinuo
 */
public interface FileUploadService {

    /**
     * 文件上传
     * @param files file
     * @param projectName 项目名称
     * @return 统一返回
     */
    Result fileUpload (MultipartFile[] files, String projectName);

}
