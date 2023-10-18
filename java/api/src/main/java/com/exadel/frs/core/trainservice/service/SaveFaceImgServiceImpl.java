package com.exadel.frs.core.trainservice.service;

import com.exadel.frs.commonservice.entity.SaveFaceImg;
import com.exadel.frs.commonservice.projection.EmbeddingProjection;
import com.exadel.frs.commonservice.projection.SaveFaceImgProjection;
import com.exadel.frs.commonservice.projection.StorageImgProjection;
import com.exadel.frs.commonservice.repository.SaveFaceImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
//通过findAll方法的Sort类进行排序，根据实体类字段进行排序。descending降序，ascending升序，默认不填为ascending升序
public class SaveFaceImgServiceImpl implements SaveFaceImgService
{

    @Autowired
    private SaveFaceImgRepository saveFaceImgRepository;

    @Override
    public Optional<SaveFaceImg> findById(long id) {
        return  saveFaceImgRepository.findById(id);
    }

    @Override
    public SaveFaceImg AddSaveFace(SaveFaceImg saveFaceImg)
    {
        return  saveFaceImgRepository.save(saveFaceImg);
    }

    @Override
    public Page<SaveFaceImg> listSaveFaceImgs(String apiKey, String subjectName, Pageable pageable)
    {
        return saveFaceImgRepository.findAll(pageable);
//        return null;
    }

    @Override
    public List<SaveFaceImg> listSaveFaceImgs(String apiKey) {

        return saveFaceImgRepository.findAll();
    }

    @Override
    public Page<SaveFaceImgProjection> listSaveFaceImgs(String apiKey, Pageable pageable) {
        return null; //saveFaceImgRepository.findBySaveImgsMasterId(pageable);
    }

    @Override
    public List<SaveFaceImgProjection> listSaveFaceImgs() {
        return  null; //saveFaceImgRepository.findBySaveImgs();
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
