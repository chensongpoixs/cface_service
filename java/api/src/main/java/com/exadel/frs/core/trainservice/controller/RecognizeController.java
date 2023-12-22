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
import com.exadel.frs.commonservice.entity.Embedding;
import com.exadel.frs.commonservice.entity.SaveFaceImg;
import com.exadel.frs.commonservice.entity.SaveFaceImgSub;
//import com.exadel.frs.commonservice.sdk.storage.feign.StorageFeignClient;
//import com.exadel.frs.commonservice.sdk.storage.feign.StorageFeignClient;
import com.exadel.frs.core.trainservice.dto.*;
import com.exadel.frs.core.trainservice.service.EmbeddingsProcessService;
//import com.exadel.frs.core.trainservice.service.SaveFaceImgService;
import com.exadel.frs.core.trainservice.service.SaveFaceImgService;
import com.exadel.frs.core.trainservice.service.SaveFaceImgSubService;
import com.exadel.frs.core.trainservice.util.MultipartFileToFileUtils;
import io.swagger.annotations.ApiParam;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import liquibase.pro.packaged.D;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
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

    private final Environment env;

//    private final StorageFeignClient storageFeignClient;
    private final SaveFaceImgService saveFaceImgService;
    private final SaveFaceImgSubService saveFaceImgSubService;
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
            @RequestParam(value = FACE_PLUGINS, required = false, defaultValue = "landmarks, gender, age")
            final String facePlugins,
            @ApiParam(value = STATUS_DESC)
            @RequestParam(value = STATUS, required = false, defaultValue = STATUS_DEFAULT_VALUE)
            final Boolean status,
            @ApiParam(value = DETECT_FACES_DESC)
            @RequestParam(value = DETECT_FACES, required = false, defaultValue = DETECT_FACES_DEFAULT_VALUE)
            final Boolean detectFaces,
//            @ApiParam(value = DETECT_FACE_TIMESTAMP)
//            @RequestParam(value = FACE_TIMESTAMP, required = false, defaultValue = "1")
            @ApiParam(value = DETECT_FACE_TIMESTAMP )
            @RequestParam(defaultValue = PREDICTION_COUNT_DEFAULT_VALUE, name = FACE_TIMESTAMP, required = false)
            @Min(value = 1, message = DETECT_FACE_TIMESTAMP)
            final int timestamp,
            @ApiParam(value = DETECT_DeviceID )
            @RequestParam(defaultValue = PREDICTION_COUNT_DEFAULT_VALUE, name = DETECT_DEVICE_ID, required = false)
//            @Min(value = 1, message = DETECT_DeviceID)
            final int device_id

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
        long cur_ms =  System.currentTimeMillis();
        FacesRecognitionResponseDto facesRecognitionResponseDto = (FacesRecognitionResponseDto)recognitionService.processImage(processImageParams);



       if (timestamp > 0)
       {
           new Thread(() ->
           {
               String path = env.getProperty("environment.storage.path");
//               log.info("storage path = " + path);
               if (facesRecognitionResponseDto.getResult().size() > 0)
               {
                   SaveFaceImg saveFaceImg = new SaveFaceImg();
//        saveFaceImg.setId(1L);
                   saveFaceImg.setApiKey(apiKey);
                   saveFaceImg.setTimestamp(timestamp);


                   Date day = new Date();
                   SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd");
                   String file_prefix =    "/"+ sdf.format(day) +"/" + apiKey + "/";
                   SimpleDateFormat  file_prefixDate = new SimpleDateFormat("yyyyMMddHHmmss");
//            String new_file_name = file_prefixDate.format(day) + "_" +UUID.randomUUID();
                   String master_file_name = file_prefixDate.format(day) + "_" +  UUID.randomUUID().toString() /*+ ".jpg"*/;
                   /*获取文件原名称*/
                   String originalFilename = file.getOriginalFilename();
                   /*获取文件格式*/
                   String fileFormat = originalFilename.substring(originalFilename.lastIndexOf("."));
//            String outpath = "D:/Work/cai/face/images/" ;//+ sdf.format(day) +"/" + apiKey + "/";
                   String maser_new_jpg =  MultipartFileToFileUtils.saveMultipartFile(file, path, file_prefix, master_file_name);
                   saveFaceImg.setImgUrl(maser_new_jpg);
                   saveFaceImg.setDeviceId(device_id);
//                   log.info("maser_new_jpg = "+maser_new_jpg+",savefaceimg = " + saveFaceImg.toString());
                   SaveFaceImg newsaveface =   saveFaceImgService.AddSaveFace(saveFaceImg);
                   for(FacePredictionResultDto facePredictionResultDto  :facesRecognitionResponseDto.getResult())
                   {
                       SaveFaceImgSub saveFaceImgSub = new SaveFaceImgSub();
                       saveFaceImgSub.setSaveFaceImg(newsaveface);
                       Embedding embedding = new Embedding();

                       saveFaceImgSub.setGender(1 );
                       saveFaceImgSub.setMinAge(facePredictionResultDto.getAge().getLow());
                       saveFaceImgSub.setMaxAge(facePredictionResultDto.getAge().getHigh());
                       List<FaceSimilarityDto> temp_face = facePredictionResultDto.getSubjects();
                       for (FaceSimilarityDto temp_fce :temp_face)
                       {
                           saveFaceImgSub.setSimilarity(temp_fce.getSimilarity());
                           embedding.setId(UUID.fromString(temp_fce.getEmbeddingId()));
//                saveFaceImgSub.setEmbeddingId(temp_fce.getEmbeddingId());

                           break;
                       }

                       saveFaceImgSub.setEmbeddingId(embedding);
                       String subImgName = "/"+UUID.randomUUID().toString() + fileFormat;
                       log.info("subimage = " + subImgName);
                       MultipartFileToFileUtils.buildSubImage(path + maser_new_jpg , path + file_prefix +master_file_name +subImgName
                               , facePredictionResultDto.getBox().getXMin(), facePredictionResultDto.getBox().getYMin()
                               , facePredictionResultDto.getBox().getXMax() - facePredictionResultDto.getBox().getXMin(), facePredictionResultDto.getBox().getYMax()- facePredictionResultDto.getBox().getYMin(), fileFormat);

//                       facePredictionResultDto.getSubjects();
//                       for (FaceSimilarityDto temp_fce :temp_face)
//                       {
//                           t
////                           temp_fce.getImgUrl();
////                           saveFaceImgSub.setSimilarity(temp_fce.getSimilarity());
////                           embedding.setId(UUID.fromString(temp_fce.getEmbeddingId()));
////                saveFaceImgSub.setEmbeddingId(temp_fce.getEmbeddingId());
//
//                           break;
//                       }
                       saveFaceImgSub.setSubImgUrl(file_prefix +master_file_name + subImgName);
                       saveFaceImgSub.setBoxMinX(facePredictionResultDto.getBox().getXMin());
                       saveFaceImgSub.setBoxMinY(facePredictionResultDto.getBox().getYMin());
                       saveFaceImgSub.setBoxMaxX(facePredictionResultDto.getBox().getXMax());
                       saveFaceImgSub.setBoxMaxY(facePredictionResultDto.getBox().getYMax());
                       log.info("saveface img sub = " + saveFaceImgSub.toString());
                       saveFaceImgSubService.AddSaveFaceImgSub(saveFaceImgSub);
                   }
               }
           }, "save img ").start();
       }
