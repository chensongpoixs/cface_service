package com.exadel.frs.core.trainservice.util;

import com.exadel.frs.core.trainservice.exel.ExelTable;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
@Slf4j
public class ZipFile
{
    public static boolean ZipFile(String zipFilenamePath, String fileToCompress, ExelTable tabledata)
    {


        ZipOutputStream zipOutputStream = null;
        try {
            zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFilenamePath));
        } catch (FileNotFoundException e) {
            log.info("FileNotFoundException =" +String.valueOf(e));

            throw new RuntimeException(e);
        }



//        FileInputStream fileInputStream = null;
//        try {
//              fileInputStream = new FileInputStream(fileToCompress);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//
//        byte[]  buffer = new byte[1024];
//        int bytesRend = 0;
//        try {
//        while ((bytesRend = fileInputStream.read(buffer)) != -1)
//        {
//
//                zipOutputStream.write(buffer, 0, bytesRend);
//
//        }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

//        try {


            tabledata.write(zipOutputStream, fileToCompress);
//            zipOutputStream.write(data.toString().getBytes());
//            data.append(1);
//            data.length();

//            int total_count = data.length();
//            int count = total_count / (1024 * 1024);
//            int s = total_count %(1024 * 1024);
//            for (int i = 0; i < count; ++i)
//            {
////                data.getChars();
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        try {
            zipOutputStream.finish();
//            zipOutputStream.closeEntry();
            zipOutputStream.close();;
        } catch (IOException e) {
            log.info("IOException = ==" + String.valueOf(e));
            throw new RuntimeException(e);
        }
        return true;
    }
    /**
     * 删除单个文件
     * @param   sPath    被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static  boolean deleteFile(String sPath) {
       boolean flag = false;
      File  file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }
}
