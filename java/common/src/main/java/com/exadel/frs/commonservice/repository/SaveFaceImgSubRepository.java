package com.exadel.frs.commonservice.repository;

import com.exadel.frs.commonservice.entity.SaveFaceImg;
import com.exadel.frs.commonservice.entity.SaveFaceImgSub;
import com.exadel.frs.commonservice.projection.SaveFaceImgProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface SaveFaceImgSubRepository extends JpaRepository<SaveFaceImgSub, Long>
{
//long subId, long deviceId, long timestamp,   String imgUrl,
// Integer gender, long minAge, long maxAge, Double similarity,
// long boxMinX, long boxMinY, long boxMaxX, long boxMaxY
//    @Query(  """
//                  select
//                        new com.exadel.frs.commonservice.projection.SaveFaceImgProjection(a.id, a.saveFaceImg.deviceId, a.saveFaceImg.timestamp , a.saveFaceImg.imgUrl, a.subImgUrl  a.gender, a.minAge, a.maxAge, a.similarity, a.boxMinX, a.boxMinY, a.boxMaxX, a.boxMaxY)
//                  from
//                        SaveFaceImgSub   a
//                  """ )
//    List<SaveFaceImgProjection> findBySaveSubImgs();
    // https://blog.csdn.net/qq_52736300/article/details/130653916
    //a.embeddingId.id, a.embeddingId.subject.subjectName,
    @Query(  """
                  select
                        new com.exadel.frs.commonservice.projection.SaveFaceImgProjection(a.id, a.saveFaceImg.deviceId, a.saveFaceImg.timestamp , a.saveFaceImg.imgUrl, a.subImgUrl, a.embeddingId.id, a.embeddingId.subject.subjectName, a.gender, a.minAge, a.maxAge, a.similarity, a.boxMinX, a.boxMinY, a.boxMaxX, a.boxMaxY)
                  from
                        SaveFaceImgSub   a
                  where
                        a.saveFaceImg.apiKey = :apiKey
                  and
                        a.saveFaceImg.timestamp between :startTimestamp and :endTimestamp  
                  """ )
    Page<SaveFaceImgProjection> findBySaveFaceImgSubApiKeyBetweenTimestamp(@Param("apiKey") String apiKey , @Param("startTimestamp") Integer startTimestamp, @Param("endTimestamp") Integer endTimestamp,  Pageable pageable);









    @Query(  """
                  select
                        new com.exadel.frs.commonservice.projection.SaveFaceImgProjection(a.id, a.saveFaceImg.deviceId, a.saveFaceImg.timestamp , a.saveFaceImg.imgUrl, a.subImgUrl, a.embeddingId.id, a.embeddingId.subject.subjectName, a.gender, a.minAge, a.maxAge, a.similarity, a.boxMinX, a.boxMinY, a.boxMaxX, a.boxMaxY)
                  from
                        SaveFaceImgSub   a
                  where
                        a.saveFaceImg.apiKey = :apiKey
                  and
                        a.saveFaceImg.timestamp between :startTimestamp and :endTimestamp  
                  and 
                        a.saveFaceImg.deviceId = :deviceId
                  """ )
    Page<SaveFaceImgProjection> findBySaveFaceImgSubApiKeyBetweenTimestampAndDeviceId(@Param("apiKey") String apiKey , @Param("startTimestamp") Integer startTimestamp, @Param("endTimestamp") Integer endTimestamp, @Param("deviceId") Integer deviceId,  Pageable pageable);


    /*
                      and
                        (cast(:deviceId as int) is -1 or a.saveFaceImg.deviceId = :deviceId)
                  and
                         (cast(:gender as int) is 0 or a.gender = :gender)

                         AndDeviceIdAndGender
     */

    @Query(  """
                  select
                        new com.exadel.frs.commonservice.projection.SaveFaceImgProjection(a.id, a.saveFaceImg.deviceId, a.saveFaceImg.timestamp , a.saveFaceImg.imgUrl, a.subImgUrl, a.embeddingId.id, a.embeddingId.subject.subjectName, a.gender, a.minAge, a.maxAge, a.similarity, a.boxMinX, a.boxMinY, a.boxMaxX, a.boxMaxY)
                  from
                        SaveFaceImgSub   a
                  where
                        a.saveFaceImg.apiKey = :apiKey
                  and
                        a.saveFaceImg.timestamp between :startTimestamp and :endTimestamp 
                  and
                        (cast(:gender as string) is '0' or a.gender = :gender)
                  and
                         (cast(:subjectName as string) is null or a.embeddingId.subject.subjectName = :subjectName) 
                  order by
                        a.saveFaceImg.timestamp asc
                  """ )
    Page<SaveFaceImgProjection> findBySaveFaceImgSubApiKeyBetweenTimestampAndGenderAndSubjectNameAsc ( String apiKey , Integer startTimestamp,  Integer endTimestamp,   Integer gender, String subjectName,    Pageable pageable);
    @Query(  """
                  select
                        new com.exadel.frs.commonservice.projection.SaveFaceImgProjection(a.id, a.saveFaceImg.deviceId, a.saveFaceImg.timestamp , a.saveFaceImg.imgUrl, a.subImgUrl, a.embeddingId.id, a.embeddingId.subject.subjectName, a.gender, a.minAge, a.maxAge, a.similarity, a.boxMinX, a.boxMinY, a.boxMaxX, a.boxMaxY)
                  from
                        SaveFaceImgSub   a
                  where
                        a.saveFaceImg.apiKey = :apiKey
                  and
                        a.saveFaceImg.timestamp between :startTimestamp and :endTimestamp
                  and
                         a.saveFaceImg.deviceId in  (:deviceIds)
                  and
                        (cast(:gender as string) is '0' or a.gender = :gender)
                  and
                         (cast(:subjectName as string) is null or a.embeddingId.subject.subjectName = :subjectName) 
                  order by
                        a.saveFaceImg.timestamp asc
                  """ )
    Page<SaveFaceImgProjection> findBySaveFaceImgSubApiKeyBetweenTimestampAndInDeviceIdAndGenderAndSubjectNameAsc ( String apiKey , Integer startTimestamp,  Integer endTimestamp,   List deviceIds, Integer gender, String subjectName,    Pageable pageable);

    //(cast(:deviceId as string) is '-1' or
    @Query(  """
                  select
                        new com.exadel.frs.commonservice.projection.SaveFaceImgProjection(a.id, a.saveFaceImg.deviceId, a.saveFaceImg.timestamp , a.saveFaceImg.imgUrl, a.subImgUrl, a.embeddingId.id, a.embeddingId.subject.subjectName, a.gender, a.minAge, a.maxAge, a.similarity, a.boxMinX, a.boxMinY, a.boxMaxX, a.boxMaxY)
                  from
                        SaveFaceImgSub   a
                  where
                        a.saveFaceImg.apiKey = :apiKey
                  and
                        a.saveFaceImg.timestamp between :startTimestamp and :endTimestamp 
                  and
                        (cast(:gender as string) is '0' or a.gender = :gender)
                  and
                         (cast(:subjectName as string) is null or a.embeddingId.subject.subjectName = :subjectName)
                  order by
                        a.saveFaceImg.timestamp desc
                  """ )
    Page<SaveFaceImgProjection> findBySaveFaceImgSubApiKeyBetweenTimestampAndGenderAndSubjectNameDesc ( String apiKey , Integer startTimestamp,  Integer endTimestamp,     Integer gender, String subjectName,    Pageable pageable);
    @Query(  """
                  select
                        new com.exadel.frs.commonservice.projection.SaveFaceImgProjection(a.id, a.saveFaceImg.deviceId, a.saveFaceImg.timestamp , a.saveFaceImg.imgUrl, a.subImgUrl, a.embeddingId.id, a.embeddingId.subject.subjectName, a.gender, a.minAge, a.maxAge, a.similarity, a.boxMinX, a.boxMinY, a.boxMaxX, a.boxMaxY)
                  from
                        SaveFaceImgSub   a
                  where
                        a.saveFaceImg.apiKey = :apiKey
                  and
                        a.saveFaceImg.timestamp between :startTimestamp and :endTimestamp
                  and
                         a.saveFaceImg.deviceId in  (:deviceIds)
                  and
                        (cast(:gender as string) is '0' or a.gender = :gender)
                  and
                         (cast(:subjectName as string) is null or a.embeddingId.subject.subjectName = :subjectName)
                  order by
                        a.saveFaceImg.timestamp desc
                  """ )
    Page<SaveFaceImgProjection> findBySaveFaceImgSubApiKeyBetweenTimestampAndInDeviceIdAndGenderAndSubjectNameDesc ( String apiKey , Integer startTimestamp,  Integer endTimestamp,   List  deviceIds, Integer gender, String subjectName,    Pageable pageable);

    //    @Query(  """
//                  select
//                        new com.exadel.frs.commonservice.projection.SaveFaceImgProjection(a.id, a.saveFaceImg.deviceId, a.saveFaceImg.timestamp , a.saveFaceImg.imgUrl, a.subImgUrl, a.embeddingId.id, a.embeddingId.subject.subjectName, a.gender, a.minAge, a.maxAge, a.similarity, a.boxMinX, a.boxMinY, a.boxMaxX, a.boxMaxY)
//                  from
//                        SaveFaceImgSub   a
//                  where
//                        a.saveFaceImg.apiKey = :apiKey
//                  and
//                        a.saveFaceImg.timestamp between :startTimestamp and :endTimestamp
//                  and
//                        (cast(:deviceId as string) is '-1' or a.saveFaceImg.deviceId = :deviceId)
//                  and
//                        (cast(:gender as string) is '0' or a.gender = :gender)
//                  and
//                         (cast(:subjectName as string) is null or a.embeddingId.subject.subjectName = :subjectName)
//                  ORDER BY
//                        a.saveFaceImg.timestamp desc
//                  """ )
//    Page<SaveFaceImgProjection> findBySaveFaceImgSubApiKeyBetweenTimestampAndDeviceIdAndGenderAndSubjectNameOrder( String apiKey , Integer startTimestamp,  Integer endTimestamp,   Integer deviceId, Integer gender, String subjectName,    Pageable pageable);

    @Modifying
    @Query("delete from SaveFaceImgSub a where a.id = :Id")
    int deleteById(@Param("Id") long Id);

    /*
where
                        a.apiKey = :apiKey
     */
}
