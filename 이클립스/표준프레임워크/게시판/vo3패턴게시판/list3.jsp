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
<title>테스트3 리스트</title>
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
  
  #paging{	text-align: cetner;  }
</style>
<script  src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>

	//검색리스트
	
	function searchList(){
		var f = $('#searchForm');
		
		//카테고리
		var cateCnt = $('[name=searchCate]:checked',f).length;
		
		
		//검색조건
		var searchConditionIdx = $('#searchCondition option:selected').index();
		
		
		//검색어
		var searchKeyword = $('#searchKeyword').val();
		$('#searchKeyword').val($.trim(searchKeyword));
		
		//등록일 (시작, 종료)
		var searchBeginDate = $('#searchBeginDate').val();
		var serchEndDate = $('#serchEndDate').val();
		
		
		//초기화 설정이면 초기화 이동
		if(cateCnt ==0 && searchConditionIdx==0 && searchKeyword=='' && searchBeginDate=='' && serchEndDate ==''){ 
			location.href='<c:url value="/testList3.do" />';
			return;
		}
		
		//검색어 설정이 있다면 검색범위 체크	
		searchKeyword = $('#searchKeyword').val();
		if(searchConditionIdx==0 && searchKeyword !=''){
			alert('검색범위를 설택해 주세요');
			$('#searchCondition').focus();
			return;
		}
		
		
		cateSerialize(); //카테고리 직렬화	
		$('#pageIndex').val(1); //페이지 1로 초기화
		f.attr('action','<c:url value="/testList3.do" />');
		f.submit();

		
		
	}
	
	//등록
	function moveRegistForm(){
		var f = $('#searchForm');
					
		//등록시에는 id 초기화
		$('[name=id]',f).val('');
		cateSerialize();
		$(f).attr('action','<c:url value="/testAddForm3.do" />');	
		f.submit();
	}
	
	//리스트 선택(multi) 삭제
	function listRemove(){
		cateSerialize();
		
		var cnt = $('input[type=checkbox][name=delIds]:checked').length; //체크갯수
		
		if(cnt <=0){
			alert('삭제할 글을 선택해 주세요');
			return;
		}
		var f = $('#searchForm');
		f.attr('action','<c:url value="/testDeleteMultiProc3.do" />');
		f.submit();
	}
	
	//상세보기
	function viewMove(id){
		var f = $('#searchForm');
		cateSerialize();
		$('[name=id]',f).val(id);
		$(f).attr('action','<c:url value="/testView3.do" />');	
		f.submit();
	}
	
	//카테고리 체크박스 직렬화
	
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
	
	//cate 직렬화 후 hidden searchCates에 담는다
	function cateSerialize(){
		var obj = $('#searchForm input[name=searchCate]:checked');
		var cates = multiParamsSerialize(obj);
		$('#searchForm input[name=searchCates]').val(cates);
		
	}
	
	$(function(){
		//검색버튼
		$('#searchBtn').on('click',function(event){searchList();});
		//등록버튼
		$('#registBtn').on('click',function(event){moveRegistForm();});
		//삭제버튼
		$('#listRemoveBtn').on('click',function(event){listRemove();});
		
		//모두체크박스
		$('#allCheck').on('click',function(event){
			$('input[type=checkbox][name=delIds]').prop('checked' , $(this).is(':checked'));

		});
		
	});
</script>
</head>
<body>

<div id="content">
	<a href="<c:url value="/" />">home</a>
	<h1>BBS 3</h1>
	<form id="searchForm" method="get">
		<input type="hidden" id="pageIndex" name="pageIndex" value="${searchVO.pageIndex}" />
		<input type="hidden" id="id" name="id" value="<c:out value="${searchVO.id}" />"  />
		<input type="hidden" id="searchCates" name="searchCates" value="<c:out value="${searchVO.searchCates}" />"  />
		<!--[검색] 카테고리 선택 -->
		<c:forEach items="${category}" var="cate" varStatus="status">
			<input type="checkbox" id="c_${satus.index }" name="searchCate" value="${cate.id}" 
			<c:if test="${searchVO.searchCates.contains(cate.id)}">checked</c:if>
			 />
			<label for="c_${satus.index }">${cate.name}</label>
			
		</c:forEach>
		
		<!--[검색] (제목,내용 )조건 선택-->
		<select id="searchCondition" name="searchCondition">
			<option value="none">검색범위선택</option>
			<option value="s"<c:if test="${searchVO.searchCondition eq 's'}"> selected</c:if>>제목</option>
			<option value="c"<c:if test="${searchVO.searchCondition eq 'c'}"> selected</c:if>>내용</option>
			<option value="sc"<c:if test="${searchVO.searchCondition eq 'sc'}"> selected</c:if>>제목+내용</option>
			
		</select>
		
		<!--[검색] 검색 단어-->
		<input type="text" id="searchKeyword" name="searchKeyword" value="" /> 
		<script>
			$(function(){
				$('#searchKeyword').val('<c:out value="${searchVO.searchKeyword}" escapeXml="false" />');
			});
		</script>
		
		<!--[검색] 등록 시작일~종료일 선택-->
		등록일: <input type="date" id="searchBeginDate" name="searchBeginDate" title="등록일  검색시작 설정"/>~
		<input type="date" id="searchEndDate" name="searchEndDate" title="등록일 검색종료 설정"/>
		<button type="button" id="searchBtn">검색</button>
		<script>
			$(function(){
				$('#searchBeginDate').val('<c:out value="${searchVO.searchBeginDate}" escapeXml="false" />');
				$('#searchEndDate').val('<c:out value="${searchVO.searchEndDate}" escapeXml="false" />');
			});
		</script>
		<table>
			<tr>
				<th><input type="checkbox" id="allCheck" name="allCheck" value="allCheck"/></th>
				<th>No</th>
				<th>ID</th>
				<th>작성자</th>
				<th>카테고리</th>
				<th>제목</th>
				<th>등록일</th>
				<th>수정일</th>
			</tr>
			
			<c:forEach items="${testList}" var="test" varStatus="status">
			<tr<c:if test="${searchVO.id eq test.id }"> class="lasted-viewd"</c:if>>
				
				<td class="center"><input type="checkbox" id="delIds" name="delIds" value="${test.id}"/></td>
				<td class="center">${listFirstIndex - status.index }</td>
				<td class="center">${test.id}</td>
				<td class="center">${test.userName}</td>
				<td class="center">${test.cateName}</td>
				<td><a href="javascript:void(0)" onclick="viewMove('${test.id}')">${test.sbScription}</a></td>
				<td class="center">
					<fmt:parseDate value="${test.regDate}" var="regDateString" pattern="yyyyMMddHHmmss"/>
					<fmt:formatDate value="${regDateString}" pattern="yyyy-MM-dd"/>
				</td>
				<td class="center">
					<fmt:parseDate value="${test.upDate}" var="regDateString" pattern="yyyyMMddHHmmss"/>
					<fmt:formatDate value="${regDateString}" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
			</c:forEach>
		
		</table>
		<div id="paging">
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="pageMove" />
			<script>
				function pageMove(pageNo){
					$('#pageIndex').val(pageNo);
					var f = $('#searchForm');
					$(f).attr('action','<c:url value="/testView3.do" />');	
					f.submit();
				}
			</script>
		</div>
		
		<div>
			<button id="registBtn" type="button">등록</button>
			<button id="listRemoveBtn" type="button">삭제</button>
		</div>
	</form>
</div>

</body>
</html>