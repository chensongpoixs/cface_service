package com.exadel.frs.core.trainservice.service;

import com.exadel.frs.commonservice.entity.SaveFaceImg;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


//@Transactional("tmPg")
public interface SaveFaceImgService
{

      void AddSaveFace(SaveFaceImg saveFaceImg);
}
