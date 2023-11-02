package com.exadel.frs.core.trainservice.dto;

import static com.exadel.frs.commonservice.system.global.RegExConstants.ALLOWED_SPECIAL_CHARACTERS;
import static com.exadel.frs.core.trainservice.system.global.Constants.SUBJECT_DESC;
import static com.exadel.frs.core.trainservice.system.global.Constants.SUBJECT_SUB_ID_DESC;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiParam;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDto {

//    private UUID id;


    @ApiParam(value = SUBJECT_DESC, required = true)
    @JsonProperty("subject")
    @NotBlank(message = "Subject name cannot be empty")
    @Size(min = 1, max = 50, message = "Subject name size must be between 1 and 50")
    @Pattern(regexp = ALLOWED_SPECIAL_CHARACTERS, message = "The name cannot contain the following special characters: ';', '/', '\\'")
    private String subjectName;


    @ApiParam(value = SUBJECT_SUB_ID_DESC, required = true)
    @JsonProperty("subid")
    @NotBlank(message = "Subject subId cannot be empty")

    private long subId;

//    private String imgUrl;
}
