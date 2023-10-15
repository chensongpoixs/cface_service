package com.exadel.frs.core.trainservice.service;

import com.exadel.frs.commonservice.entity.SaveFaceImg;
import com.exadel.frs.commonservice.projection.EmbeddingProjection;
import com.exadel.frs.commonservice.projection.StorageImgProjection;
import com.exadel.frs.commonservice.repository.SaveFaceImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveFaceImgServiceImpl implements SaveFaceImgService
{

    @Autowired
    private SaveFaceImgRepository saveFaceImgRepository;

    @Override
    public SaveFaceImg AddSaveFace(SaveFaceImg saveFaceImg)
    {
        return  saveFaceImgRepository.save(saveFaceImg);
    }

//    @Override
//    public Page<StorageImgProjection> listStorageImgs(String apiKey, long timestamp, Pageable pageable) {
//        return saveFaceImgRepository.findSaveFaceImgINfoApiKey(apiKey, timestamp, pageable);
//    }


//    public Page<StorageImgProjection> listStorageImgs(String apiKey, long timestamp, Pageable pageable) {
//        return saveFaceImgRepository.findSaveFaceImgINfoApiKey(apiKey, timestamp, pageable);
//    }

//    @Override
//    public void AddSaveFace(SaveFaceImg saveFaceImg)
//    {
//        saveFaceImgRepository.save(saveFaceImg);
//    }
}
