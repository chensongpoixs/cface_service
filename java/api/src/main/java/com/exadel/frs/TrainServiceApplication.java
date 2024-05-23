/*
 * Copyright (c) 2020 the original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.exadel.frs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.text.SimpleDateFormat;
import java.util.Date;

@EnableScheduling
@EnableFeignClients(basePackages = "com.exadel.frs.commonservice.system.feign")
//@EnableFeignClients(basePackages = "com")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class TrainServiceApplication {

    public static void main(String[] args)
    {

        SimpleDateFormat file_prefixDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String new_file_name = file_prefixDate.format(day) + "_" +UUID.randomUUID();
        String master_file_name = file_prefixDate.format(new Date((long) 1716445538 *1000))  /*+ ".jpg"*/;
        System.out.println("charset = "+System.getProperty("file.encoding") + " date = " + master_file_name);
        SpringApplication.run(TrainServiceApplication.class, args);
    }
}


//
//import java.io.*;
//import java.nio.file.*;
//import java.util.zip.*;
//
//public class TrainServiceApplication {
//
//    public static void main(String[] args)
//    {
//        if (true)
//        {
//            String str = "img/image1.png";
//
//            System.out.println(str.substring(str.lastIndexOf('.')));
//
//
//            return;
//        }
//
//
//        // 定义图片文件路径
//        String[] imagePaths = {
//                "img/image1.png",
//                "img/image2.png",
//                "img/image3.png",
//                "img/image1.png",
//                "img/image2.png",
//                "img/image3.png",
//                "img/image1.png",
//                "img/image2.png",
//                "img/image3.png",
//                "img/image1.png",
//                "img/image2.png",
//                "img/image3.png",
//                "img/image1.png",
//                "img/image2.png",
//                "img/image3.png",
//                "img/image1.png",
//                "img/image2.png",
//                "img/image3.png"
//        };
//
//        // 定义输出ZIP文件路径
//        String zipFilePath = "img/output.zip";
//
//        // 调用方法将图片打包成ZIP文件
//        try {
//            zipImages(imagePaths, zipFilePath);
//            System.out.println("Images successfully zipped into " + zipFilePath);
//        } catch (IOException e) {
//            System.err.println("Error zipping images: " + e.getMessage());
//        }
//    }
//
//    public static void zipImages(String[] imagePaths, String zipFilePath) throws IOException {
//        // 创建ZIP输出流
//        int count = 0;
//        try (FileOutputStream fos = new FileOutputStream(zipFilePath);
//             ZipOutputStream zos = new ZipOutputStream(fos)) {
//
////            for (String imagePath : imagePaths)
//            while(count < 100000)
//            {
//                // 读取图片文件并添加到ZIP输出流
//                File imageFile = new File(imagePaths[2]);
//                if (imageFile.exists() && !imageFile.isDirectory()) {
//                    addToZipFile(imageFile, zos, ++count);
//                } else {
//                    System.err.println("File not found or is a directory: " +imagePaths[2]);
//                }
//            }
//        }
//    }
//
//    private static void addToZipFile(File file, ZipOutputStream zos, int count) throws IOException {
//        // 创建输入流读取文件内容
//        try (FileInputStream fis = new FileInputStream(file)) {
//            // 创建ZIP条目
//            System.out.println("String.valueOf(count) = " + String.valueOf(count));
//            ZipEntry zipEntry = new ZipEntry(String.valueOf(count)+ file.getName() );
//            zos.putNextEntry(zipEntry);
//
//            // 将文件内容写入ZIP输出流
//            byte[] buffer = new byte[1024];
//            int length;
//            while ((length = fis.read(buffer)) >= 0) {
//                zos.write(buffer, 0, length);
//            }
//
//            // 关闭当前ZIP条目
//            zos.closeEntry();
//        }
//    }
//}
