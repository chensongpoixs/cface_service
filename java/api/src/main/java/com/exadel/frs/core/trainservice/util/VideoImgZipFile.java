package com.exadel.frs.core.trainservice.util;

import com.exadel.frs.commonservice.httpclient.DeviceInfo;
import com.exadel.frs.commonservice.projection.CaputreImgProjection;
import com.exadel.frs.commonservice.projection.VideoImgStorageProjection;
import com.exadel.frs.core.trainservice.dto.CaputreDto;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
@Slf4j
public class VideoImgZipFile
{

    public void VideoZipFile()
    {

    }
    public static void zipImages(String imgprofixpath, List<VideoImgStorageProjection> imagePaths, String zipFilePath, Map<Integer, DeviceInfo> deviceInfoMap) throws IOException {
        // 创建ZIP输出流
//        String DroneUrl = env.getProperty("environment.drone.url");
        Map<Integer, Integer> hash = new HashMap<>();
        int count = 0;
//        Map<Integer, DeviceInfo> deviceInfoMap = Http_Client.GetDeviceListInfo(DroneUrl + HttpDefault.DRONE_API_DEVICE_LIST);
        try (FileOutputStream fos = new FileOutputStream(zipFilePath);
             ZipOutputStream zos = new ZipOutputStream(fos)) {
            log.info("imagePaths = " + imagePaths.size());
            for (VideoImgStorageProjection videoImgStorageProjection : imagePaths)
            {
                // 读取图片文件并添加到ZIP输出流
                File imageFile = new File(imgprofixpath + videoImgStorageProjection.imgUrl());
                DeviceInfo deviceInfo =   deviceInfoMap.get(videoImgStorageProjection.device_id());
                String new_file_name = "";
                if (null ==  deviceInfo )
                {
                    new_file_name =   "未知设备" + String.valueOf(++count)  ;
                }
                else
                {
                    new_file_name = deviceInfo.getName();
                   // exelRow.setDeviceIdAddress(deviceInfo.getName());
                }
                 Integer hash_value = hash.get(videoImgStorageProjection.timestamp());
                Integer temp = 0;
                if (hash_value == null)
                {
                    hash.put(videoImgStorageProjection.timestamp(), 1);
                    temp = 1;
                }
                else
                {
                    hash.put(videoImgStorageProjection.timestamp(), hash_value + 1);
                    temp = 1 + hash_value;
                }
                SimpleDateFormat file_prefixDate = new SimpleDateFormat("yyyyMMddHHmmss");
//            String new_file_name = file_prefixDate.format(day) + "_" +UUID.randomUUID();
                String master_file_name = file_prefixDate.format(new Date((long) videoImgStorageProjection.timestamp() * 1000))  /*+ ".jpg"*/;

               String file_name_ptr= new_file_name +"_"+ master_file_name + "_" + String.valueOf(temp)   + videoImgStorageProjection.imgUrl().substring( videoImgStorageProjection.imgUrl().lastIndexOf("."));
                log.info("deviceInfoMap size = " +deviceInfoMap.toString()+ "videoImgStorageProjection " + videoImgStorageProjection.toString()+"img name = " + file_name_ptr);
                if (imageFile.exists() && !imageFile.isDirectory()) {
                    addToZipFile(imageFile, zos, file_name_ptr);
                } else {
                    log.info("File not found or is a directory: " +videoImgStorageProjection.imgUrl());
//                    System.err.println("File not found or is a directory: " +imagePaths[2]);
                }
            }
        }
    }


    public static void zipFaceImages(String imgprofixpath, List<CaputreDto> imagePaths, String zipFilePath, Map<Integer, DeviceInfo> deviceInfoMap) throws IOException {
        // 创建ZIP输出流
//        String DroneUrl = env.getProperty("environment.drone.url");
        Map<Long, Integer> hash = new HashMap<>();
        int count = 0;
//        Map<Integer, DeviceInfo> deviceInfoMap = Http_Client.GetDeviceListInfo(DroneUrl + HttpDefault.DRONE_API_DEVICE_LIST);
        try (FileOutputStream fos = new FileOutputStream(zipFilePath);
             ZipOutputStream zos = new ZipOutputStream(fos)) {
            log.info("imagePaths = " + imagePaths.size());
            for (CaputreDto videoImgStorageProjection : imagePaths)
            {
                // 读取图片文件并添加到ZIP输出流
                File imageFile = new File(imgprofixpath + videoImgStorageProjection.getImgUrl());
                DeviceInfo deviceInfo =   deviceInfoMap.get(videoImgStorageProjection.getDevice_id());
                String new_file_name = "";
                if (null ==  deviceInfo )
                {
                    new_file_name =   "未知设备" + String.valueOf(++count)  ;
                }
                else
                {
                    new_file_name = deviceInfo.getName();
                    // exelRow.setDeviceIdAddress(deviceInfo.getName());
                }
                Integer hash_value = hash.get(videoImgStorageProjection.getTimestamp());
                Integer temp = 0;
                if (hash_value == null)
                {
                    hash.put(videoImgStorageProjection.getTimestamp(), 1);
                    temp = 1;
                }
                else
                {
                    hash.put(videoImgStorageProjection.getTimestamp(), hash_value + 1);
                    temp = 1 + hash_value;
                }
                SimpleDateFormat file_prefixDate = new SimpleDateFormat("yyyyMMddHHmmss");
//            String new_file_name = file_prefixDate.format(day) + "_" +UUID.randomUUID();
                String master_file_name = file_prefixDate.format(new Date((long) videoImgStorageProjection.getTimestamp() * 1000))  /*+ ".jpg"*/;

                String file_name_ptr= new_file_name +"_"+ master_file_name + "_" + String.valueOf(temp)   + videoImgStorageProjection.getImgUrl().substring( videoImgStorageProjection.getImgUrl().lastIndexOf("."));
                log.info("deviceInfoMap size = " +deviceInfoMap.toString()+ "videoImgStorageProjection " + videoImgStorageProjection.toString()+"img name = " + file_name_ptr);
                if (imageFile.exists() && !imageFile.isDirectory()) {
                    addToZipFile(imageFile, zos, file_name_ptr);
                } else {
                    log.info("File not found or is a directory: " +videoImgStorageProjection.getImgUrl());
//                    System.err.println("File not found or is a directory: " +imagePaths[2]);
                }
            }
        }
    }

