<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"         uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"      uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring"    uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<meta charset="UTF-8">
<title>BBS 등록/수정완료</title>

<script  src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
 
<script>
	
	
	//등록 후 이동
	function moveNewList(){
		location.href='<c:url value="/testList2.do" />';
	}
	//수정 , 삭제 후 이동
	function moveSearchList(){
		var f = $('#searchForm');
		$(f).attr('action','<c:url value="/testList2.do" />');	
		
		f.submit();
	}
	
		
	$(function(){
		var procId = document.searchForm.id;
		
		if(procId==''){ //등록 이동
			moveNewList();
		}else{ //수정,삭제 이동
			moveSearchList();
		}
	});
</script>
</head>
<body>
	
	<form id="searchForm" name="searchForm" method="get">
	<!-- 이동 파라미터들  -->
	<input type="hidden" name="id" value="${testVO.id}" />
	<input type="hidden" name="pageIndex" value="${searchVO.pageIndex}" />
	<input type="hidden" name="searchCondition" value="${searchVO.searchCondition}" />
	<input type="hidden" name="searchKeyword" value="${searchVO.searchKeyword}" />
	<input type="hidden" name="cates" value="${searchVO.cates}" />
	<input type="hidden" name="beginDt" value="${searchVO.beginDt}" />
	<input type="hidden" name="endDt" value="${searchVO.endDt}" />
	</form>
</body>
</html>