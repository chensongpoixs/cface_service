package com.exadel.frs.core.trainservice.controller;

import static com.exadel.frs.commonservice.system.global.Constants.DET_PROB_THRESHOLD;
import static com.exadel.frs.core.trainservice.system.global.Constants.*;
import static com.exadel.frs.core.trainservice.system.global.Constants.API_STORAGE_TIMESTAMP_SORT;
import static org.springframework.http.HttpStatus.CREATED;
import com.exadel.frs.commonservice.entity.Embedding;
import com.exadel.frs.commonservice.entity.Img;
import com.exadel.frs.commonservice.entity.Subject;
import com.exadel.frs.commonservice.projection.EmbeddingProjection;
import com.exadel.frs.core.trainservice.aspect.WriteEndpoint;
import com.exadel.frs.core.trainservice.dto.*;
import com.exadel.frs.core.trainservice.mapper.EmbeddingMapper;
import com.exadel.frs.core.trainservice.mapper.FacesMapper;
import com.exadel.frs.core.trainservice.service.EmbeddingService;
import com.exadel.frs.core.trainservice.service.SubjectService;
import com.exadel.frs.core.trainservice.util.MultipartFileToFileUtils;
import com.exadel.frs.core.trainservice.validation.ImageExtensionValidator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiParam;
import java.io.IOException;
import java.util.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.compress.archivers.zip.ZipUtil;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.juli.logging.Log;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Validated
@RestController
@RequestMapping(API_V1 + "/recognition")
@RequiredArgsConstructor
@Slf4j
public class EmbeddingController {

    private final EmbeddingService embeddingService;
    private final SubjectService subjectService;
    private final ImageExtensionValidator imageValidator;
    private final EmbeddingMapper embeddingMapper;
    private final FacesMapper facesMapper;
    private final Environment env;

