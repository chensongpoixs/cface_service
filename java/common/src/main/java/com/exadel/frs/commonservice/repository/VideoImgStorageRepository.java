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

import java.util.List;

@Repository
public interface VideoImgStorageRepository extends JpaRepository<VideoImgStorageTable, Long>
{
    @Query(  """
                  select
                        new com.exadel.frs.commonservice.projection.VideoImgStorageProjection(a.id, a.deviceId, a.timestamp , a.imgUrl)
                  from
                        VideoImgStorageTable   a
                  where
                          a.deviceId in ?1
                  and
                        a.timestamp between ?2 and ?3
                  """ )
    Page<VideoImgStorageProjection> findByVideoImgStorageAndDeviceIdBetweenTimestamp(  List<Integer> deviceIds ,  Integer startTimestamp,   Integer endTimestamp, Pageable pageable);



    @Query(  """
                  select
                        new com.exadel.frs.commonservice.projection.VideoImgStorageProjection(a.id, a.deviceId, a.timestamp , a.imgUrl)
                  from
                        VideoImgStorageTable   a
                  where
                        a.timestamp between :startTimestamp and :endTimestamp
                  """ )
    Page<VideoImgStorageProjection> findByVideoImgStorageBetweenTimestamp( @Param("startTimestamp") Integer startTimestamp, @Param("endTimestamp") Integer endTimestamp, Pageable pageable);
    @Query(  """
                  select
                        new com.exadel.frs.commonservice.projection.VideoImgStorageProjection(a.id, a.deviceId, a.timestamp , a.imgUrl)
                  from
                        VideoImgStorageTable   a
                  """ )
    Page<VideoImgStorageProjection> findByVideoImgStorage( Pageable pageable);

}
