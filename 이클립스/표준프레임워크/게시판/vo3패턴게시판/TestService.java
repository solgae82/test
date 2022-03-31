package egovframework.test.e3.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

public interface TestService {

	/**
	 * 공통코드 가져오기
	 * @param codeName
	 * @return
	 * @throws Exception
	 */
	public List<CodeVO> selectCategory(String codeName) throws Exception;
	
	/**
	 * BBS 등록
	 * @param testVO
	 * @throws Exception
	 */
	public Integer insertBbs(TestVO testVO) throws Exception;
	
	/**
	 * BBS 리스트 가져오기
	 * @param testVO
	 * @return
	 * @throws Exception
	 */
	public List<EgovMap> selectBbsList(TestVO searchVO)  throws Exception;
	
	/**
	 *  BBS 리스트 총 갯수 반환
	 * @param testVO
	 * @return
	 * @throws Exception
	 */
	public Integer selectBbsCount(TestVO searchVO)  throws Exception;
	
	/**
	 *  BBS 상세보기
	 * @param testVO
	 * @return
	 * @throws Exception
	 */
	public TestVO selectBbs(TestVO searchVO)  throws Exception;
	
	/**
	 * BBS 업데이트
	 * @param testVO
	 * @throws Exception
	 */
	public Integer updateBbs(TestVO testVO) throws Exception;
	
	/**
	 * BBS 글 한개 삭제 
	 * @param testVO
	 * @throws Exception
	 */
	public Integer deleteBbs(TestVO testVO) throws Exception;
	
	
	/**
	 * html태그필터가 바꾼 html 특수문자를 다시 되돌린다 &lt; => '<'
	 * @param testVO
	 * @throws Exception
	 */
	public void unescapeHtml(TestVO testVO) throws Exception;
	/**
	 * 구분자 있는 문자열 -> List 반환 
	 * (stack,'구분자')
	 * @param str
	 * @param delemeter
	 * @return
	 * @throws Exception
	 */
	public List<String> convertStringToList(String str, String delemeter) throws Exception;
	
	/**
	 * BBS 글 여러개 삭제
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public Integer deleteMultiBbs(List<String> ids) throws Exception;
	
	/**
	 * 리스트 페이지 첫 번호 출력
	 * @param paginationInfo
	 * @return
	 * @throws Exception
	 */
	public int getCurrentPageFirstIndex(PaginationInfo paginationInfo) throws Exception;
	
}
