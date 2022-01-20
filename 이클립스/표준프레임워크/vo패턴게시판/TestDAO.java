package egovframework.test.e2.service.impl;


import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.test.e2.service.CodeVO;
import egovframework.test.e2.service.TestVO;


@Mapper("test2DAO")
public interface TestDAO{
	
	public List<CodeVO> selectCateList(String param) throws Exception;
	public void insertBbs(TestVO testVO) throws Exception;
	public void updateBbs(TestVO testVO) throws Exception;
	public void updateBbs2(TestVO testVO) throws Exception;
	public void deleteBbs(TestVO testVO) throws Exception;
	public List<EgovMap> selectListBbs(TestVO testVO) throws Exception;
	public Integer selectCountBbs(TestVO testVO) throws Exception;
	public TestVO selectBbs(TestVO testVO) throws Exception;
	public Integer deleteMultiBbs(List<String> delIds) throws Exception;
	

}
