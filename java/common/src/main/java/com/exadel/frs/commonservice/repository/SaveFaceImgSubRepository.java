package com.exadel.frs.commonservice.repository;

import com.exadel.frs.commonservice.entity.SaveFaceImg;
import com.exadel.frs.commonservice.entity.SaveFaceImgSub;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface SaveFaceImgSubRepository extends PagingAndSortingRepository<SaveFaceImgSub, Long>
{
}
