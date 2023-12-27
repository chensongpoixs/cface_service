package com.exadel.frs.commonservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
public class CaputreImg
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    @Column(name = "api_key", nullable = false, length = 255)
    private String apiKey;
    @Column(name = "timestamp", nullable = true, precision = 0)
    private Integer  timestamp;
    @Basic
    @Column(name = "img_url", nullable = false, length = 255)
    private String imgUrl;

    @Basic
    @Column(name = "device_id", nullable = true, precision = 0)
    private Integer  deviceId;
}
