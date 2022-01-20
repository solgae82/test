package egovframework.test.e2.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TestVO extends Test implements Serializable{
	
	
	private static final long serialVersionUID = -858838578081269359L;
	
	/** 검색조건 */
	private String searchCondition = "";

	/** 검색Keyword */
	private String searchKeyword = "";

	/** 검색사용여부 */
	private String searchUseYn = "";

	/** 현재페이지 */
	private int pageIndex = 1;

	/** 페이지갯수 (한페이지에 출력되는 게시물 수)*/
	private int pageUnit = 10;

	/** 페이지사이즈 (페이지 리스트에 출력되는 페이지 수)*/
	private int pageSize = 10;

	/** firstIndex */
	private int firstIndex = 1;

	/** lastIndex */
	private int lastIndex = 1;

	/** recordCountPerPage */
	private int recordCountPerPage = 10;
	
	/* 카테고리 배열파라미터  직렬화 문자열*/
	private String cates = "";
	
	/* 카테고리 배열파라미터  직렬화 문자열 분해 변환*/
	private List<String> cateList = null;
	
	/* 등록일 기간 시작일 */
	private String beginDt = "";
	
	/* 등록일 기간 종료일 */
	private String endDt = "";
	
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

	public String getSearchUseYn() {
		return searchUseYn;
	}

	public void setSearchUseYn(String searchUseYn) {
		this.searchUseYn = searchUseYn;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCates() {
		return cates;
	}

	public void setCates(String cates) {
		this.cates = cates;
	}

	public List<String> getCateList() {
		return cateList;
	}

	public void setCateList(List<String> cateList) {
		this.cateList = cateList;
	}

	public String getBeginDt() {
		return beginDt;
	}

	public void setBeginDt(String beginDt) {
		this.beginDt = beginDt;
	}

	public String getEndDt() {
		return endDt;
	}

	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}


	
}
