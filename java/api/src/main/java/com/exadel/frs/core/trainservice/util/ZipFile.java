package com.exadel.frs.core.trainservice.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
@Slf4j
public class ZipFile
{
    public static boolean ZipFile(String zipFilenamePath, String fileToCompress, String data)
    {


        ZipOutputStream zipOutputStream = null;
        try {
            zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFilenamePath));
        } catch (FileNotFoundException e) {
            log.info("FileNotFoundException =" +String.valueOf(e));

            throw new RuntimeException(e);
        }

        ZipEntry zipEntry = new ZipEntry(fileToCompress);


        try {
            zipOutputStream.putNextEntry(zipEntry);
        } catch (IOException e) {
            log.info("IOException ="+String.valueOf(e));
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

        try {
            zipOutputStream.write(data.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
}
