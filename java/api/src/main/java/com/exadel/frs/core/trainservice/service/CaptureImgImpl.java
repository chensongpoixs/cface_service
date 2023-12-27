package com.exadel.frs.core.trainservice.service;

import com.exadel.frs.commonservice.projection.CaputreImgProjection;
import com.exadel.frs.commonservice.repository.CaputreRepository;
import com.exadel.frs.commonservice.repository.SaveFaceImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CaptureImgImpl
{
    @Autowired
    private CaputreRepository caputreRepository;

    @Transactional
    public Page<CaputreImgProjection> AllListFaceSubImg(String apiKey, int startTimestamp, int endTimestamp, List deviceIds, int ASCDESC, Pageable pageable)
    {
        return caputreRepository.findDownloadBySaveFaceImgSubApiKeyBetweenTimestampAndGenderAndSubjectNameAsc(apiKey, startTimestamp, endTimestamp, pageable);
    }

}
