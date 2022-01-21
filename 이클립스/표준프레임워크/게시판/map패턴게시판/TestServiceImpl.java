package egovframework.test.e1.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.test.e1.service.TestService;

@Service("testService")
public class TestServiceImpl extends EgovAbstractServiceImpl implements TestService {

	@Resource(name="testMapper")
	private TestMapper mapper;
	
	/** ID Generation */
	@Resource(name = "egovBbsIdGnrService")
	private EgovIdGnrService egovIdGnrService;
	
	/**
	 * 글 등록
	 * @param map
	 * @return 등록 갯수
	 * @throws Exception
	 */
	public String insert(Map<String, Object> map) throws Exception {
		
		String id = egovIdGnrService.getNextStringId();
		map.put("id", id);
		
		Integer cnt = mapper.insert(map);
		
		return id;
				
	}

	/**
	 * 글 수정
	 * @param map
	 * @return 수정갯수
	 * @throws Exception
	 */
	public Integer update(Map<String, Object> map) throws Exception {
		
		return mapper.update(map);
	}

	/**
	 * 글 한개 가져오기
	 * @param map
	 * @return 글 레코드
	 * @throws Exception
	 */
	public EgovMap selectTest(Map<String, Object> map) throws Exception {
		
		return mapper.selectTest(map);
	}

	/**
	 * 리스트 가져오기
	 * @param map
	 * @return 글 리스트
	 * @throws Exception
	 */
	public List<EgovMap> selectTestList(Map<String, Object> map) throws Exception {
		
		return mapper.selectTestList(map);
	}

	
	/**
	 * 리스트 전체갯수 가져오기
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Integer selectTestCount(Map<String, Object> map) throws Exception {
		return mapper.selectTestCount(map);
	}

	
	/**
	 * 글 삭제 (한개)
	 * @param map
	 * @return 삭제 갯수
	 * @throws Exception
	 */
	public Integer delete(Map<String, Object> map) throws Exception {

		return mapper.delete(map);
	}
	
	/**
	 * 글 삭제 (멀티)
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public Integer deleteMulti(List<String> ids) throws Exception{
		return mapper.deleteMulti(ids);
	}

	/**
	 * 카테고리 가져오기
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<EgovMap> selectCateList() throws Exception {

		Map<String,Object> cateParam = new HashMap<String, Object>();
		cateParam.put("codeName", "category");
		return mapper.selectCateList(cateParam);
	}

	/**
	 * 구분자 있는 문자열 리스트로 반환
	 * @param str (문자열)
	 * @param delemeter (구분자)
	 * @return List<String>
	 * @throws Exception
	 */
	public List<String> convertStringToList(String str, String delemeter) throws Exception {
				
		List<String> list = null;
		if(str == null || "".equals(str)) {return list;}
		if(delemeter == null || "".equals(delemeter)) {return list;}
		list = Arrays.asList(str.split(delemeter));
		return list;
	}

	/**
	 * 현재 페이지 첫 레코드 번호 반환
	 * @param pageInfo
	 * @return
	 * @throws Exception
	 */
	public int getCurrentPageFirstIdx(PaginationInfo paginationInfo) throws Exception {
		
		int total = paginationInfo.getTotalRecordCount();
		int currPage = paginationInfo.getCurrentPageNo();
		int pageSize = paginationInfo.getPageSize();
		
		return (total + 1) - ((currPage - 1) * pageSize + 1);
	}

	/**
	 * 등록,수정 유효성 검증 후 에러 메시지 반환
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getValidateResult(Map paramMap) throws Exception {
		
		Map<String, Object> errorMap = new LinkedHashMap<String, Object>();
				
		//1.제목필드 체크
		List<String> errorList1 = new ArrayList<String>();
		String sbScription = (String)paramMap.get("sbScription");
		if(StringUtils.isBlank(sbScription)) {
			errorList1.add("제목을 입력해 주세요");
		}		
		
		if(errorList1.size() > 0) {	
			errorMap.put("sbScription", errorList1);
		}else {
			paramMap.put("sbScription", sbScription.trim());//공백제거 후 reset
		}
		
		//2.카테고리필드 체크
		List<String> errorList2 = new ArrayList<String>();
		String cate = (String)paramMap.get("cate");
		if(StringUtils.isBlank(cate) || StringUtils.equals(cate, "none")) {
			errorList2.add("카테고리를 선택해 주세요");
		}		
		if(errorList2.size() > 0) {	errorMap.put("cate", errorList2);}
				
		//3.내용필드 체크
		List<String> errorList3 = new ArrayList<String>();
		String description = (String)paramMap.get("description");
		if(StringUtils.isBlank(description)) {
			errorList3.add("내용을 입력해 주세요");
		}		
		if(errorList3.size() > 0) {	errorMap.put("description", errorList3);}
		
		
		return errorMap;
	}
	
}
