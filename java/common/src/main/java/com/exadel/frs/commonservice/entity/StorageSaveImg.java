//package com.exadel.frs.commonservice.entity;
//
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class StorageSaveImg
//{
//    @Id
//    @OneToMany(targetEntity =  SaveFaceImgSub.class)
//    @JoinColumn(name = "id", referencedColumnName = "master_id")
//    private SaveFaceImgSub saveFaceImgSub;
//
////    @Basic
////    @Column(name = "uuid_id", nullable = false, length = 255)
////    private String uuid_id;
//
//    @Basic
//    @Column(name = "api_key", nullable = false, length = 255)
//    private String api_key;
//    @Column(name = "timestamp", nullable = true, precision = 0)
//    private Integer  timestmap;
//
//
//    @Basic
//    @Column(name = "img_url", nullable = false, length = 255)
//    private String img_url;
//
//    @Basic
//    @Column(name = "device_id", nullable = true, precision = 0)
//    private Integer  device_id;
//
//
//
//
//}
