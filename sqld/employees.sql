--------------------------------------------------------
--  파일이 생성됨 - 목요일-10월-22-2026   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table EMPLOYEES
--------------------------------------------------------

  CREATE TABLE "DONG"."EMPLOYEES" 
   (	"EMP_NAME" VARCHAR2(20) NOT NULL ENABLE, 
	"DEPT_NAME" VARCHAR2(20), 
	"JOB_TITLE" VARCHAR2(20), 
	"SALARY" NUMBER, 
	"HIRE_DATE" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"
REM INSERTING into DONG.EMPLOYEES
SET DEFINE OFF;
Insert into DONG.EMPLOYEES (EMP_NAME,DEPT_NAME,JOB_TITLE,SALARY,HIRE_DATE) values ('James','Sales','Manager',9000,to_date('22/01/10','RR/MM/DD'));
Insert into DONG.EMPLOYEES (EMP_NAME,DEPT_NAME,JOB_TITLE,SALARY,HIRE_DATE) values ('Sarah','Sales','Assistant Manager',7500,to_date('22/03/15','RR/MM/DD'));
Insert into DONG.EMPLOYEES (EMP_NAME,DEPT_NAME,JOB_TITLE,SALARY,HIRE_DATE) values ('Michale','Sales','Assistant Manager',7200,to_date('22/03/20','RR/MM/DD'));
Insert into DONG.EMPLOYEES (EMP_NAME,DEPT_NAME,JOB_TITLE,SALARY,HIRE_DATE) values ('Emily','Sales','Staff',6000,to_date('23/02/10','RR/MM/DD'));
Insert into DONG.EMPLOYEES (EMP_NAME,DEPT_NAME,JOB_TITLE,SALARY,HIRE_DATE) values ('David','Sales','Staff',6000,to_date('23/04/01','RR/MM/DD'));
Insert into DONG.EMPLOYEES (EMP_NAME,DEPT_NAME,JOB_TITLE,SALARY,HIRE_DATE) values ('Olivia','Planning','Manger',9500,to_date('21/11/05','RR/MM/DD'));
Insert into DONG.EMPLOYEES (EMP_NAME,DEPT_NAME,JOB_TITLE,SALARY,HIRE_DATE) values ('Danial','Planning','Assistant Manager',9500,to_date('22/08/22','RR/MM/DD'));
Insert into DONG.EMPLOYEES (EMP_NAME,DEPT_NAME,JOB_TITLE,SALARY,HIRE_DATE) values ('Sophia','Planning','Staff',7500,to_date('23/01/15','RR/MM/DD'));
Insert into DONG.EMPLOYEES (EMP_NAME,DEPT_NAME,JOB_TITLE,SALARY,HIRE_DATE) values ('Chris','Development','Manager',10000,to_date('21/07/01','RR/MM/DD'));
Insert into DONG.EMPLOYEES (EMP_NAME,DEPT_NAME,JOB_TITLE,SALARY,HIRE_DATE) values ('Emma','Development','Staff',6500,to_date('22/10/30','RR/MM/DD'));
Insert into DONG.EMPLOYEES (EMP_NAME,DEPT_NAME,JOB_TITLE,SALARY,HIRE_DATE) values ('Liam','Development','Staff',6200,to_date('23/06/01','RR/MM/DD'));
--------------------------------------------------------
--  Constraints for Table EMPLOYEES
--------------------------------------------------------

  ALTER TABLE "DONG"."EMPLOYEES" MODIFY ("EMP_NAME" NOT NULL ENABLE)
