package com.exadel.frs.core.trainservice.controller;

import static com.exadel.frs.core.trainservice.system.global.Constants.*;
//import static com.exadel.frs.core.trainservice.system.global.Constants.FACE_TIMESTAMP;
import static org.springframework.http.HttpStatus.CREATED;

import com.exadel.frs.commonservice.entity.SaveFaceImg;
import com.exadel.frs.commonservice.entity.SaveFaceImgSub;
import com.exadel.frs.commonservice.projection.SaveFaceImgProjection;
import com.exadel.frs.core.trainservice.dto.StorageImgDto;
import com.exadel.frs.core.trainservice.dto.SubjectDto;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping(API_V1 + "/recognition/subjects")
@RequiredArgsConstructor
@Slf4j
public class SubjectController {

    private final SubjectService subjectService;

    private final SaveFaceImgService saveFaceImgService;
    private final SaveFaceImgSubService saveFaceImgSubService;
    @PostMapping
    @ResponseStatus(CREATED)
    public SubjectDto createSubject(
            @ApiParam(value = API_KEY_DESC, required = true)
            @RequestHeader(X_FRS_API_KEY_HEADER)
            final String apiKey,
            @Valid
            @RequestBody
            final SubjectDto subjectDto
    ) {
        var subject = subjectService.createSubject(apiKey, subjectDto.getSubjectName());
        return new SubjectDto((subject.getSubjectName()));
    }

