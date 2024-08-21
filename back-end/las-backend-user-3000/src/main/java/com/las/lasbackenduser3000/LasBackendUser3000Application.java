package com.las.lasbackenduser3000;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.core.io.ClassPathResource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * 用户模块
 * @author martin
 */
@SpringBootApplication
@EnableScheduling
public class LasBackendUser3000Application {

    public static void main(String[] args) {
        copyPythonScriptToAppDirectory();
        SpringApplication.run(LasBackendUser3000Application.class, args);
    }


    private static void copyPythonScriptToAppDirectory() {
        try {
            // 获取资源文件
            ClassPathResource resource = new ClassPathResource("minecraft/avgen.py");
            Path pyDir = Path.of("./pyScripts");
            if (!Files.exists(pyDir)) {
                Files.createDirectory(pyDir);
            }
            Path tempFile = pyDir.resolve("avgen.py");
            // 检查文件是否存在
            if (!Files.exists(tempFile)) {
                Files.copy(resource.getInputStream(), tempFile);
            }

            System.out.println("Python script copied to: " + tempFile.toAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
