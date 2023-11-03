package com.exadel.frs.commonservice.projection;

import com.exadel.frs.commonservice.entity.Embedding;
import java.util.UUID;

public record EmbeddingProjection(UUID embeddingId, String subjectName, String imgUrl) {

    public static EmbeddingProjection from(Embedding embedding) {
        return new EmbeddingProjection(
                embedding.getId(),
                embedding.getSubject().getSubjectName()
                , embedding.getFaceImgUrl()
        );
    }

    public static EmbeddingProjection from(EnhancedEmbeddingProjection projection) {
        return new EmbeddingProjection(
                projection.embeddingId(),
                projection.subjectName(),
                projection.imgUrl()
        );
    }

    public EmbeddingProjection withNewSubjectName(String newSubjectName) {
        return new EmbeddingProjection(
                this.embeddingId(),
                newSubjectName,
                this.imgUrl
        );
    }
}
