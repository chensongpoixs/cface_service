package com.exadel.frs.core.trainservice.exel;//package com.exadel.frs.commonservice.exel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static com.exadel.frs.core.trainservice.exel.ExelConstants.*;

//import static com.exadel.frs.commonservice.exel.ExelConstants.*;

@Entity
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class ExelTable
{
    private List<ExelRow>    exelRows = new ArrayList<>();

    public boolean add(ExelRow exelRow)
    {
            return    exelRows.add(exelRow);
    }

    public boolean write(ZipOutputStream outputstream, String fileToCompress)   {
        ZipEntry zipEntry = new ZipEntry(fileToCompress);


        try {
            outputstream.putNextEntry(zipEntry);
        } catch (IOException e) {
            log.info("IOException ="+String.valueOf(e));
            throw new RuntimeException(e);
        }
        String p = "<table>";

        p +=
                "\n" +
                        "<tr>\n" +
                        "<td width=150>"+ExelRowCreateTimestamp+"</td>\n" +
                        "<td width=100>"+ ExelRowCaptureAddress +"</td>\n" +
                        "<td width=100>"+ ExelRowUserName +"</td>\n" +
                        "<td width=100>"+ ExelRowGender +"</td>\n" +
                        "<td width=100>"+ ExelRowSimilarity +"</td>\n" +
                        "<td width=400>"+ ExelRowCaptureImg +"</td>\n" +
                        "<td width=400>"+ ExelRowFaceImg +"</td>\n" +
                        "</tr>";
        try {
        outputstream.write(p.getBytes());

//        outputstream.finish();;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {

            for(ExelRow row : exelRows)
            {
    //            p += row.ExelRowToString();
                outputstream.write(row.ExelRowToString().getBytes());
//                outputstream.finish();
            }
//            outputstream.finish();;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {

        outputstream.write("</table>".getBytes());
//        outputstream.finish();;
       // p += "</table>";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public String  ExelTableToString()
    {
        String p = "<table>";

        p +=
                "\n" +
                "<tr>\n" +
                "<td width=150>"+ExelRowCreateTimestamp+"</td>\n" +
                "<td width=100>"+ ExelRowCaptureAddress +"</td>\n" +
                "<td width=100>"+ ExelRowUserName +"</td>\n" +
                "<td width=100>"+ ExelRowGender +"</td>\n" +
                "<td width=100>"+ ExelRowSimilarity +"</td>\n" +
                "<td width=400>"+ ExelRowCaptureImg +"</td>\n" +
                "<td width=400>"+ ExelRowFaceImg +"</td>\n" +
                "</tr>";

        for(ExelRow row : exelRows)
        {
            p += row.ExelRowToString();
        }





        p += "</table>";

        return p;
    }
    public StringBuffer  ExelTableTobufferString()
    {
        StringBuffer stringBuffer = new StringBuffer();
        String p = "<table>";

        p +=
                "\n" +
                        "<tr>\n" +
                        "<td width=150>"+ExelRowCreateTimestamp+"</td>\n" +
                        "<td width=100>"+ ExelRowCaptureAddress +"</td>\n" +
                        "<td width=100>"+ ExelRowUserName +"</td>\n" +
                        "<td width=100>"+ ExelRowGender +"</td>\n" +
                        "<td width=100>"+ ExelRowSimilarity +"</td>\n" +
                        "<td width=400>"+ ExelRowCaptureImg +"</td>\n" +
                        "<td width=400>"+ ExelRowFaceImg +"</td>\n" +
                        "</tr>";
        stringBuffer.append(p);
        for(ExelRow row : exelRows)
        {
            stringBuffer.append(row.ExelRowToString());
           // p += row.ExelRowToString();
        }





        stringBuffer.append("</table>");
        //p += "</table>";

        return stringBuffer;
    }
}
