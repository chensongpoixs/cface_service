package com.exadel.frs.core.trainservice.service;

import com.exadel.frs.commonservice.entity.SaveFaceImg;
import com.exadel.frs.commonservice.projection.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


//@Transactional("tmPg")
public interface SaveFaceImgService
{
      Optional<SaveFaceImg> findById(long id);
      int deleteByApiKeyAndId(String api_key, long id);
      SaveFaceImg AddSaveFace(SaveFaceImg saveFaceImg);
      Page<SaveFaceImg> listSaveFaceImgs(String apiKey, String subjectName, Pageable pageable);
//      List<SaveFaceImg>  listSaveFaceImgs(String apiKey);
      Page<CaputreImgProjection> AllListFaceSubImg(String apiKey, long startTimestamp, long endTimestamp, List  deviceIds, int ASCDESC, Pageable pageable);
      Page<SaveFaceImgProjection> listSaveFaceImgs(String apiKey,   Pageable pageable) ;
      List<SaveFaceImgProjection> listSaveFaceImgs( ) ;
//        Page<StorageImgProjection> listStorageImgs(String apiKey, long timestamp, Pageable pageable);
}
