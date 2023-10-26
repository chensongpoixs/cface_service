package com.exadel.frs.commonservice.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "t_video_img_table", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideoImgStorageTable
{
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "timestamp", nullable = true, precision = 0)
    private Integer  timestamp;

    @Basic
    @Column(name = "device_id", nullable = true, precision = 0)
    private Integer  deviceId;

    @Basic
    @Column(name = "img_url", nullable = false, length = 1024)
    private String imgUrl;


}
