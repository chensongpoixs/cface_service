package com.exadel.frs.core.trainservice.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.input.ClosedInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;


@Slf4j
public class FileBase64
{

//    public boolean

    public static String FileBase64ToString(String imgurlPath)
    {
        // 读取文件内容为字节数组

//        Path file =  new FILE(imgurlPath));
//        if (file == null)
//        {
//            log.warn("not find file name  =" + imgurlPath);
//            return "";
//        }
        try {
            File file = new File(imgurlPath);
            if (file == null || !file.exists())
            {
                log.warn("not find file name  =" + imgurlPath);
                return "";
            }
            if (file.length() > Integer.MAX_VALUE)
            {
                log.warn("file is too big , bot to read !!!");
                return "";
            }
            byte[] read_buffer = new byte[(int) file.length()];
            FileInputStream in = null;
            in = new FileInputStream(file);
            int len = 0;
            if ((len = in.available()) <= read_buffer.length)
            {
                in.read(read_buffer, 0, len);
            }
            in.close();
//            ClosedInputStream(in);
            //            byte[]  fileContent = Files.readAllBytes(file);
//            if (fileContent == null || fileContent.length < 1)
//            {
//                return "";
//            }
            return  Base64.getEncoder().encodeToString(read_buffer);
        } catch (IOException e) {
            log.warn("FileBase64ToString" + String.valueOf(e));
            return "";
//            throw new RuntimeException(e);
        }

        // 使用Base64编码
//        String base64Encoded = Base64.getEncoder().encodeToString(fileContent);

//        System.out.println("Base64 encoded: " + base64Encoded);
//        return "";
        //return  Base64.getEncoder().encodeToString(fileContent);
    }
}
