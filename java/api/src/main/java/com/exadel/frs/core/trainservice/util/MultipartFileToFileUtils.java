package com.exadel.frs.core.trainservice.util;

import lombok.extern.slf4j.Slf4j;
import net.lingala.zip4j.model.FileHeader;
import org.nd4j.common.io.Assert;
import org.nd4j.common.io.StringUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Slf4j
public class MultipartFileToFileUtils
{

    public static  String getIsoToUtf8(String str)
    {
//        if (StringUtils.isBlank(str))
//        {
//            return "";
//        }

        String newStr = "";
        newStr = new String(str.getBytes(StandardCharsets.US_ASCII));
        return newStr;
    }

    @SuppressWarnings("unchecked")
    private static String getEncoding(String path) throws Exception {
        String encoding = "GBK";
        net.lingala.zip4j.core.ZipFile zipFile = new net.lingala.zip4j.core.ZipFile(path);
        zipFile.setFileNameCharset(encoding);
        List<FileHeader> list = zipFile.getFileHeaders();
        for (int i = 0; i < list.size(); i++) {
            FileHeader fileHeader = list.get(i);
            String fileName = fileHeader.getFileName();
            if (isMessyCode(fileName)) {
                encoding = "UTF-8";
                break;
            }
        }
        return encoding;
    }