    @GetMapping
    public Map<String, Object> listSubjects(
            @ApiParam(value = API_KEY_DESC, required = true)
            @RequestHeader(X_FRS_API_KEY_HEADER)
            final String apiKey
    ) {
     if (true)
     {
         long  seed = 1000;
         Random random = new Random(seed);
         SaveFaceImg saveFaceImg = new SaveFaceImg();
//        saveFaceImg.setId(1L);
         saveFaceImg.setApiKey("0a16386c-2609-4e37-9883-a6ec18555d2a");
         saveFaceImg.setTimestamp(random.nextInt());
         saveFaceImg.setImgUrl("images/" + UUID.randomUUID().toString() + ".jpg");

         saveFaceImg.setDeviceId(random.nextInt() %5);
         SaveFaceImg newsaveface =   saveFaceImgService.AddSaveFace(saveFaceImg);
//        List<SaveFaceImg> saveFaceImgs = new ArrayList<>();
//        saveFaceImgs.add(saveFaceImg);
//        Embedding embedding = new Embedding();
//        Embedding
         for (int i = 0; i < 5; ++i)
         {
             SaveFaceImgSub saveFaceImgSub = new SaveFaceImgSub();
//             saveFaceImgSub.setSubImgUrl("images/"+ UUID.randomUUID().toString());
//        saveFaceImgSub.setSaveFaceImg(saveFaceImg);
//        saveFaceImgSub.setSaveFaceImg(saveFaceImg);
             saveFaceImgSub.setGender(random.nextInt() %1);
             saveFaceImgSub.setMinAge(random.nextInt() % 80);
             saveFaceImgSub.setMaxAge(random.nextInt() % 80);
             saveFaceImgSub.setSimilarity(random.nextFloat());
             saveFaceImgSub.setBoxMinX(random.nextInt());
             saveFaceImgSub.setBoxMinY(random.nextInt());
             saveFaceImgSub.setBoxMaxX(random.nextInt());
             saveFaceImgSub.setBoxMaxY(random.nextInt());
             saveFaceImgSub.setSaveFaceImg(newsaveface);
             saveFaceImgSubService.AddSaveFaceImgSub(saveFaceImgSub);
         }
//         SaveFaceImgSub saveFaceImgSub2 = new SaveFaceImgSub();
////        saveFaceImgSub.setSaveFaceImg(saveFaceImg);
////        saveFaceImgSub.setSaveFaceImg(saveFaceImg);
//         saveFaceImgSub2.setGender(2);
//         saveFaceImgSub2.setMin_age(22);
//         saveFaceImgSub2.setMax_age(42);
//         saveFaceImgSub2.setSimilarity(42.4f);
//         saveFaceImgSub2.setBox_min_x(42);
//         saveFaceImgSub2.setBox_min_y(23);
//         saveFaceImgSub2.setBox_max_x(324);
//         saveFaceImgSub2.setBox_max_y(424);
//         List<SaveFaceImgSub> saveFaceImgSubs = new ArrayList<>();
//         saveFaceImgSubs.add(saveFaceImgSub);
//         saveFaceImgSubs.add(saveFaceImgSub2);
////         saveFaceImg.setSaveFaceImgSubs(saveFaceImgSubs);
//
//         saveFaceImgSub.setSavefaceimg(newsaveface);
//         saveFaceImgSubService.AddSaveFaceImgSub(saveFaceImgSub);
//         saveFaceImgSub2.setSavefaceimg(newsaveface);
//         saveFaceImgSubService.AddSaveFaceImgSub(saveFaceImgSub2);
     }
//        List<SaveFaceImgProjection> saveFaceImgs =  saveFaceImgSubService.listSaveFaceSubImgs();
//        log.info(saveFaceImgs.toString());
//        saveFaceImgSubService.AddSaveFaceImgSub(saveFaceImgSub2);
//
//        log.info(newsaveface.toString());

//      Optional<SaveFaceImg> p =  saveFaceImgService.findById(17);
//       log.info(p.toString());
//        saveFaceImgSub.setSaveFaceImg(newsaveface);
//        SaveFaceImgSub saveFaceImgSub1 =  saveFaceImgSubService.AddSaveFaceImgSub(saveFaceImgSub);
//        log.info(saveFaceImgSub1.toString());
        return Map.of(
                "subjects",
                subjectService.getSubjectsNames(apiKey)
        );
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
            final SubjectDto subjectDto) {
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

//    @GetMapping("/search")
//    public  StorageImgs listStorageImg(
//            @ApiParam(value = API_KEY_DESC, required = true)
//            @RequestHeader(name = X_FRS_API_KEY_HEADER)
//            final String apiKey,
//            @ApiParam(value = DETECT_FACE_TIMESTAMP)
//            @Valid
//            @RequestParam(name = FACE_TIMESTAMP, required = false)
//            final long timestamp,
//            final Pageable pageable
//    )
//    {
//
//        log.info("==============================================>");
////        try {
//////            List<SaveFaceImgProjection> saveFaceImgs =  saveFaceImgService.listSaveFaceImgs( );
//////            log.info(saveFaceImgs.toString());
////////            saveFaceImgs.stream().map(Objects::toString).forEach(System.out::println);
////////            Optional<SaveFaceImg> p = saveFaceImgService.findById(timestamp);
////////            if (!p.isEmpty())
////////            {
////////                log.info(p.get().toString());
////////            }
////        }
////        catch (final Exception exception)
////        {
////            log.info(exception.toString());
////        }
//
//        List<SaveFaceImgProjection> saveFaceImgProjections =  saveFaceImgSubService.listSaveFaceSubImgs();
////        System.out.println(saveFaceImgProjections.toArray().toString());
//        log.info(saveFaceImgProjections.toString());
//        return null;//new StorageImgs(saveFaceImgSubService.listSaveFaceSubImgByApiKey(apiKey, pageable).map(saveFaceImgMapper::toResponseDto/*SaveFaceImgMapper::toResponseDto*/));
////        return null;
//        //return new StorageImg(storageSaveFaceImgService.findStorageImg(apiKey, timestamp, pageable));
////        return new StorageImg(saveFaceImgService.listStorageImgs(apiKey, timestamp, pageable) .map( p -> new StorageImgDto()));
//    }


//    @RequiredArgsConstructor
//    private static final class StorageImgs {
//
//        private final Page<StorageImgDto> source;
//
////        public StorageImg(Page<StorageImgProjection> listStorageImgs) {
////
////        }
//
//        // As of backward compatibility we are not allowed to rename property 'faces' --> 'embedding'
//        public List<StorageImgDto> getFaces() {
//            return source.getContent();
//        }
//
//        @JsonProperty("total_pages")
//        public int getTotalPages() {
//            return source.getTotalPages();
//        }
//
//        @JsonProperty("total_elements")
//        public long getTotalElements() {
//            return source.getTotalElements();
//        }
//
//        @JsonProperty("page_number")
//        public int getNumber() {
//            return source.getNumber();
//        }
//
//        @JsonProperty("page_size")
//        public int getSize() {
//            return source.getSize();
//        }
//    }
}
