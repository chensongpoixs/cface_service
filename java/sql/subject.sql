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

 Date: 16/10/2023 10:46:47
*/


-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS "public"."subject";
CREATE TABLE "public"."subject" (
  "id" uuid NOT NULL,
  "api_key" varchar(36) COLLATE "pg_catalog"."default",
  "subject_name" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of subject
-- ----------------------------
INSERT INTO "public"."subject" VALUES ('fba61656-c5ba-4e0d-8d77-0cbf11029337', '1f348698-6985-4296-914e-a5e3270cfad7', 'chensong');
INSERT INTO "public"."subject" VALUES ('79d3f019-2568-4bb0-96d8-d6bf858e1a0f', '1f348698-6985-4296-914e-a5e3270cfad7', '44_刘丽娟');
INSERT INTO "public"."subject" VALUES ('1737c724-3e36-4c94-a996-c273126f52ba', '1f348698-6985-4296-914e-a5e3270cfad7', '75_刘卫华');
INSERT INTO "public"."subject" VALUES ('8cfe77ce-b178-46b6-84dc-39e6bb54ea63', '1f348698-6985-4296-914e-a5e3270cfad7', '31_刘静');
INSERT INTO "public"."subject" VALUES ('eb4aabda-d334-4dc2-bb40-74c2ab595c27', '1f348698-6985-4296-914e-a5e3270cfad7', '08_周舟');
INSERT INTO "public"."subject" VALUES ('9bafcd19-7c11-44ef-ac8e-428415913d33', '1f348698-6985-4296-914e-a5e3270cfad7', '79_张全杰');
INSERT INTO "public"."subject" VALUES ('f93eaf39-78da-4989-af03-fdd7e52569f1', '1f348698-6985-4296-914e-a5e3270cfad7', '88_张池塘');
INSERT INTO "public"."subject" VALUES ('96efa678-242b-4b29-bd17-1a1dadd5c495', '1f348698-6985-4296-914e-a5e3270cfad7', '03_张童杰');
INSERT INTO "public"."subject" VALUES ('3550c452-8c48-4d34-a46d-1d718c1d4825', '1f348698-6985-4296-914e-a5e3270cfad7', '72_张默');
INSERT INTO "public"."subject" VALUES ('ed53ba78-1988-478a-98e4-c34533de50ae', '1f348698-6985-4296-914e-a5e3270cfad7', '32_徐宝成');
INSERT INTO "public"."subject" VALUES ('d7104bf5-6e3c-4d57-926a-cfb329eda216', '1f348698-6985-4296-914e-a5e3270cfad7', '04_徐振凯');
INSERT INTO "public"."subject" VALUES ('82cd86f3-52f1-43aa-965d-95d413c5c33e', '1f348698-6985-4296-914e-a5e3270cfad7', '14_徐振海');
INSERT INTO "public"."subject" VALUES ('3283263d-2107-427b-8563-573eae0931e6', '1f348698-6985-4296-914e-a5e3270cfad7', '12_徐继保');
INSERT INTO "public"."subject" VALUES ('7cda0979-fa0e-45c9-96f1-94129d73fe02', '1f348698-6985-4296-914e-a5e3270cfad7', '48_李晓明');
INSERT INTO "public"."subject" VALUES ('914c236a-8c9c-4b37-879d-de5f6ff2fe44', '1f348698-6985-4296-914e-a5e3270cfad7', '37_李雪健');
INSERT INTO "public"."subject" VALUES ('12a6caa4-ae8d-4b70-a6cb-41f063ceeb72', '1f348698-6985-4296-914e-a5e3270cfad7', '10_杜蓉');
INSERT INTO "public"."subject" VALUES ('d1cfe70e-f9cc-4a28-b2a9-8df5dae07c34', '1f348698-6985-4296-914e-a5e3270cfad7', '59_毛智睿');
INSERT INTO "public"."subject" VALUES ('2e651f53-9d19-4f8c-a315-c99067be6eb2', '1f348698-6985-4296-914e-a5e3270cfad7', '19_王天志');
INSERT INTO "public"."subject" VALUES ('6092d668-d8b8-4cae-b72c-57fa67e73543', '1f348698-6985-4296-914e-a5e3270cfad7', '23_邓叶飞');
INSERT INTO "public"."subject" VALUES ('6b42aaf2-3deb-4c97-8ee1-0fed455d7965', '1f348698-6985-4296-914e-a5e3270cfad7', '02_陈虹旭');
INSERT INTO "public"."subject" VALUES ('2761fcdc-a6f6-448d-bcb6-dc94963b5451', '1f348698-6985-4296-914e-a5e3270cfad7', '77_陈超');
INSERT INTO "public"."subject" VALUES ('4596d6c6-1328-403f-a8f4-2d20d3b7e0fc', '1f348698-6985-4296-914e-a5e3270cfad7', '13_高志强');
INSERT INTO "public"."subject" VALUES ('1ad39625-a533-40e5-82ff-1d7b904ccd2b', '1f348698-6985-4296-914e-a5e3270cfad7', '00_王春草');
INSERT INTO "public"."subject" VALUES ('41d5fe2f-e7a0-40e5-be52-9afca7ea85f2', '1f348698-6985-4296-914e-a5e3270cfad7', '01_马丽娟');

-- ----------------------------
-- Uniques structure for table subject
-- ----------------------------
ALTER TABLE "public"."subject" ADD CONSTRAINT "api_key_subject_name_uindex" UNIQUE ("api_key", "subject_name");

-- ----------------------------
-- Primary Key structure for table subject
-- ----------------------------
ALTER TABLE "public"."subject" ADD CONSTRAINT "pk_subject" PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Keys structure for table subject
-- ----------------------------
ALTER TABLE "public"."subject" ADD CONSTRAINT "fk_subject_model_api_key" FOREIGN KEY ("api_key") REFERENCES "public"."model" ("api_key") ON DELETE CASCADE ON UPDATE CASCADE;
