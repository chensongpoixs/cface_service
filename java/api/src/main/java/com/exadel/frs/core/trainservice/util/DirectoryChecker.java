package com.exadel.frs.core.trainservice.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Slf4j
public class DirectoryChecker
{
    public static boolean checkDirectory(String directoryPath) {
        Path path = Paths.get(directoryPath);
        return Files.exists(path) && Files.isDirectory(path);
    }

    public static void mkdirDirectory(String directoryPath)
    {
        // 指定要检查和创建的目录路径
//        String directoryPath = "path/to/your/directory";

        // 创建File对象
        File directory = new File(directoryPath);

        // 如果目录不存在，就创建它
        if (!directory.exists()) {
            boolean result = directory.mkdirs();
            if (result)
            {
                log.info("dir create succe : " +directoryPath);
//                System.out.println("目录创建成功：" + directoryPath);
            } else {
//                System.out.println("目录创建失败：" + directoryPath);
                log.info("dir create failed !!! : " +directoryPath);
            }
        } else {
//            System.out.println("目录已存在：" + directoryPath);
            log.info("dir  save : " +directoryPath);
        }
    }
}
