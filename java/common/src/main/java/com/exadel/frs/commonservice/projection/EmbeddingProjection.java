package com.exadel.frs.commonservice.projection;

import com.exadel.frs.commonservice.entity.Embedding;
import java.util.UUID;

public record EmbeddingProjection(UUID embeddingId, UUID subjectId, String subjectName, String imgUrl, int subId, long createTime) {

    public static EmbeddingProjection from(Embedding embedding) {
        return new EmbeddingProjection(
                embedding.getId(),
                embedding.getSubject().getId(),
                embedding.getSubject().getSubjectName()
                , embedding.getFaceImgUrl()
                , embedding.getSubject().getSubId()
                , embedding.getSubject().getCreateTime()
        );
    }

    public static EmbeddingProjection from(EnhancedEmbeddingProjection projection) {
        return new EmbeddingProjection(
                projection.embeddingId(),
                projection.subjectId(),
                projection.subjectName(),
                projection.imgUrl(),
                projection.subId(),
                projection.createTime()
        );
    }

    public EmbeddingProjection withNewSubjectName(String newSubjectName, int subId) {
        return new EmbeddingProjection(
                this.embeddingId(),
                this.subjectId(),
                newSubjectName,
                this.imgUrl,
                subId,
                this.createTime()
        );
    }
}
