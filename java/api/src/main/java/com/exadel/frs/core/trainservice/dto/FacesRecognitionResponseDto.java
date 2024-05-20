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
package com.exadel.frs.core.trainservice.dto;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(NON_NULL)
public class FacesRecognitionResponseDto extends FaceProcessResponse {

    @JsonProperty(value = "plugins_versions")
    private PluginsVersionsDto pluginsVersions;
    private List<FacePredictionResultDto> result;


    public FacesRecognitionResponseDto  builderDto(Double mask_)
    {
        if (this.getResult()==null || this.getResult().isEmpty())
        {
            return this;
        }

        List<FacePredictionResultDto> facePredictionResultDtoList = new ArrayList<>();
        for (FacePredictionResultDto facePredictionResultDto:  this.getResult())
        {
            List<FaceSimilarityDto> faceSimilarityDtos = new ArrayList<>();
            for (FaceSimilarityDto faceSimilarityDto : facePredictionResultDto.subjects)
            {
                if (faceSimilarityDto.getSimilarity() > mask_)
                {
                    faceSimilarityDtos.add(faceSimilarityDto);
                }
            }
            if (faceSimilarityDtos.size() > 0)
            {
                FacePredictionResultDto facePredictionResultDto1 = new FacePredictionResultDto();
                facePredictionResultDto1 = facePredictionResultDto;
                facePredictionResultDto1.setSubjects(faceSimilarityDtos);
                facePredictionResultDtoList.add(facePredictionResultDto1);
            }
//            if ((facePredictionResultDto.getMask() == null)||
//                    (facePredictionResultDto.getMask() != null && facePredictionResultDto.getMask().getProbability()> 0.75))
//            {
//                facePredictionResultDtoList.add(facePredictionResultDto);
//            }

        }
        result = facePredictionResultDtoList;
        return this;
    }
    @Override
    public FacesRecognitionResponseDto prepareResponse(ProcessImageParams processImageParams) {
        if (this.getResult()==null || this.getResult().isEmpty()){
            return this;
        }

        String facePlugins = processImageParams.getFacePlugins();
        if (isEmpty(facePlugins) || !facePlugins.contains(CALCULATOR)) {
            this.getResult().forEach(r -> r.setEmbedding(null));
        }

        if (Boolean.FALSE.equals(processImageParams.getStatus())) {
            this.setPluginsVersions(null);
            this.getResult().forEach(r -> r.setExecutionTime(null));
        }
//        List<FacePredictionResultDto> facePredictionResultDtoList = new ArrayList<>();

//        for (FacePredictionResultDto facePredictionResultDto:  this.getResult())
//        {
//            for (FaceSimilarityDto faceSimilarityDto : facePredictionResultDto.subjects)
//            {
//                if (faceSimilarityDto.getSimilarity() > 0.75)
//                {
//
//                }
//            }
////            if ((facePredictionResultDto.getMask() == null)||
////                    (facePredictionResultDto.getMask() != null && facePredictionResultDto.getMask().getProbability()> 0.75))
////            {
////                facePredictionResultDtoList.add(facePredictionResultDto);
////            }
//
//        }
//        this.setResult(facePredictionResultDtoList);
        return this;
    }
}
