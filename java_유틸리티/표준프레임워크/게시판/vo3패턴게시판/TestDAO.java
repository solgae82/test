package egovframework.test.e3.service.impl;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.test.e3.service.CodeVO;
import egovframework.test.e3.service.TestVO;

@Mapper("test3DAO")
public interface TestDAO {
	
	/**
	 * 카테고리 리스트 가져오기
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
	 * BBS 리스트 총 갯수 반환
	 * @param testVO
	 * @return
	 * @throws Exception
	 */
	public Integer selectBbsCount(TestVO searchVO)  throws Exception;
	
	/**
	 * BBS 상세보기
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
	 * BBS 글 여러개 삭제
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public Integer deleteMultiBbs(List<String> ids) throws Exception;
}
