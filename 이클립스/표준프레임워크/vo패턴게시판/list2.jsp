<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"         uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"      uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring"    uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>

<html>
<head>
<meta charset="UTF-8">
<title>BBS 리스트</title>
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
  
  td.center{text-align: center;}
  
  tr.lasted-viewd{background: #ff7750;}
  
  label{font-size: 0.8em;color: red;}
</style>
<script  src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
 
<script>
	//등록
	function regist(){
				
		var f = $('#searchForm');
		prework(f);
				
		$(f).attr('action','<c:url value="/testAddForm2.do" />');
		f.submit();
	}
	
	//글보기
	function contentView(obj){
		
		var f = $('#searchForm');
		prework(f);
		
		$(f).find('input[name=id]').val($(obj).attr('data-id'));
		$(f).attr('action','<c:url value="/testView2.do" />');	
		f.submit();
	}
	
	//검색
	function search(){
		
		var f = $('#searchForm');
		prework(f);
				
		$(f).attr('action','<c:url value="/testList2.do" />');	
		f.submit();
	}
	
	//리스트 체크 삭제
	function listDelete(){
		var f = $('#searchForm');
		prework(f);
		
		if(confirm('선택한 항목을 삭제하시겠습니까?')){
			$(f).attr('action','<c:url value="/testDeleteMultiProc2.do" />');	
			f.submit();	
		}
		
	}
	
	//폼 보내기 전 가공
	function prework(f){
		
		//id 초기화
		$('input[name=id]' , f).val('');
		
		//검색어 공백 제거
		var searchKeyword = $('input[name=searchKeyword]' , f).val();
		searchKeyword = $.trim(searchKeyword);
		$('input[name=searchKeyword]' , f).val(searchKeyword)
		
		//체크박스 값 직렬화
		var value = paramArraySerialize($('input[name=cate]:checked' , f));
		$('input[name=cates]' , f).val(value);
		
	}
	
	
	//배열파라미터 직렬화
	function paramArraySerialize(obj){
		
		var value ='';
		$(obj).each(function(index, item){
			value += $(item).val() + '/';
		});
		
		if(value.length > 0){
			value= value.substring(0,(value.length-1));
		}
				
		return value;
	}
	
	$(function(){
		
		//등록버튼
		$('#registBtn').on('click',function(event){regist();});
		
		//검색버튼
		$('#searchBtn').on('click',function(event){search();});
		
		//리스트  체크 삭제 버튼
		$('#listDelBtn').on('click',function(event){listDelete();});
		
		//전체선택 체크박스
		$('#allCheck').on('click',function(event){
			
			$('#searchForm input[name=delId]').prop('checked', $(this).is(':checked'));
		});
	});
</script>
</head>
<body>

	<div id="content">
		<a href="<c:url value="/" />">home</a>
		<a href="<c:url value="/testList2.do" />">BBS</a>
		
		<h1>BBS</h1>
		<!-- 리스트 검색 -->
		<form id="searchForm" name="searchForm" method="get">
			<input type="hidden" id="pageIndex" name="pageIndex" value="${searchVO.pageIndex}" />
			<input type="hidden" id="id" name="id" value="" />
			<input type="hidden" id="cates" name="cates" value="" />
			<!--  카테고리 -->
			<c:forEach items="${cateList}" var="code" varStatus="status">
			<input type="checkbox" id="cate_<c:out value="${code.id}" />" name="cate" value="<c:out value="${code.id}" />" <c:if test="${searchVO.cates.contains(code.id) }">checked</c:if> />
			<label for="cate_<c:out value="${code.id}" />"><c:out value="${code.name }" /></label>
			</c:forEach>
			<!-- 시작일 , 종료일 -->	
			<input type="date" id="beginDt" name="beginDt" value="${searchVO.beginDt}" /> ~ <input type="date" id="endDt" name="endDt" value="${searchVO.endDt}" />	
			<!--  제목, 내용  : 검색어 -->
			<select id="searchCondition" name="searchCondition">
				<option value="sbScription"<c:if test="${searchVO.searchCondition eq 'sbScription'}"> selected</c:if>>제목</option>
				<option value="description"<c:if test="${searchVO.searchCondition eq 'description'}"> selected</c:if>>내용</option>
			</select>
			<input type="text" id="searchKeyword" name="searchKeyword" value="${searchVO.searchKeyword}" />
			<button type="button" id="searchBtn">검색</button>
		
				
		<!--  리스트 영역 -->
		<table>
			<tr>
				<th><input type="checkbox" title="전체선택" name=allCheck"" id="allCheck" /></th>
				<th>ID</th>
				<th>작성자</th>
				<th>카테고리</th>
				<th>제목</th>
				<th>등록일</th>
				<th>최신수정일</th>
			</tr>
			<c:forEach items="${bbsList}" var="bbs" varStatus="status">
			<tr${searchVO.id eq bbs.id ?' class="lasted-viewd"' : ''}>
				<th><input type="checkbox" title="항목체크" name="delId" value="${bbs.id}" /></th>
				<td class="center">${bbs.id}</td>
				<td class="center">${bbs.userId}</td>
				<td class="center">${bbs.cateNm}</td>
				<td><a href="javascript: void(0)" onclick="contentView(this);return false;" data-id="${bbs.id}">${bbs.sbScription}</a></td>
				<td class="center">
					<fmt:parseDate value="${bbs.regDate}" var="regDate" pattern="yyyyMMddHHmmss"/>
					<fmt:formatDate value="${regDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td class="center">
					<fmt:parseDate value="${bbs.upDate}" var="regDate" pattern="yyyyMMddHHmmss"/>
					<fmt:formatDate value="${regDate}" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
			</c:forEach>
		</table>
		<!-- 버튼 영역 -->
		<div>
			<button type="button" id="registBtn">등록</button>
			<button type="button" id="listDelBtn">삭제</button>
		</div>
		
		<!-- 페이징 -->
		<div id="paging">
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="pageMove"/>
			<script>
				function pageMove(pageNo){
					$('#pageIndex').val(pageNo);
					search();	
				}
			</script>
		</div>
		</form>
	</div>
</body>
</html>