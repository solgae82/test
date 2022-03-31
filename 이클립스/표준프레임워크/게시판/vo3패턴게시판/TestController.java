package egovframework.test.e3.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.property.EgovPropertyService;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.test.e3.service.TestService;
import egovframework.test.e3.service.TestVO;
import egovframework.test.e3.service.impl.BbsValidator;
import net.sf.json.JSONObject;

@Controller("test3Controller")
@SessionAttributes({"testVO"})
public class TestController {

	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	@Resource(name="test3Service")
	TestService testService;
	
	//사용자 ID
	private static final String USER_ID="user03";
	
	/**
	 * 리스트 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/testList3.do")
	public String testList3(@ModelAttribute("searchVO") TestVO searchVO , Model model) throws Exception{
		
		
		testService.unescapeHtml(searchVO);
		//페이징 셋
		
		searchVO.setPageUnit(propertiesService.getInt("pageUnit")); //페이지 레코드 개수
		searchVO.setPageSize(propertiesService.getInt("pageSize")); //페이지리스트 출력 개수
		
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex()); //현재 페이지 번호
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit()); //페이지 레코드 개수
		paginationInfo.setPageSize(searchVO.getPageSize()); //페이지리스트 출력 개수
		
		/* 쿼리에 쓰일 페이징 관련 값 셋팅*/
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
				
		//쿼리에 쓰일 검색 카테고리 파싱
		List<String> catelist = testService.convertStringToList(searchVO.getSearchCates(), "/");
		searchVO.setSearchCate(catelist);
		
		//전체 게시물 갯수 셋팅 (리스트 출력에 쓰임)
		int totalCnt = testService.selectBbsCount(searchVO);
		paginationInfo.setTotalRecordCount(totalCnt);

		
		//최종 출력될 데이터 셋		
		model.addAttribute("category", testService.selectCategory("category")); //카테고리
		model.addAttribute("testList", testService.selectBbsList(searchVO)); //현재페이지 리스트
		model.addAttribute("paginationInfo", paginationInfo); //페이지 리스트 출력을 위해
		model.addAttribute("listFirstIndex", testService.getCurrentPageFirstIndex(paginationInfo));//게시물 첫 출력번호 값
		
		return "/test3/list3";
	}
	
	
	/**
	 * 글 등록 폼
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/testAddForm3.do")
	public String testAddForm3(@ModelAttribute("searchVO") TestVO searchVO , Model model) throws Exception{
		
		TestVO testVO = new TestVO();
		model.addAttribute("category", testService.selectCategory("category"));
		model.addAttribute("testVO", testVO);
		model.addAttribute("testVOJson", JSONObject.fromObject(testVO)); //json형식 오브젝트 
		
		return "/test3/registForm3";
	}
	
	/**
	 * 글 등록 처리
	 * @param testVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/testAddProc3.do")
	public String testAddProc3(@ModelAttribute("searchVO") TestVO searchVO ,
				@ModelAttribute("testVO") TestVO testVO , BindingResult bindResult, Model model
				,SessionStatus sessionStatus) throws Exception{
		
		
		
		BbsValidator validator = new BbsValidator();
		validator.validate(testVO, bindResult);
		
		
		if(bindResult.hasErrors()) { //재입력 요청시
			model.addAttribute("category", testService.selectCategory("category"));
			model.addAttribute("testVOJson", JSONObject.fromObject(testVO)); //json형식 오브젝트
			
			return "/test3/registForm3";
		}
		
		testVO.setUserId(USER_ID);
		testService.insertBbs(testVO);
		model.addAttribute("procMode", "write");
		
		sessionStatus.setComplete();
		
		return "/test3/proc3";
	}
	
	/**
	 * 상세보기
	 * @param testVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/testView3.do")
	public String testView3(@ModelAttribute("searchVO") TestVO searchVO , Model model) throws Exception{
				
		model.addAttribute("testVO", testService.selectBbs(searchVO));
		
		return "/test3/view3";
	}
	
	/**
	 * 글 수정 폼
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/testUpdateForm3.do")
	public String testUpdateForm3(@ModelAttribute("searchVO") TestVO searchVO , Model model) throws Exception{
				
		TestVO testVO = testService.selectBbs(searchVO);
		model.addAttribute("category", testService.selectCategory("category"));
		model.addAttribute("testVO", testVO);
		model.addAttribute("testVOJson", JSONObject.fromObject(testVO)); //json형식 오브젝트
		
		return "/test3/registForm3";
	}
	
	/**
	 * 글 수정 처리
	 * @param testVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/testUpdateProc3.do")
	public String testUpdateProc3(@ModelAttribute("searchVO") TestVO searchVO , 
			@ModelAttribute("testVO") TestVO testVO , BindingResult bindResult,  Model model
			,SessionStatus sessionStatus) throws Exception{
		
		BbsValidator validator = new BbsValidator();
		validator.validate(testVO, bindResult);
	
		if(bindResult.hasErrors()) {
			model.addAttribute("category", testService.selectCategory("category"));
			model.addAttribute("testVOJson", JSONObject.fromObject(testVO)); //json형식 오브젝트
			
			return "/test3/registForm3";
		}
		
		testVO.setUserId(USER_ID);
		testService.updateBbs(testVO);
		model.addAttribute("procMode", "update");
				
		sessionStatus.setComplete();
		
		return "/test3/proc3";
	}
	
	
	/**
	 * 글 삭제 처리
	 * @param testVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/testDeleteProc3.do")
	public String testDeleteProc3(@ModelAttribute("searchVO") TestVO searchVO , Model model) throws Exception{
		

		testService.deleteBbs(searchVO);
		model.addAttribute("procMode", "delete");
				
		return "/test3/proc3";
	}
	
	
	/**
	 * 글 리스트 멀티 삭제 처리
	 * @param testVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/testDeleteMultiProc3.do")
	public String testDeleteMultiProc3(@ModelAttribute("searchVO") TestVO searchVO , @RequestParam("delIds")List<String> delIds, Model model) throws Exception{
		

		testService.deleteMultiBbs(delIds);
		model.addAttribute("procMode", "deleteMulti");
				
		return "/test3/proc3";
	}
	
}
