package com.exadel.frs.core.trainservice.service;

import com.exadel.frs.commonservice.entity.SaveFaceImgSub;
import com.exadel.frs.commonservice.projection.SaveFaceImgProjection;
import com.exadel.frs.commonservice.repository.SaveFaceImgRepository;
import com.exadel.frs.commonservice.repository.SaveFaceImgSubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaveFaceImgSubServiceImpl implements SaveFaceImgSubService
{


    @Autowired
    private   SaveFaceImgSubRepository saveFaceImgSubRepository;
    @Override
    public SaveFaceImgSub AddSaveFaceImgSub(SaveFaceImgSub saveFaceImgSub)
    {
        return saveFaceImgSubRepository.save(saveFaceImgSub);

    }

    @Override
    public List<SaveFaceImgProjection> listSaveFaceSubImgs() {

        return   null; //saveFaceImgSubRepository.findBySaveSubImgs();
    }

    @Override
    public Page<SaveFaceImgProjection> listSaveFaceSubImgByApiKey(String apiKey, long startTimestamp, long endTimestamp, Pageable pageable) {

        return  saveFaceImgSubRepository.findBySaveFaceImgSubApiKeyBetweenTimestamp(apiKey, (int) startTimestamp,  (int) endTimestamp, pageable);
    }
}
