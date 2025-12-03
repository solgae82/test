----- 문자 셋 설정
/*
	SYS AS SYSDBA 로 접속하자
	위치 : $ORACLE_HOME/product/버전/dbs/init.ora
	parallel_server => true / false 확인 
	false로 설정 되어 있어야 설정시 오류가 발생하지 않는다.
*/
show parameter parallel_server; 

-- 문자셋 확인 쿼리
select * from nls_database_parameters where parameter like '%CHARACTERSET%';

select * from sys.props$ where name='NLS_LANGUAGE';

-- 문자셋 변경하기
update sys.props$ set value$='UTF8' where name='NLS_CHARACTERSET';
update sys.props$ set value$='UTF8' where name='NLS_NCHAR_CHARACTERSET';
update sys.props$ set value$='KOREAN_KOREA.UTF8' where name='NLS_LANGUAGE';

commit;


-------------------// DONG 계정 생성 권한 주기

-- DONG 테이블스페이스 생성
create tablespace DONG
datafile 'dong.dbf'	/*설치된 $ORACL_HOME/dbs/ */
size 100M
autoextend on next 5M
maxsize unlimited;

--  DONG temporary 테이블스페이스 생성
create temporary tablespace DONGTEMP
tempfile 'dongtemp.dbf' /*설치된 $ORACL_HOME/dbs/ */
size 50M
autoextend on next 5M
maxsize unlimited;


-- 계정 생성
CREATE USER DONG IDENTIFIED BY "패스워드"
DEFAULT TABLESPACE DONG
TEMPORARY TABLESPACE DONGTEMP
ACCOUNT UNLOCK;

-- 권한 주기
grant connect, resource, dba to DONG;


