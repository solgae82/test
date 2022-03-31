package egovframework.test.e3.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.test.e3.service.CodeVO;
import egovframework.test.e3.service.TestService;
import egovframework.test.e3.service.TestVO;

@Service("test3Service")
public class TestServiceImpl extends EgovAbstractServiceImpl implements TestService {
	private static final Logger LOGGER = LoggerFactory.getLogger(TestServiceImpl.class);
	
	@Resource(name="test3DAO")
	TestDAO testDAO;

	/** ID Generation */
	@Resource(name = "egovBbsIdGnrService")
	private EgovIdGnrService egovIdGnrService;

	
	@Override
	public List<CodeVO> selectCategory(String codeName) throws Exception {
		return testDAO.selectCategory(codeName);
	}

	@Override
	public Integer insertBbs(TestVO testVO) throws Exception {
		testVO.setId(egovIdGnrService.getNextStringId());
		this.unescapeHtml(testVO);		//필터가 바꾼 html 엔티티 원래대로 되돌리기
		return testDAO.insertBbs(testVO);		
	}

	@Override
	public List<EgovMap> selectBbsList(TestVO searchVO) throws Exception {
		
		return testDAO.selectBbsList(searchVO);
	}

	@Override
	public Integer selectBbsCount(TestVO searchVO) throws Exception {
		
		return testDAO.selectBbsCount(searchVO);
	}

	@Override
	public TestVO selectBbs(TestVO searchVO) throws Exception {
		return testDAO.selectBbs(searchVO);
	}

	@Override
	public Integer updateBbs(TestVO testVO) throws Exception {
		
		this.unescapeHtml(testVO);	//필터가 바꾼 html 엔티티 원래대로 되돌리기
		return testDAO.updateBbs(testVO);
		
	}

	@Override
	public Integer deleteBbs(TestVO testVO) throws Exception {
		return testDAO.deleteBbs(testVO);
		
	}
	
	/**
	 * html태그필터가 바꾼 html 특수문자를 다시 되돌린다 &lt; => '<'
	 * @param testVO
	 * @throws Exception
	 */
	public void unescapeHtml(TestVO testVO) throws Exception{
		String sbScription = StringEscapeUtils.unescapeHtml4(testVO.getSbScription());
		String description = StringEscapeUtils.unescapeHtml4(testVO.getDescription());
		String searchKeyword = StringEscapeUtils.unescapeHtml4(testVO.getSearchKeyword());
		
		testVO.setSbScription(sbScription);
		testVO.setDescription(description);
		testVO.setSearchKeyword(searchKeyword);
	}

	@Override
	public List<String> convertStringToList(String str, String delemeter) throws Exception {
		List<String> list = null;
		if(StringUtils.isEmpty(str) || StringUtils.isEmpty(delemeter)) {return list;}
		list = Arrays.asList(str.split(delemeter));
		return list;
	}

	@Override
	public Integer deleteMultiBbs(List<String> ids) throws Exception {
		return testDAO.deleteMultiBbs(ids);
	}
	
	
	@Override
	public int getCurrentPageFirstIndex(PaginationInfo paginationInfo) throws Exception {
		
		int total = paginationInfo.getTotalRecordCount();
		int currentPageNumber = paginationInfo.getCurrentPageNo();
		int pageSize = paginationInfo.getPageSize();
		
		return (total + 1) -((currentPageNumber - 1) * pageSize + 1);
		
	}
	
		
		
}
