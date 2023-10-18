package com.exadel.frs.commonservice.repository;

import com.exadel.frs.commonservice.entity.SaveFaceImg;
import com.exadel.frs.commonservice.entity.SaveFaceImgSub;
import com.exadel.frs.commonservice.projection.SaveFaceImgProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface SaveFaceImgSubRepository extends JpaRepository<SaveFaceImgSub, Long>
{


    @Query(  """
                  select
                        new com.exadel.frs.commonservice.projection.SaveFaceImgProjection(a.gender, a.saveFaceImg.imgUrl)
                  from
                        SaveFaceImgSub   a
                  """ )
    List<SaveFaceImgProjection> findBySaveSubImgs( );
}
