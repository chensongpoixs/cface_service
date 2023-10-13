/*
 * Copyright (c) 2020 the original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.exadel.frs.core.trainservice.controller;

import static com.exadel.frs.commonservice.system.global.Constants.DET_PROB_THRESHOLD;
import static com.exadel.frs.core.trainservice.system.global.Constants.*;

//import com.exadel.frs.commonservice.entity.SaveFaceImg;
import com.exadel.frs.commonservice.entity.SaveFaceImg;
import com.exadel.frs.core.trainservice.dto.Base64File;
import com.exadel.frs.core.trainservice.dto.EmbeddingsRecognitionProcessResponse;
import com.exadel.frs.core.trainservice.dto.EmbeddingsRecognitionRequest;
import com.exadel.frs.core.trainservice.dto.FacesRecognitionResponseDto;
import com.exadel.frs.core.trainservice.dto.ProcessEmbeddingsParams;
import com.exadel.frs.core.trainservice.dto.ProcessImageParams;
import com.exadel.frs.core.trainservice.service.EmbeddingsProcessService;
//import com.exadel.frs.core.trainservice.service.SaveFaceImgService;
import com.exadel.frs.core.trainservice.service.SaveFaceImgService;
import com.exadel.frs.core.trainservice.util.MultipartFileToFileUtils;
import io.swagger.annotations.ApiParam;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import liquibase.pro.packaged.D;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(API_V1)
@RequiredArgsConstructor
@Validated
@Slf4j
public class RecognizeController {

    private final EmbeddingsProcessService recognitionService;
    private final SaveFaceImgService saveFaceImgService;
    @PostMapping(value = "/recognition/recognize", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FacesRecognitionResponseDto recognize(
            @ApiParam(value = API_KEY_DESC, required = true)
            @RequestHeader(X_FRS_API_KEY_HEADER)
            final String apiKey,
            @ApiParam(value = IMAGE_FILE_DESC, required = true)
            @RequestParam
            final MultipartFile file,
            @ApiParam(value = LIMIT_DESC, example = NUMBER_VALUE_EXAMPLE)
            @RequestParam(defaultValue = LIMIT_DEFAULT_VALUE, required = false)
            @Min(value = 0, message = LIMIT_MIN_DESC)
            final Integer limit,
            @ApiParam(value = PREDICTION_COUNT_DESC, example = NUMBER_VALUE_EXAMPLE)
            @RequestParam(defaultValue = PREDICTION_COUNT_DEFAULT_VALUE, name = PREDICTION_COUNT_REQUEST_PARAM, required = false)
            @Min(value = 1, message = PREDICTION_COUNT_MIN_DESC)
            final Integer predictionCount,
            @ApiParam(value = DET_PROB_THRESHOLD_DESC, example = NUMBER_VALUE_EXAMPLE)
            @RequestParam(value = DET_PROB_THRESHOLD, required = false)
            final Double detProbThreshold,
            @ApiParam(value = FACE_PLUGINS_DESC)
            @RequestParam(value = FACE_PLUGINS, required = false, defaultValue = "")
            final String facePlugins,
            @ApiParam(value = STATUS_DESC)
            @RequestParam(value = STATUS, required = false, defaultValue = STATUS_DEFAULT_VALUE)
            final Boolean status,
            @ApiParam(value = DETECT_FACES_DESC)
            @RequestParam(value = DETECT_FACES, required = false, defaultValue = DETECT_FACES_DEFAULT_VALUE)
            final Boolean detectFaces,
//            @ApiParam(value = DETECT_FACE_TIMESTAMP)
//            @RequestParam(value = FACE_TIMESTAMP, required = false, defaultValue = "1")
            @ApiParam(value = DETECT_FACE_TIMESTAMP, example = NUMBER_VALUE_EXAMPLE)
            @RequestParam(defaultValue = PREDICTION_COUNT_DEFAULT_VALUE, name = FACE_TIMESTAMP, required = false)
            @Min(value = 1, message = DETECT_FACE_TIMESTAMP)
            final Integer timestamp,
//            @ApiParam(value = DETECT_DeviceID)
//            @RequestParam(value = DETECT_DEVICE_ID, required = false, defaultValue = "0")
            @ApiParam(value = DETECT_DeviceID, example = NUMBER_VALUE_EXAMPLE)
            @RequestParam(defaultValue = PREDICTION_COUNT_DEFAULT_VALUE, name = DETECT_DEVICE_ID, required = false)
            @Min(value = 1, message = DETECT_DeviceID)
            final Integer device_id

    ) {
        ProcessImageParams processImageParams = ProcessImageParams
                .builder()
                .apiKey(apiKey)
                .file(file)
                .limit(limit)
                .detProbThreshold(detProbThreshold)
                .facePlugins(facePlugins)
                .status(status)
                .detectFaces(detectFaces)
                .additionalParams(Collections.singletonMap(PREDICTION_COUNT, predictionCount))
                .build();
//        log.info("apikey =  "+apiKey );
//        System.out.println(" apiKey =" + apiKey);

//        String file_name = file.getOriginalFilename();
//        InputStream inputStream = file.getInputStream();

        long cur_ms =  System.currentTimeMillis();
//        String outpath = "";
//        String originalFilename = file.getOriginalFilename();
        /*获取文件格式*/
