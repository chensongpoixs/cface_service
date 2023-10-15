//package com.exadel.frs.commonservice.repository;
//
//import com.exadel.frs.commonservice.entity.SaveFaceImgSub;
//import com.exadel.frs.commonservice.entity.StorageSaveImg;
//import com.exadel.frs.commonservice.projection.StorageImgProjection;
//import feign.Param;
//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.PagingAndSortingRepository;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface StorageSaveImgRepository extends PagingAndSortingRepository<StorageSaveImg, Long>
//{
////    @Query("select i from Img i join Embedding e on e.img.id = i.id where e.id = :embeddingId and e.subject.apiKey = :apiKey")
////    @Query(" select new com.exadel.frs.commonservice.projection.StorageImgProjection(master.timestamp, master.img_url, master.device_id,   subtable.gender, subtable.min_age, subtable.max_age, subtable.similarity, subtable.box_min_x, subtable.box_min_y, subtable.box_max_x, subtable.box_max_y )  \n" +
////            " from t_savefaceimg  master, t_savefaceimg_subtable subtable \n" +
////            " where  master.api_key = :apiKey  and master.timestamp > :timestamp and master.id = subtable.master_id ;")
////    Page<StorageImgProjection> findSaveFaceImgINfoApiKey(@Param("apiKey") String apiKey, @Param("timestamp") long timestamp,  Pageable pageable);
//}
