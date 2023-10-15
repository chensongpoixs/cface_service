package com.exadel.frs.core.trainservice.controller;

import com.exadel.frs.commonservice.projection.StorageImgProjection;
import com.exadel.frs.core.trainservice.dto.StorageImgDto;
import com.exadel.frs.core.trainservice.mapper.StorageFaceImgMapper;
import com.exadel.frs.core.trainservice.service.SaveFaceImgService;
import com.exadel.frs.core.trainservice.service.StorageSaveFaceImgServiceImpl;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.exadel.frs.core.trainservice.system.global.Constants.*;

@RestController
@RequestMapping(API_V1 +"/storage")
@RequiredArgsConstructor
@Validated
@Slf4j
public class StorageController
{

    private final StorageSaveFaceImgServiceImpl storageSaveFaceImgService;
//    private final SaveFaceImgService saveFaceImgService;
    @GetMapping("/histroy/search")
    public StorageImg listStorageImg(
            @ApiParam(value = API_KEY_DESC, required = true)
            @RequestHeader(name = X_FRS_API_KEY_HEADER)
            final String apiKey,
            @ApiParam(value = DETECT_FACE_TIMESTAMP)
            @Valid
            @RequestParam(name = FACE_TIMESTAMP, required = false)
            final long timestamp,
            final Pageable pageable
    )
    {

        return null;
        //return new StorageImg(storageSaveFaceImgService.findStorageImg(apiKey, timestamp, pageable));
//        return new StorageImg(saveFaceImgService.listStorageImgs(apiKey, timestamp, pageable) .map( p -> new StorageImgDto()));
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
        private static final class StorageImg {

            private final Page<StorageImgDto> source;

//        public StorageImg(Page<StorageImgProjection> listStorageImgs) {
//
//        }

        // As of backward compatibility we are not allowed to rename property 'faces' --> 'embedding'
            public List<StorageImgDto> getFaces() {
                return source.getContent();
            }

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
