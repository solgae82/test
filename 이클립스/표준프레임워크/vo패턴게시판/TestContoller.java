package egovframework.test.e2.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.test.e2.service.CodeVO;
import egovframework.test.e2.service.TestService;
import egovframework.test.e2.service.TestVO;
import egovframework.test.e2.service.impl.BbsValidator;

@Controller
@SessionAttributes({"testVO"})
public class TestContoller {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestContoller.class);
	
	private String USER_ID="user00";	
	
	@Resource(name="test2Service")
	TestService testService;		
	
	@Resource(name="bBsValidator")
	BbsValidator bBsValidator;
	
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	//리스트등에서 세션 에러가 나므로 항상 미리 생성해야한다	
	@ModelAttribute("testVO")
	public TestVO setModel() {
		return new TestVO();
	}
	
	/**
	 * StringUtils 등록(jstl에서 사용하기 위해)
	 * @return
	 */
	@ModelAttribute("StringUtils")
	public StringUtils setStringUtils() {
		return new StringUtils();
	}
	
	/**
	 * 등록  폼
	 * @param testVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/testAddForm2.do")
	public String testAddForm2(@ModelAttribute("searchVO") TestVO searchVO , 
			Model model, SessionStatus session) throws Exception{
				
		model.addAttribute("cateList", testService.selectCateList("category"));
		model.addAttribute("testVO", new TestVO());
		session.setComplete();
		
		return "/test2/registForm2";
	}
	
	/**
	 * 수정 폼
	 * @param searchVO
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/testUpdateForm2.do")
	public String testUpdateForm2(@ModelAttribute("searchVO") TestVO searchVO , 
			Model model, SessionStatus session) throws Exception{
		
		model.addAttribute("cateList", testService.selectCateList("category"));
		model.addAttribute("testVO" , testService.selectBbs(searchVO));		

		return "/test2/registForm2";
	}
	
	/**
	 * 등록  처리
	 * @param testVO
	 * @param bind
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/testAddPorc2.do")
	public String testAddPorc2( @ModelAttribute("searchVO") TestVO searchVO , 
			@ModelAttribute("testVO") TestVO testVO , 
			BindingResult bind,  
			Model model, 
			SessionStatus session) throws Exception{
				
		bBsValidator.validate(testVO, bind); //명시적 유효성 검사
		
		if(bind.hasErrors()) { //재입력 요청시
			
			model.addAttribute("cateList", testService.selectCateList("category"));
						
			return "/test2/registForm2"; 
		}
		
		testVO.setUserId(USER_ID); // 강제 userID 셋팅	
		
		testService.insertBbs(testVO);	
		
		session.setComplete();		
		
		return "/test2/proc2";
	}

	/**
	 * 수정 처리
	 * @param searchVO
	 * @param testVO
	 * @param bind
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/testUpdatePorc2.do")
	public String testUpdatePorc2(@ModelAttribute("searchVO") TestVO searchVO , 
			@ModelAttribute("testVO") TestVO testVO , 
			BindingResult bind,  
			Model model, 
			SessionStatus session) throws Exception{
		
		bBsValidator.validate(testVO, bind); //명시적 유효성 검사
		
		if(StringUtils.isEmpty(testVO.getId())) {
			bind.reject(null, "글 수정 아이디가 없습니다.");
		}		
		
		
		if(bind.hasErrors()) { //재입력 요청시
			
			model.addAttribute("cateList", testService.selectCateList("category"));
			
			return "/test2/registForm2"; 
		}
		
		testVO.setUserId(USER_ID); // 강제 userID 셋팅
				
		//testService.updateBbs(testVO); //부분 필드 업데이트
		testService.updateBbs2(testVO);//세션을 이용하면 모든 필드 업데이트 가능함.
				
		session.setComplete();		
		
		return "/test2/proc2";
	}
	
	/**
	 * 리스트 페이지
	 * @param testVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/testList2.do")
	public String testList2(@ModelAttribute("searchVO")TestVO searchVO, Model model) throws Exception{
		
		searchVO.setPageUnit(propertiesService.getInt("pageUnit")); //페이지 레코드 갯수
		searchVO.setPageSize(propertiesService.getInt("pageSize")); //페이지리스트 출력 기본 갯수
		
		PaginationInfo paginationInfo = new PaginationInfo();
		
		/* 페이징 셋팅*/		
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		
		/* 페이징 계산 값 셋팅 (쿼리에 쓰일 값들)*/
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		/* 쿼리에 쓰일 값 분해*/		
		searchVO.setCateList(testService.convertStringToList(searchVO.getCates(), "/"));
		
		//전체 갯수 셋팅
		int totalRecordCount = testService.selectCountBbs(searchVO);
		paginationInfo.setTotalRecordCount(totalRecordCount);
		
		/*리스트 가져오기*/
		model.addAttribute("cateList", testService.selectCateList("category"));
		model.addAttribute("bbsList",testService.selectListBbs(searchVO));
		model.addAttribute("pageFirstIndex", testService.getCurrentPageFirstIndex(paginationInfo));//첫 출력 번호
		model.addAttribute("paginationInfo",paginationInfo);
		
		
		return "/test2/list2";
	}
	
	/**
	 * 게시물 view
	 * @param testVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/testView2.do")
	public String testView2(@ModelAttribute("searchVO")TestVO searchVO , Model model) throws Exception{
		
		model.addAttribute("cateList", testService.selectCateList("category"));
		model.addAttribute("testVO" , testService.selectBbs(searchVO)); //InitBinder 방식일  경우 validator 검사 대상 (TestVO)
		
		return "/test2/view2";
	}
	/**
	 * 게시물 한개 삭제
	 * @param searchVO
	 * @param testVO
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/testDeleteOneProc2.do")
	public String testDeleteOneProc2(@ModelAttribute("searchVO") TestVO searchVO , 
			@ModelAttribute("testVO") TestVO testVO , 
			SessionStatus session) throws Exception{
		
		testService.deleteBbs(testVO);
		session.setComplete();
		
		return "/test2/proc2";
	}
	
	/**
	 * 게시물 여러개 삭제
	 * @param searchVO
	 * @param testVO
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/testDeleteMultiProc2.do")
	public String testDeleteMultiProc2(@ModelAttribute("searchVO") TestVO searchVO , 
			@RequestParam("delId") List<String> delIds , 
			SessionStatus session) throws Exception{
		
		testService.deleteMultiBbs(delIds);
		session.setComplete();
		
		return "/test2/proc2";
	}
	

	/**
	 * 이 방식은 위험하다...
	 * 
	 * @param binder
	 */
	/*
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		
		binder.setValidator(new BbsValidator());
		
	}
	*/
}
