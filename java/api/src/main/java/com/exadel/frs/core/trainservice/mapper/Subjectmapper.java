package com.exadel.frs.core.trainservice.mapper;

import com.exadel.frs.commonservice.projection.SaveFaceImgProjection;
import com.exadel.frs.commonservice.projection.SubjectProjection;
import com.exadel.frs.core.trainservice.dto.StorageImgDto;
import com.exadel.frs.core.trainservice.dto.SubjectDto;
import com.exadel.frs.core.trainservice.dto.SubjectSubIdDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface Subjectmapper
{
    SubjectSubIdDto toResponseDto(SubjectProjection projection);
}