    @WriteEndpoint
    @ResponseStatus(CREATED)
    @PostMapping("/faces")
    public EmbeddingDto addEmbedding(
            @ApiParam(value = IMAGE_WITH_ONE_FACE_DESC, required = true)
            @RequestParam
            final MultipartFile file,
            @ApiParam(value = SUBJECT_DESC, required = true)
            @Valid
            @NotBlank(message = SUBJECT_NAME_IS_EMPTY)
            @RequestParam(SUBJECT)
            final String subjectName,
            @ApiParam(value = DET_PROB_THRESHOLD_DESC, example = NUMBER_VALUE_EXAMPLE)
            @RequestParam(value = DET_PROB_THRESHOLD, required = false)
            final Double detProbThreshold,
            @ApiParam(value = API_KEY_DESC, required = true)
            @RequestHeader(X_FRS_API_KEY_HEADER)
            final String apiKey
    ) throws IOException {
        imageValidator.validate(file);

        final Pair<Subject, Embedding> pair = subjectService.saveCalculatedEmbedding(
                file,
                subjectName,
                detProbThreshold,
                apiKey
        );

        return new EmbeddingDto(pair.getRight().getId().toString(), pair.getLeft().getId().toString(), subjectName, pair.getRight().getFaceImgUrl(), pair.getLeft().getSubId(), pair.getLeft().getCreateTime());
    }
    @WriteEndpoint
    @ResponseStatus(CREATED)
    @PostMapping("/facezip")
    public ZipResultDto facezip(

            @ApiParam(value = DET_PROB_THRESHOLD_DESC, example = NUMBER_VALUE_EXAMPLE)
            @RequestParam(value = DET_PROB_THRESHOLD, required = false)
            final Double detProbThreshold,
            @ApiParam(value = API_KEY_DESC, required = true)
            @RequestHeader(X_FRS_API_KEY_HEADER)
            final String apiKey,
            @ApiParam(value = "xxx.zip", required = true)
            @RequestParam
            final MultipartFile file,
            @ApiParam(value = "sub_id", required = true)
            @RequestParam
            final int sub_id
    ) throws IOException {
//        imageValidator.validate(file);

        ZipResultDto zipResultDto = new ZipResultDto();
        log.info("======================================");
        List<MultipartFile> files = null;
        try {
            files  =  MultipartFileToFileUtils.UnZip(file);
        }
        catch (Exception e)
        {
            zipResultDto.setResult(500);
            return zipResultDto;
//            return null ;
        }


        List<ZipFaceDto> zipFaceDtoList = new ArrayList<ZipFaceDto>();

        for (MultipartFile m : files)
        {
            String fileFormat = m.getOriginalFilename().substring( m.getOriginalFilename().lastIndexOf(".") +1);
            String fileName = m.getOriginalFilename().substring(0,  m.getOriginalFilename().lastIndexOf("."));
            ZipFaceDto zipFaceDto = new ZipFaceDto();
            zipFaceDto.setResult(0);
            zipFaceDto.setSubjectName(m.getOriginalFilename());
            if (fileFormat.equals("jpg") || fileFormat.equals("png"))
            {
                try {
                    Pageable pageable = PageRequest.of(0, 10, Sort.unsorted());

 ;//getContent
                    if (embeddingService.listEmbeddings(apiKey, fileName, 0, pageable).getContent().isEmpty())
                    {
                        log.info("filename = ===" + m.getOriginalFilename());
                        subjectService.createSubject(apiKey, fileName, sub_id);
                        final Pair<Subject, Embedding> pair = subjectService.saveCalculatedEmbedding(
                                m,
                                fileName,
                                detProbThreshold,
                                apiKey
                        );
                    }
                    else
                    {
                        zipFaceDto.setResult(2);
                    }
//                EmbeddingDto embeddingDto =  new EmbeddingDto(pair.getRight().getId().toString(), fileName, pair.getRight().getFaceImgUrl(), pair.getLeft().getSubId());
//                embeddingDtos.add(embeddingDto);


                }catch (IOException e) {
                    zipFaceDto.setResult(1);
//                EmbeddingDto embeddingDto =  new EmbeddingDto();
//                embeddingDto.setSubjectName(fileName);
//                embeddingDtos.add(embeddingDto);
                    log.info (e.getMessage());
                }
            }
            else
            {
                zipFaceDto.setResult(3);
            }
            zipFaceDtoList.add(zipFaceDto);
        }


        zipResultDto.setZipFaceDtos(zipFaceDtoList);

        return zipResultDto;
    }



//    @WriteEndpoint
//    @ResponseStatus(CREATED)
//    @PostMapping(value = "/faces", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public EmbeddingDto addEmbeddingBase64(
//            @ApiParam(value = API_KEY_DESC, required = true)
//            @RequestHeader(X_FRS_API_KEY_HEADER)
//            final String apiKey,
//            @ApiParam(value = SUBJECT_DESC)
//            @Valid
//            @NotBlank(message = SUBJECT_NAME_IS_EMPTY)
//            @RequestParam(value = SUBJECT)
//            final String subjectName,
//            @ApiParam(value = DET_PROB_THRESHOLD_DESC, example = NUMBER_VALUE_EXAMPLE)
//            @RequestParam(value = DET_PROB_THRESHOLD, required = false)
//            final Double detProbThreshold,
//            @Valid
//            @RequestBody
//            final Base64File request
//    ) {
//        imageValidator.validateBase64(request.getContent());
//
//        final Pair<Subject, Embedding> pair = subjectService.saveCalculatedEmbedding(
//                request.getContent(),
//                subjectName,
//                detProbThreshold,
//                apiKey
//        );
//
////        int subId = pair.getLeft().getSubId();
////        val subId1 = subId;
//        return new EmbeddingDto(pair.getRight().getId().toString(), pair.getLeft().getId().toString(), subjectName, pair.getRight().getFaceImgUrl(), pair.getLeft().getSubId());
//    }

    @ResponseBody
    @GetMapping(value = "/faces/{embeddingId}/img", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] downloadImg(HttpServletResponse response,
                              @ApiParam(value = API_KEY_DESC, required = true)
                              @RequestHeader(name = X_FRS_API_KEY_HEADER)
                              final String apiKey,
                              @ApiParam(value = IMAGE_ID_DESC, required = true)
                              @PathVariable
                              final UUID embeddingId
    ) {
        response.addHeader(HttpHeaders.CACHE_CONTROL, CACHE_CONTROL_HEADER_VALUE);
        return embeddingService.getImg(apiKey, embeddingId)
                               .map(Img::getContent)
                               .orElse(new byte[]{});
    }

