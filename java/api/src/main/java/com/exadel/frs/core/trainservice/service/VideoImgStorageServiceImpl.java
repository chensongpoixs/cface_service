package com.exadel.frs.core.trainservice.service;


import com.exadel.frs.commonservice.entity.SaveFaceImg;
import com.exadel.frs.commonservice.entity.SaveFaceImgSub;
import com.exadel.frs.commonservice.entity.VideoImgStorageTable;
import com.exadel.frs.commonservice.projection.SaveFaceImgProjection;
import com.exadel.frs.commonservice.projection.VideoImgStorageProjection;
import com.exadel.frs.commonservice.repository.SaveFaceImgSubRepository;
import com.exadel.frs.commonservice.repository.VideoImgStorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoImgStorageServiceImpl
{
    @Autowired
    private VideoImgStorageRepository videoImgStorageRepository;


    public VideoImgStorageTable AddSaveFaceImgSub(VideoImgStorageTable videoImgStorageTable)
    {
        return videoImgStorageRepository.save(videoImgStorageTable);

    }

//    @Override
//    public List<SaveFaceImgProjection> listSaveFaceSubImgs() {
//
//        return   null; //saveFaceImgSubRepository.findBySaveSubImgs();
//    }

    public VideoImgStorageTable AddVideoImgStorage(VideoImgStorageTable videoImgStorageTable)
    {
        return  videoImgStorageRepository.save(videoImgStorageTable);
    }

    public Page<VideoImgStorageProjection> listStorageVideoImgAndDeiveIdAndTimestamp(List deivice_id, long startTimestamp, long endTimestamp, Pageable pageable) {

        if (deivice_id.size()> 0)
        {
            return  videoImgStorageRepository.findByVideoImgStorageAndIdBetweenTimestamp(deivice_id, (int) startTimestamp,  (int) endTimestamp, pageable);

        }
        return  videoImgStorageRepository.findByVideoImgStorageBetweenTimestamp( (int) startTimestamp,  (int) endTimestamp, pageable);

    }
}
