package com.exadel.frs.commonservice.projection;

import com.exadel.frs.commonservice.entity.Embedding;
import java.util.UUID;

public record EmbeddingProjection(UUID embeddingId, String subjectName, String imgUrl, int subId) {

    public static EmbeddingProjection from(Embedding embedding) {
        return new EmbeddingProjection(
                embedding.getId(),
                embedding.getSubject().getSubjectName()
                , embedding.getFaceImgUrl()
                , embedding.getSubject().getSubId()
        );
    }

    public static EmbeddingProjection from(EnhancedEmbeddingProjection projection) {
        return new EmbeddingProjection(
                projection.embeddingId(),
                projection.subjectName(),
                projection.imgUrl(),
                projection.subId()
        );
    }

    public EmbeddingProjection withNewSubjectName(String newSubjectName, int subId) {
        return new EmbeddingProjection(
                this.embeddingId(),
                newSubjectName,
                this.imgUrl,
                subId
        );
    }
}
