package com.exadel.frs.commonservice.projection;

import com.exadel.frs.commonservice.entity.Embedding;

import java.util.UUID;

public record StorageImgProjection(long timestamp, String img_url, long device_id , long gender, long min_age, long max_age, Double similarity, long box_min_x, long box_min_y, long box_max_x, long box_max_y)
{

//    public static StorageImgProjection from(StorageImgDto embedding) {
//        return new StorageImgProjection(
//                embedding.getId(),
//                embedding.getSubject().getSubjectName()
//        );
//    }
}
