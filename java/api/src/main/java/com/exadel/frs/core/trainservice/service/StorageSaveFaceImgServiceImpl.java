package com.exadel.frs.core.trainservice.service;

//import com.exadel.frs.commonservice.repository.StorageSaveImgRepository;
import com.exadel.frs.core.trainservice.dto.StorageImgDto;
import liquibase.repackaged.org.apache.commons.lang3.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StorageSaveFaceImgServiceImpl implements StorageSaveFaceImgService
{
//    private final StorageSaveImgRepository storageSaveImgRepository;
    @Override
    public Page<StorageImgDto> findStorageImg(String apiKey, long timestamp, Pageable pageable)
    {
        return null;
        //return storageSaveImgRepository.findSaveFaceImgINfoApiKey(apiKey, timestamp, pageable).map(p -> new StorageImgDto());
    }
}
