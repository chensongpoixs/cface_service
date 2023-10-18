package com.exadel.frs.core.trainservice.service;

import com.exadel.frs.commonservice.entity.Embedding;
import com.exadel.frs.commonservice.entity.SaveFaceImg;
import com.exadel.frs.commonservice.entity.SaveFaceImgSub;
import com.exadel.frs.core.trainservice.EmbeddedPostgreSQLTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

@Slf4j
@Transactional
public class SaveFaceImgSubServiceImplTest extends EmbeddedPostgreSQLTest
{
    @Autowired
    private   SaveFaceImgSubService saveFaceImgSubService;

    @Test
    //@ParameterizedTest
    void    TestSaveImgSubTable()
    {
        SaveFaceImg saveFaceImg = new SaveFaceImg();
//        saveFaceImg.setId(4L);
//        saveFaceImg.setApi_key("===========api_key======");
//        saveFaceImg.setTimestmap(766767);
////        saveFaceImg.setImg_url("imgurl");
//        saveFaceImg.setDevice_id(5);
////        Embedding embedding = new Embedding();
////        Embedding
//        SaveFaceImgSub saveFaceImgSub = new SaveFaceImgSub();
////        saveFaceImgSub.setSaveFaceImg(saveFaceImg);
//        saveFaceImgSub.setGender(1);
//        saveFaceImgSub.setMin_age(2);
//        saveFaceImgSub.setMax_age(4);
//        saveFaceImgSub.setSimilarity(4.4f);
//        saveFaceImgSub.setBox_min_x(4);
//        saveFaceImgSub.setBox_min_y(3);
//        saveFaceImgSub.setBox_max_x(34);
//        saveFaceImgSub.setBox_max_y(44);
//        SaveFaceImgSub newsaveface =   saveFaceImgSubService.AddSaveFaceImgSub(saveFaceImgSub);
//        log.info(newsaveface.toString());
    }
}
