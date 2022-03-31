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
<title>테스트3 등록/수정</title>
<style>
 div#content{
  	width: 60%; margin: 0 auto;
  }
  div#content textarea{width: 100%; }
 
 span.fieldError{
		color: red;
		font-size: 0.8em;
	}
	span.globalError{
		color: blue;
		border: 3px dotted red;
		padding: 10px;
	}
.json{border: 1px dotted red}
</style>
<script  src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>

	//등록
	function writeForm(){
		//if(!isValidator()){return;}
		var f = $('#writeForm');
		$(f).attr('action','<c:url value="/testAddProc3.do"/>');
		f.submit();
	}
	//수정
	function updateForm(){
		//if(!isValidator()){return;}
		var f = $('#writeForm');
		$(f).attr('action','<c:url value="/testUpdateProc3.do"/>');
		f.submit();
	}
	
	//유효성검사
	function isValidator(){
		if($('#sbScription').val()==''){
			alert('제목을 입력해주세요');
			$('#sbScription').focus();
			return false;
		}
		
		if($('#description').val()==''){
			alert('내용을 입력해주세요');
			$('#description').focus();
			return false;
		}
		
		return true;
	}
	//등록 취소
	function writeCancelForm(){
		var f = $('#writeForm');
		$(f).attr('action','<c:url value="/testList3.do"/>');
		f.submit();
	}
	//수정취소
	function updateCancelForm(){
		var f = $('#writeForm');
		$(f).attr('action','<c:url value="/testView3.do"/>');
		f.submit();
	}
	
	//VO JSON 형식
	var testVOJson = '';
	
	<c:if test="${!empty testVOJson}" >
	testVOJson = ${testVOJson}; //순수 json 객체로 찍어줘야 에러 안 난다
	</c:if>
	function insertInputForm(){
		
		if(testVOJson ==''){return;}
		
		$('#sbScription').val(testVOJson.sbScription);
		$('#description').val(testVOJson.description);

		
	}
	
	$(function(){
		//등록
		$('#writeBtn').on('click',function(event){writeForm()});
		//등록취소
		$('#cancelWriteBtn').on('click',function(event){writeCancelForm()});
		//수정
		$('#updateBtn').on('click',function(event){updateForm()});
		//수정취소
		$('#cancelUpdateBtn').on('click',function(event){updateCancelForm()});
		
		//제목,내용은 스크립트로 넣는다
		insertInputForm();
	});	
	
	
	
</script>
</head>
<body>

<div id="content">
	<div class="json">
	${testVOJson}
	</div>
	<a href="<c:url value="/" />">home</a>
	<h1>BBS 3 
		<c:choose>
			<c:when test="${empty testVO.id}">
				등록폼
			</c:when>
			<c:otherwise>
				수정폼
			</c:otherwise>
		</c:choose>
	</h1>
	<form id="writeForm" name="writeForm" method="post" autocomplete="off">
		<c:if test="${!empty testVO.id}">
		<!-- 세션(SessionAttributes)을 사용했다면 이곳을 출력할 수 있다 -->
		등록일 : 
		<fmt:parseDate value="${testVO.regDate}" var="regDateString" pattern="yyyyMMddHHmmss"/>
		<fmt:formatDate value="${regDateString}" pattern="yyyy-MM-dd HH:mm:ss"/>
		
		<br />
		</c:if>
		제목 : <input type="text"   id="sbScription" name="sbScription" value="" />
		<form:errors path="testVO.sbScription" cssClass="fieldError"/>
		<br />
		카테고리 : 
		<select id="cate" name="cate">
			<option value="none">선택해주세요</option>
			<c:forEach items="${category}" var="code" varStatus="status">
				<option value="${code.id}"<c:if test="${code.id eq testVO.cate}"> selected</c:if>>${code.name}</option>
			</c:forEach>
		</select>
		<form:errors path="testVO.cate" cssClass="fieldError"/>
		<br />
		내용 : <textarea rows="10" id="description" name="description"></textarea>
		<form:errors path="testVO.description" cssClass="fieldError"/>
		<!--  이동 파라미터들 -->
		<input type="hidden" id="pageIndex" name="pageIndex" value="${searchVO.pageIndex}" />
		<input type="hidden" id="id" name="id" value="${searchVO.id}" />
		<input type="hidden" id="searchCates" name="searchCates" value="${searchVO.searchCates}" />
		<input type="hidden" id="searchCondition" name="searchCondition" value="${searchVO.searchCondition}" />
		<input type="hidden" id="searchKeyword" name="searchKeyword" value="${searchVO.searchKeyword}" />
		<input type="hidden" id="searchBeginDate" name="searchBeginDate" value="${searchVO.searchBeginDate}" />
		<input type="hidden" id="searchEndDate" name="searchEndDate" value="${searchVO.searchEndDate}" />
	</form>
	<div id="btn-area">
		<c:choose>
			<c:when test="${empty testVO.id}">
				<button id="writeBtn" type="button">등록</button>
				<button id="cancelWriteBtn" type="button">취소</button>
			</c:when>
			<c:otherwise>
				<button id="updateBtn" type="button">수정</button>
				<button id="cancelUpdateBtn" type="button">취소</button>
			</c:otherwise>
		</c:choose>
				
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