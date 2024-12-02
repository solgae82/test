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
<title>
	<c:choose>
		<c:when test="${procMode eq 'write'}">등록완료</c:when>
		<c:when test="${procMode eq 'update'}">수정완료</c:when>
		<c:when test="${procMode eq 'delete'}">삭제완료</c:when>
	</c:choose>
</title>
<style>
 div#content{
  	width: 60%; margin: 0 auto;
  }
</style>
<script  src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>

	var procMode ='<c:out value="${procMode}"/>';
	
	$(function(){
		
		var f = $('#searchForm');
		switch(procMode){
			case 'write': //새글 등록일때 무조건 검색없는 새 리스트 이동
				
				location.href='<c:url value="/testList3.do"/>' ;
			break;
			case 'update':
				f.attr('action','<c:url value="/testView3.do"/>');
				f.submit();
				break;

			case 'delete':
				f.attr('action','<c:url value="/testList3.do"/>');
				f.submit();
				break;
				
			case 'deleteMulti':
				f.attr('action','<c:url value="/testList3.do"/>');
				f.submit();
				break;
				
			default:
				f.attr('action','<c:url value="/testList3.do"/>');
				f.submit();
				break;
			
		}
		
	});
</script>
</head>
<body>

<div id="content">
	
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