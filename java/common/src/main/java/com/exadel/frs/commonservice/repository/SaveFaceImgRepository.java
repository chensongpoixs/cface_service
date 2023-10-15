package com.exadel.frs.commonservice.repository;

import com.exadel.frs.commonservice.entity.Img;
import com.exadel.frs.commonservice.entity.SaveFaceImg;
import com.exadel.frs.commonservice.projection.EmbeddingProjection;
import com.exadel.frs.commonservice.projection.StorageImgProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SaveFaceImgRepository extends PagingAndSortingRepository<SaveFaceImg, Long>
{
    //@Query("select i from Img i join Embedding e on e.img.id = i.id where e.id = :embeddingId and e.subject.apiKey = :apiKey")
//    @Query(" select new com.exadel.frs.commonservice.projection.StorageImgProjection(master.timestamp, master.img_url, master.device_id,   s.subject_name, subtable.gender, subtable.min_age, subtable.max_age, subtable.similarity, subtable.box_min_x, subtable.box_min_y, subtable.box_max_x, subtable.box_max_y )  \n" +
//            " from t_savefaceimg  master, t_savefaceimg_subtable subtable, embedding e,  subject s \n" +
//            " where  master.api_key = :apiKey  and master.timestamp > :timestamp and master.id = subtable.master_id and e.subject_id  = s.id  and subtable.embeddingid = e.id::character varying;")
//    Page<StorageImgProjection> findSaveFaceImgINfoApiKey(@Param("apiKey") String apiKey, @Param("timestamp") long timestamp, Pageable pageable);
}
