package com.exadel.frs.commonservice.entity;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
//JPA 中的 @Table 注解用于指定实体类对应的数据库表的名称和其他属性。
//        常用属性：
//        - name：指定数据库表的名称，默认值为实体类的名称。
//        - schema：指定数据库表所在的 schema。
//        - catalog：指定数据库表所在的 catalog。
//        - uniqueConstraints：指定数据库表的唯一约束条件。
//        - indexes：指定数据库表的索引。
//        示例：
//        点击复制代码
//        java
//@Entity
//@Table(name = "user", schema = "public", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
//public class User {
//    //...
//}
@Table(name = "t_savefaceimg", schema = "public")
//记得添加上依赖包
//        结合@Data注解进行自动注入
//主要功能是：丰富java自动资源管理，自动生成getter、setter、equals、hashCode和toString等等
@Data
//讲解一下实体类中另外两个注解
//@AllArgsConstructor ： 注解在类上，有参构造
//@NoArgsConstructor ： 注解在类上，无参构造
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class SaveFaceImg
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @OneToMany(fetch = FetchType.LAZY, targetEntity=SaveFaceImgSub.class)
//    @JoinColumn(name = "id", referencedColumnName = "master_id")
    private Long id;

    //task对channel时一对多
    @OneToMany(
            //EAGER 为全局加载，在有内存屏蔽的情况下建议使用，因为session关闭后无法查询hannelDO
            //普通情况使用LAZY即可，LAZY时，只有在getChannelDOs时才会去查询ChannelDO
            fetch=FetchType.LAZY,
            targetEntity=SaveFaceImgSub.class,
            mappedBy="saveFaceImg"
    )
    private List<SaveFaceImgSub> saveFaceImgSubs  = new ArrayList<>();
//    @ToString.Exclude
//    @Builder.Default
//    @OneToMany(mappedBy = "saveFaceImg", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<SaveFaceImgSub> saveFaceImgSubs = new ArrayList<>();


//    @Basic
//    @Column(name = "uuid_id", nullable = false, length = 255)
//    private String uuid_id;

    @Basic
    @Column(name = "api_key", nullable = false, length = 255)
    private String apiKey;
    @Column(name = "timestamp", nullable = true, precision = 0)
    private Integer  timestmap;


    @Basic
    @Column(name = "img_url", nullable = false, length = 255)
    private String imgUrl;

    @Basic
    @Column(name = "device_id", nullable = true, precision = 0)
    private Integer  deviceId;

}