    public static void zipFaceProImages(String imgprofixpath, List<CaputreImgProjection> imagePaths, String zipFilePath, Map<Integer, DeviceInfo> deviceInfoMap) throws IOException {
        // 创建ZIP输出流
//        String DroneUrl = env.getProperty("environment.drone.url");
        Map<Long, Integer> hash = new HashMap<>();
        int count = 0;
//        Map<Integer, DeviceInfo> deviceInfoMap = Http_Client.GetDeviceListInfo(DroneUrl + HttpDefault.DRONE_API_DEVICE_LIST);
        try (FileOutputStream fos = new FileOutputStream(zipFilePath);
             ZipOutputStream zos = new ZipOutputStream(fos)) {
            log.info("imagePaths = " + imagePaths.size());
            for (CaputreImgProjection videoImgStorageProjection : imagePaths)
            {
                // 读取图片文件并添加到ZIP输出流
                File imageFile = new File(imgprofixpath + videoImgStorageProjection.imgUrl());
                DeviceInfo deviceInfo =   deviceInfoMap.get(videoImgStorageProjection.device_id());
                String new_file_name = "";
                if (null ==  deviceInfo )
                {
                    new_file_name =   "未知设备" + String.valueOf(++count)  ;
                }
                else
                {
                    new_file_name = deviceInfo.getName();
                    // exelRow.setDeviceIdAddress(deviceInfo.getName());
                }
                Integer hash_value = hash.get(videoImgStorageProjection.timestamp());
                Integer temp = 0;
                if (hash_value == null)
                {
                    hash.put((long) videoImgStorageProjection.timestamp(), 1);
                    temp = 1;
                }
                else
                {
                    hash.put((long) videoImgStorageProjection.timestamp(), hash_value + 1);
                    temp = 1 + hash_value;
                }
                SimpleDateFormat file_prefixDate = new SimpleDateFormat("yyyyMMddHHmmss");
//            String new_file_name = file_prefixDate.format(day) + "_" +UUID.randomUUID();
                String master_file_name = file_prefixDate.format(new Date((long) videoImgStorageProjection.timestamp() * 1000))  /*+ ".jpg"*/;

                String file_name_ptr= new_file_name +"_"+ master_file_name + "_" + String.valueOf(temp)   + videoImgStorageProjection.imgUrl().substring( videoImgStorageProjection.imgUrl().lastIndexOf("."));
                log.info("deviceInfoMap size = " +deviceInfoMap.toString()+ "videoImgStorageProjection " + videoImgStorageProjection.toString()+"img name = " + file_name_ptr);
                if (imageFile.exists() && !imageFile.isDirectory()) {
                    addToZipFile(imageFile, zos, file_name_ptr);
                } else {
                    log.info("File not found or is a directory: " +videoImgStorageProjection.imgUrl());
//                    System.err.println("File not found or is a directory: " +imagePaths[2]);
                }
            }
        }
    }

    private static void addToZipFile(File file, ZipOutputStream zos, String new_file_name) throws IOException {
        // 创建输入流读取文件内容
        try (FileInputStream fis = new FileInputStream(file)) {
            // 创建ZIP条目
//            System.out.println("String.valueOf(count) = " + String.valueOf(count));
            ZipEntry zipEntry = new ZipEntry(new_file_name);
            zos.putNextEntry(zipEntry);

            // 将文件内容写入ZIP输出流
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) >= 0) {
                zos.write(buffer, 0, length);
            }

            // 关闭当前ZIP条目
            zos.closeEntry();
        }
    }
}
