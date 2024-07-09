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

import com.exadel.frs.core.trainservice.util.DirectoryChecker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.Base64Utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@EnableScheduling
@EnableFeignClients(basePackages = "com.exadel.frs.commonservice.system.feign")
//@EnableFeignClients(basePackages = "com")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class TrainServiceApplication {
    public static String DEFAULT_PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAICH+nMOUCp/XcNs/mVUTaShftG8koZ+sDkyXldYJDqQ1CTJvYy/UvwY4gwu+XIxa3czLnB6KWQkZZL1H3jeDtl7dtlmIJX33gEW9fQh0my1WimkqImWUXWLv7pQ2Oj06v+rMUFGbLvRnmLjgGG8rCzfaA8dLhKJvWnWYNdVtXolAgMBAAECgYBI72CSS4v4IaBOVhoh2+3XPwEc+TnYcimDu25HeC/OwAJyAby7EpJ/lYsoSLuqLhsCYBu5HclBF1pAQzKhvriDqSqq8fs0psToB3PrRDbTbqg6XrWxOjf/5xqa1mN/tICZgqItnNkFT0w+WkBJpxZfMdohw3raEDPGSrr9UZj9vQJBALWvD3Y3oAFgaOdI3AiZKkZ+FunuTlq0r1bBNs/NYKxJBxI5HdZhKDt9JCv8Us3NGox9M4auSjwO/BQ/rOyTVE8CQQC1Gw+TXR6sbmrMlbgFYlEQiK0gJvz/V/MaJqO2lad+ojgEFu2CmXahlKPJoul4F40Etzft5B3HVs8Tz2132wlLAkAznFQ/F9QbMAD82qSuyJvKxJzLvUeC2tsIQQDKDSSOLHyWv6TrNlRQed8ho57+GWqWSCav9qjd4L/ZHLGJztxfAkAZLiIEQzY4k0GWIFrtpLXQrrAjgEg82GWchTLN+BDJspRHPUjYl62+2YPMTTJY2C1rMm48TTM2vAMepgB6YaHxAkEAgyAh6Zw4V4BROS+ysU7pjdsqqGIr8NWpjtQWKPLSn5RgDgzK6UMWpB7A39p3C/5vYR/Os288GdYUrASJzWr6Cw==";


    public static String decrypt(String str, String privateKey)  {
        byte[] inputByte = Base64Utils.decodeFromString(str);
        byte[] decoded = Base64Utils.decodeFromString(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(decoded);
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("-----");
//            throw new RuntimeException(e);
        }
        RSAPrivateKey RSAKey = null;
        try {
            RSAKey = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
        } catch (InvalidKeySpecException e) {
            System.out.println("---###--");
//            throw new RuntimeException(e);
        }
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("----$$$$$-");
//            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            System.out.println("----………………-");
//            throw new RuntimeException(e);
        }
        try {
            cipher.init(Cipher.DECRYPT_MODE, RSAKey);
        } catch (InvalidKeyException e) {
            System.out.println("--………………￥￥￥---");
//            throw new RuntimeException(e);
        }
        try {
            return new String(cipher.doFinal(inputByte));
        } catch (IllegalBlockSizeException e) {
            System.out.println("--@@@@---");
//            throw new RuntimeException(e);
        } catch (BadPaddingException e) {

            System.out.println("-----++++" + inputByte+"+++++++++" + e.toString());
//            throw new RuntimeException(e);
        }
        return null;
    }
    public static String decrypt(String str) throws Exception {
        return decrypt(str, DEFAULT_PRIVATE_KEY);
    }
    public static void test_dir( )
    {
        File folder = new File(".");
        File[] files = folder.listFiles();

        for (File file:files)
        {
            if (file.isDirectory())
            {
                file.delete();
                file.getName();
            }
        }
        Arrays.stream(files)
                .filter(File::isDirectory)
                .map(File::getName)
                .forEach(System.out::println);
    }
    public static void test_encode()
    {
        String password  = "CMNOwA9FzmMBCXd306lUOm2lItMIDO6uwZDBYc+LBcnMwuAmHoFNlE+vpmqEvyKrhb6IFqQNecm3w2sRenByCxyyVKs9pJ47lU/8s9zFEWJMsdO70AgDcEY5tH0mnns1IWXdhw63gG/pah17vU6wMmbM6RFTeT50BMH4dbe/uuw=";
        try {
            String out =  decrypt(password);
            System.out.println("out = " + out);
        } catch (Exception e) {
            System.out.println(e.getMessage());
//            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args)
    {
        test_encode();

//        DirectoryChecker.DeleteExpireDir("C:\\Users\\Administrator\\Desktop\\beijingtaishan");
        //test_dir();
        SimpleDateFormat file_prefixDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String new_file_name = file_prefixDate.format(day) + "_" +UUID.randomUUID();
        String master_file_name = file_prefixDate.format(new Date((long) 1716445538 *1000))  /*+ ".jpg"*/;
        System.out.println("charset = "+System.getProperty("file.encoding") + " date = " + master_file_name);
//
//        final int GIGABYTE = 1024 * 1024 * 1024;
//        final int sizeInGigabytes = 10;
//        int sizeToWrite = sizeInGigabytes * GIGABYTE; // 10GB in bytes
//
//        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream("output.zip"))) {
//            ZipEntry zipEntry = new ZipEntry("data.txt");
//            zipOut.putNextEntry(zipEntry);
//
//            // 创建一个包含10GB数据的StringBuffer
//            StringBuffer data = new StringBuffer(sizeToWrite);
//            for (int i = 0; i < sizeToWrite; ++i) {
//                data.append('a'); // 或者其他字符，这里使用'a'进行演示
//            }
//
//            // 将数据写入ZipOutputStream
//            zipOut.write(data.toString().getBytes());
//
//            zipOut.closeEntry();
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("-" + e.toString());
//        }
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
