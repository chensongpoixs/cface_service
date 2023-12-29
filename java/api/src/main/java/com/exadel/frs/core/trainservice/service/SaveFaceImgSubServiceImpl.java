package com.exadel.frs.core.trainservice.service;

import com.exadel.frs.commonservice.entity.SaveFaceImgSub;
import com.exadel.frs.commonservice.projection.DownloadDataProjection;
import com.exadel.frs.commonservice.projection.SaveFaceImgProjection;
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
    public Page<SaveFaceImgProjection> listSaveFaceSubImgByApiKey(String apiKey, long startTimestamp, long endTimestamp,  Pageable pageable) {

        return  saveFaceImgSubRepository.findBySaveFaceImgSubApiKeyBetweenTimestamp(apiKey, (int) startTimestamp,  (int) endTimestamp,   pageable);
    }

    @Override
    public Page<SaveFaceImgProjection> listSaveFaceSubImgByApiKeyBeteenTimestampAndDeivceIdAndGroupIdsAndSubjectName(String apiKey, long startTimestamp, long endTimestamp, List  deviceIds, List groupIds,  int gender, String subjectName,  int ASCDESC, Pageable pageable) {
//        if (sort> 0)
//        {
//            return saveFaceImgSubRepository.findBySaveFaceImgSubApiKeyBetweenTimestampAndDeviceIdAndGenderAndSubjectNameOrder(apiKey, (int) startTimestamp, (int) endTimestamp, deviceId, gender,  subjectName,  pageable);
//        }
        // TODO@chensong 20231031 写法太露啦
        if (ASCDESC> 0 )
        {
            if ( deviceIds.size()> 0 &&groupIds.size() > 0)
            {
                return saveFaceImgSubRepository.findBySaveFaceImgSubApiKeyBetweenTimestampAndInDeviceIdAndGroupidAndGenderAndSubjectNameDesc (apiKey, (int) startTimestamp, (int) endTimestamp, deviceIds, groupIds, gender,  subjectName,  pageable);

            }
            if (deviceIds.size()> 0)
            {
                return saveFaceImgSubRepository.findBySaveFaceImgSubApiKeyBetweenTimestampAndInDeviceIdAndGenderAndSubjectNameDesc (apiKey, (int) startTimestamp, (int) endTimestamp, deviceIds,  gender,  subjectName,  pageable);

            }
            if (groupIds.size()> 0)
            {
                return saveFaceImgSubRepository.findBySaveFaceImgSubApiKeyBetweenTimestampAndGroupidAndGenderAndSubjectNameDesc (apiKey, (int) startTimestamp, (int) endTimestamp, groupIds,  gender,  subjectName,  pageable);

            }
            return saveFaceImgSubRepository.findBySaveFaceImgSubApiKeyBetweenTimestampAndGenderAndSubjectNameDesc (apiKey, (int) startTimestamp, (int) endTimestamp,  gender,  subjectName,  pageable);

        }
        if (deviceIds.size()> 0&&groupIds.size() > 0)
        {
              return saveFaceImgSubRepository.findBySaveFaceImgSubApiKeyBetweenTimestampAndInDeviceIdAndGroupidAndGenderAndSubjectNameAsc (apiKey, (int) startTimestamp, (int) endTimestamp, deviceIds, groupIds, gender,  subjectName,  pageable);
        }
        if (deviceIds.size()> 0)
        {
            return saveFaceImgSubRepository.findBySaveFaceImgSubApiKeyBetweenTimestampAndInDeviceIdAndGenderAndSubjectNameAsc (apiKey, (int) startTimestamp, (int) endTimestamp, deviceIds,  gender,  subjectName,  pageable);

        }
        if (groupIds.size()> 0)
        {
            return saveFaceImgSubRepository.findBySaveFaceImgSubApiKeyBetweenTimestampAndGroupidAndGenderAndSubjectNameAsc (apiKey, (int) startTimestamp, (int) endTimestamp, groupIds,  gender,  subjectName,  pageable);

        }
        return saveFaceImgSubRepository.findBySaveFaceImgSubApiKeyBetweenTimestampAndGenderAndSubjectNameAsc (apiKey, (int) startTimestamp, (int) endTimestamp, gender,  subjectName,  pageable);
    }

    @Override
    public int removeSaveFaceSubimgByApiAkyAndId(String apiKey, long id) {
        return  saveFaceImgSubRepository.deleteById(id);
    }

    @Override
    public Page<DownloadDataProjection> AllDownloadDataFaceSubImgAndGrupID(String apiKey, long startTimestamp, long endTimestamp, List  deviceIds, List groupIds, int gender, String subjectName,  int ASCDESC, Pageable pageable)
    {
        if (ASCDESC> 0 )
        {
            if ( deviceIds.size()> 0 && groupIds.size() > 0)
            {
                return saveFaceImgSubRepository.findDownloadBySaveFaceImgSubApiKeyBetweenTimestampAndInDeviceIdAndGroupIdAndGenderAndSubjectNameDesc (apiKey, (int) startTimestamp, (int) endTimestamp, deviceIds, groupIds, gender,  subjectName,  pageable);

            }
            if (deviceIds.size() > 0)
            {
                return saveFaceImgSubRepository.findDownloadBySaveFaceImgSubApiKeyBetweenTimestampAndInDeviceIdAndGenderAndSubjectNameDesc (apiKey, (int) startTimestamp, (int) endTimestamp, deviceIds, gender,  subjectName,  pageable);

            }
            if (groupIds.size() > 0)
            {
                return saveFaceImgSubRepository.findDownloadBySaveFaceImgSubApiKeyBetweenTimestampAndGroupIdAndGenderAndSubjectNameDesc (apiKey, (int) startTimestamp, (int) endTimestamp, groupIds, gender,  subjectName,  pageable);

            }
            return saveFaceImgSubRepository.findDownloadBySaveFaceImgSubApiKeyBetweenTimestampAndGenderAndSubjectNameDesc (apiKey, (int) startTimestamp, (int) endTimestamp,  gender,  subjectName,  pageable);

        }
        if ( deviceIds.size()> 0 && groupIds.size() > 0)
        {
            return saveFaceImgSubRepository.findDownloadBySaveFaceImgSubApiKeyBetweenTimestampAndInDeviceIdAndGroupIdAndGenderAndSubjectNameAsc (apiKey, (int) startTimestamp, (int) endTimestamp, deviceIds, groupIds, gender,  subjectName,  pageable);

        }
        else if (deviceIds.size() > 0)
        {
            return saveFaceImgSubRepository.findDownloadBySaveFaceImgSubApiKeyBetweenTimestampAndInDeviceIdAndGenderAndSubjectNameAsc (apiKey, (int) startTimestamp, (int) endTimestamp, deviceIds, gender,  subjectName,  pageable);

        }
        else if (groupIds.size() > 0)
        {
            return saveFaceImgSubRepository.findDownloadBySaveFaceImgSubApiKeyBetweenTimestampAndGroupIdAndGenderAndSubjectNameAsc (apiKey, (int) startTimestamp, (int) endTimestamp, groupIds, gender,  subjectName,  pageable);

        }

        return saveFaceImgSubRepository.findDownloadBySaveFaceImgSubApiKeyBetweenTimestampAndGenderAndSubjectNameAsc (apiKey, (int) startTimestamp, (int) endTimestamp, gender,  subjectName,  pageable);

//        return null;
    }

    @Override
    public List<DownloadDataProjection> listDownloadDataFaceSubImgById(List ids) {
        return saveFaceImgSubRepository.findBySaveFaceImgSubInIds(ids);
    }


    @Override
    public List<DownloadDataProjection> AllDownloadDataFaceSubImgById(List ids)
    {
        return saveFaceImgSubRepository.findBySaveFaceImgSubInIds(ids);
//        return null;
    }



}
