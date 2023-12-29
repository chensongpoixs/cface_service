package com.exadel.frs.core.trainservice.controller;


import com.exadel.frs.commonservice.entity.Embedding;
import com.exadel.frs.commonservice.entity.SaveFaceImg;
import com.exadel.frs.commonservice.entity.SaveFaceImgSub;
import com.exadel.frs.commonservice.entity.VideoImgStorageTable;
import com.exadel.frs.commonservice.httpclient.DeviceInfo;
import com.exadel.frs.commonservice.httpclient.HttpDefault;
import com.exadel.frs.commonservice.httpclient.Http_Client;
import com.exadel.frs.commonservice.projection.DownloadDataProjection;
import com.exadel.frs.commonservice.projection.VideoImgStorageProjection;
import com.exadel.frs.core.trainservice.aspect.WriteEndpoint;
import com.exadel.frs.core.trainservice.dto.*;
import com.exadel.frs.core.trainservice.exel.ExelRow;
import com.exadel.frs.core.trainservice.exel.ExelTable;
import com.exadel.frs.core.trainservice.exel.VideoExelRow;
import com.exadel.frs.core.trainservice.exel.VideoExelTable;
import com.exadel.frs.core.trainservice.mapper.SaveFaceImgMapper;
import com.exadel.frs.core.trainservice.mapper.VideoImgStorageMapper;
import com.exadel.frs.core.trainservice.service.SaveFaceImgServiceImpl;
import com.exadel.frs.core.trainservice.service.SaveFaceImgSubService;
import com.exadel.frs.core.trainservice.service.VideoImgStorageServiceImpl;
import com.exadel.frs.core.trainservice.util.FileBase64;
import com.exadel.frs.core.trainservice.util.MultipartFileToFileUtils;
import com.exadel.frs.core.trainservice.util.ZipFile;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.exadel.frs.commonservice.system.global.Constants.DET_PROB_THRESHOLD;
import static com.exadel.frs.core.trainservice.system.global.Constants.*;

@RestController
@RequestMapping(API_V1 +"/video_storage")
@RequiredArgsConstructor
@Validated
@Slf4j
public class VideoImgStorageController
{
    private final VideoImgStorageServiceImpl videoImgStorageService;
//    private final SaveFaceImgSubService saveFaceImgSubService;
    //    private final SaveFaceImgSubService saveFaceImgSubService;
//    private final SaveFaceImgService saveFaceImgService;
    private final VideoImgStorageMapper videoImgStorageMapper;

    private final Environment env;




    @PostMapping(value = "/vido_save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public int save(
            @ApiParam(value = API_KEY_DESC, required = true)
            @RequestHeader(X_FRS_API_KEY_HEADER)
            final String apiKey,
            @ApiParam(value = IMAGE_FILE_DESC, required = true)
            @RequestParam
            final MultipartFile file,
            @ApiParam(value = API_STORAGE_TIMESTAMP_DES )
            @RequestParam(defaultValue = VIDEO_STORAGE_COUNT_DEFAULT_VALUE, name = API_STORAGE_TIMESTAMP, required = false)
            @Min(value = 1, message = API_STORAGE_TIMESTAMP_DES)
            final int timestamp,
            @ApiParam(value = API_STORAGE_DEVICEID_DES )
            @RequestParam( name = API_STORAGE_DEVICEID, required = true)
            final int device_id
    )
    {
        String path = env.getProperty("environment.storage.path") ;
        log.info("video img storage path = " + path);

        ///////////////////////////////
        Date day = new Date();
        SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd");
        String file_prefix = "/video/"+ sdf.format(day) +"/";
        SimpleDateFormat  file_prefixDate = new SimpleDateFormat("yyyyMMddHHmmss");
//            String new_file_name = file_prefixDate.format(day) + "_" +UUID.randomUUID();
        String master_file_name = file_prefixDate.format(day) + "_" +  UUID.randomUUID().toString() /*+ ".jpg"*/;

//            String outpath = "D:/Work/cai/face/images/" ;//+ sdf.format(day) +"/" + apiKey + "/";
        String maser_new_jpg =  MultipartFileToFileUtils.saveMultipartFile(file, path, file_prefix, master_file_name);


        VideoImgStorageTable videoImgStorageTable = new VideoImgStorageTable();
        videoImgStorageTable.setImgUrl(maser_new_jpg);
        int new_timestamp = timestamp;
        if (new_timestamp <= 0)
        {
//            Date date = new Date();
//            long timestamp = date.getTime();
//            System.out.println("当前时间戳：" + timestamp);
            new_timestamp = (int) day.getTime() /( 1000 /** 1000*/);
        }
        videoImgStorageTable.setTimestamp(new_timestamp);
        videoImgStorageTable.setDeviceId(device_id);

//        log.info(videoImgStorageTable.toString());
//        return 0;
        VideoImgStorageTable videoImgStorageTable1 =  videoImgStorageService.AddVideoImgStorage(videoImgStorageTable);

        /////////////////////////////////////////////

        return videoImgStorageTable1.getId() > 0 ? 1: 0;

    }











