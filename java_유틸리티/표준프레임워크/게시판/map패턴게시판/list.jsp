<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"         uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"      uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring"    uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test-BBS</title>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
  
<style>
 table {
    width: 80%;
    border: 1px solid #444444;
    border-collapse: collapse;
    margin: 0 auto;
  }
  th, td {
    border: 1px solid #444444;
  }
  
  td.center{text-align: center}
  
  form{
  	width: 80%;
  	margin: 0 auto;
  }
  
  .cate-1{font-size: 0.8em;color: black}
  .cate-2{font-size: 0.8em;color: red;font-weight: bolder}
  .cate-3{font-size: 0.8em;color: blue}
  
  #paging{
  	text-align: cetner;
  	
  }
</style>
<script
  src="https://code.jquery.com/jquery-3.6.0.min.js"
  integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
  crossorigin="anonymous"></script>
  <script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<script>
	
	$(function(){
		
		//등록
		$('#regist').on('click',function(event){
			fn_egov_addView();
			
		});
		
		//all checkbox
		$('#allCheck').on('click',function(event){
				$('input[type=checkbox][name=ids]').prop('checked' , $(this).is(':checked'));
				
		});
		
		//선택 삭제
		$('#delete').on('click',function(event){
			var obj = $('input[type=checkbox][name=ids]:checked');
			var cnt = $(obj).length;
			
			if(cnt > 0){
				var params = $(obj).serialize();
				$.post('<c:url value="/deleteMultiProc.do" />',params,function(data){
					if(data=='OK'){
						location.reload();
					}
					
				});
			}else{
				alert('선택된 글이 없습니다.');
			}
			
			return false;
		});
		
		//검색
		$('#search_btn').on('click',function(event){
			
			document.search.action = "<c:url value='/testList.do'/>";
			$('#search input[name=pageIndex]').val(1); //검색시는 초기화 해준다
			
			//체크박스
			var checkCnt = $('#search input[name=cate]:checked').length;
			
			//검색어 공백제거
			var searchText = $.trim($('#search input[name=searchText]').val());
			$('#search input[name=searchText]').val(searchText); //공백제거
						
			//등록일 시작~종료일
			var begin = $('#search input[name=beginDate]').val();
			var end  = $('#search input[name=endDate]').val();
						
			if(checkCnt > 0 || searchText != '' || begin !='' || end != ''){
				cateSerialize();
				$('#search').submit();
			}else{ //설정 값이 없으면 초기화
				initReload();		
			}
		});
		
		
	});
	
	function initReload(){
			
		location.href = '<c:url value="/testList.do" />';
		
	}
	
	//배열 변수는 직렬화 한다
	function multiParamsSerialize(obj){
		
		var cate_serialize='';
		$(obj).each(function(index,item){
			cate_serialize += $(item).val() + '/';
		});
		if(cate_serialize.length > 0){
			cate_serialize = cate_serialize.substring(0,(cate_serialize.length-1));
		}
		return cate_serialize;
	}
	
	//cate 직렬화 후 hidden cates에 담는다
	function cateSerialize(){
		var obj = $('#search input[name=cate]:checked');
		var cates = multiParamsSerialize(obj);
		$('#search input[name=cates]').val(cates);
		
	}
	
	// 글등록 화면 이동
	function fn_egov_addView(){
		cateSerialize();
		document.search.action = "<c:url value='/testAddForm.do'/>";
       	document.search.submit();
	}
	
	/* 글 수정 화면 function */
    function fn_egov_select(id) {
    	cateSerialize();
    	document.search.id.value = id;
       	document.search.action = "<c:url value='/testUpdateForm.do'/>";
       	document.search.submit();
    }
	
	/* 글 목록 화면 pagination 페이지 링크 function */
    function fn_egov_link_page(pageNo){
    	cateSerialize();
    	document.search.pageIndex.value = pageNo;
    	document.search.action = "<c:url value='/testList.do'/>";
       	document.search.submit();
    }
	


</script>
</head>
<body>

<form id="search" name="search" method="post">
	<input type="hidden" name="id" value="${param.id}" />
	<input type="hidden" name="pageIndex" value="${pageIndex}" />
	<input type="hidden" name="cates" value="${param.cates}" />
	<c:forEach items="${cateList}" var="c" varStatus="status">
		<input type="checkbox" id="c_${c.id}" name="cate" value="${c.id}"<c:if test="${fn:contains(param.cates , c.id)}"> checked</c:if> /><label for="c_${c.id}">${c.name}</label>
	</c:forEach>
	<select name="searchCate">
		<option value="sbScription"<c:if test="${param.searchCate == 'sbScription'}"> selected</c:if>>제목</option>
		<option value="description"<c:if test="${param.searchCate == 'description'}"> selected</c:if>>글내용</option>
	</select>
	<input type="text" name="searchText" value="${param.searchText}"/>
	<button type="button" id="search_btn">검색</button>
	<c:if test="${!empty param.cate or !empty param.searchText or !empty param.beginDate or !empty param.endDate }">
	<a href="<c:url value="/testList.do" />">초기화</a>
	</c:if>
	&nbsp;등록일 : 
	<input type="date" name="beginDate" value="${param.beginDate}" />~<input type="date" name="endDate" value="${param.endDate}"  />
</form>
<table>
	<tr>
		<th><input type="checkbox" id="allCheck" /></th>
		<th>번호</th>
		<th>ID</th>
		<th>제목</th>
		<th>등록자</th>
		<th>등록일</th>
		<th>최근수정일</th>
	</tr>
	
	<c:forEach items="${list }" var="test" varStatus="status">
	<tr>
		<td class="center"><input type="checkbox" name="ids" value="${test.id}" /></td>
		<td class="center"><c:out value="${rowFirstIndex - status.index}" /></td>
		<td class="center"><c:out value="${test.id }" /></td>
		<td>
			<c:choose>
				<c:when test="${'1' eq test.cate}"><span class="cate-1">[일반]</span></c:when>
				<c:when test="${'2'	eq test.cate}"><span class="cate-2">[공지]</span></c:when>
				<c:when test="${'3' eq test.cate}"><span class="cate-3">[갤러리]</span></c:when>
			</c:choose>
			
			<a href="javascript: void(0)" onclick="fn_egov_select('${test.id}');return false;" ><c:out value="${test.sbScription }" /></a>
		</td>
		<td class="center"><c:out value="${test.uname }" /></td>
		<td class="center">
		<c:if test="${!empty test.regDate }">
			<fmt:parseDate value="${test.regDate}" var="regDateString" pattern="yyyyMMddHHmmss"/>
			<fmt:formatDate value="${regDateString}" pattern="yyyy-MM-dd"/>
			
		</c:if>
		</td>
		<td class="center">
		<c:if test="${!empty test.upDate }">
			<fmt:parseDate value="${test.upDate}" var="upDateString"  pattern="yyyyMMddHHmmss" />
			<fmt:formatDate value="${upDateString}" pattern="yyyy-MM-dd"/>
		
		</c:if>
		</td>
	</tr>
	</c:forEach>
</table>

<div style="width: 80%; margin: 0 auto;">
	<div id="paging">
   		<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="fn_egov_link_page" />
	</div>
	<div style="text-align: right;">
		<button type="button" id="regist">글 등록</button>
		<button type="button" id="delete">글 삭제</button>
	</div>
	
</div>

</body>
</html>