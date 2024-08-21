package com.las.lasbackenduser3000.task;

import com.las.lasbackenduser3000.model.FileUpload;
import com.las.lasbackenduser3000.model.User;
import com.las.lasbackenduser3000.service.api.impl.FileUploadServiceImpl;
import com.las.lasbackenduser3000.service.db.impl.UserServiceImpl;
import com.las.lasbackenduser3000.utils.python.Run;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sunyinuo
 */
@Component
@Slf4j
public class MinecraftAvatarGenerator {
    public final UserServiceImpl userService;
    public final FileUploadServiceImpl fileUploadService;

    public MinecraftAvatarGenerator(UserServiceImpl userService, FileUploadServiceImpl fileUploadService) {
        this.userService = userService;
        this.fileUploadService = fileUploadService;
    }

    @Scheduled(cron = "0 * * * * ?")
    public void autoGetMinecraftAvatar() throws IOException {
        log.info("定时任务(autoGetMinecraftAvatar)开始获取所有用户头像");

        //所有用户mc id
        List<String> allUsersMinecraftId = new ArrayList<>();
        //所有用户
        List<User> userList = userService.getUserList();

        //遍历所有用户
        for (User user : userList) {
            //添加用户mc id至集合
            allUsersMinecraftId.add(user.getMinecraftName());
        }

        //遍历所有用户mc id
        for (String id : allUsersMinecraftId) {
            log.info(id);
            //运行python脚本,获取用户头像
            String result = Run.RUN(id);
            //判断是否获取成功
            if (result != null && !result.equals("ERR")) {
                //获取头像 file对象
                File file = new File("avatar/AVATAR_" + id + ".png");
                //创建fileInputStream，转换
                FileInputStream fileInputStream = new FileInputStream(file);
                //创建MultipartFile
                MultipartFile multipartFile = new MockMultipartFile("AVATAR_" + id + ".png", file.getName(), "image/png", fileInputStream);

                //上传
                fileUploadService.uploadMinecraftAvatar(new MultipartFile[]{multipartFile}, id);
            }
        }

    }
}