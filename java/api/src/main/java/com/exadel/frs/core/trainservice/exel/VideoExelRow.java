package com.exadel.frs.core.trainservice.exel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoExelRow
{
//    private Integer createTimestamp;

    private Long    id;

    private String  deviceIdAddress;

    private Integer  timestamp;

    private String  VideImg;

    public String  ExelRowToString()
    {
        Date date = new Date(timestamp);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return
                "</tr>\n" +
                        "\n" +
                        "<tr>\n" +
//                        "<td width=100>"+dateFormat.format(date)+"</td>\n" +
                        "<td width=100>"+ id  +"</td>\n" +
                        "<td width=100>"+ deviceIdAddress +"</td>\n" +
                        "<td width=100>"+ dateFormat.format(date) +"</td>\n" +
//                        "<td width=100>"+ similarity +"</td>\n" +
//                        "<td width=100>"+
//                        "<img src=\"data:image/jpeg;base64," + captureImg + "\"/></td> " +
                        "<td width=100>"+
                        "<img src=\"data:image/jpeg;base64," + VideImg + "\"/>" +
                        "</td>" +
                        "</tr>"
                ;
    }
}
