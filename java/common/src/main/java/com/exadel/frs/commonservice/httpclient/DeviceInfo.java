package com.exadel.frs.commonservice.httpclient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceInfo
{
    private Integer id;

    private String sn;


    private String name;

    private Integer type;
    private String liveDeviceId;
    private String liveChannel;
    private Integer createdTime;
    private Integer updatedTime;
    //        "id": 54,
//        "sn": "1581F5FJD228B00A0127",
//        "name": "邵七堤'",
//        "type": 1,
//        "liveDeviceId": "44010200491321905570",
//        "liveChannel": "44010200491321905571",
//        "createdTime": 0,
//        "updatedTime": 0
}
