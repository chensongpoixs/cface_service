//package com.exadel.frs.commonservice.sdk.storage.feign;
//
//import com.exadel.frs.commonservice.sdk.faces.feign.dto.FindFacesResponse;
//import feign.Headers;
//import feign.Param;
//import feign.RequestLine;
//import org.springframework.web.multipart.MultipartFile;
//
//public interface StorageFeignClient {
//
//
//
//        @RequestLine("POST /find_faces")
//        @Headers("Content-Type: multipart/form-data")
//        int findFaces(
//                @Param(value = "limit")
//                Integer faceLimit,
//                @Param(value = "det_prob_threshold")
//                Double thresholdC,
//                @Param(value = "face_plugins")
//                String facePlugins,
//                @Param(value = "detect_faces")
//                Boolean detectFaces);
//}
