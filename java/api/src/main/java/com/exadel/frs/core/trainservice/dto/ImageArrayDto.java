package com.exadel.frs.core.trainservice.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

import static com.exadel.frs.commonservice.system.global.RegExConstants.ALLOWED_SPECIAL_CHARACTERS;
import static com.exadel.frs.core.trainservice.system.global.Constants.SUBJECT_DESC;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageArrayDto
{

    @ApiParam(value = "image ids ", required = true)
    @JsonProperty("image_ids")
//    @NotBlank(message = "image  ids ")
//    @Size(min = 1, max = 50, message = "Subject name size must be between 1 and 50")
//    @Pattern(regexp = ALLOWED_SPECIAL_CHARACTERS, message = "The name cannot contain the following special characters: ';', '/', '\\'")
    private List<Long> imageIds = new ArrayList<>();
}
