package com.exadel.frs.core.trainservice.service;

import com.exadel.frs.commonservice.entity.SaveFaceImg;
import com.exadel.frs.commonservice.repository.SaveFaceImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveFaceImgServiceImpl implements SaveFaceImgService
{

    @Autowired
    private SaveFaceImgRepository saveFaceImgRepository;

    @Override
    public void AddSaveFace(SaveFaceImg saveFaceImg)
    {
        saveFaceImgRepository.save(saveFaceImg);
    }

//    @Override
//    public void AddSaveFace(SaveFaceImg saveFaceImg)
//    {
//        saveFaceImgRepository.save(saveFaceImg);
//    }
}
