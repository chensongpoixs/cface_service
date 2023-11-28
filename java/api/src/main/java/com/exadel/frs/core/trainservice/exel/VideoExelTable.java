package com.exadel.frs.core.trainservice.exel;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

import static com.exadel.frs.core.trainservice.exel.ExelConstants.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoExelTable
{

    private List<VideoExelRow  > exelRows = new ArrayList<>();
    public boolean add(VideoExelRow exelRow)
    {
        return    exelRows.add(exelRow);
    }

    public String  ExelTableToString()
    {
        String p = "<table>";

        p +=
                "\n" +
                        "<tr>\n" +
                        "<td width=100>"+VideoExelRowId+"</td>\n" +
                        "<td width=100>"+ExelRowCaptureAddress+"</td>\n" +
                        "<td width=100>"+ VideoExelRowCaptureTimestamp +"</td>\n" +
                        "<td width=100>"+ VideoExelRowCaptureImg +"</td>\n" +
//                        "<td width=100>"+ ExelRowGender +"</td>\n" +
//                        "<td width=100>"+ ExelRowSimilarity +"</td>\n" +
//                        "<td width=100>"+ ExelRowCaptureImg +"</td>\n" +
//                        "<td width=100>"+ ExelRowFaceImg +"</td>\n" +
                        "</tr>";

        for(VideoExelRow  row : exelRows)
        {
            p += row.ExelRowToString();
        }





        p += "</table>";

        return p;
    }
}
