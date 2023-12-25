package com.exadel.frs.core.trainservice.controller;

import static com.exadel.frs.commonservice.system.global.Constants.DET_PROB_THRESHOLD;
import static com.exadel.frs.core.trainservice.system.global.Constants.*;
//import static com.exadel.frs.core.trainservice.system.global.Constants.FACE_TIMESTAMP;
import static org.springframework.http.HttpStatus.CREATED;

import com.exadel.frs.commonservice.entity.Embedding;
import com.exadel.frs.commonservice.entity.SaveFaceImg;
import com.exadel.frs.commonservice.entity.SaveFaceImgSub;
import com.exadel.frs.commonservice.entity.Subject;
import com.exadel.frs.commonservice.projection.SaveFaceImgProjection;
import com.exadel.frs.commonservice.projection.SubjectProjection;
import com.exadel.frs.core.trainservice.dto.*;
import com.exadel.frs.core.trainservice.mapper.SubjectEmbeddingMapper;
import com.exadel.frs.core.trainservice.mapper.Subjectmapper;
import com.exadel.frs.core.trainservice.service.EmbeddingService;
import com.exadel.frs.core.trainservice.service.SaveFaceImgService;
import com.exadel.frs.core.trainservice.service.SaveFaceImgSubService;
import com.exadel.frs.core.trainservice.service.SubjectService;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiParam;

import java.util.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping(API_V1 + "/recognition/subjects")
@RequiredArgsConstructor
@Slf4j
public class SubjectController {

    private final SubjectService subjectService;
    private final EmbeddingService embeddingService;

    private final SaveFaceImgService saveFaceImgService;
    private final SaveFaceImgSubService saveFaceImgSubService;

    private final SubjectEmbeddingMapper subjectEmbeddingMapper;
    private final Subjectmapper subjectmapper;

    private final Environment env;
    @PostMapping
    @ResponseStatus(CREATED)
    public SubjectDto createSubject(
            @ApiParam(value = API_KEY_DESC, required = true)
            @RequestHeader(X_FRS_API_KEY_HEADER)
            final String apiKey,
            @Valid
            @RequestBody
            final SubjectHttpDto subjectHttpDto,
            @ApiParam(value = SUBJECT_SUB_ID_DESC, required = true)
    @Validated
    @RequestParam(value = "subId" )
           final int subId
    ) {
        var subject = subjectService.createSubject(apiKey, subjectHttpDto.getSubjectName(), subId);
        return new SubjectDto( (subject.getSubjectName()) ,  subject.getSubId() );
    }

    @GetMapping
    public SubjectSubs listSubjects(
            @ApiParam(value = API_KEY_DESC, required = true)
            @RequestHeader(X_FRS_API_KEY_HEADER)
            final String apiKey,
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
//        return new SubjectSubs(embeddingService.listEmbeddings(apiKey, pageable).map(subjectEmbeddingMapper::toResponseDto), env.getProperty("environment.storage.url"));
//        return Map.of(
//                "subjects",
//                subjectService.getSubjectsNames(apiKey)
//        );

        List<GroupDto> groupDtoList = new ArrayList<>();
        Map<Integer, Integer> hash = new HashMap<>();
        Page<SubjectSubIdDto>  subjectSubIdDtos =  subjectService.findByApikey(apiKey,   pageable). map(subjectmapper::toResponseDto);

        List<SubjectSubIdDto> subjectSubIdDtos1 = subjectSubIdDtos.getContent();
        for (SubjectSubIdDto su : subjectSubIdDtos1)
        {
            //++hash[su.getSubId()];

            hash.put(su.getSubId(), (hash.get(su.getSubId()) == null)? 1:  hash.get(su.getSubId()) +1);
        }
        for (Integer key : hash.keySet())
        {
            GroupDto groupDto = new GroupDto(key, (hash.get(key) != null? hash.get(key): 0));
            groupDtoList.add(groupDto);
        }
//        log.info("_++_+_"+groupDtoList.toString());
        return new SubjectSubs(subjectSubIdDtos, groupDtoList);
//        return new SubjectSubs(subjectService.findByApikey(apiKey, pageable). map(subjectmapper::toResponseDto));
    }

    @PutMapping("/{subject}")
    public Map<String, Object> renameSubject(
            @ApiParam(value = API_KEY_DESC, required = true)
            @RequestHeader(X_FRS_API_KEY_HEADER)
            final String apiKey,
            @ApiParam(value = SUBJECT_DESC, required = true)
            @Valid
            @NotBlank(message = SUBJECT_NAME_IS_EMPTY)
            @PathVariable("subject")
            final String oldSubjectName,
            @Valid
            @RequestBody
            final SubjectHttpDto subjectDto) {
        return Map.of(
                "updated",
                subjectService.updateSubjectName(apiKey, oldSubjectName, subjectDto.getSubjectName())
        );
    }

