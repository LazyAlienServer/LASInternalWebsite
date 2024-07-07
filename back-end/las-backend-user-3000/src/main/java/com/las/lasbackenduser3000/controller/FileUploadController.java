package com.las.lasbackenduser3000.controller;

import com.las.lasbackenduser3000.aop.privilegeControl.PrivilegeControl;
import com.las.lasbackenduser3000.service.api.impl.FileUploadServiceImpl;
import com.las.lasbackenduser3000.service.api.impl.GetFileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import result.Result;

/**
 * @author sunyinuo
 */
@RestController
@RequestMapping("/usersignin/api")
public class FileUploadController {
    public final FileUploadServiceImpl fileUploadService;
    public final GetFileServiceImpl getFile;

    public FileUploadController(FileUploadServiceImpl fileUploadService, GetFileServiceImpl getFile) {
        this.fileUploadService = fileUploadService;
        this.getFile = getFile;
    }

    /**
     * 接收文件
     * @param files 文件
     * @return 文件信息
     */
    @PostMapping("/fileUpload")
    @PrivilegeControl(roleIdList = {1})
    public Result fileUpload(@RequestHeader(value = "token") String token,
                             @RequestParam("file") MultipartFile[] files ){

        return fileUploadService.fileUpload(files,token);
    }

    /**
     * 获取文件
     * @param id id
     * @return 数据
     */
    @GetMapping("/getFile/{id}")
    public byte[] getFile(@PathVariable("id") String id) {
        return getFile.getFile(id);
    }
}
