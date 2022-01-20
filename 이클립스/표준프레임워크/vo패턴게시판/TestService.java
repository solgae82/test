package egovframework.test.e2.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

public interface TestService {
	
	/**
	 * 공통카테고리 가져오기
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<CodeVO> selectCateList(String param) throws Exception;
	
	/**
	 * BBS 등록
	 * @param testVO
	 * @throws Exception
	 */
	public void insertBbs(TestVO testVO) throws Exception;
	
	/**
	 * BBS 수정 (설정한 필드만 업데이트)
	 * @param testVO
	 * @throws Exception
	 */
	public void updateBbs(TestVO testVO) throws Exception;
	
	/**
	 * BBS 수정 (모든 필드 업데이트)
	 * @param testVO
	 * @throws Exception
	 */
	public void updateBbs2(TestVO testVO) throws Exception;
	
	/**
	 * BBS 게시물 한개 삭제
	 * @param testVO
	 * @throws Exception
	 */
	public void deleteBbs(TestVO testVO) throws Exception;
	
	/**
	 * BBS 리스트 가져오기
	 * @param testVO
	 * @return
	 * @throws Exception
	 */
	public List<EgovMap> selectListBbs(TestVO testVO) throws Exception;
	
	/**
	 * BBS 전체갯수 가져오기
	 * @param testVO
	 * @return
	 * @throws Exception
	 */
	public Integer selectCountBbs(TestVO testVO) throws Exception;
	/**
	 * BBS 게시물 한개 가져오기
	 * @param testVO
	 * @return
	 * @throws Exception
	 */
	public TestVO selectBbs(TestVO testVO) throws Exception;
	
	/**
	 * 현재일시 문자열 반환 
	 * @return "20221225093015"
	 * @throws Exception
	 */
	public String nowDateString() throws Exception;
	
	/**
	 * 현재 페이지 첫 레코드 번호 반환
	 * @param paginationInfo
	 * @return
	 * @throws Exception
	 */
	public int getCurrentPageFirstIndex(PaginationInfo paginationInfo) throws Exception;
	
	/**
	 * 구분자 +문자열데이터 , 구분자로 리스트 변환
	 * @param str(직렬화 문자열)
	 * @param delemeter(구분자
	 * @return
	 * @throws Exception
	 */
	public List<String> convertStringToList(String str , String delemeter) throws Exception;
	
	/**
	 * BBS 여러개 삭제하기
	 * @param delIds
	 * @return
	 * @throws Exception
	 */
	public Integer deleteMultiBbs(List<String> delIds) throws Exception;
}