    private static boolean isMessyCode(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            // 当从Unicode编码向某个字符集转换时，如果在该字符集中没有对应的编码，则得到0x3f（即问号字符?）
            // 从其他字符集向Unicode编码转换时，如果这个二进制数在该字符集中没有标识任何的字符，则得到的结果是0xfffd
            if ((int) c == 0xfffd) {
                // 存在乱码
                return true;
            }
        }
        return false;
    }
    public static String decodeZipEntryName(String entryName) {
        // 尝试使用 UTF-8 解码
        return new String(entryName.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }

    public static java.util.List<MultipartFile> UnZip(MultipartFile multipartFiles){
        String originalFilename = multipartFiles.getOriginalFilename();
        //是否是zip文件类型
        if (!originalFilename.endsWith(".zip")){
            throw new RuntimeException(originalFilename+"文件格式错误！请上传.zip格式文件");
        }
        //解压
        List<MultipartFile> multipartFileList = new ArrayList<>();
        ZipInputStream zipInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        String zipEntryFile;
        try {
//            System.setProperties("sun.zip.encoding", System.getProperty("sun.jnu.encoding"));
//            String encoding;
//            encoding = getEncoding(originalFilename);
            zipInputStream = new ZipInputStream(multipartFiles.getInputStream(), Charset.forName("GBK")/*, Charset.forName("UTF-8")*//*, Charset.forName(encoding)*/);
            bufferedInputStream = new BufferedInputStream(zipInputStream);
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry())!=null)
            {
//                String entryName = zipEntry.getName();
//                zipEntryFile = new String(entryName.getBytes("UTF-8"), "UTF-8");

                zipEntryFile =  /*decodeZipEntryName*/(zipEntry.getName()) ;
                //文件名称为空
                Assert.notNull(zipEntryFile,"压缩文件中子文件的名字格式不正确");
//                String originalFilename = zipEntryFile.getOriginalFilename();
                /*获取文件格式*/
                String fileFormat = zipEntryFile.substring(zipEntryFile.lastIndexOf("."));
                //每个文件的流
                byte[] bytes = new byte[(int)zipEntry.getSize()];
                bufferedInputStream.read(bytes,0,(int)zipEntry.getSize());
                InputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                MultipartFile multipartFile = new MockMultipartFile((zipEntryFile), (zipEntryFile),/*"JPG"*/ fileFormat.substring(1, fileFormat.length()),byteArrayInputStream);
//                System.out.println(str);
//                String str1 = new String(zipEntryFile.getBytes("ISO-8859-1"), "utf-8");
//                log.info("str1 = " + str1);
//                String str2 = new String(zipEntryFile.getBytes("gb2312"), "utf-8");
//                System.out.println(str2);
//                log.info("str2 = " + str2);
//                String str3 = new String(zipEntryFile.getBytes("gbk"), "utf-8");
//                System.out.println(str3);
//                log.info("str3 = " + str3);
                log.info("+++++++++++++++++"+ multipartFile.getOriginalFilename());
//                MockMultipartFile();
                multipartFileList.add(multipartFile);
                byteArrayInputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (bufferedInputStream!=null){
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (zipInputStream!=null){
                try {
                    zipInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return multipartFileList;
    }

    /**
     * @param file
     * @param targetDirPath 存储MultipartFile文件的目标文件夹
     * @return 文件的存储的绝对路径
     */
    public static String saveMultipartFile(MultipartFile file, String targetDirPath, String path_profix, String new_file_name) {

        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            log.info("saveMultipartFile===================");
            return null;
        } else {
//            Date day = new Date();
//            SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd");
//            String file_prefix =    sdf.format(day) +"/" + apiKey + "/";
//
////        static long image_count = 0;
//            SimpleDateFormat  file_prefixDate = new SimpleDateFormat("yyyyMMddHHmmss");
//            String new_file_name = file_prefixDate.format(day) + "_" +UUID.randomUUID();
            /*获取文件原名称*/
            String originalFilename = file.getOriginalFilename();
            /*获取文件格式*/
            String fileFormat = originalFilename.substring(originalFilename.lastIndexOf("."));

//            String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
            toFile = new File(targetDirPath   +path_profix + File.separator + new_file_name + fileFormat);

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
                log.info(e.getMessage());
            }
            return path_profix + new_file_name + fileFormat;
//            return absolutePath;
        }

//        log.info("============+++++");
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





    public static int diff_rect(int image, int sub_image)
    {
        if (sub_image < 0)
        {
            return 0;
        }
        if (sub_image > image)
        {
            return image;
        }
        return sub_image;
    }

    public static boolean  buildSubImage(String img_file_path, String sub_img_file_path, int x, int y, int w, int h, String format)
    {
        try {
            // 读取原始图像
//            File input = new File(img_file_path);
            BufferedImage originalImage = ImageIO.read(new File(img_file_path));

            originalImage.getWidth();
            originalImage.getHeight();

           // double v = x - ((w - x) / 10 * 8);
            int diff_x = ((w  ) / 10 * 8);
            int diff_y = ((h  ) / 10 * 8);
            int image_x = diff_rect(originalImage.getWidth(),x - diff_x);
            int image_y = diff_rect(originalImage.getHeight(),y - diff_y);
            int image_w = diff_rect(originalImage.getWidth() - image_x , w + (diff_x * 2));
            int image_h = diff_rect(originalImage.getHeight() -image_y ,h + (diff_y * 3));

            // Assume the subtracted area is at (100, 100) with width 200 and height 200
//            int x = 100;
//            int y = 100;
//            int width = 200;
//            int height = 200;
            // 定义要裁剪的矩形区域
            Rectangle cropRect = new Rectangle(image_x, image_y, image_w, image_h);
           // log.info(" save sub img " +x +", "+ y +","+ w +"," + h +"cropRect = " + cropRect.toString() + ", img url = " + sub_img_file_path + ", OK !!!");
    log.info("format ===>>>>>>>>>>>" + format);
            // 获取裁剪后的图像
            BufferedImage croppedImage = originalImage.getSubimage(cropRect.x, cropRect.y, cropRect.width, cropRect.height);
            File dir = new File(sub_img_file_path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            // 将裁剪后的图像写入到文件

            ImageIO.write(croppedImage,   format.substring(1, format.length()), dir);

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
            log.info(" save sub img " +x +", "+ y +","+ w +"," + h +"cropRect = " + cropRect.toString() + ", img url = " + sub_img_file_path + ", OK !!!");
        } catch (IOException e) {
            log.error( e.getMessage());
            log.info("======================================================================");
            return false;
        }
        return true;
    }

}
