package com.exadel.frs.core.trainservice.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StorageImgDto
{

    // As of backward compatibility we are not allowed to rename property 'image_id' --> 'embedding_id'
    // but, notice, actually it is Embedding.id
    @JsonProperty("timestamp")
    private long timestamp;

    @JsonProperty("master_url")
    private String master_url;

    @JsonProperty("device_id")
    private long device_id;


    ////////////////////////////////////////////////subtable///////////////////////////////////////////////////////

//    @JsonProperty("subject")
//    private String subjectName;


    /////////////////////////////////////////////////////////////////////////////////
    @JsonProperty("gender")
    private long gender;

    @JsonProperty("min_age")
    private long min_age;


    @JsonProperty("max_age")
    private long max_age;


    @JsonProperty("similarity")
    private Double similarity;


    @JsonProperty("box_min_x")
    private long box_min_x;


    @JsonProperty("box_min_y")
    private long box_min_y;


    @JsonProperty("box_max_x")
    private long box_max_x;


    @JsonProperty("box_max_y")
    private long box_max_y;

//    public <U> StorageImgDto(Page<U> map) {
//
//    }


//    public <U> StorageImgDto(Page<U> map) {
//    }
}
