<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
<jsp:include page="mainMenu.jsp"/>
<hr>
<div align="center">
	<div><h1>블랙리스트 정보</h1></div>
	<div>
		<form action="blackSearch.do">	
				<input class="search" type="text" value="홍길동">
				<input class="search" type="text" value="hong">
				<button id="btn">검색</button>	
		</form>
	</div>
	<div>
		<table border="1">
			<tr>
				<th width="100">아이디</th>
				<th width="100">이  름</th>
				<th width="200">주  소</th>
				<th width="150">전화번호</th>
				<th width="150">가입일자</th>
				<th width="100">권 한 </th>
				<th width="42.5">변경</th>
			</tr>
			<c:if test="${author eq 'black'}">
			<c:forEach var="member" items="${members}">
				<tr>
					<td>${member.id}</td>
					<td>${member.name}</td>
					<td>${member.address}</td>
					<td>${member.tel}</td>
					<td>${member.eDate}</td>
					<td>${member.author}</td>
					<td><input type="text" value="얄짤 ㄴㄴ">${member.author}</td>
					<td><button>수정</button></td>
				</tr>
			</c:forEach>
			</c:if>
		</table>
	</div>
		
</div>
</body>
</html>