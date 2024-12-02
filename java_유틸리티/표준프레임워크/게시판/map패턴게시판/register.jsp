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
<title>test-BBS</title>

<style>
.view{border: 1px solid skyblue;width: 80%; margin: 0 auto;}
span.error {font-size: 0.8em; color: #ff7744;}
</style>
<script
  src="https://code.jquery.com/jquery-3.6.0.min.js"
  integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
  crossorigin="anonymous"></script>
<script>

	$(function(){
		
		//등록 및 수정
		$('#save').on('click',function(event){
			var f = document.forms['f'];
			
			$(f).submit();
			
		});
		
		//리스트 돌아가기
		$('#list').on('click',function(event){
						
			document.listSearch.action="<c:url value="/testList.do" />";
			document.listSearch.submit();
		});
		
		//삭제 ajax
		$('#delete').on('click',function(event){
			if(confirm('삭제 하시겠습니까?')){
				var f = document.forms['f'];

				$.post('<c:url value="/deleteProc.do" />',{id:f.id.value},function(data){
					if($.trim(data) =='OK'){
						document.listSearch.action="<c:url value="/testList.do" />";
						document.listSearch.submit();
					}else{
						console.log(data);
						alert('서버에러 입니다.');
					}	
				});
				
			}
			
			
		});
		
		
	});
	
	//등록,수정 에러 메시지 나타내기
	var errorJson = '${errorJSON}'; //json형식 에러 객체 {"필드명":['에러1','에러2'..]..}
	
	/**
	*	에러 메시지를 alert 또는 에러 필드 옆 dom 메시지로 뿌려주는 객체
	*/
	var errorObject = {
			mode : 'dom', /* alert , dom*/
			alertAll : true, /* true면 모든 에러 갯수 만큼 alert 출력 */
			errorJson : null,
			setMode : function(mode){this.mode=mode;},
			setAlertAll : function(bool){this.alertAll=bool;},
			setErrorJson : function(json){
				try{
					if(errorJson != ''){
						this.errorJson = JSON.parse(errorJson);
					}					
					
				}catch(e){
					this.errorJson = null;
					console.log('errorJson parse Error=>' , e);
				}
				
			},
			domMsgBoxInit : function(){
				for(var field in this.errorJson ){
					
					$('#' + field + ' + span.error').remove();
				}
			},
			excute: function(){
				switch(this.mode){
					case 'alert' : this.errorAlert(); break;
					case 'dom' : this.errorDomInsert();break;
				}
			},
			
			errorAlert : function(){
				if(this.alertAll){
					var i=0;
					var firstFieldName = null;
					for(var field in this.errorJson ){
											
						for(var m=0; m < this.errorJson[field].length; m++){
							alert(this.errorJson[field][m]);	
						}
						if(i <= 0){
							firstFieldName = field;
						}
						i++;
					}	
					if(firstFieldName != null){
						$('#' + firstFieldName ).focus();	
					}
					
				}else{ //한개만 실행
					console.log(1);
					var i =0;
					for(var field in this.errorJson ){
						
						alert(this.errorJson[field][0]);
						$('#' + field ).focus();
						if(i <= 0){break;}
						i++;
					}	
				}
				
			},
			errorDomInsert : function(){
				this.domMsgBoxInit(); //초기화
				for(var field in this.errorJson ){
					$('#' + field ).after('<span class="error">'+ this.errorJson[field][0]+'</span>');
				}
			},
			
			
	};

	$(function(){
		//유효성 검사
		if(errorJson != ''){
			errorObject.setErrorJson(errorJson);
			errorObject.excute();
		}
	});
</script>
</head>
<body>
<form id="listSearch" name="listSearch" method="post">
	<input type="hidden" name="id" value="${param.id}" />
	<input type="hidden" name="pageIndex" value="${param.pageIndex}" />
	<input type="hidden" name="cates" value="${param.cates}" />
	<input type="hidden" name="searchField" value="${param.searchField}" />
	<input type="hidden" name="searchText" value="${param.searchText}"/>
	<input type="hidden" name="beginDate" value="${param.beginDate}" />
	<input type="hidden" name="endDate" value="${param.endDate}"  />
</form>

<div style="width: 80%; margin: 0 auto;">
	<form  id="f" name="f" action="<c:url value="/testProc.do"/>" method="post">
		
		<input type="hidden" name="id" value="<c:out value="${test.id }" escapeXml="false" />" />
		
		제목 : 
		<input type="text" id="sbScription" name="sbScription" value="<c:out value="${test.sbScription }" escapeXml="false" />" />
		
		 <br />
		
		카테고리:
			<select id="cate" name="cate">
				<option value="none">선택해주세요</option>
				<c:if test="${!empty cateList}">
					<c:forEach items="${cateList}" var="cate" varStatus="status">
				<option value="${cate.id}"<c:if test="${test.cate != null && cate.id.equals(test.cate) }"> selected</c:if>>${cate.name}</option>	
					</c:forEach>
				
				</c:if>
			</select>
			
		<br />
		
		
		작성자 : 
		<c:out value="${test.userName}" escapeXml="false" />
		<input type="hidden" name="userName" value="<c:out value="${test.userName }" escapeXml="false" />" />
		<br />
		<c:if test="${!empty param.id }">
			<fmt:parseDate var="regDateString" value="${test.regDate}" pattern="yyyyMMddHHmmss" />
		등록일: <fmt:formatDate  value="${regDateString}" pattern="yyyy-MM-dd HH:mm:ss" /> 
		
		<br />
			<c:if test="${!empty test.upDate }">
				<fmt:parseDate var="upDateString" value="${test.upDate}" pattern="yyyyMMddHHmmss"/>
		최근수정일: <fmt:formatDate  value="${upDateString}" pattern="yyyy-MM-dd HH:mm:ss"/>
		
		<br />
			</c:if> 
		</c:if>
		

		내용 : <textarea rows="10" style="width: 100%" id="description" name="description"><c:out value="${test.description }" escapeXml="false" /></textarea>
		<br />
		
		
		<div style="text-align: right">
			<button type="button" id="save">${!empty param.id ?'수정':'저장' }</button> 
			<button type="button" id="delete">삭제</button>
			<button type="button" id="list">리스트</button>
		</div>
		
		<c:if test="${!empty param.id }">
		<!-- 수정일때는 이전 리스트 페이지로 돌아가야 한다 -->
		<!-- 리스트 검색 파라미터들 -->
		<input type="hidden" name="id" value="${param.id}" />
		<input type="hidden" name="pageIndex" value="${param.pageIndex}" />
		<input type="hidden" name="cates" value="${param.cates}" />
		<input type="hidden" name="searchField" value="${param.searchField}" />
		<input type="hidden" name="searchText" value="${param.searchText}"/>
		<input type="hidden" name="beginDate" value="${param.beginDate}" />
		<input type="hidden" name="endDate" value="${param.endDate}"  />
		<!-- 재입력요청시 필요 파라미터 -->
		<input type="hidden" name="regDate" value="${test.regDate}"  />
		<input type="hidden" name="upDate" value="${test.upDate}"  />
		</c:if>
	</form>
</div>

<div class="view">
	<h4>view 로  내용 뿌려줄 경우</h4>
	<c:set var="des" value="${fn:replace(test.description,'<','&lt;') }" />
	<c:set var="des" value="${fn:replace(des,'>','&gt;') }" />
	
	<p><c:out value="${fn:replace(des,newLine,'<br />')}" escapeXml="false" /></p>
	
</div>

</body>
</html>