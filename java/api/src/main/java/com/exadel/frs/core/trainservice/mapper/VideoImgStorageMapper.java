package com.exadel.frs.core.trainservice.mapper;


import com.exadel.frs.commonservice.projection.SaveFaceImgProjection;
import com.exadel.frs.commonservice.projection.VideoImgStorageProjection;
import com.exadel.frs.commonservice.repository.VideoImgStorageRepository;
import com.exadel.frs.core.trainservice.dto.StorageImgDto;
import com.exadel.frs.core.trainservice.dto.VideoImgStorageDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VideoImgStorageMapper
{
    VideoImgStorageDto toResponseDto(VideoImgStorageProjection projection);
}
