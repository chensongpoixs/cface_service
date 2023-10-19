package com.exadel.frs.core.trainservice.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StorageImgDto
{

    // As of backward compatibility we are not allowed to rename property 'image_id' --> 'embedding_id'
    // but, notice, actually it is Embedding.id
    @JsonProperty("id") // subtable id
    private long subId;

    @JsonProperty("device_id")
    private int deviceId;

    @JsonProperty("timestamp")
    private int timestamp;

    @JsonProperty("img_url")
    private String imgUrl;




    ////////////////////////////////////////////////subtable///////////////////////////////////////////////////////

   @JsonProperty("sub_img_url")
    private String subImgUrl;

    ////////////////////////
    @JsonProperty("embedding_id")
    private UUID embeddingId;


    @JsonProperty("subject_name")
    private String subjectName;
    ///////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////
    @JsonProperty("gender")
    private int gender;

    @JsonProperty("min_age")
    private int minAge;


    @JsonProperty("max_age")
    private int maxAge;


    @JsonProperty("similarity")
    private float similarity;


    @JsonProperty("box_min_x")
    private int boxMinX;


    @JsonProperty("box_min_y")
    private int boxMinY;


    @JsonProperty("box_max_x")
    private int boxMaxX;


    @JsonProperty("box_max_y")
    private int boxMaxY;

//    public <U> StorageImgDto(Page<U> map) {
//
//    }


//    public <U> StorageImgDto(Page<U> map) {
//    }
}
