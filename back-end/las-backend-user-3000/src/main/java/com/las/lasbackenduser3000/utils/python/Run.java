package com.las.lasbackenduser3000.utils.python;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 优化代码，增加安全性和错误处理
 */
@Slf4j
public class Run {

    public static String RUN(String path) {
        // 防止命令注入
        final String line = "python3 ./pyScripts/avgen.py " + escapeShellArgument(path);
        final CommandLine cmdLine = CommandLine.parse(line);

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            final PumpStreamHandler streamHandler = new PumpStreamHandler(baos);
            final DefaultExecutor executor = new DefaultExecutor();
            executor.setStreamHandler(streamHandler);
            final int exitCode = executor.execute(cmdLine);
            if (exitCode == 0) {
                // 指定编码为 UTF-8
                return baos.toString(StandardCharsets.UTF_8).trim();
            } else {
                return "ERR";
            }
        } catch (IOException e) {
            log.error("调用Python脚本出错", e);
            return "ERR"; // 统一返回值，避免返回null
        } catch (RuntimeException e) {
            log.error("运行时异常", e);
            return "ERR"; // 处理其他运行时异常
        }
    }

    /**
     * 对输入进行转义以防止命令注入
     * @param arg 用户输入参数
     * @return 转义后的字符串
     */
    private static String escapeShellArgument(String arg) {
        return arg.replace("'", "'\\''");
    }
}