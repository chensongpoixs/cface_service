package com.exadel.frs.commonservice.projection;

import com.exadel.frs.commonservice.entity.Embedding;
import com.exadel.frs.commonservice.entity.SaveFaceImg;

import java.util.UUID;

public record SaveFaceImgProjection(long subId, int deviceId, long timestamp, String imgUrl, String subImgUrl,  UUID embeddingId, String subjectName,  int gender, int minAge, int maxAge, Float similarity, int boxMinX, int boxMinY, int boxMaxX, int boxMaxY)
{


//    public static SaveFaceImgProjection from(SaveFaceImg saveFaceImg) {
//        return new SaveFaceImgProjection(
//                embedding.getId(),
//                embedding.getSubject().getSubjectName()
//        );
//    }





}
