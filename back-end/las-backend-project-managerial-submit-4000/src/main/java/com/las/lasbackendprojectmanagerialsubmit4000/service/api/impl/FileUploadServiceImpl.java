package com.las.lasbackendprojectmanagerialsubmit4000.service.api.impl;

import com.las.lasbackendprojectmanagerialsubmit4000.model.FileUpload;
import com.las.lasbackendprojectmanagerialsubmit4000.service.api.FileUploadService;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import result.Result;
import result.ResultEnum;
import result.ResultUtil;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author sunyinuo
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {

    private final MongoTemplate mongoTemplate;


    public FileUploadServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    /**
     * 文件上传
     * @param files       file
     * @param projectName 项目名称
     * @return 统一返回
     */
    @Override
    public Result fileUpload(MultipartFile[] files, String projectName) {
        FileUpload fileUpload = new FileUpload();
        for (MultipartFile file : files) {

            //文件名
            fileUpload.setName(file.getOriginalFilename());
            //时间
            fileUpload.setCreatedTime(LocalDateTime.now());
            //内容
            try {
                fileUpload.setContent(new Binary(file.getBytes()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //类型
            fileUpload.setContentType(file.getContentType());
            //大小
            fileUpload.setSize(file.getSize());
            //项目名
            fileUpload.setProjectName(projectName);
        }

        //返回存入数据库的信息
        return ResultUtil.result(ResultEnum.SUCCESS.getCode(), mongoTemplate.save(fileUpload),null);
    }
}
