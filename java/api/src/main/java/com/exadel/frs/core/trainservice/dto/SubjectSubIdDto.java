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
public class SubjectSubIdDto
{
    @JsonProperty("id") // subtable id
    private UUID id;

    @JsonProperty("subject_name")
    private String subjectName;

    @JsonProperty("sub_id")
    private int subId;

//    @JsonProperty("img_url")
//    private String imgUrl;
}
