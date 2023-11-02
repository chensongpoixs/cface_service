package com.exadel.frs.core.trainservice.exel;

import com.exadel.frs.commonservice.exel.ExelRow;
import com.exadel.frs.commonservice.exel.ExelTable;
import org.junit.jupiter.api.Test;

public class ExelTableTest
{

    public  void outtest(String str, String out)
    {
        out = "chensong";
    }
    @Test
    public void test()
    {
        String p = null;
        outtest("chenosng", p);
        System.out.println(p);
//        return;
        ExelTable exelTable = new ExelTable();

        /*

        private Integer createTimestamp;

    private String  captureAddress;

    private String  userName;

    private String  gender;

    private double  similarity;

    private String  captureImg;

    private String  faceImg;
         */

        exelTable.add(new ExelRow(1698828338, "抓拍地点1", "姓名", "性别", 89.23, "caputreimg", "faceimg" ));

        exelTable.add(new ExelRow(1698828338, "抓拍地点2", "姓名", "性别", 89.23, "caputreimg", "faceimg" ));

        exelTable.add(new ExelRow(1698828338, "抓拍地点3", "姓名", "性别", 89.23, "caputreimg", "faceimg" ));

        exelTable.add(new ExelRow(1698828338, "抓拍地点4", "姓名", "性别", 89.23, "caputreimg", "faceimg" ));
        exelTable.add(new ExelRow(1698828338, "抓拍地点5", "姓名", "性别", 89.23, "caputreimg", "faceimg" ));


        System.out.println(exelTable.ExelTableToString());

//        exelTable.ExelTableToString()
    }
}
