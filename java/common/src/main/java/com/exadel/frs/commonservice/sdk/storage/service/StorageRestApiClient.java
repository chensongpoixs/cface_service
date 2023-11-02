//package com.exadel.frs.commonservice.sdk.storage.service;
//
//import com.exadel.frs.commonservice.sdk.faces.exception.FacesServiceException;
//import com.exadel.frs.commonservice.sdk.faces.exception.NoFacesFoundException;
//import com.exadel.frs.commonservice.sdk.faces.feign.dto.FacesStatusResponse;
//import com.exadel.frs.commonservice.sdk.faces.feign.dto.FindFacesResponse;
//import com.exadel.frs.commonservice.sdk.storage.StorageApiClient;
//import feign.FeignException;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//@AllArgsConstructor
//@Component
//public class StorageRestApiClient implements StorageApiClient
//{
//    private final StorageApiClient storageApiClient;
//
//    @Override
//    public FacesStatusResponse getInfo() {
//        return null;
//    }
//
////    @Override
////    public int findFaces(Integer faceLimit, Double thresholdC, String facePlugins, Boolean detectFaces) {
////        try {
////            return storageApiClient.findFaces( faceLimit, thresholdC, facePlugins, detectFaces);
////        } catch (FeignException.BadRequest ex) {
////            throw new NoFacesFoundException();
////        } catch (FeignException e) {
////            throw new FacesServiceException(e.getMessage());
////        }
////    }
//}
