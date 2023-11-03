package com.exadel.frs.commonservice.projection;

import java.util.UUID;

public record SubjectEmbeddingProjection(UUID id, String subjectName, int subId, String imgUrl)
{
}
