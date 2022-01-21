package egovframework.test.e1.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

public interface TestService {

	/**
	 * 글 등록
	 * @param map
	 * @return 등록 갯수
	 * @throws Exception
	 */
	String insert (Map<String, Object> map) throws Exception;
	
	/**
	 * 글 수정
	 * @param map
	 * @return 수정갯수
	 * @throws Exception
	 */
	Integer update(Map<String, Object> map) throws Exception;
	
	/**
	 * 글 한개 가져오기
	 * @param map
	 * @return 글 레코드
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
	 * 글 삭제 (한개)
	 * @param map
	 * @return 삭제 갯수
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
	List<EgovMap> selectCateList() throws Exception;
	
	/**
	 * 구분자 있는 문자열 리스트로 반환
	 * @param str (문자열)
	 * @param delemeter (구분자)
	 * @return List<String>
	 * @throws Exception
	 */
	List<String> convertStringToList(String str, String delemeter) throws Exception;
	
	/**
	 * 현재 페이지 첫 레코드 번호 반환
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 */
	int getCurrentPageFirstIdx(PaginationInfo paginationInfo) throws Exception;
	
	/**
	 * 등록,수정 유효성 검증 후 에러 메시지 반환
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map<String,Object> getValidateResult(Map paramMap) throws Exception;
}