    @GetMapping("/faces")
    public Faces listEmbeddings(
            @ApiParam(value = API_KEY_DESC, required = true)
            @RequestHeader(name = X_FRS_API_KEY_HEADER)
            final String apiKey,
            @ApiParam(value = SUBJECT_DESC)
            @Valid
            @RequestParam(name = SUBJECT, required = false)
            final String subjectName,
            @ApiParam(value = "sub_id")
            @Validated
            @RequestParam(defaultValue = "-1", value = "sub_id" , required = true)
            final int sub_id,
            @ApiParam( value = "subject 字段  0: 升序 1: 降序" )
            @Valid
            @RequestParam(defaultValue = "0", name = "ascdesc", required = false )
            final int ASCDESC, //API_STORAGE_FACE_GENDER_DES
            @ApiParam(value = "page", required = true)
            @Validated
            @RequestParam(value = "page" )
            final int page,
            @ApiParam(value = "page_size", required = true)
            @Validated
            @RequestParam(value = "page_size" )
            final int pageSize
    ) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.unsorted());
        List<GroupDto> groupDtoList = new ArrayList<>();
        Map<Integer, Integer> hash = new HashMap<>();
        Page<EmbeddingDto> embeddingDtos = null;
        if (sub_id < 0)
        {
            embeddingDtos= embeddingService.listEmbeddings(apiKey, subjectName, ASCDESC, PageRequest.of(page, pageSize, Sort.unsorted())).map(embeddingMapper::toResponseDto);

        }
        else
        {
            embeddingDtos= embeddingService.allEmbeddingsSubId(apiKey, subjectName, sub_id, ASCDESC, pageable).map(embeddingMapper::toResponseDto);

        }

        List<EmbeddingDto> subjectSubIdDtos1 = embeddingDtos.getContent();
        for (EmbeddingDto su : subjectSubIdDtos1)
        {
            //++hash[su.getSubId()];

            hash.put(su.getSubId(), (hash.get(su.getSubId()) == null)? 1:  hash.get(su.getSubId()) +1);
        }
        for (Integer key : hash.keySet())
        {
            GroupDto groupDto = new GroupDto(key, (hash.get(key) != null? hash.get(key): 0));
            groupDtoList.add(groupDto);
        }
        return new Faces(embeddingDtos,groupDtoList , env.getProperty("environment.storage.url"));
    }

    @WriteEndpoint
    @DeleteMapping("/faces")
    public Map<String, Object> removeAllSubjectEmbeddings(
            @ApiParam(value = API_KEY_DESC, required = true)
            @RequestHeader(name = X_FRS_API_KEY_HEADER)
            final String apiKey,
            @ApiParam(value = SUBJECT_DESC)
            @Validated
            @NotBlank(message = SUBJECT_NAME_IS_EMPTY)
            @RequestParam(name = SUBJECT, required = false)
            final String subjectName
    ) {
        return Map.of(
                "deleted",
                subjectService.removeAllSubjectEmbeddings(apiKey, subjectName)
        );
    }


    @WriteEndpoint
    @DeleteMapping("/faces/{embeddingId}")
    public EmbeddingDto deleteEmbeddingById(
            @ApiParam(value = API_KEY_DESC, required = true)
            @RequestHeader(name = X_FRS_API_KEY_HEADER)
            final String apiKey,
            @ApiParam(value = IMAGE_ID_DESC, required = true)
            @PathVariable
            final UUID embeddingId
    ) {
        var embedding = subjectService.removeSubjectEmbedding(apiKey, embeddingId);
        return new EmbeddingDto(embeddingId.toString(), embedding.getSubject().getId().toString(), embedding.getSubject().getSubjectName(), embedding.getFaceImgUrl(), embedding.getSubject().getSubId(), embedding.getSubject().getCreateTime());
    }

    @WriteEndpoint
    @PostMapping("/faces/delete")
    public List<EmbeddingDto> deleteEmbeddingsById(
            @ApiParam(value = API_KEY_DESC, required = true)
            @RequestHeader(name = X_FRS_API_KEY_HEADER)
            final String apiKey,
            @ApiParam(value = IMAGE_IDS_DESC, required = true)
            @RequestBody
            final List<UUID> embeddingIds
    ) {
        List<Embedding> list = subjectService.removeSubjectEmbeddings(apiKey, embeddingIds);
        List<EmbeddingDto> dtoList = list.stream()
                                         .map(c -> new EmbeddingDto(c.getId().toString(), c.getSubject().getId().toString(), c.getSubject().getSubjectName(), c.getFaceImgUrl(), c.getSubject().getSubId(), c.getSubject().getCreateTime()))
                                         .toList();
        return dtoList;
    }

    @PostMapping(value = "/faces/{embeddingId}/verify", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public VerificationResult recognizeFile(
            @ApiParam(value = API_KEY_DESC, required = true)
            @RequestHeader(X_FRS_API_KEY_HEADER)
            final String apiKey,
            @ApiParam(value = IMAGE_WITH_ONE_FACE_DESC, required = true)
            @RequestParam
            final MultipartFile file,
            @ApiParam(value = LIMIT_DESC, example = NUMBER_VALUE_EXAMPLE)
            @RequestParam(defaultValue = LIMIT_DEFAULT_VALUE, required = false)
            @Min(value = 0, message = LIMIT_MIN_DESC)
            final Integer limit,
            @ApiParam(value = IMAGE_ID_DESC, required = true)
            @PathVariable
            final UUID embeddingId,
            @ApiParam(value = DET_PROB_THRESHOLD_DESC, example = NUMBER_VALUE_EXAMPLE)
            @RequestParam(value = DET_PROB_THRESHOLD, required = false)
            final Double detProbThreshold,
            @ApiParam(value = FACE_PLUGINS_DESC)
            @RequestParam(value = FACE_PLUGINS, required = false, defaultValue = "")
            final String facePlugins,
            @ApiParam(value = STATUS_DESC)
            @RequestParam(value = STATUS, required = false, defaultValue = STATUS_DEFAULT_VALUE)
            final Boolean status
    ) {
        imageValidator.validate(file);
        var processImageParams = ProcessImageParams.builder()
                                                   .additionalParams(Map.of(IMAGE_ID, embeddingId))
                                                   .apiKey(apiKey)
                                                   .detProbThreshold(detProbThreshold)
                                                   .file(file)
                                                   .facePlugins(facePlugins)
                                                   .limit(limit)
                                                   .status(status)
                                                   .build();

        var pair = subjectService.verifyFace(processImageParams);
        return new VerificationResult(
                pair.getLeft(),
                facesMapper.toPluginVersionsDto(pair.getRight())
        );
    }

    @PostMapping(value = "/faces/{embeddingId}/verify", consumes = MediaType.APPLICATION_JSON_VALUE)
    public VerificationResult recognizeBase64(
            @ApiParam(value = API_KEY_DESC, required = true)
            @RequestHeader(X_FRS_API_KEY_HEADER)
            final String apiKey,
            @ApiParam(value = IMAGE_ID_DESC, required = true)
            @PathVariable
            final UUID embeddingId,
            @ApiParam(value = LIMIT_DESC, example = NUMBER_VALUE_EXAMPLE)
            @RequestParam(defaultValue = LIMIT_DEFAULT_VALUE, required = false)
            @Min(value = 0, message = LIMIT_MIN_DESC)
            final Integer limit,
            @ApiParam(value = DET_PROB_THRESHOLD_DESC, example = NUMBER_VALUE_EXAMPLE)
            @RequestParam(value = DET_PROB_THRESHOLD, required = false)
            final Double detProbThreshold,
            @ApiParam(value = FACE_PLUGINS_DESC)
            @RequestParam(value = FACE_PLUGINS, required = false, defaultValue = "")
            final String facePlugins,
            @ApiParam(value = STATUS_DESC)
            @RequestParam(value = STATUS, required = false, defaultValue = STATUS_DEFAULT_VALUE)
            final Boolean status,
            @RequestBody
            @Valid
            final Base64File request
    ) {
        imageValidator.validateBase64(request.getContent());

        var processImageParams = ProcessImageParams.builder()
                                                   .additionalParams(Map.of(IMAGE_ID, embeddingId))
                                                   .apiKey(apiKey)
                                                   .detProbThreshold(detProbThreshold)
                                                   .imageBase64(request.getContent())
                                                   .facePlugins(facePlugins)
                                                   .limit(limit)
                                                   .status(status)
                                                   .build();

        var pair = subjectService.verifyFace(processImageParams);
        return new VerificationResult(
                pair.getLeft(),
                facesMapper.toPluginVersionsDto(pair.getRight())
        );
    }

    @PostMapping(value = "/embeddings/faces/{imageId}/verify", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmbeddingsVerificationProcessResponse recognizeEmbeddings(
            @ApiParam(value = API_KEY_DESC, required = true)
            @RequestHeader(X_FRS_API_KEY_HEADER)
            final String apiKey,
            @ApiParam(value = IMAGE_ID_DESC, required = true)
            @PathVariable
            final UUID imageId,
            @RequestBody
            @Valid
            final EmbeddingsRecognitionRequest recognitionRequest
    ) {
        ProcessEmbeddingsParams processParams =
                ProcessEmbeddingsParams.builder()
                                       .apiKey(apiKey)
                                       .embeddings(recognitionRequest.getEmbeddings())
                                       .additionalParams(Map.of(IMAGE_ID, imageId))
                                       .build();

        return subjectService.verifyEmbedding(processParams);
    }

    @RequiredArgsConstructor
    private static final class Faces {

        private final Page<EmbeddingDto> source;

        private final List<GroupDto>   groupDtos;
        private final String url;
        // As of backward compatibility we are not allowed to rename property 'faces' --> 'embedding'
        public List<EmbeddingDto> getFaces() {
            return source.getContent();
        }

        public String getUrl() {return url;}

        @JsonProperty("group_infos")
        public List<GroupDto> GetGroupInfos() {
            return groupDtos;
        }
        @JsonProperty("total_pages")
        public int getTotalPages() {
            return source.getTotalPages();
        }

        @JsonProperty("total_elements")
        public long getTotalElements() {
            return source.getTotalElements();
        }

        @JsonProperty("page_number")
        public int getNumber() {
            return source.getNumber();
        }

        @JsonProperty("page_size")
        public int getSize() {
            return source.getSize();
        }
    }
}
