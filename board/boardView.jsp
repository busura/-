<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/top.jsp"/>

<c:if test="${board==null}">
	<script>
		alert('존재하지 않는 글입니다');
		history.back();
	</script>
</c:if>    
<c:if test="${board!=null}">    
    <div class="container m2">
    	<h1>Board 글 보기</h1>
    	<br>
    	<a href="user/boardForm.do">글쓰기</a>|
    	<a href="boardList.do">글목록</a>
    	<br>
    	<table border="1" id="userTable">
    		<tr>
    			<td width="20%"><b>글번호</b></td>
    			<td width="30%">${board.bno}</td>
    			<td width="20%"><b>작성일</b></td>
    			<td width="30%">
    			<fmt:formatDate value="${board.wdate}" pattern="yy-MM-dd hh:mm:ss"/>
    			</td>
    		</tr> 
    		<%-- <tr>
    			<td width="20%"><b>글쓴이</b></td>
    			<td width="30%">${board.writer}</td>
    			<td width="20%"><b>첨부파일</b></td>
    			<td width="30%">
    			<c:if test="${board.filename!= null}">
	    			<a href="upload/${board.filename}" download><img src="images/attach.png" width="20px">
	    				${board.filename}
	    			</a>
	    			
	    			[${board.filesize} bytes]
    			</c:if>
    			</td>
    		</tr> --%>
    		<tr>
    			<td width="20%"><b>제목</b></td>
    			<td colspan="3" style='text-align:left'>${board.title}
    			</td>
    		</tr> 
    		<tr>
    			<td width="20%" style="height:300px"><b>
    			글내용
    			</b></td>
    			<td colspan="3"  style='text-align:left'>${board.content}
    			</td>
    		</tr> 
    		<tr>
    			<td colspan="4">
    				<a href="boardList.do">글목록</a>
    				<!-- 로그인한 사람과 글쓴이가 같다면 수정/삭제 링크 출력 -->
    				<c:if test="${loginUser.writer eq board.writer}">
	    				|<a href="#" onclick="goEdit()">수정</a>|
	    				<a href="#" onclick="goDel()">삭제</a>
    				</c:if>
    			</td>
    		</tr>  	
    	</table>
    	<!-- 수정 또는 삭제를 위한 form -->
    	<form name='bf' id='bf'>
    		<input type='hidden' name='bno' id='bno' value='${board.bno}'>
    	</form>
    </div>
</c:if>    
    
    <script>
		const goEdit=function(){
			bf.action='user/boardEditForm.do';
			bf.method='post';
			bf.submit();
		}   
		
		const goDel=function(){
			let yn=confirm('정말 삭제할까요?');
			if(yn){
				bf.action='user/boardDel.do';
				bf.method='post';
				bf.submit();
			}
		}//---------------------------
    </script>
    
<jsp:include page="/foot.jsp"/>    