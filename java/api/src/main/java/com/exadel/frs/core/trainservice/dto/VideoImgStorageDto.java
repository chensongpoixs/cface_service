package com.exadel.frs.core.trainservice.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoImgStorageDto
{
    @JsonProperty("id") // subtable id
    private long id;

    @JsonProperty("device_id")
    private int device_id;

    @JsonProperty("timestamp")
    private int timestamp;

    @JsonProperty("img_url")
    private String imgUrl;

}
