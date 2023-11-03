/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.133
 Source Server Type    : PostgreSQL
 Source Server Version : 110021 (110021)
 Source Host           : 192.168.1.133:5432
 Source Catalog        : frs
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 110021 (110021)
 File Encoding         : 65001

 Date: 17/10/2023 17:11:16
*/


-- ----------------------------
-- Table structure for t_savefaceimg
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_savefaceimg";
CREATE TABLE "public"."t_savefaceimg" (
  "id" int8 NOT NULL DEFAULT nextval('t_savefaceimg_id_seq'::regclass),
  "api_key" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "timestamp" int4 NOT NULL,
  "img_url" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "device_id" int4 NOT NULL
)
;

-- ----------------------------
-- Primary Key structure for table t_savefaceimg
-- ----------------------------
ALTER TABLE "public"."t_savefaceimg" ADD CONSTRAINT "t_savefaceimg_pkey" PRIMARY KEY ("id");




CREATE TABLE  t_savefaceimg  (
  "id"  BIGSERIAL	PRIMARY KEY, 
  "api_key" varchar(255)   NOT NULL,
  "timestamp" int4 NOT NULL,
  "img_url" varchar(255) NOT NULL,
  "device_id" int4 NOT NULL
);

-- landmarks,gender,age
CREATE TABLE t_savefaceimg_subtable (
	"id" BIGSERIAL	PRIMARY KEY, 
	"embedding_id"  uuid NOT NULL,
	"sub_img_url"  varchar(1024) ,
	"master_id"    int8  NOT NULL,
	"gender"       int4  ,
	"min_age"      int4  ,
	"max_age" 	   int4  ,
	"similarity"   float8 NOT NULL,
	"box_min_x"    int4  NOT NULL,
	"box_min_y"    int4  NOT NULL,
	"box_max_x"    int4  NOT NULL,
	"box_max_y"    int4  NOT NULL
);









-- landmarks,gender,age
CREATE TABLE t_video_img_table (
	"id" BIGSERIAL	PRIMARY KEY, 
	"timestamp"  uuid NOT NULL,
	"device_id"   int4  NOT NULL,
	"img_url"  	varchar(1024)  NOT NULL
);