package com.exadel.frs.core.trainservice.controller;

import com.exadel.frs.commonservice.entity.SaveFaceImg;
import com.exadel.frs.commonservice.projection.SaveFaceImgProjection;
import com.exadel.frs.core.trainservice.aspect.WriteEndpoint;
import com.exadel.frs.core.trainservice.dto.StorageImgDto;
import com.exadel.frs.core.trainservice.mapper.SaveFaceImgMapper;
//import com.exadel.frs.core.trainservice.mapper.StorageFaceImgMapper;
import com.exadel.frs.core.trainservice.service.SaveFaceImgServiceImpl;
import com.exadel.frs.core.trainservice.service.SaveFaceImgSubService;
import com.exadel.frs.core.trainservice.service.StorageSaveFaceImgServiceImpl;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import java.util.List;
import java.util.Map;

import static com.exadel.frs.core.trainservice.system.global.Constants.*;

@RestController
@RequestMapping(API_V1 +"/storage")
@RequiredArgsConstructor
@Validated
@Slf4j

/*
@Validated
@RestController
@RequestMapping(API_V1 + "/recognition/subjects")
@RequiredArgsConstructor
 */
public class StorageController
{

//    private final StorageSaveFaceImgServiceImpl storageSaveFaceImgService;
    private final SaveFaceImgServiceImpl saveFaceImgService;
    private final SaveFaceImgSubService saveFaceImgSubService;
//    private final SaveFaceImgSubService saveFaceImgSubService;
//    private final SaveFaceImgService saveFaceImgService;
    private final SaveFaceImgMapper saveFaceImgMapper;
    private final Environment env;
    /*
   @ApiParam(value = SUBJECT_DESC)
            @Valid
            @RequestParam(name = SUBJECT, required = false)
     */

    @GetMapping("/search")
    public StorageImgs listStorageImg(
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
            final int device_id, //API_STORAGE_FACE_GENDER_DES
            @ApiParam(value = API_STORAGE_FACE_GENDER_DES )
            @Valid
            @RequestParam( defaultValue = "0", name = API_STORAGE_FACE_GENDER, required = false )
            final int gender, //API_STORAGE_FACE_GENDER_DES
            @ApiParam(value = API_STORAGE_FACE_SUBJECTNAME_DES )
            @Valid
            @RequestParam( name = API_STORAGE_FACE_SUBJECTNAME, required = false )
            final String subjectName, //API_STORAGE_FACE_GENDER_DES
            final Pageable pageable
    )
    {

//        log.info("==============================================>");
//        try {
////            List<SaveFaceImgProjection> saveFaceImgs =  saveFaceImgService.listSaveFaceImgs( );
////            log.info(saveFaceImgs.toString());
//////            saveFaceImgs.stream().map(Objects::toString).forEach(System.out::println);
//////            Optional<SaveFaceImg> p = saveFaceImgService.findById(timestamp);
//////            if (!p.isEmpty())
//////            {
//////                log.info(p.get().toString());
//////            }
//        }
//        catch (final Exception exception)
//        {
//            log.info(exception.toString());
//        }

//        List<SaveFaceImgProjection> saveFaceImgProjections =  saveFaceImgSubService.listSaveFaceSubImgs();
//        System.out.println(saveFaceImgProjections.toArray().toString());
//        log.info(saveFaceImgProjections.toString());
//        return null;
        return new StorageImgs(saveFaceImgSubService.listSaveFaceSubImgByApiKeyBeteenTimestampAndDeivceIdAndSubjectName(apiKey, start_timestamp, end_timestamp, device_id, gender, subjectName,  pageable).map(saveFaceImgMapper::toResponseDto/*SaveFaceImgMapper::toResponseDto*/),
                env.getProperty("environment.storage.url"));
//        return null;
        //return new StorageImg(storageSaveFaceImgService.findStorageImg(apiKey, timestamp, pageable));
//        return new StorageImg(saveFaceImgService.listStorageImgs(apiKey, timestamp, pageable) .map( p -> new StorageImgDto()));
    }




    @WriteEndpoint
    @DeleteMapping("/delete_img")
    public Subtable removeAllSubjectEmbeddings(
            @ApiParam(value = API_KEY_DESC, required = true)
            @RequestHeader(name = X_FRS_API_KEY_HEADER)
            final String apiKey,
            @ApiParam(value = "delete img id", required = true)
            @Validated
            @RequestParam(name = "img_id")
            final long img_id
    ) {
         return new  Subtable(saveFaceImgSubService.removeSaveFaceSubimgByApiAkyAndId(apiKey, img_id));
    }



    ///
//    @PostMapping(value = "/histroy/search", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public FacesRecognitionResponseDto recognize(
//            @ApiParam(value = API_KEY_DESC, required = true)
//            @RequestHeader(X_FRS_API_KEY_HEADER)
//            final String apiKey,
//            @ApiParam(value = IMAGE_FILE_DESC, required = true)
//            @RequestParam
//            final MultipartFile file,
//            @ApiParam(value = LIMIT_DESC, example = NUMBER_VALUE_EXAMPLE)
//            @RequestParam(defaultValue = LIMIT_DEFAULT_VALUE, required = false)
//            @Min(value = 0, message = LIMIT_MIN_DESC)
//            final Integer limit,
//            @ApiParam(value = PREDICTION_COUNT_DESC, example = NUMBER_VALUE_EXAMPLE)
//            @RequestParam(defaultValue = PREDICTION_COUNT_DEFAULT_VALUE, name = PREDICTION_COUNT_REQUEST_PARAM, required = false)
//            @Min(value = 1, message = PREDICTION_COUNT_MIN_DESC)
//            final Integer predictionCount,
//            @ApiParam(value = DET_PROB_THRESHOLD_DESC, example = NUMBER_VALUE_EXAMPLE)
//            @RequestParam(value = DET_PROB_THRESHOLD, required = false)
//            final Double detProbThreshold,
//            @ApiParam(value = FACE_PLUGINS_DESC)
//            @RequestParam(value = FACE_PLUGINS, required = false, defaultValue = "")
//            final String facePlugins,
//            @ApiParam(value = STATUS_DESC)
//            @RequestParam(value = STATUS, required = false, defaultValue = STATUS_DEFAULT_VALUE)
//            final Boolean status,
//            @ApiParam(value = DETECT_FACES_DESC)
//            @RequestParam(value = DETECT_FACES, required = false, defaultValue = DETECT_FACES_DEFAULT_VALUE)
//            final Boolean detectFaces
//
//    )
//    {
//
//        return null;
//    }
    @RequiredArgsConstructor
    private static final class Subtable {
        private final int count;


        @JsonProperty("count")
        private int getCount () {return count;}

    }
        @RequiredArgsConstructor
        private static final class StorageImgs {

            private final Page<StorageImgDto> source;

            private final String httpUrl;
//        public StorageImg(Page<StorageImgProjection> listStorageImgs) {
//
//        }

        // As of backward compatibility we are not allowed to rename property 'faces' --> 'embedding'
            public List<StorageImgDto> getFaces() {
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
