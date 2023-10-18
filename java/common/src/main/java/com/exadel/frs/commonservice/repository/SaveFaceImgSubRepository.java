package com.exadel.frs.commonservice.repository;

import com.exadel.frs.commonservice.entity.SaveFaceImg;
import com.exadel.frs.commonservice.entity.SaveFaceImgSub;
import com.exadel.frs.commonservice.projection.SaveFaceImgProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
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
    @Query(  """
                  select
                        new com.exadel.frs.commonservice.projection.SaveFaceImgProjection(a.id, a.saveFaceImg.deviceId, a.saveFaceImg.timestamp , a.saveFaceImg.imgUrl, a.subImgUrl,  a.gender, a.minAge, a.maxAge, a.similarity, a.boxMinX, a.boxMinY, a.boxMaxX, a.boxMaxY)
                  from
                        SaveFaceImgSub   a
                  where
                        a.saveFaceImg.apiKey = :apiKey
                  and
                        a.saveFaceImg.timestamp between :startTimestamp and :endTimestamp  
                  """ )
    Page<SaveFaceImgProjection> findBySaveFaceImgSubApiKeyBetweenTimestamp(@Param("apiKey") String apiKey , @Param("startTimestamp") Integer startTimestamp, @Param("endTimestamp") Integer endTimestamp,   Pageable pageable);
    /*
where
                        a.apiKey = :apiKey
     */
}
