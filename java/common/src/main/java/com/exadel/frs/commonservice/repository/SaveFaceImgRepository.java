package com.exadel.frs.commonservice.repository;

import com.exadel.frs.commonservice.entity.Img;
import com.exadel.frs.commonservice.entity.Model;
import com.exadel.frs.commonservice.entity.SaveFaceImg;
import com.exadel.frs.commonservice.projection.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface SaveFaceImgRepository extends JpaRepository<SaveFaceImg, Long>
{


    @Modifying
    @Query("delete from SaveFaceImg a where a.apiKey = :apiKey and a.id = :Id")
    int deleteByApiKeyAndId( @Param("apiKey") String apiKey, @Param("Id")   long Id);
//

//    @Modifying
//    @Query("""
//                delete
//                from
//                    SaveFaceImg s
//                where
//                    s.apiKey = :apiKey
//                and
//                    s.id = :id
//           """)
//    int deleteSaveFaceImgByApiKeyAndSubId(@Param("apiKey") String apiKey, @Param("id")   int id);


}
