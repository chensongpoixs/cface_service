package com.exadel.frs.core.trainservice.util;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;


@Slf4j
public class FileBase64
{

//    public boolean

    public static String FileBase64ToString(String imgurlPath)
    {
        // 读取文件内容为字节数组
        byte[] fileContent = new byte[0];
        try {
            fileContent = Files.readAllBytes(Paths.get(imgurlPath));
        } catch (IOException e) {
            log.info("FileBase64ToString" + String.valueOf(e));
            return "";
//            throw new RuntimeException(e);
        }

        // 使用Base64编码
//        String base64Encoded = Base64.getEncoder().encodeToString(fileContent);

//        System.out.println("Base64 encoded: " + base64Encoded);

        return  Base64.getEncoder().encodeToString(fileContent);
    }
}
