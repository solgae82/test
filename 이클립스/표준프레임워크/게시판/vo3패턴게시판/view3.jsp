<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"         uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"      uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring"    uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>테스트3 상세보기</title>
<style>
 div#content{
  	width: 60%; margin: 0 auto;
  }
  div#content textarea{width: 100%; }
 
</style>
<script  src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>

	
	//수정 이동
	function moveUpdate(){
		
		var f = $('#searchForm');
		$(f).attr('action','<c:url value="/testUpdateForm3.do" />');
		f.submit();
	}
	
	//삭제 처리
	function deleteProc(){
		var f = $('#searchForm');
		$(f).attr('action','<c:url value="/testDeleteProc3.do" />');
		f.submit();
	}
	//목록 이동
	function moveList(){
		var f = $('#searchForm');
		$(f).attr('action','<c:url value="/testList3.do" />');	
		f.submit();
	}
	
	$(function(){
		//수정 버튼
		$('#updateBtn').on('click',function(event){moveUpdate();});
		//삭제 버튼
		$('#deleteBtn').on('click',function(event){deleteProc();});
		//목록 버튼
		$('#listBtn').on('click',function(event){moveList();});
	});
</script>
</head>
<body>

<div id="content">
	<a href="<c:url value="/" />">home</a>
	<h1>BBS 3 상세보기(${testVO.id})</h1>
	
		작성자 : <c:out value="${testVO.userName }" /><br />
		등록일 : 
		<fmt:parseDate value="${testVO.regDate}" var="regDateString" pattern="yyyyMMddHHmmss"/>
		<fmt:formatDate value="${regDateString}" pattern="yyyy-MM-dd HH:mm:ss"/>
		제목 : <c:out value="${testVO.sbScription }" /> <br />
		카테고리 :  <c:out value="${testVO.cateName }" /><br /> 
		
		내용 : <br/>
		
		
		<hr />

		<% pageContext.setAttribute("newLine", "\n"); %>
		<c:set var="content" value="${fn:replace(testVO.description,'<','&lt;') }" />
		<c:set var="content" value="${fn:replace(content,'>','&gt;') }" />
		<c:set var="content" value="${fn:replace(content,newLine,'<br />') }" />
		<c:out value="${content}" escapeXml="false" />	
	
	<div id="btn-area">
		<button id="updateBtn" type="button">수정</button>
		<button id="deleteBtn" type="button">삭제</button>
		<button id="listBtn" type="button">목록</button>
	</div>
	
	<form id="searchForm" name="searchForm" method="post">
		<!--  이동 파라미터들 -->
		<input type="hidden" id="pageIndex" name="pageIndex" value="${searchVO.pageIndex}" />
		<input type="hidden" id="id" name="id" value="${searchVO.id}" />
		<input type="hidden" id="searchCates" name="searchCates" value="${searchVO.searchCates}" />
		<input type="hidden" id="searchCondition" name="searchCondition" value="${searchVO.searchCondition}" />
		<input type="hidden" id="searchKeyword" name="searchKeyword" value="${searchVO.searchKeyword}" />
		<input type="hidden" id="searchBeginDate" name="searchBeginDate" value="${searchVO.searchBeginDate}" />
		<input type="hidden" id="searchEndDate" name="searchEndDate" value="${searchVO.searchEndDate}" />
	</form>
</div>

</body>
</html>