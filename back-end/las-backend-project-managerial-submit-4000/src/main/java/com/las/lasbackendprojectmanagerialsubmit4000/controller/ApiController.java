package com.las.lasbackendprojectmanagerialsubmit4000.controller;

import com.las.lasbackendprojectmanagerialsubmit4000.aop.privilegeControl.PrivilegeControl;
import com.las.lasbackendprojectmanagerialsubmit4000.service.api.impl.FileUploadServiceImpl;
import com.las.lasbackendprojectmanagerialsubmit4000.service.api.impl.GetFileServiceImpl;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import result.Result;

/**
 * api控制器
 * @author sunyinuo
 */
@RestController
@RequestMapping("/projectmanagerial/api")
public class ApiController {

    private final FileUploadServiceImpl fileUploadService;
    private final GetFileServiceImpl getFileService;

    public ApiController(FileUploadServiceImpl fileUploadService, GetFileServiceImpl getFileService) {
        this.fileUploadService = fileUploadService;
        this.getFileService = getFileService;
    }


    /**
     * 接收文件
     * @param files 文件
     * @return 文件信息
     */
    @PostMapping("/fileUpload")
    @PrivilegeControl(roleIdList = {1})
    public Result fileUpload(@RequestHeader(value = "token") String token,
                             @RequestParam("file") MultipartFile[] files ,
                             @RequestParam("projectName") String projectName){

        return fileUploadService.fileUpload(files,projectName);
    }

    /**
     * 获取文件
     * @param id id
     * @return 数据
     */
    @GetMapping("/getFile/{id}")
    public byte[] getFile(@PathVariable("id") String id) {
        return getFileService.getFile(id);
    }


}