    @DeleteMapping("/{subject}")
    public Map<String, Object> deleteSubject(
            @ApiParam(value = API_KEY_DESC, required = true)
            @RequestHeader(X_FRS_API_KEY_HEADER)
            final String apiKey,
            @ApiParam(value = SUBJECT_DESC, required = true)
            @Valid
            @NotBlank(message = SUBJECT_NAME_IS_EMPTY)
            @PathVariable("subject")
            final String subjectName
    ) {
        return Map.of(
                "subject",
                subjectService.deleteSubjectByName(apiKey, subjectName)
                              .getRight()
                              .getSubjectName()
        );
    }

    @DeleteMapping
    public Map<String, Object> deleteSubjects(
            @ApiParam(value = API_KEY_DESC, required = true)
            @RequestHeader(X_FRS_API_KEY_HEADER)
            final String apiKey
    ) {
        return Map.of(
                "deleted",
                subjectService.deleteSubjectsByApiKey(apiKey)
        );
    }


    static int count = 0;
    @GetMapping("/listsubjectsubId")
    public SubjectSubs listSubjectsSubId(
            @ApiParam(value = API_KEY_DESC, required = true)
            @RequestHeader(X_FRS_API_KEY_HEADER)
            final String apiKey,
            @ApiParam(value = SUBJECT_SUB_ID_DESC, required = true)
            @Validated
            @RequestParam(value = "sub_id" )
            final int subId,
            @ApiParam(value = "page", required = true)
            @Validated
            @RequestParam(value = "page" )
            final int page,
            @ApiParam(value = "page_size", required = true)
            @Validated
            @RequestParam(value = "page_size" )
            final int pageSize
            )
    {

        Pageable pageable = PageRequest.of(page, pageSize, Sort.unsorted());


//        log.info("packet =" + pageable1.getOffset() + ", " + pageable1.getPageNumber() + ", " + pageable1.getPageSize());
       // Page<SubjectProjection> subjects =  subjectService.findByApikeySubId(apiKey, subId, pageable);

//        return null;
        List<GroupDto> groupDtoList = new ArrayList<>();
        Map<Integer, Integer> hash = new HashMap<>();
        Page<SubjectSubIdDto>  subjectSubIdDtos =  subjectService.findByApikeySubId(apiKey, subId, pageable). map(subjectmapper::toResponseDto);
        List<SubjectSubIdDto> subjectSubIdDtos1 = subjectSubIdDtos.getContent();
        for (SubjectSubIdDto su : subjectSubIdDtos1)
        {
            //++hash[su.getSubId()];

            hash.put(su.getSubId(), hash.get(su.getSubId()) +1);
        }
        for (Integer key : hash.keySet())
        {
            GroupDto groupDto = new GroupDto(key, hash.get(key));
            groupDtoList.add(groupDto);
        }
        return new SubjectSubs(subjectSubIdDtos, groupDtoList);
    }
    @DeleteMapping("/deletesubjectsubId")
    public int deleteSubjectsSubId(
            @ApiParam(value = API_KEY_DESC, required = true)
            @RequestHeader(X_FRS_API_KEY_HEADER)
            final String apiKey,
            @ApiParam(value = SUBJECT_SUB_ID_DESC, required = true)
            @Validated
//            @JsonProperty("subid")
//            @NotBlank(message = "Subject subId cannot be empty")
//            @ApiParam(value = DET_PROB_THRESHOLD_DESC, example = NUMBER_VALUE_EXAMPLE)
            @RequestParam(value = "subId" )
            final int subId
    )
    {
//        return 0;
        int count = 0;
        List<SubjectProjection> subjectProjectionList =  subjectService.ListfindByApiKeySubId(apiKey, subId);
        for (SubjectProjection subjectProjection: subjectProjectionList)
        {
            subjectService.deleteSubjectByName(apiKey, subjectProjection.subjectName());
//            count += subjectService.deleteByApiKeyAndSubId(apiKey)
        }
        if (subjectProjectionList != null)
        {
            return subjectProjectionList.size();
        }
        return count;
//        return subjectService.deleteByApiKeyAndSubId  (apiKey, subId);
    }





    @RequiredArgsConstructor
    private static final class SubjectSubs {

        private final Page<SubjectSubIdDto> source;

        private final List<GroupDto>  groupDtos;


//        public StorageImg(Page<StorageImgProjection> listStorageImgs) {
//
//        }
//        private final String url;
        // As of backward compatibility we are not allowed to rename property 'faces' --> 'embedding'
        public List<SubjectSubIdDto> getFaces() {
            return source.getContent();
        }

//        public String getUrl()   { return url;}
        @JsonProperty("group_info")
        public List<GroupDto> getGroupDtos() {return groupDtos;}
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
