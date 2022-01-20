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
<title>BBS 등록/수정</title>
<style>
	div#content{
	 	width: 60%; margin: 0 auto;
	}
	span.fieldError{
		color: red;
		font-size: 0.8em;
	}
	span.globalError{
		color: blue;
		border: 3px dotted red;
		padding: 10px;
	}
</style>
<script  src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
 
<script>
	//글 등록
	function addBBS(){
		var f = $('#registForm');
		$(f).attr('action','<c:url value="/testAddPorc2.do" />');
		f.submit();
	}
	
	//글 수정
	function updateBBS(){
		var f = $('#registForm');
		$(f).attr('action','<c:url value="/testUpdatePorc2.do" />');
		f.submit();
	}
	
	//취소버튼
	function cancel(){
		var f = $('#searchForm');
		
		if($('[name=id]' , f).val() == ''){
			$(f).attr('action','<c:url value="/testList2.do" />');	
		}else{
			$(f).attr('action','<c:url value="/testView2.do" />');
		}
		
		f.submit();
	}
	
	//리스트 돌아가기
	function moveList(){
		var f = $('#searchForm');
		$(f).attr('action','<c:url value="/testList2.do" />');
		f.submit();
	}
	
	
	$(function(){
		
		//등록버튼
		$('#addBtn').on('click',function(event){addBBS();});
		//수정버튼
		$('#updateBtn').on('click',function(event){updateBBS();});
		//취소버튼
		$('#cancelBtn').on('click',function(event){cancel();});
		//리스트 돌아가기 버튼
		$('#listBtn').on('click',function(event){moveList();});
	});
</script>
</head>
<body>
	<spring:hasBindErrors name="testVO" />
	
	
	<div id="content">
		<a href="<c:url value="/" />">home</a>
		<a href="<c:url value="/testList2.do" />">BBS</a>
		<h1>BBS</h1>
		
		<div>
		<form:errors path="testVO" cssClass="globalError"/>
		</div>	
		<form id="registForm" name="registForm" method="get">
			
			제목:<input type="text" id="sbScription" name="sbScription" value="${testVO.sbScription}" />
			<form:errors path="testVO.sbScription" cssClass="fieldError"/>
			<br />
			카테고리: 
			<select id="cate" name="cate">
				<option value="none">선택하세요</option>
				<c:forEach items="${cateList}" var="code" varStatus="status">
					<option value="${code.id}"<c:if test="${testVO.cate eq code.id}">selected</c:if>>${code.name}</option>
				</c:forEach>
			</select>
			<form:errors path="testVO.cate"  cssClass="fieldError"/>
			<br />
			내용 :<br /><textarea rows="10" cols="80" id="description" name="description">${testVO.description}</textarea><br/>
			<form:errors path="testVO.description"  cssClass="fieldError" />	
			<br />
			
			
			
			<div>
				
				<c:choose>
					<c:when test="${empty testVO.id}">
						<button type="button" id="addBtn">등록</button>	
					</c:when>
					<c:when test="${!empty testVO.id}">
						<button type="button" id="updateBtn">수정</button>	
					</c:when>
				</c:choose>
				
				<button type="button" id="cancelBtn">취소</button>
				<button type="button" id="listBtn">목록</button>
			</div>
			<!-- 이동파라미터들  -->
			<input type="hidden" id="id" name="id" value="${testVO.id}" />
			<input type="hidden" id="pageIndex" name="pageIndex" value="${searchVO.pageIndex}" />
			<input type="hidden" id="searchCondition" name="searchCondition" value="${searchVO.searchCondition}" />
			<input type="hidden" id="searchKeyword" name="searchKeyword" value="${searchVO.searchKeyword}" />
			<input type="hidden" id="cates" name="cates" value="${searchVO.cates}" />
			<input type="hidden" id="beginDt" name="beginDt" value="${searchVO.beginDt}" />
			<input type="hidden" id="endDt" name="endDt" value="${searchVO.endDt}" />
		</form>
	</div>
	
	
	<form id="searchForm" name="searchForm" method="get">
		<!-- 이동 파라미터들 -->
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