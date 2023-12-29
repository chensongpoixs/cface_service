package com.exadel.frs.core.trainservice.service;

import com.exadel.frs.commonservice.entity.SaveFaceImgSub;
import com.exadel.frs.commonservice.projection.DownloadDataProjection;
import com.exadel.frs.commonservice.projection.SaveFaceImgProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SaveFaceImgSubService
{
    SaveFaceImgSub AddSaveFaceImgSub(SaveFaceImgSub saveFaceImgSub);
    List<SaveFaceImgProjection> listSaveFaceSubImgs( ) ;


    Page<SaveFaceImgProjection>  listSaveFaceSubImgByApiKey(String apiKey, long startTimestamp, long endTimestamp, Pageable pageable);
    Page<SaveFaceImgProjection>  listSaveFaceSubImgByApiKeyBeteenTimestampAndDeivceIdAndGroupIdsAndSubjectName(String apiKey, long startTimestamp, long endTimestamp, List  deviceIds, List groupIds, int gender, String subjectName, int ASCDESC,   Pageable pageable);

    int removeSaveFaceSubimgByApiAkyAndId(String apiKey, long id);


    public Page<DownloadDataProjection> AllDownloadDataFaceSubImgAndGrupID(String apiKey, long startTimestamp, long endTimestamp, List  deviceIds, List groupIds, int gender, String subjectName,  int ASCDESC, Pageable pageable);
    List<DownloadDataProjection> listDownloadDataFaceSubImgById(List ids);

    List<DownloadDataProjection> AllDownloadDataFaceSubImgById(List ids);
}
