package egovframework.test.e3.service;

import java.util.List;

public class TestVO extends Test {
	private static final long serialVersionUID = -858838578081269359L;

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	/*스칼라쿼리 용: 사용자명*/
	private String userName = "";
	
	/*스칼라쿼리 용: 카테고리명*/
	private String cateName = "";
	
	/*현재 페이지 */
	private int pageIndex = 1;
	
	/* 페이지에 출력될 게시물 수 */
	private int pageUnit = 10;
	
	/* 페이징 리스트에 출력될 페이지 수*/
	private int pageSize = 10;
	
	private int firstIndex = 1;
	
	private int lastIndex = 1;
	
	private int recordCountPerPage = 10;
	
	/* 검색 : 카테고리 직렬화 문자열 */
	private String searchCates = "";
	
	
	/* 검색 : 카테고리 직렬화 문자열 -> List로 담기 변수 */
	private List<String> searchCate;
	
	/* 검색: 검색조건 */
	private String searchCondition = "";
	
	/* 검색: 검색단어 */
	private String searchKeyword = "";
	
	/* 검색: 등록일 (범위시작) */
	private String searchBeginDate = "";
	
	/* 검색: 등록일(범위종료)*/
	private String searchEndDate = "";


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageUnit() {
		return pageUnit;
	}

	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public String getSearchCates() {
		return searchCates;
	}

	public void setSearchCates(String searchCates) {
		this.searchCates = searchCates;
	}

	public List<String> getSearchCate() {
		return searchCate;
	}

	public void setSearchCate(List<String> searchCate) {
		this.searchCate = searchCate;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public String getSearchBeginDate() {
		return searchBeginDate;
	}

	public void setSearchBeginDate(String searchBeginDate) {
		this.searchBeginDate = searchBeginDate;
	}

	public String getSearchEndDate() {
		return searchEndDate;
	}

	public void setSearchEndDate(String searchEndDate) {
		this.searchEndDate = searchEndDate;
	}

	
		
}
	
