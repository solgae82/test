--------------------------------------------------------
--  파일이 생성됨 - 일요일-10월-25-2026   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table GF_EMPLOYEES
--------------------------------------------------------

  CREATE TABLE "DONG"."GF_EMPLOYEES" 
   (	"EMP_NAME" VARCHAR2(20 BYTE), 
	"DEPT_NAME" VARCHAR2(20 BYTE), 
	"JOB_TITLE" VARCHAR2(20 BYTE), 
	"SALARY" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into DONG.GF_EMPLOYEES
SET DEFINE OFF;
Insert into DONG.GF_EMPLOYEES (EMP_NAME,DEPT_NAME,JOB_TITLE,SALARY) values ('James','Sales','Manager',7000);
Insert into DONG.GF_EMPLOYEES (EMP_NAME,DEPT_NAME,JOB_TITLE,SALARY) values ('Sarah','Sales','Assistant Manager',6000);
Insert into DONG.GF_EMPLOYEES (EMP_NAME,DEPT_NAME,JOB_TITLE,SALARY) values ('Michale','Sales','Assistant Manager',5500);
Insert into DONG.GF_EMPLOYEES (EMP_NAME,DEPT_NAME,JOB_TITLE,SALARY) values ('Emily','Sales','Staff',4000);
Insert into DONG.GF_EMPLOYEES (EMP_NAME,DEPT_NAME,JOB_TITLE,SALARY) values ('David','Sales','Staff',4200);
Insert into DONG.GF_EMPLOYEES (EMP_NAME,DEPT_NAME,JOB_TITLE,SALARY) values ('Olivia','Planning','Manager',8000);
Insert into DONG.GF_EMPLOYEES (EMP_NAME,DEPT_NAME,JOB_TITLE,SALARY) values ('Danial','Planning','Assistant Manager',7500);
Insert into DONG.GF_EMPLOYEES (EMP_NAME,DEPT_NAME,JOB_TITLE,SALARY) values ('Sophia','Planning','Staff',5000);
Insert into DONG.GF_EMPLOYEES (EMP_NAME,DEPT_NAME,JOB_TITLE,SALARY) values ('Chris','Development','Manager',9000);
Insert into DONG.GF_EMPLOYEES (EMP_NAME,DEPT_NAME,JOB_TITLE,SALARY) values ('Emma','Development','Staff',6000);
Insert into DONG.GF_EMPLOYEES (EMP_NAME,DEPT_NAME,JOB_TITLE,SALARY) values ('Liam','Development','Staff',6200);
Insert into DONG.GF_EMPLOYEES (EMP_NAME,DEPT_NAME,JOB_TITLE,SALARY) values ('Dt.X',null,'Intern',1000);
--------------------------------------------------------
--  Constraints for Table GF_EMPLOYEES
--------------------------------------------------------

  ALTER TABLE "DONG"."GF_EMPLOYEES" MODIFY ("EMP_NAME" NOT NULL ENABLE);
