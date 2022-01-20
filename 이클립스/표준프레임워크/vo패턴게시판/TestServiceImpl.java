package egovframework.test.e2.service.impl;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.test.e2.service.CodeVO;
import egovframework.test.e2.service.Test;
import egovframework.test.e2.service.TestService;
import egovframework.test.e2.service.TestVO;


@Service("test2Service")
public class TestServiceImpl extends EgovAbstractServiceImpl implements TestService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestServiceImpl.class);
	
	@Resource(name="test2DAO")
	TestDAO testDAO;
	
	@Resource(name = "egovBbsIdGnrService")
	private EgovIdGnrService egovIdGnrService;

	
	
	@Override
	public List<CodeVO> selectCateList(String param) throws Exception {
		
		return testDAO.selectCateList(param);
	}

	@Override
	public void insertBbs(TestVO testVO) throws Exception {
		
		testVO.setId(egovIdGnrService.getNextStringId());
		testVO.setRegDate(this.nowDateString());
		testDAO.insertBbs(testVO);
		
	}

	@Override
	public void updateBbs(TestVO testVO) throws Exception {
		
		testVO.setUpDate(this.nowDateString());
		testDAO.updateBbs(testVO);
		
	}
	
	public void updateBbs2(TestVO testVO) throws Exception {
		
		testVO.setUpDate(this.nowDateString());
		testDAO.updateBbs2(testVO);
		
	}

	@Override
	public void deleteBbs(TestVO testVO) throws Exception {
		
		testDAO.deleteBbs(testVO);
	}

	@Override
	public List<EgovMap> selectListBbs(TestVO testVO) throws Exception {
				
		return testDAO.selectListBbs(testVO);
		
	}

	
	@Override
	public Integer selectCountBbs(TestVO testVO) throws Exception {

		return testDAO.selectCountBbs(testVO);
	}

	@Override
	public TestVO selectBbs(TestVO testVO) throws Exception {
		return testDAO.selectBbs(testVO);
	}

	@Override
	public String nowDateString() throws Exception {
				
		SimpleDateFormat simpleFormat  = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar cal = Calendar.getInstance();
				
		return simpleFormat.format(cal.getTime());
	}

	@Override
	public int getCurrentPageFirstIndex(PaginationInfo paginationInfo) throws Exception {
		
		int total = paginationInfo.getTotalRecordCount();
		int currentPageNumber = paginationInfo.getCurrentPageNo();
		int pageSize = paginationInfo.getPageSize();
		
		return (total + 1) -((currentPageNumber - 1) * pageSize + 1);
		
	}

	@Override
	public List<String> convertStringToList(String str, String delemeter) throws Exception {
		
		List<String> list = null;
		if(StringUtils.isEmpty(str) || StringUtils.isEmpty(delemeter)) {return list;}
		list = Arrays.asList(str.split(delemeter));
		return list;
	}

	@Override
	public Integer deleteMultiBbs(List<String> delIds) throws Exception {
		
		return testDAO.deleteMultiBbs(delIds);
	}	
		
	
	
	
}
