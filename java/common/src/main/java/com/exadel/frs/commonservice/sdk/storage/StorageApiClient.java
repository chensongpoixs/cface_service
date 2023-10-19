//package com.exadel.frs.commonservice.sdk.storage;
//
//import com.exadel.frs.commonservice.sdk.faces.feign.dto.FindFacesResponse;
//import org.springframework.web.multipart.MultipartFile;
//
//public interface StorageApiClient
//{
//    /**
//     * Calls /find_faces endpoint of Faces API
//     * @param faceLimit   - The limit of faces that you want recognized. Value of 0 represents no limit
//     * @param thresholdC  - The minimum required confidence that a found face is actually a face
//     * @param facePlugins - Comma-separated slugs of face plugins. Empty value - face plugins disabled, returns only bounding boxes
//     * @return result of the operation
//     */
//    int findFaces(
//            Integer faceLimit,
//            Double thresholdC,
//            String facePlugins,
//            Boolean detectFaces);
//
//}
