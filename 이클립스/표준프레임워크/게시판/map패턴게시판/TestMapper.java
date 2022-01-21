package egovframework.test.e1.service.impl;


import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;


@Mapper("testMapper")
public interface TestMapper {

	/**
	 * 글 등록
	 * @param map
	 * @return 등록 갯수
	 * @throws Exception
	 */
	Integer insert(Map<String, Object> map) throws Exception;
	
	/**
	 * 글 수정
	 * @param map
	 * @return 수정 갯수
	 * @throws Exception
	 */
	Integer update(Map<String, Object> map) throws Exception;
	
	/**
	 * 글 한개 가져오기
	 * @param map
	 * @return 글 한개  레코드
	 * @throws Exception
	 */
	EgovMap selectTest(Map<String, Object> map) throws Exception;
	
	/**
	 * 리스트 가져오기
	 * @param map
	 * @return 글 리스트
	 * @throws Exception
	 */
	List<EgovMap> selectTestList(Map<String, Object> map) throws Exception;
	
	/**
	 * 리스트 전체갯수 가져오기
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Integer selectTestCount(Map<String, Object> map) throws Exception;
	
	/**
	 * 글 한개 삭제
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Integer delete(Map<String, Object> map) throws Exception;
	
	
	/**
	 * 글 삭제 (멀티)
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	Integer deleteMulti(List<String> ids) throws Exception;
	
	/**
	 * 카테고리 가져오기
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<EgovMap> selectCateList(Map<String, Object> map) throws Exception;
	
	
		
}
