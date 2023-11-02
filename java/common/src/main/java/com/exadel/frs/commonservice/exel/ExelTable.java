package com.exadel.frs.commonservice.exel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

import static com.exadel.frs.commonservice.exel.ExelConstants.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExelTable
{
    private List<ExelRow>    exelRows = new ArrayList<>();

    public boolean add(ExelRow exelRow)
    {
            return    exelRows.add(exelRow);
    }

    public String  ExelTableToString()
    {
        String p = "<table>";

        p +=
                "\n" +
                "<tr>\n" +
                "<td width=100>"+ExelRowCreateTimestamp+"</td>\n" +
                "<td width=100>"+ ExelRowCaptureAddress +"</td>\n" +
                "<td width=100>"+ ExelRowUserName +"</td>\n" +
                "<td width=100>"+ ExelRowGender +"</td>\n" +
                "<td width=100>"+ ExelRowSimilarity +"</td>\n" +
                "<td width=100>"+ ExelRowCaptureImg +"</td>\n" +
                "<td width=100>"+ ExelRowFaceImg +"</td>\n" +
                "</tr>";

        for(ExelRow row : exelRows)
        {
            p += row.ExelRowToString();
        }





        p += "</table>";

        return p;
    }
}
