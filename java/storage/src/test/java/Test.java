import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
//import java.net.http.HttpClient;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.exadel.frs.commonservice.httpclient.DeviceInfo;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
public class Test
{
    @org.junit.jupiter.api.Test
    public void test()
    {
        //1970-01-01 00:00:00
        Integer timestamp = 60 * 60 * 24;
        //1970-01-01 00:00:45
        Date date = new Date(timestamp * 1000 );
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat.format(date));
    }




    public static String sendGet00Request(String url) {
        HttpURLConnection connection = null;
        try {
            URL reqUrl = new URL(url);
            connection = (HttpURLConnection) reqUrl.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                return response.toString();
            } else {
                System.out.println("HTTP GET request failed with response code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }
    public static String sendGetRequest(String url) {
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return null;
    }
    @org.junit.jupiter.api.Test
    public void testhttpclient()
    {
        String url = "http://192.168.1.141:5556/api/device/deviceList";
        System.out.println("[]===="+ sendGetRequest(url));
    }

    @org.junit.jupiter.api.Test
    public void test_fastjson()
    {
        Map<Integer, DeviceInfo> deviceinfomap = new HashMap<>();

        String result = sendGetRequest("http://127.0.0.1:5556/api/device/deviceList");

        JSONObject jsonpObject = new JSONObject(JSON.parseObject(result));


        Integer code = (Integer) jsonpObject.get("code");
        System.out.println("code = " + code);
        List<DeviceInfo> deviceInfolists = JSON.parseArray(jsonpObject.get("data").toString(), DeviceInfo.class);


        for (DeviceInfo deviceInfo: deviceInfolists)
        {
            System.out.println(deviceInfo.toString());
            deviceinfomap.put(deviceInfo.getId(), deviceInfo);
            //deviceinfomap.(deviceInfo.getId(), deviceInfo);
        }
    }
}
