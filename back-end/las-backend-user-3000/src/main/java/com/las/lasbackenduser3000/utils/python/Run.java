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
        int maxRetries = 3;

        // 使用新的线程处理重试逻辑
        final StringBuilder result = new StringBuilder("ERR");
        Thread retryThread = new Thread(() -> {
            int attempts = 0;

            while (attempts < maxRetries) {
                attempts++;
                try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                    final PumpStreamHandler streamHandler = new PumpStreamHandler(baos);
                    final DefaultExecutor executor = new DefaultExecutor();
                    executor.setStreamHandler(streamHandler);
                    final int exitCode = executor.execute(cmdLine);
                    if (exitCode == 0) {
                        // 指定编码为 UTF-8
                        result.setLength(0); // 清空之前的内容
                        result.append(baos.toString(StandardCharsets.UTF_8).trim());
                        break;
                    } else {
                        log.warn("Attempt {} failed with exit code {}", attempts, exitCode);
                    }
                } catch (IOException e) {
                    log.error("调用Python脚本出错, 尝试 {} 次", attempts, e);
                } catch (RuntimeException e) {
                    log.error("运行时异常, 尝试 {} 次", attempts, e);
                }
            }
        });

        retryThread.start();
        try {
            retryThread.join(); // 等待线程完成
        } catch (InterruptedException e) {
            log.error("线程被中断", e);
        }

        return result.toString(); // 返回结果
    }

    /*
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

     */

    /**
     * 对输入进行转义以防止命令注入
     * @param arg 用户输入参数
     * @return 转义后的字符串
     */
    private static String escapeShellArgument(String arg) {
        return arg.replace("'", "'\\''");
    }
}