package com.exadel.frs.core.trainservice.service;

import com.exadel.frs.commonservice.exception.IncorrectPredictionCountException;
import com.exadel.frs.commonservice.sdk.faces.feign.dto.FindFacesResponse;
import com.exadel.frs.commonservice.sdk.faces.feign.dto.FindFacesResult;
import com.exadel.frs.core.trainservice.component.FaceClassifierPredictor;
import com.exadel.frs.core.trainservice.dto.FacePredictionResultDto;
import com.exadel.frs.core.trainservice.dto.FaceSimilarityDto;
import com.exadel.frs.core.trainservice.dto.FacesRecognitionResponseDto;
import com.exadel.frs.core.trainservice.dto.ProcessImageParams;
import com.exadel.frs.core.trainservice.mapper.FacesMapper;
import com.exadel.frs.commonservice.sdk.faces.FacesApiClient;
import com.exadel.frs.core.trainservice.validation.ImageExtensionValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.exadel.frs.core.trainservice.system.global.Constants.PREDICTION_COUNT;
import static java.math.RoundingMode.HALF_UP;

@Slf4j
@RequiredArgsConstructor
public class FaceRecognizeProcessServiceImpl implements FaceProcessService {

    private final FaceClassifierPredictor classifierPredictor;
    private final FacesApiClient facesApiClient;
    private final ImageExtensionValidator imageExtensionValidator;
    private final FacesMapper facesMapper;




    @Override
    public FacesRecognitionResponseDto processImage(ProcessImageParams processImageParams) {
        Object predictionCountObj = processImageParams.getAdditionalParams().get(PREDICTION_COUNT);
        Integer predictionCount = (Integer) predictionCountObj;
        if (predictionCount == 0 || predictionCount < -1) {
            throw new IncorrectPredictionCountException();
        }

        FindFacesResponse findFacesResponse;
        long cur_ms = System.currentTimeMillis();
        if (processImageParams.getFile() != null) {
            MultipartFile file = (MultipartFile) processImageParams.getFile();
            imageExtensionValidator.validate(file);
            findFacesResponse = facesApiClient.findFacesWithCalculator(
                    file,
                    processImageParams.getLimit(),
                    processImageParams.getDetProbThreshold(),
                    processImageParams.getFacePlugins(),
                    processImageParams.getDetectFaces()
            );
        } else {
            imageExtensionValidator.validateBase64(processImageParams.getImageBase64());
            findFacesResponse = facesApiClient.findFacesBase64WithCalculator(
                    processImageParams.getImageBase64(),
                    processImageParams.getLimit(),
                    processImageParams.getDetProbThreshold(),
                    processImageParams.getFacePlugins(),
                    processImageParams.getDetectFaces()
            );
        }

        long end_ms = System.currentTimeMillis();
        log.info("[======== >>> " + (end_ms - cur_ms) + " ms]");
        log.info(findFacesResponse.toString());

        if (findFacesResponse == null) {
            return FacesRecognitionResponseDto.builder().build();
        }
//        FindFacesResponse new_findFacesResponse = new FindFacesResponse();
//        new_findFacesResponse.setPluginsVersions(findFacesResponse.getPluginsVersions());
//        List<FindFacesResult> findFacesResults_ = new ArrayList<>();
//        for (FindFacesResult findResult1 : findFacesResponse.getResult())
//        {
//            if (findResult1.getMask().getProbability() > 0.7)
//            {
//                findFacesResults_.add(findResult1);
//            }
////            @Override
////            public FacesRecognitionResponseDto toFacesRecognitionResponseDto(FindFacesResponse facesResponse)
////            {
////            if ( facesResponse == null )
////            {
////                return null;
////            }
//
//
////            return facesRecognitionResponseDto;
//        }
//        if (findFacesResults_.size() <= 0)
//        {
//            return FacesRecognitionResponseDto.builder().build();
//        }
//        new_findFacesResponse.setResult(findFacesResults_);
//        FacesRecognitionResponseDto facesRecognitionDto = new FacesRecognitionResponseDto();
//
//        facesRecognitionDto.setPluginsVersions( pluginsVersionsToPluginsVersionsDto( facesResponse.getPluginsVersions() ) );
//        facesRecognitionDto.setResult( findFacesResultListToFacePredictionResultDtoList( facesResponse.getResult() ) );
//

//        }
        val facesRecognitionDto = facesMapper.toFacesRecognitionResponseDto(findFacesResponse);
        if (facesRecognitionDto == null) {
            return FacesRecognitionResponseDto.builder().build();
        }

        String apiKey = processImageParams.getApiKey();
        for (val findResult : facesRecognitionDto.getResult())
        {

            /*
             List<FacePredictionResultDto> list1 = new ArrayList<FacePredictionResultDto>( list.size() );
        for ( FindFacesResult findFacesResult : list )
        {

            if (findFacesResult.getMask().getProbability() > 0.70)
            {
                list1.add( findFacesResultToFacePredictionResultDto( findFacesResult ) );
            }

        }
             */
//            if (findResult.g)
            final ArrayList<FaceSimilarityDto> faces = processFaceResult(predictionCount, apiKey, findResult);

            findResult.setSubjects(faces);
        }

        return facesRecognitionDto.prepareResponse(processImageParams);
    }

    private ArrayList<FaceSimilarityDto> processFaceResult(Integer predictionCount, String apiKey, FacePredictionResultDto findResult) {
        double[] input = Stream.of(findResult.getEmbedding()).mapToDouble(d -> d).toArray();
        val predictions = classifierPredictor.predict(apiKey, input, predictionCount, 0);
        val faces = new ArrayList<FaceSimilarityDto>();
        for (val prediction : predictions) {
            var pred = BigDecimal.valueOf(prediction.getLeft());
            pred = pred.setScale(5, HALF_UP);
            faces.add(new FaceSimilarityDto(prediction.getRight().getSubjectName(), prediction.getRight().getEmbeddingId(), pred.floatValue(), ""));
        }

        var inBoxProb = BigDecimal.valueOf(findResult.getBox().getProbability());
        inBoxProb = inBoxProb.setScale(5, HALF_UP);
        findResult.getBox().setProbability(inBoxProb.doubleValue());
        return faces;
    }
}
