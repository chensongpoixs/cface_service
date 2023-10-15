package com.exadel.frs.core.trainservice.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Slf4j
public class MultipartFileToFileUtils
{
    /**
     * @param file
     * @param targetDirPath 存储MultipartFile文件的目标文件夹
     * @return 文件的存储的绝对路径
     */
    public static String saveMultipartFile(MultipartFile file, String apiKey, String targetDirPath) {

        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            return null;
        } else {
            Date day = new Date();
            SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd");
            String file_prefix =    sdf.format(day) +"/" + apiKey + "/";

//        static long image_count = 0;
            SimpleDateFormat  file_prefixDate = new SimpleDateFormat("yyyyMMddHHmmss");
            String new_file_name = file_prefixDate.format(day) + "_" +UUID.randomUUID();
            /*获取文件原名称*/
            String originalFilename = file.getOriginalFilename();
            /*获取文件格式*/
            String fileFormat = originalFilename.substring(originalFilename.lastIndexOf("."));

//            String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
            toFile = new File(targetDirPath + file_prefix + File.separator + new_file_name + fileFormat);

            String absolutePath = null;
            try {
                absolutePath = toFile.getCanonicalPath();

                /*判断路径中的文件夹是否存在，如果不存在，先创建文件夹*/
                String dirPath = absolutePath.substring(0, absolutePath.lastIndexOf(File.separator));
                File dir = new File(dirPath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                InputStream ins = file.getInputStream();

                inputStreamToFile(ins, toFile);
                ins.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return file_prefix + new_file_name + fileFormat;
//            return absolutePath;
        }

    }

    //获取流文件
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream  os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除本地临时文件
     *
     * @param file
     */
    public static void deleteTempFile(File file) {
        if (file != null) {
            File del = new File(file.toURI());
            del.delete();
        }
    }






    public static boolean  buildSubImage(String img_file_path, String sub_img_file_path, int x, int y, int w, int h)
    {
        try {
            // 读取原始图像
//            File input = new File(img_file_path);
            BufferedImage originalImage = ImageIO.read(new File(img_file_path));

            // Assume the subtracted area is at (100, 100) with width 200 and height 200
//            int x = 100;
//            int y = 100;
//            int width = 200;
//            int height = 200;
            // 定义要裁剪的矩形区域
            Rectangle cropRect = new Rectangle(x, y, w, h);
            // 获取裁剪后的图像
            BufferedImage croppedImage = originalImage.getSubimage(cropRect.x, cropRect.y, cropRect.width, cropRect.height);
            File dir = new File(sub_img_file_path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            // 将裁剪后的图像写入到文件
            ImageIO.write(croppedImage, "jpg", dir);
            // Create a new BufferedImage that represents the subtracted area
//            BufferedImage subtraction = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
//            Graphics2D g = subtraction.createGraphics();
//            g.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN));
//            g.drawImage(image, x, y, w, h, null);
//            g.dispose();
//
//            // Write the subtracted image to a file
//            File output = new File(sub_img_file_path);
//            ImageIO.write(subtraction, "jpg", output);
            log.info(" save sub img " +x +", "+ y +","+ w +"," + h +", img url = " + sub_img_file_path + ", OK !!!");
        } catch (IOException e) {
            log.error( e.getMessage());
            log.info("======================================================================");
            return false;
        }
        return true;
    }

}
