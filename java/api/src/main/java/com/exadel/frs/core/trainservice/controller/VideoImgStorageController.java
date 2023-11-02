package com.exadel.frs.core.trainservice.controller;


import com.exadel.frs.commonservice.entity.Embedding;
import com.exadel.frs.commonservice.entity.SaveFaceImg;
import com.exadel.frs.commonservice.entity.SaveFaceImgSub;
import com.exadel.frs.commonservice.entity.VideoImgStorageTable;
import com.exadel.frs.core.trainservice.dto.*;
import com.exadel.frs.core.trainservice.mapper.SaveFaceImgMapper;
import com.exadel.frs.core.trainservice.mapper.VideoImgStorageMapper;
import com.exadel.frs.core.trainservice.service.SaveFaceImgServiceImpl;
import com.exadel.frs.core.trainservice.service.SaveFaceImgSubService;
import com.exadel.frs.core.trainservice.service.VideoImgStorageServiceImpl;
import com.exadel.frs.core.trainservice.util.MultipartFileToFileUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Min;
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
            new_timestamp = (int) day.getTime();
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
            final Pageable pageable
    )
    {
        String url = env.getProperty("environment.storage.url");
        log.info("storage path = " + url);
        List<Integer>   devicdids = new ArrayList<>();
        String str = "";
        if (device_id != "-1" && device_id.length() >0)
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
                        devicdids.add(Integer.parseInt(str));

                        str = "";
                    }
                }
                  if (device_id.length() ==  (i+1) )
                {
                    if (str != "")
                    {
                        devicdids.add(Integer.parseInt(str));
                        devicdids.add(Integer.parseInt(str));

                        str = "";
                    }
                }
            }
        }

        for(Integer v : devicdids)
        {
            log.info("----> devieid = " + v);
        }
        return new VideoImgStorage(videoImgStorageService.listStorageVideoImgAndDeiveIdAndTimestamp(  devicdids, start_timestamp, end_timestamp,   pageable).map(videoImgStorageMapper::toResponseDto/*SaveFaceImgMapper::toResponseDto*/),
                url);
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
}
