--------------------------------------------------------
--  파일이 생성됨 - 일요일-10월-25-2026   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table TB_UNPIVOT
--------------------------------------------------------

  CREATE TABLE "DONG"."TB_UNPIVOT" 
   (	"NM" VARCHAR2(20 BYTE), 
	"MATH" NUMBER, 
	"ENGLISH" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into DONG.TB_UNPIVOT
SET DEFINE OFF;
Insert into DONG.TB_UNPIVOT (NM,MATH,ENGLISH) values ('강감찬',90,95);
Insert into DONG.TB_UNPIVOT (NM,MATH,ENGLISH) values ('홍길동',90,85);
Insert into DONG.TB_UNPIVOT (NM,MATH,ENGLISH) values ('이순신',95,100);
