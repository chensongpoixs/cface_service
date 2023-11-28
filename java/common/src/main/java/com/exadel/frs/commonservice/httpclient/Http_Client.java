package com.exadel.frs.commonservice.httpclient;

//import com.fasterxml.jackson.databind.util.JSONPObject;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

//import static org.springframework.data.repository.init.ResourceReader.Type.JSON;

@Slf4j
public class Http_Client
{
    public Http_Client()
    {

    }

    public static String sendGetRequest(String url)
    {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    return EntityUtils.toString(entity);
                }
            } else {
                System.out.println("HTTP GET request failed with response code: " + statusCode);
                log.warn("HTTP ["+ url +"] GET request failed with response code: " + statusCode);
                return  null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("HTTP ["+ url +"] GET request failed with Exception: " + e.toString());
            return  null;
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        log.warn("warn ---> " );
        return null;
    }


    /**
     *
     * @param url
     * @return
     */
    /*
    {
  "data": [
    {
      "id": 1,
      "sn": "1581F5FHD22AT00B0094",
      "name": "飞机1号+1",
      "type": 1,
      "liveDeviceId": "44010200491322512940",
      "liveChannel": "44010200491322512941",
      "createdTime": 0,
      "updatedTime": 1692005904
    },
    {
      "id": 2,
      "sn": "5YSZKAC0020EBX",
      "name": "遥控器1号",
      "type": 2,
      "liveDeviceId": "",
      "liveChannel": "",
      "createdTime": 0,
      "updatedTime": 1691560992
    },
    {
      "id": 3,
      "sn": "B",
      "name": "机场3号",
      "type": 3,
      "liveDeviceId": "",
      "liveChannel": "",
      "createdTime": 0,
      "updatedTime": 0
    },
    {
      "id": 4,
      "sn": "C",
      "name": "球机2号",
      "type": 4,
      "liveDeviceId": "",
      "liveChannel": "",
      "createdTime": 0,
      "updatedTime": 0
    },
    {
      "id": 25,
      "sn": "32",
      "name": "机场4号",
      "type": 3,
      "liveDeviceId": "",
      "liveChannel": "",
      "createdTime": 1684130330,
      "updatedTime": 1684130330
    },
    {
      "id": 39,
      "sn": "41910200491320000001",
      "name": "石匣里布控球",
      "type": 4,
      "liveDeviceId": "41910200491320000001",
      "liveChannel": "41910200491320000002",
      "createdTime": 1689822791,
      "updatedTime": 1689822799
    },
    {
      "id": 48,
      "sn": "1ZNBK7L00C011V",
      "name": "天津1",
      "type": 1,
      "liveDeviceId": "44010200491329001960",
      "liveChannel": "44010200491329001961",
      "createdTime": 1690290376,
      "updatedTime": 1690290376
    },
    {
      "id": 49,
      "sn": "44010200491329001960",
      "name": "球1+2",
      "type": 4,
      "liveDeviceId": "44010200491329001960",
      "liveChannel": "44010200491329001961",
      "createdTime": 1690808997,
      "updatedTime": 1692005916
    },
    {
      "id": 53,
      "sn": "44010200491110000001",
      "name": "执法记录仪",
      "type": 4,
      "liveDeviceId": "44010200491110000001",
      "liveChannel": "44010200491310000012",
      "createdTime": 0,
      "updatedTime": 0
    },
    {
      "id": 54,
      "sn": "1581F5FJD228B00A0127",
      "name": "邵七堤'",
      "type": 1,
      "liveDeviceId": "44010200491321905570",
      "liveChannel": "44010200491321905571",
      "createdTime": 0,
      "updatedTime": 0
    }
  ],
  "code": 200
}
     */
    public static  Map<Integer, DeviceInfo> GetDeviceListInfo(String url)
    {

        String result = sendGetRequest(url);
        if (result == null)
        {
            return null;
        }
        Map<Integer, DeviceInfo>  deviceinfomap = new HashMap<>();


        JSONObject jsonpObject = new JSONObject(JSON.parseObject(result));


       Integer code = (Integer) jsonpObject.get("code");
        List<DeviceInfo> deviceInfolists = JSON.parseArray(jsonpObject.get("data").toString(), DeviceInfo.class);


        for (DeviceInfo deviceInfo: deviceInfolists)
        {
            log.info(deviceInfo.toString());
            deviceinfomap.put(deviceInfo.getId(), deviceInfo);
            //deviceinfomap.(deviceInfo.getId(), deviceInfo);
        }



        return deviceinfomap;

    }







}
