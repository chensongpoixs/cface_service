package com.exadel.frs.core.trainservice.exel;//package com.exadel.frs.commonservice.exel;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExelRow
{
    private Integer createTimestamp;

    private String  deviceIdAddress;

    private String  userName;

    private String  gender;

    private double  similarity;

    private String  captureImg;

    private String  faceImg;

    public String  ExelRowToString()
    {
        Date date = new Date(createTimestamp);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return
                        "<tr height=400>\n" +
                        "<td width=150>"+dateFormat.format(date)+"</td>\n" +
                        "<td width=100>"+ deviceIdAddress +"</td>\n" +
                        "<td width=100>"+ userName +"</td>\n" +
                        "<td width=100>"+ gender +"</td>\n" +
                        "<td width=100>"+ similarity +"</td>\n" +
                        "<td width=400>"+
                        "<img src=\"data:image/jpeg;base64," + captureImg + "\"/></td> " +
                        "<td width=400>"+
                        "<img src=\"data:image/jpeg;base64," + faceImg + "\"/>" +
                        "</td>" +
                        "</tr>"
                ;
    }
}
