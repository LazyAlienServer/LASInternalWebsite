package com.las.lasbackenduser3000.utils.python;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author sunyinuo
 */
@Slf4j
public class Run {

    public static String RUN(String path){
        final String line = "python3 ./pyScripts/avgen.py " + path;
        final CommandLine cmdLine = CommandLine.parse(line);

        try (final ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            final PumpStreamHandler streamHandler = new PumpStreamHandler(baos);

            final DefaultExecutor executor = new DefaultExecutor();
            executor.setStreamHandler(streamHandler);

            final int exitCode = executor.execute(cmdLine);

            if (exitCode == 0){
                return baos.toString().trim();
            }else {
                return "ERR";
            }
        }
        catch (final IOException e) {
            log.error("调用Python脚本出错", e);
        }

        return null;
    }

}
