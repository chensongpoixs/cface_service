package com.exadel.frs.core.trainservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceArrayDto
{



        @ApiParam(value = "devide ids ", required = true)
        @JsonProperty("device_ids")
//    @NotBlank(message = "image  ids ")
//    @Size(min = 1, max = 50, message = "Subject name size must be between 1 and 50")
//    @Pattern(regexp = ALLOWED_SPECIAL_CHARACTERS, message = "The name cannot contain the following special characters: ';', '/', '\\'")
        private List<Integer> device_ids = new ArrayList<>();
}
