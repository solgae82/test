--------------------------------------------------------
--  파일이 생성됨 - 일요일-10월-25-2026   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table TB_PIVOT
--------------------------------------------------------

  CREATE TABLE "DONG"."TB_PIVOT" 
   (	"NM" VARCHAR2(20 BYTE), 
	"SUBJECT" VARCHAR2(20 BYTE), 
	"SCORE" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into DONG.TB_PIVOT
SET DEFINE OFF;
Insert into DONG.TB_PIVOT (NM,SUBJECT,SCORE) values ('홍길동','math',90);
Insert into DONG.TB_PIVOT (NM,SUBJECT,SCORE) values ('홍길동','english',85);
Insert into DONG.TB_PIVOT (NM,SUBJECT,SCORE) values ('강감찬','math',90);
Insert into DONG.TB_PIVOT (NM,SUBJECT,SCORE) values ('강감찬','english',95);
Insert into DONG.TB_PIVOT (NM,SUBJECT,SCORE) values ('이순신','math',95);
Insert into DONG.TB_PIVOT (NM,SUBJECT,SCORE) values ('이순신','english',100);
