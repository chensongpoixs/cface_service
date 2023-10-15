package com.exadel.frs.core.trainservice.service;

import com.exadel.frs.commonservice.entity.SaveFaceImg;
import com.exadel.frs.commonservice.projection.StorageImgProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


//@Transactional("tmPg")
public interface SaveFaceImgService
{

      SaveFaceImg AddSaveFace(SaveFaceImg saveFaceImg);
//        Page<StorageImgProjection> listStorageImgs(String apiKey, long timestamp, Pageable pageable);
}