    @GetMapping("/video_search")
    public VideoImgStorage listStorageVideoImg
            (
            @ApiParam(value = API_KEY_DESC, required = true)
            @RequestHeader(name = X_FRS_API_KEY_HEADER)
            final String apiKey,
            @ApiParam(value = API_STORAGE_START_TIMESTAMP_DES , required = true)
            @Valid
            @RequestParam(name = API_STORAGE_START_TIMESTAMP )
            final long start_timestamp,
            @ApiParam(value = API_STORAGE_END_TIMESTAMP_DES , required = true)
            @Valid
            @RequestParam(name = API_STORAGE_END_TIMESTAMP )
            final long end_timestamp,
            @ApiParam(value = API_STORAGE_FACE_DEVICEID_DES  )
            @Valid
            @RequestParam(defaultValue = "-1", name = API_STORAGE_FACE_DEVICEID, required = false )
            final String device_id, //API_STORAGE_FACE_GENDER_DES
            @ApiParam(value = "page", required = true)
            @Validated
            @RequestParam(value = "page" )
            final int page,
            @ApiParam(value = "page_size", required = true)
            @Validated
            @RequestParam(value = "page_size" )
            final int pageSize
    )
    {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.unsorted());
        String url = env.getProperty("environment.storage.url");
        log.info("storage path = " + url + ", device_id = " + device_id);

        List<Integer>   devicdids = new ArrayList<>();
        String str = "";
        if (  device_id.length() >0)
        {
            log.info("device_id.charAt(0) = " + device_id.charAt(0));
            if (device_id.charAt(0) != '-')
            {
                for (int i = 0; i < device_id.length(); ++i)
                {
                    if (device_id.charAt(i) < ('9' +1) && device_id.charAt(i) > ('0' -1))
                    {
                        str +=device_id.charAt(i);
                    }
                    else if (device_id.charAt(i) == ',' /*|| device_id.length() ==  (i)*/ )
                    {
                        // TODO@chensong Java的接口定义需要这样玩的哈
                        if (str != "")
                        {
                            devicdids.add(Integer.parseInt(str));
//                        devicdids.add(Integer.parseInt(str));

                            str = "";
                        }
                    }
                    if (device_id.length() ==  (i+1) )
                    {
                        if (str != "")
                        {
                            devicdids.add(Integer.parseInt(str));
//                        devicdids.add(Integer.parseInt(str));

                            str = "";
                        }
                    }
                }
            }
        }

