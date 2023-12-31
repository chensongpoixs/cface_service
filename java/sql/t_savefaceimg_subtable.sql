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

 Date: 17/10/2023 17:11:24
*/


-- ----------------------------
-- Table structure for t_savefaceimg_subtable
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_savefaceimg_subtable";
CREATE TABLE "public"."t_savefaceimg_subtable" (
  "id" int8 NOT NULL DEFAULT nextval('t_savefaceimg_subtable_id_seq'::regclass),
  "embeddingid" varchar(1024) COLLATE "pg_catalog"."default",
  "master_id" int8 NOT NULL,
  "gender" int4,
  "min_age" int4,
  "max_age" int4,
  "similarity" float8 NOT NULL,
  "box_min_x" int4 NOT NULL,
  "box_min_y" int4 NOT NULL,
  "box_max_x" int4 NOT NULL,
  "box_max_y" int4 NOT NULL
)
;

-- ----------------------------
-- Primary Key structure for table t_savefaceimg_subtable
-- ----------------------------
ALTER TABLE "public"."t_savefaceimg_subtable" ADD CONSTRAINT "t_savefaceimg_subtable_pkey" PRIMARY KEY ("id");
