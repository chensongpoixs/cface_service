package com.exadel.frs.commonservice.repository;


import com.exadel.frs.commonservice.entity.VideoImgStorageTable;
import com.exadel.frs.commonservice.projection.SaveFaceImgProjection;
import com.exadel.frs.commonservice.projection.VideoImgStorageProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoImgStorageRepository extends JpaRepository<VideoImgStorageTable, Long>
{
    @Query(  """
                  select
                        new com.exadel.frs.commonservice.projection.VideoImgStorageProjection(a.id, a.deviceId, a.timestamp , a.imgUrl)
                  from
                        VideoImgStorageTable   a
                  where
                        (cast(:deviceId as string) is '-1' or a.deviceId = :deviceId)
                  and
                        a.timestamp between :startTimestamp and :endTimestamp  
                  """ )
    Page<VideoImgStorageProjection> findByVideoImgStorageAndIdBetweenTimestamp(@Param("deviceId") Integer deviceId , @Param("startTimestamp") Integer startTimestamp, @Param("endTimestamp") Integer endTimestamp, Pageable pageable);

}
