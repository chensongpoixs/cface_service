package com.exadel.frs.core.trainservice.service;

import com.exadel.frs.core.trainservice.dto.StorageImgDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StorageSaveFaceImgService
{


    Page<StorageImgDto> findStorageImg(String apiKey, long timestamp, Pageable pageable);
}
