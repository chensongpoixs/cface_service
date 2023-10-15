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
public class SaveFaceImgSub
{
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Basic
//    @Column(name = "uuid_id", nullable = false, length = 255)
//    private String uuid_id;

    @Basic
    @Column(name = "embeddingid", nullable = true, length = 255)
    private String embeddingId;

    @Basic
    @Column(name = "master_id", nullable = true, precision = 0)
    private long  master_id;


    @Basic
    @Column(name = "gender", nullable = true, precision = 0)
    private Integer gender;

    @Basic
    @Column(name = "min_age", nullable = true, precision = 0)
    private Integer  min_age;


    @Basic
    @Column(name = "max_age", nullable = true, precision = 0)
    private Integer  max_age;

    @Basic
    @Column(name = "similarity", nullable = true, precision = 0)
    private float  similarity;
    @Basic
    @Column(name = "box_min_x", nullable = true, precision = 0)
    private Integer  box_min_x;

    @Basic
    @Column(name = "box_min_y", nullable = true, precision = 0)
    private Integer  box_min_y;

    @Basic
    @Column(name = "box_max_x", nullable = true, precision = 0)
    private Integer  box_max_x;

    @Basic
    @Column(name = "box_max_y", nullable = true, precision = 0)
    private Integer  box_max_y;
}
