package com.exadel.frs.commonservice.repository;

import com.exadel.frs.commonservice.entity.SaveFaceImg;
import com.exadel.frs.commonservice.entity.SaveFaceImgSub;
import com.exadel.frs.commonservice.projection.SaveFaceImgProjection;
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
    @Query(  """
                  select
                        new com.exadel.frs.commonservice.projection.SaveFaceImgProjection(a.id, a.saveFaceImg.deviceId, a.saveFaceImg.timestamp , a.saveFaceImg.imgUrl,  a.gender, a.minAge, a.maxAge, a.similarity, a.boxMinX, a.boxMinY, a.boxMaxX, a.boxMaxY)
                  from
                        SaveFaceImgSub   a 
                  """ )
    List<SaveFaceImgProjection> findBySaveSubImgs();
//    @Query(  """
//                  select
//                        new com.exadel.frs.commonservice.projection.SaveFaceImgProjection(a.gender, a.saveFaceImg.imgUrl)
//                  from
//                        SaveFaceImgSub   a
//                  where
//                        a.apiKey = :apiKey
//                  """ )
//    List<SaveFaceImgProjection> findBySaveSubImgsApiKey(@Param("apiKey") String apiKey );
}
