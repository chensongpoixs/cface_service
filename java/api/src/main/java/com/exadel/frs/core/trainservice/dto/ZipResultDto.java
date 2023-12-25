package com.exadel.frs.core.trainservice.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZipResultDto
{
    @JsonProperty("result") // subtable id
    private int result;

    @JsonProperty("ZipFaceDto")
    private List<ZipFaceDto> zipFaceDtos;
}
