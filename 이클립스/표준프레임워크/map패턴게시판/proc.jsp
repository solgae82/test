<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"         uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"      uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring"    uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test-BBS-proc_finish</title>
<script
  src="https://code.jquery.com/jquery-3.6.0.min.js"
  integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
  crossorigin="anonymous"></script>
<script>

	//처리 후 post 이동을 위한 페이지
	$(function(){

			document.listSearch.action="<c:url value="/testList.do" />";
			document.listSearch.submit();
					
	});
	
	

</script>
</head>
<body>
<form id="listSearch" name="listSearch" method="post">
	<c:if test="${!empty param.id}">
	<input type="hidden" name="id" value="${param.id}" />
	<input type="hidden" name="pageIndex" value="${param.pageIndex}" />
	<input type="hidden" name="cates" value="${param.cates}" />
	<input type="hidden" name="searchField" value="${param.searchField}" />
	<input type="hidden" name="searchText" value="${param.searchText}"/>
	<input type="hidden" name="beginDate" value="${param.beginDate}" />
	<input type="hidden" name="endDate" value="${param.endDate}"  />
	</c:if>
</form>


</body>
</html>