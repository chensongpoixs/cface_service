package com.exadel.frs.core.trainservice.service;

import com.exadel.frs.commonservice.entity.Embedding;
import com.exadel.frs.commonservice.projection.EmbeddingProjection;
import com.exadel.frs.commonservice.projection.EnhancedEmbeddingProjection;
import com.exadel.frs.commonservice.entity.Img;
import com.exadel.frs.commonservice.projection.SubjectEmbeddingProjection;
import com.exadel.frs.commonservice.repository.EmbeddingRepository;
import com.exadel.frs.commonservice.repository.ImgRepository;
import com.exadel.frs.core.trainservice.system.global.Constants;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class EmbeddingService {

    private final EmbeddingRepository embeddingRepository;
    private final ImgRepository imgRepository;

    @Transactional
    public int updateEmbedding(UUID embeddingId, double[] embedding, String calculator) {
        return embeddingRepository.updateEmbedding(embeddingId, embedding, calculator);
    }

    @Transactional
    public <T> T doWithEnhancedEmbeddingProjectionStream(String apiKey, Function<Stream<EnhancedEmbeddingProjection>, T> func) {
        try (val stream = embeddingRepository.findBySubjectApiKey(apiKey)) {
            return func.apply(stream);
        }
    }

    public List<Embedding> getWithImgAndCalculatorNotEq(String calculator) {
        return embeddingRepository.getWithImgAndCalculatorNotEq(calculator);
    }

    public Optional<Img> getImg(Embedding embedding) {
        return Optional.ofNullable(embedding.getImg().getId())
                .flatMap(imgRepository::findById);
    }

    public Optional<Img> getImg(String apiKey, UUID embeddingId) {
        return imgRepository.getImgByEmbeddingId(apiKey, embeddingId);
    }

    public Page<EmbeddingProjection> listEmbeddings(String apiKey, String subjectName , int ASCDESC,  Pageable pageable)
    {
        if (ASCDESC != 0)
        {
            return embeddingRepository.findBySubjectApiKeyAndSubjectNameAsc(apiKey, subjectName, pageable);
        }
        return embeddingRepository.findBySubjectApiKeyAndSubjectNameDesc(apiKey, subjectName, pageable);
    }

    public Page<EmbeddingProjection> allEmbeddingsSubId(String apiKey, String subjectName , int subId,int ASCDESC, Pageable pageable)
    {
        if (ASCDESC != 0)
        {
            return embeddingRepository.findBySubjectApiKeyAndSubjectNameAndSubIdAsc(apiKey, subjectName,subId, pageable);
        }
        return embeddingRepository.findBySubjectApiKeyAndSubjectNameAndSubIdDesc(apiKey, subjectName,subId, pageable);
    }
    public Page<SubjectEmbeddingProjection> listEmbeddings(String apiKey, Pageable pageable) {
        return embeddingRepository.findBySubjectApiKeySub(apiKey, pageable);
    }
    public Page<SubjectEmbeddingProjection> listEmbeddings(String apiKey, int subId, Pageable pageable) {
        return embeddingRepository.findBySubjectApiKeyAndSubId(apiKey,   subId, pageable);
    }


    public boolean isDemoCollectionInconsistent() {
        return embeddingRepository.countBySubjectApiKeyAndCalculatorNotEq(
                Constants.DEMO_API_KEY,
                Constants.FACENET2018
        ) > 0;
    }

    public boolean isDbInconsistent(String currentCalculator) {
        return embeddingRepository.countBySubjectApiKeyNotEqAndCalculatorNotEq(
                Constants.DEMO_API_KEY,
                currentCalculator
        ) > 0;
    }
}
