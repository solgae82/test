package egovframework.test.e1.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.rte.fdl.property.EgovPropertyService;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.test.e1.service.TestService;

import net.sf.json.JSONObject;

@Controller
public class TestController {

	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	@Resource(name = "testService")
	private TestService testService;
	
	private static final String USER_ID =  "user00"; //사용자 아이디
	
	
	/**
	 * view에서 쓸 객체 등록
	 * @return
	 */
	@ModelAttribute("StringUtils")
	public StringUtils setStringUtils() { 
		return new StringUtils();
	}
	/**
	 * 글 등록 폼
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/testAddForm.do")
	public String addForm(Model model) throws Exception {
		model.addAttribute("cateList", testService.selectCateList());/*일반/공지사항/갤러리*/
		return "test/register";
	}

	/**
	 * 글 등록 및 수정
	 * 
	 * @param paramMap
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/testProc.do", method = { RequestMethod.POST })
	public String testProc(@RequestParam Map<String, Object> paramMap, Model model) throws Exception {

		String id = (String)paramMap.get("id");
		paramMap.put("userId", USER_ID); //걍 세션 사용했다고 치고..
		
		//유효성 체크
		Map<String,Object> errorMap =testService.getValidateResult(paramMap);
		if(errorMap.size() > 0) { // 에러가 있다면 재입력 요청 페이지
			model.addAttribute("cateList", testService.selectCateList());/*일반/공지사항/갤러리*/
			model.addAttribute("error", errorMap);
			model.addAttribute("errorJSON", JSONObject.fromObject(errorMap)); //json형식 오브젝트
			model.addAttribute("test", paramMap); //파라미터 다시 setting
			
			return "test/register";
		}
		
		//유효성 통과 했다면, 등록 및 수정
		if (id == null || "".equals(id)) { // 등록
			String insertId = testService.insert(paramMap); // 입력한 id값, 다른 테이블의 외부키 사용시 필요
			
		} else { // 수정
			int cnt = testService.update(paramMap);
		}
		
		
		return "test/proc";
	}

	/**
	 * 글 수정 폼
	 * 
	 * @param paramMap
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/testUpdateForm.do")
	public String updateForm(@RequestParam Map<String, Object> paramMap, Model model) throws Exception {

		model.addAttribute("cateList", testService.selectCateList()); /*일반/공지사항/갤러리*/
		model.addAttribute("newLine", "\n"); //jstl에서 쓸 개행문자
		model.addAttribute("test", testService.selectTest(paramMap));
		
		return "test/register";
	}

	/**
	 * 리스트 페이지
	 * 
	 * @return
	 * @throws Exception
	 */

	@RequestMapping(value = "/testList.do")
	public String testList(
			@RequestParam Map<String, Object> paramMap,
			@RequestParam(value="pageIndex", required=true, defaultValue="1") int pageIndex,
			Model model) throws Exception {
		
				
		//파라미터 체크박스 카테고리값 리스트로 만들기  ,  일반/공지사항/갤러리=>[0,1,2] , sql에 쓸 리스트 
		paramMap.put("cates", testService.convertStringToList((String)paramMap.get("cates"), "/")); 		

		//체크박스 카테고리 리스트
		model.addAttribute("cateList", testService.selectCateList());/*일반/공지사항/갤러리*/
		
		//페이징
		int pageUnit = propertiesService.getInt("pageUnit");//한페이지에 출력되는 게시물 수
		int pageSize = propertiesService.getInt("pageSize");//페이지 리스트에 출력되는 페이지 수
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pageIndex); //현재 페이지 번호
		paginationInfo.setRecordCountPerPage(pageUnit);
		paginationInfo.setPageSize(pageSize);
		 
		paramMap.put("firstIndex", paginationInfo.getFirstRecordIndex()); //쿼리에 쓰일 값
		paramMap.put("lastIndex", paginationInfo.getLastRecordIndex());	// 쿼리에 쓰일 값
		paramMap.put("pageUnit", paginationInfo.getRecordCountPerPage());	// 쿼리에 쓰일 값
		
		paginationInfo.setTotalRecordCount(testService.selectTestCount(paramMap)); //전체 리스트 갯수

		/* 최종 출력 셋 */
		model.addAttribute("list", testService.selectTestList(paramMap)); //리스트 가져오기		
		model.addAttribute("paginationInfo", paginationInfo); //페이징 object
		model.addAttribute("rowFirstIndex", testService.getCurrentPageFirstIdx(paginationInfo));//첫 출력 번호
		model.addAttribute("pageIndex",pageIndex);//현재 페이지 index
		

		
		return "test/list";
	}

	/**
	 * 글 삭제(한개) , ajax
	 * 
	 * @param paramMap
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteProc.do", method = { RequestMethod.POST })
	@ResponseBody
	public String deleteTestProc(@RequestParam Map<String, Object> paramMap) throws Exception {

		int cnt = testService.delete(paramMap);
		
		return "OK";
	}

	/**
	 * 글 삭제 (멀티) , ajax
	 * @param ids
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteMultiProc.do", method = { RequestMethod.POST })
	@ResponseBody
	public String deleteMultiTestProc(@RequestParam("ids") List<String> ids) throws Exception {
		
		int cnt = testService.deleteMulti(ids);
		return "OK";
	}
	
	
	
}