        for(Integer v : devicdids)
        {
            log.info("----> devieid = " + v);
        }
        return new VideoImgStorage(videoImgStorageService.listStorageVideoImgAndDeiveIdAndTimestamp(  devicdids, start_timestamp, end_timestamp, pageable).map(videoImgStorageMapper::toResponseDto/*SaveFaceImgMapper::toResponseDto*/),
                url);
//        return new VideoImgStorage(videoImgStorageService.listStorageVideoImgAndDeiveId(  devicdids,  pageable).map(videoImgStorageMapper::toResponseDto/*SaveFaceImgMapper::toResponseDto*/),
//                url);
//        return null;
        //return new StorageImg(storageSaveFaceImgService.findStorageImg(apiKey, timestamp, pageable));
//        return new StorageImg(saveFaceImgService.listStorageImgs(apiKey, timestamp, pageable) .map( p -> new StorageImgDto()));
    }


    @RequiredArgsConstructor
    private static final class VideoImgStorage {

        private final Page<VideoImgStorageDto> source;

        private final String httpUrl;
//        public StorageImg(Page<StorageImgProjection> listStorageImgs) {
//
//        }

        // As of backward compatibility we are not allowed to rename property 'faces' --> 'embedding'
        public List<VideoImgStorageDto> getFaces() {
            return source.getContent();
        }

        @JsonProperty("http_url")
        private String getHttpUrl () {return httpUrl;}
        @JsonProperty("total_pages")
        public int getTotalPages() {
            return source.getTotalPages();
        }

        @JsonProperty("total_elements")
        public long getTotalElements() {
            return source.getTotalElements();
        }

        @JsonProperty("page_number")
        public int getNumber() {
            return source.getNumber();
        }

        @JsonProperty("page_size")
        public int getSize() {
            return source.getSize();
        }
    }

    @WriteEndpoint
    @DeleteMapping("/delete_video_img")
    public VideoDto DeleteVideoImg(
            @ApiParam(value = API_KEY_DESC, required = true)
            @RequestHeader(name = X_FRS_API_KEY_HEADER)
            final String apiKey,
            @ApiParam(value = "img id  (1 )" , required = true)
            @Valid
            @RequestParam( name = "id" , required = true)
            final long id
    ) {
         return new VideoDto(videoImgStorageService.DeleteVideoImgId(id));
    }

    @GetMapping("/download")
    public ReslutDownload Download(
            @ApiParam(value = API_KEY_DESC, required = true)
            @RequestHeader(name = X_FRS_API_KEY_HEADER)
            final String apiKey,
            @ApiParam(value = "img id  (1,2,3,4,5)" , required = true)
            @Valid
            @RequestParam(defaultValue = "-1", name = "ids" )
            final String ids)
    {

        List<Long>   imgids = new ArrayList< >();
        String str = "";
        if (   ids.length() >0)
        {
            if (ids.charAt(0) != '-')
            {
                for (int i = 0; i < ids.length(); ++i)
                {
                    log.info("[i = " + i + "],[ char = " + ids.charAt(i) + "][ size = " + ids.length() + "]");
                    if (ids.charAt(i) < ('9' +1) && ids.charAt(i) > ('0' -1))
                    {
                        str +=ids.charAt(i);
                    }
                    else if (ids.charAt(i) == ',' )
                    {
                        // TODO@chensong Java的接口定义需要这样玩的哈
                        if (str != "")
                        {
                            imgids.add(Long.parseLong(str));

                            str = "";
                        }
                    }
                    if (ids.length() ==  (i+1) )
                    {
                        if (str != "")
                        {
                            imgids.add(Long.parseLong(str));

                            str = "";
                        }
                    }
                }
            }

        }


        int result = 0;
//        List< DownloadDataProjection> downloadDatalist = null;
        for (Long v : imgids)
        {
            log.info("imgid = " + v);
        }
//        if (imgids.size()> 0)
        {
            List<VideoImgStorageProjection> downloadDatalist = videoImgStorageService.findVideoImageAndDeviceIds(imgids);
//            return new ReslutDownload(downloadDatalist.toString(), result);

            if (null != downloadDatalist && downloadDatalist.size() > 0)
            {

                String imgprofixpath = env.getProperty("environment.storage.path");
                str = "";
                VideoExelTable exelTable = new VideoExelTable();
                String DroneUrl = env.getProperty("environment.drone.url");
                Map<Integer, DeviceInfo> deviceInfoMap = Http_Client.GetDeviceListInfo(DroneUrl + HttpDefault.DRONE_API_DEVICE_LIST);
                for (VideoImgStorageProjection videoImgStorageProjection : downloadDatalist)
                {
//                    log.info("[id = "+ videoImgStorageProjection.id() +"][timestamp = "+ videoImgStorageProjection.timestamp()+"]");
                    VideoExelRow   exelRow = new VideoExelRow();
                    exelRow.setId(videoImgStorageProjection.id());
                    exelRow.setTimestamp(videoImgStorageProjection.timestamp() * 1000);

                    DeviceInfo deviceInfo =   deviceInfoMap.get(videoImgStorageProjection.device_id());
                    if (null ==  deviceInfo )
                    {
                        exelRow.setDeviceIdAddress("未知设备");
                    }
                    else
                    {
                        exelRow.setDeviceIdAddress(deviceInfo.getName());
                    }
                    exelRow.setVideImg(FileBase64.FileBase64ToString(imgprofixpath + videoImgStorageProjection.imgUrl()));
//                    exelRow.setCreateTimestamp(downloadDataProjection.timestamp());
//                    exelRow.setDeviceIdAddress(String.valueOf(downloadDataProjection.deviceId()));
//                    exelRow.setUserName(downloadDataProjection.userName());
//                    exelRow.setGender(String.valueOf(downloadDataProjection.gender()));
//                    exelRow.setSimilarity(downloadDataProjection.similarity());
//                    exelRow.setCaptureImg(FileBase64.FileBase64ToString(imgprofixpath + downloadDataProjection.captureImgUrl()));
//                    exelRow.setFaceImg(Base64.getEncoder().encodeToString(downloadDataProjection.faceImg()));
                    exelTable.add(exelRow);
                }
                Date date = new Date();
                SimpleDateFormat file_prefixDate = new SimpleDateFormat("yyyyMMdd");
                String zipPath = "/zip/" + file_prefixDate.format(date) + "/"    ;



                String uuid = UUID.randomUUID().toString()   ;


                String xlsfilepath = uuid + ".xls";


                zipPath += uuid+   ".zip";
                String absolutePath = null;
                File zipdir = new File(env.getProperty("environment.storage.path") + zipPath);
                if (!zipdir.exists()) {
                    try {
                        absolutePath = zipdir.getCanonicalPath();

                        /*判断路径中的文件夹是否存在，如果不存在，先创建文件夹*/
                        String dirPath = absolutePath.substring(0, absolutePath.lastIndexOf(File.separator));
                        File dir = new File(dirPath);
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }

                    } catch (IOException e) {
                        log.info("IOException" + String.valueOf(e));
                        throw new RuntimeException(e);
                    }
                }

                if (!ZipFile.ZipFile( env.getProperty("environment.storage.path") +zipPath, xlsfilepath, exelTable.ExelTableToString()))
                {
                    log.info("zip img failed !!!");
                }

                return new  ReslutDownload(env.getProperty("environment.storage.url") + zipPath, result);
            }
        }
        result = 500;
//
//
////        str = downloadDatalist.toString();
//
        return new  ReslutDownload(str, result);



    }

    @RequiredArgsConstructor
    private static final class ReslutDownload {
        private final String url;

        private final int result;
        @JsonProperty("url")
        private String getUrl () {return url;}


        @JsonProperty("result")
        private int getResult () {return result;}

    }
}
