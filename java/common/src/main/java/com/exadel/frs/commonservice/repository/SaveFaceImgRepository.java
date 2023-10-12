package com.exadel.frs.commonservice.repository;

import com.exadel.frs.commonservice.entity.Img;
import com.exadel.frs.commonservice.entity.SaveFaceImg;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SaveFaceImgRepository extends PagingAndSortingRepository<SaveFaceImg, UUID>
{

}
