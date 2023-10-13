package com.exadel.frs.core.trainservice.component;

import lombok.Data;

import java.util.UUID;

@Data
public class ResultEmbeddingData
{
    String embeddingId;
    String SubjectName;

    Double[] prod;
}
