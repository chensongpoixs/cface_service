package com.exadel.frs.core.trainservice.mapper;

import com.exadel.frs.commonservice.projection.SaveFaceImgProjection;
import com.exadel.frs.core.trainservice.dto.StorageImgDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SaveFaceImgMapper
{
    StorageImgDto toResponseDto(SaveFaceImgProjection projection);
}
