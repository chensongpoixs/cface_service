package com.exadel.frs.core.trainservice.service;

import com.exadel.frs.commonservice.entity.SaveFaceImgSub;
import com.exadel.frs.commonservice.projection.SaveFaceImgProjection;

import java.util.List;

public interface SaveFaceImgSubService
{
    SaveFaceImgSub AddSaveFaceImgSub(SaveFaceImgSub saveFaceImgSub);
    List<SaveFaceImgProjection> listSaveFaceSubImgs( ) ;
}
