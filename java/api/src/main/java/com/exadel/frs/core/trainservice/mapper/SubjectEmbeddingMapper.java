package com.exadel.frs.core.trainservice.mapper;

import com.exadel.frs.commonservice.projection.SubjectEmbeddingProjection;
import com.exadel.frs.commonservice.projection.SubjectProjection;
import com.exadel.frs.core.trainservice.dto.SubjectSubIdDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface SubjectEmbeddingMapper
{
    SubjectSubIdDto toResponseDto(SubjectEmbeddingProjection projection);
}
