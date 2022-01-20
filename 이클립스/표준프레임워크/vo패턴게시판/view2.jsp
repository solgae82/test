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
<title>BBS view</title>
<style>
  table {
    width: 100%;
    border: 1px solid #444444;
    border-collapse: collapse;
  }
  th, td {
    border: 1px solid #444444;
  }
  
  div#content{
  	width: 60%; margin: 0 auto;
  }
  div#content  p{
  	border : 1px solid #999;
  	padding: 20px;
  }
  
  td.center{text-align: center;}
  
</style>
<script  src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
 
<script>

	//목록 이동
	function fn_moveList(){
		var f = $('#searchForm');
		$(f).attr('action','<c:url value="/testList2.do" />');
		f.submit();
		
	}
	
	//수정 처리
	function fn_update(){
		var f = $('#searchForm');
		$(f).attr('action','<c:url value="/testUpdateForm2.do" />');
		f.submit();
	}
	
	//글 삭제 처리
	function fn_delete(){
		if(!confirm('삭제하시겠습니까?')){return;}
		var f = $('#searchForm');
		$(f).attr('action','<c:url value="/testDeleteOneProc2.do" />');
		f.submit();
	}
	
	$(function(){
		
		//목록버튼
		$('#moveListBtn').on('click',function(event){fn_moveList();});
		//수정버튼
		$('#updateBtn').on('click',function(event){fn_update();});
		//삭제 버튼
		$('#deleteBtn').on('click',function(event){fn_delete();});
		
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
	<div id="content">
		<a href="<c:url value="/" />">home</a>
		<a href="<c:url value="/testList2.do" />">BBS</a>
		<h1>BBS</h1>
		
			제목:${testVO.sbScription}<br />
			카테고리: ${testVO.cate}<br />			
			내용 :<br />
			<p>${testVO.description}</p>
	
			<div>
				<button type="button" id="moveListBtn">목록</button>
				<button type="button" id="updateBtn">수정</button>
				<button type="button" id="deleteBtn">삭제</button>
			</div>
	</div>
	
	
	
</body>
</html>