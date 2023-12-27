package com.exadel.frs.core.trainservice.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CaputreDto
{
    @JsonProperty("id")
    private long id;

    @JsonProperty("timestamp")
    private long timestamp;

    @JsonProperty("img_url")
    private String imgUrl;
    @JsonProperty("device_id")
    private int device_id;
}
