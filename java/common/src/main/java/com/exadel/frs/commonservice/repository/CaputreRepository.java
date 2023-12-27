package com.exadel.frs.commonservice.repository;

import com.exadel.frs.commonservice.entity.CaputreImg;
import com.exadel.frs.commonservice.entity.SaveFaceImg;
import com.exadel.frs.commonservice.projection.CaputreImgProjection;
import com.exadel.frs.commonservice.projection.DownloadDataProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CaputreRepository extends JpaRepository<CaputreImg, Long>
{

    @Query(  """
                  select
                        new com.exadel.frs.commonservice.projection.CaputreImgProjection(a.id, a.timestamp , a.imgUrl, a.deviceId)
                  from
                        SaveFaceImg   a
                  where
                        a.apiKey = :apiKey
                  and
                        a.timestamp between :startTimestamp and :endTimestamp
                  order by
                        a.timestamp asc
                  """ )
    Page<CaputreImgProjection> findDownloadBySaveFaceImgSubApiKeyBetweenTimestampAndGenderAndSubjectNameAsc (String apiKey , Integer startTimestamp, Integer endTimestamp, Pageable pageable);
    @Query(  """
                  select
                        new com.exadel.frs.commonservice.projection.CaputreImgProjection(a.id, a.timestamp , a.imgUrl, a.deviceId)
                  from
                        SaveFaceImg   a
                  where
                        a.apiKey = :apiKey
                  and
                        a.timestamp between :startTimestamp and :endTimestamp
                  and
                         a.deviceId in  (:deviceIds)
                  order by
                        a.timestamp asc
                  """ )
    Page<CaputreImgProjection> findDownloadBySaveFaceImgSubApiKeyBetweenTimestampAndInDeviceIdAndGenderAndSubjectNameAsc (String apiKey , Integer startTimestamp, Integer endTimestamp, List deviceIds,  Pageable pageable);

    //(cast(:deviceId as string) is '-1' or
    @Query(  """
                  select
                        new com.exadel.frs.commonservice.projection.CaputreImgProjection(a.id, a.timestamp , a.imgUrl, a.deviceId)
                  from
                        SaveFaceImg   a
                  where
                        a.apiKey = :apiKey
                  and
                        a.timestamp between :startTimestamp and :endTimestamp
                  order by
                        a.timestamp desc
                  """ )
    Page<CaputreImgProjection> findDownloadBySaveFaceImgSubApiKeyBetweenTimestampAndGenderAndSubjectNameDesc ( String apiKey , Integer startTimestamp,  Integer endTimestamp,        Pageable pageable);
    @Query(  """
                  select
                       new com.exadel.frs.commonservice.projection.CaputreImgProjection(a.id, a.timestamp , a.imgUrl, a.deviceId)
                  from
                        SaveFaceImg   a
                  where
                        a.apiKey = :apiKey
                  and
                        a.timestamp between :startTimestamp and :endTimestamp
                  and
                         a.deviceId in  (:deviceIds)
                  order by
                        a.timestamp desc
                  """ )
    Page<CaputreImgProjection> findDownloadBySaveFaceImgSubApiKeyBetweenTimestampAndInDeviceIdAndGenderAndSubjectNameDesc ( String apiKey , Integer startTimestamp,  Integer endTimestamp,   List  deviceIds,    Pageable pageable);



}