//        String fileFormat = originalFilename.substring(originalFilename.lastIndexOf("."));
//        JinshanTable jinshanTable = new JinshanTable();
//        jinshanTable.setFileId(fileId);
//        List<JinshanTable> list = jinshanTableService.selectJinshanTableList(jinshanTable);
//        long version = list.get(0).getVersion() + 1;
//        if (CollectionUtils.isEmpty(list)) {
//            String path = "D:\\downloadServer\\" + fileId + "\\version1\\";
//            outpath = "D:/downloadServer/" + fileId + "/version1/" + originalFilename;
//            File file1 = new File(path);
//            boolean mkdir = file1.mkdir();
//        } else {
//            String path = "D:\\downloadServer\\" + list.get(0).getFileId() + "\\version" + version + "\\";
//            outpath = "D:/downloadServer/" + fileId + "/version" + version;
//            File file1 = new File(path);
//            boolean mkdir = file1.mkdir();
//        }
      //  Date day = new Date();
       // SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd");
        String outpath = "D:/Work/cai/face/images/" ;//+ sdf.format(day) +"/" + apiKey + "/";
//        File file1 = new File(outpath);
//        boolean mkdir =  file1.mkdir();
////        static long image_count = 0;
//        SimpleDateFormat  file_prefix= new SimpleDateFormat("yyyyMMddHHmmss");
//        String new_file_name = file_prefix.format(day) + "_" +UUID.randomUUID();
      String local_file_name_prefix =  MultipartFileToFileUtils.saveMultipartFile(file, apiKey, outpath);
        log.info("save img = " + (System.currentTimeMillis() - cur_ms) + " ms");

        SaveFaceImg sfaceimg = new SaveFaceImg();
//        sfaceimg.setUuid_id(UUID.randomUUID().toString());
        sfaceimg.setTimestmap(timestamp);
        sfaceimg.setApi_key(apiKey);
        sfaceimg.setImg_url(local_file_name_prefix);
        sfaceimg.setDevice_id(device_id);
        SaveFaceImg new_sfaceimg =   saveFaceImgService.AddSaveFace(sfaceimg);
        log.info(new_sfaceimg.toString());
        // 拦截并保存图片和信息
        return (FacesRecognitionResponseDto) recognitionService.processImage(processImageParams);
    }

    @PostMapping(value = "/recognition/recognize", consumes = MediaType.APPLICATION_JSON_VALUE)
    public FacesRecognitionResponseDto recognizeBase64(
            @ApiParam(value = API_KEY_DESC, required = true)
            @RequestHeader(X_FRS_API_KEY_HEADER)
            final String apiKey,
            @ApiParam(value = LIMIT_DESC, example = NUMBER_VALUE_EXAMPLE)
            @RequestParam(defaultValue = LIMIT_DEFAULT_VALUE, required = false)
            @Min(value = 0, message = LIMIT_MIN_DESC)
            final Integer limit,
            @ApiParam(value = DET_PROB_THRESHOLD_DESC, example = NUMBER_VALUE_EXAMPLE)
            @RequestParam(value = DET_PROB_THRESHOLD, required = false)
            final Double detProbThreshold,
            @ApiParam(value = FACE_PLUGINS_DESC)
            @RequestParam(value = FACE_PLUGINS, required = false, defaultValue = "")
            final String facePlugins,
            @ApiParam(value = STATUS_DESC)
            @RequestParam(value = STATUS, required = false, defaultValue = STATUS_DEFAULT_VALUE)
            final Boolean status,
            @ApiParam(value = DETECT_FACES_DESC)
            @RequestParam(value = DETECT_FACES, required = false, defaultValue = DETECT_FACES_DEFAULT_VALUE)
            final Boolean detectFaces,
            @ApiParam(value = PREDICTION_COUNT_DESC, example = NUMBER_VALUE_EXAMPLE)
            @RequestParam(value = PREDICTION_COUNT_REQUEST_PARAM, required = false, defaultValue = PREDICTION_COUNT_DEFAULT_VALUE)
            @Min(value = 1, message = PREDICTION_COUNT_MIN_DESC)
            final Integer predictionCount,
            @RequestBody
            @Valid
            final Base64File request
    ) {
        ProcessImageParams processImageParams = ProcessImageParams
                .builder()
                .apiKey(apiKey)
                .imageBase64(request.getContent())
                .limit(limit)
                .detProbThreshold(detProbThreshold)
                .facePlugins(facePlugins)
                .status(status)
                .detectFaces(detectFaces)
                .additionalParams(Collections.singletonMap(PREDICTION_COUNT, predictionCount))
                .build();

        return (FacesRecognitionResponseDto) recognitionService.processImage(processImageParams);
    }

    @PostMapping(value = "/recognition/embeddings/recognize", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmbeddingsRecognitionProcessResponse recognizeEmbeddings(
            @ApiParam(value = API_KEY_DESC, required = true)
            @RequestHeader(X_FRS_API_KEY_HEADER)
            final String apiKey,
            @ApiParam(value = PREDICTION_COUNT_DESC, example = NUMBER_VALUE_EXAMPLE)
            @RequestParam(value = PREDICTION_COUNT_REQUEST_PARAM, required = false, defaultValue = PREDICTION_COUNT_DEFAULT_VALUE)
            @Min(value = 1, message = PREDICTION_COUNT_MIN_DESC)
            final Integer predictionCount,
            @RequestBody
            @Valid
            final EmbeddingsRecognitionRequest recognitionRequest
    ) {
        ProcessEmbeddingsParams processParams =
                ProcessEmbeddingsParams.builder()
                                       .apiKey(apiKey)
                                       .embeddings(recognitionRequest.getEmbeddings())
                                       .additionalParams(Collections.singletonMap(PREDICTION_COUNT, predictionCount))
                                       .build();

        return (EmbeddingsRecognitionProcessResponse) recognitionService.processEmbeddings(processParams);
    }
}
