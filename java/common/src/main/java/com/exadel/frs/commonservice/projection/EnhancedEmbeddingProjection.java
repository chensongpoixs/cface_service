package com.exadel.frs.commonservice.projection;

import java.util.UUID;

/**
 * @param embeddingData embedding column of embedding table
 */
public record EnhancedEmbeddingProjection(UUID embeddingId, double[] embeddingData, UUID subjectId, String subjectName, String imgUrl, int subId, long createTime) {

}
