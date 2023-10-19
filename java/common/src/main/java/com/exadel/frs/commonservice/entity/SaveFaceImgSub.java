package com.exadel.frs.commonservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;

@Entity
@Table(name = "t_savefaceimg_subtable", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@NamedEntityGraph(name = "savefaceimgsu-with-savefaceimg", attributeNodes = {@NamedAttributeNode("saveFaceImg")})

public class SaveFaceImgSub
{
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Basic
//    @Column(name = "uuid_id", nullable = false, length = 255)
//    private String uuid_id;

//    @Basic
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "embeddingid", nullable = true, referencedColumnName = "id")
//    private Embedding embedding;
//    @Column(name = "embeddingid", nullable = true, length = 255)
//    private String embeddingId;


    @Basic
    @Column(name = "sub_img_url", nullable = true, precision = 0)
    private String subImgUrl;


    @ManyToOne (fetch = FetchType.LAZY, targetEntity = Embedding.class/*,  orphanRemoval = true*/)
    @JoinColumn(name = "embedding_id", referencedColumnName = "id",  nullable = false)
    private Embedding embeddingId;
//    @Basic
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = SaveFaceImg.class ,optional = false)
    @JoinColumn(name = "master_id",   referencedColumnName = "id")
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "master_id", referencedColumnName = "id")
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "master_id" )
    private SaveFaceImg saveFaceImg = new SaveFaceImg();
//    @Column(name = "master_id", nullable = true, precision = 0)
//    private long  master_id;


    @Basic
    @Column(name = "gender", nullable = true, precision = 0)
    private Integer gender;

    @Basic
    @Column(name = "min_age", nullable = true, precision = 0)
    private Integer  minAge;


    @Basic
    @Column(name = "max_age", nullable = true, precision = 0)
    private Integer  maxAge;

    @Basic
    @Column(name = "similarity", nullable = true, precision = 0)
    private float  similarity;
    @Basic
    @Column(name = "box_min_x", nullable = true, precision = 0)
    private Integer  boxMinX;

    @Basic
    @Column(name = "box_min_y", nullable = true, precision = 0)
    private Integer  boxMinY;

    @Basic
    @Column(name = "box_max_x", nullable = true, precision = 0)
    private Integer  boxMaxX;

    @Basic
    @Column(name = "box_max_y", nullable = true, precision = 0)
    private Integer  boxMaxY;
}