//        log.info("storage send -storage-->>>>> " + storageFeignClient.findFaces(9, 0.9, "d", true));
//        String outpath = "D:/Work/cai/face/images/" ;//+ sdf.format(day) +"/" + apiKey + "/";
//
//        String local_file_name_prefix =  MultipartFileToFileUtils.saveMultipartFile(file, apiKey, outpath);
//
//
//        SaveFaceImg sfaceimg = new SaveFaceImg();
//        sfaceimg.setTimestmap(timestamp);
//        sfaceimg.setApi_key(apiKey);
//        sfaceimg.setImg_url(local_file_name_prefix);
//        sfaceimg.setDevice_id(device_id);
//        SaveFaceImg new_sfaceimg =   saveFaceImgService.AddSaveFace(sfaceimg);
//        log.info(new_sfaceimg.toString());
//        Date day = new Date();
//        SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd");
//        String file_prefix =    sdf.format(day) +"/" + apiKey + "/";
//        for(FacePredictionResultDto facePredictionResultDto  :facesRecognitionResponseDto.getResult())
//        {
//            SaveFaceImgSub saveFaceImgSub = new SaveFaceImgSub();
//            saveFaceImgSub.setMaster_id(new_sfaceimg.getId());
//            saveFaceImgSub.setGender(1 );
//            saveFaceImgSub.setMin_age(facePredictionResultDto.getAge().getLow());
//            saveFaceImgSub.setMax_age(facePredictionResultDto.getAge().getHigh());
//            List<FaceSimilarityDto> temp_face = facePredictionResultDto.getSubjects();
//            for (FaceSimilarityDto temp_fce :temp_face)
//            {
//                saveFaceImgSub.setSimilarity(temp_fce.getSimilarity());
//                saveFaceImgSub.setEmbeddingId(temp_fce.getEmbeddingId());
//
//                break;
//            }
//            MultipartFileToFileUtils.buildSubImage(outpath + local_file_name_prefix , outpath + file_prefix+ "/subimg/chensong_" + UUID.randomUUID().toString() + ".jpg"
//                    , facePredictionResultDto.getBox().getXMin(), facePredictionResultDto.getBox().getYMin()
//                    , facePredictionResultDto.getBox().getXMax() - facePredictionResultDto.getBox().getXMin(), facePredictionResultDto.getBox().getYMax()- facePredictionResultDto.getBox().getYMin());
//            saveFaceImgSub.setBox_min_x(facePredictionResultDto.getBox().getXMin());
//            saveFaceImgSub.setBox_min_y(facePredictionResultDto.getBox().getYMin());
//            saveFaceImgSub.setBox_max_x(facePredictionResultDto.getBox().getXMax());
//            saveFaceImgSub.setBox_max_y(facePredictionResultDto.getBox().getYMax());
//            saveFaceImgSubService.AddSaveFaceImgSub(saveFaceImgSub);
//        }
//        log.info("save img = " + (System.currentTimeMillis() - cur_ms) + " ms");
        // 拦截并保存图片和信息
//        log.info("save face img = " + (System.currentTimeMillis() - cur_ms) + " ms");
        return facesRecognitionResponseDto;
//        return (FacesRecognitionResponseDto) recognitionService.processImage(processImageParams);
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
            @RequestParam(value = FACE_PLUGINS, required = false, defaultValue = "landmarks, gender, age")
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
