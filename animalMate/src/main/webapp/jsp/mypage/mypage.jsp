<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
</head>
<body>
<br>
<h1>마이페이지</h1>
	<div class="row">
		<div class="mypagebox">
			<h1>프로필</h1>
			<table class="table">
				<tr>
					<td colspan=2 height="200px"><img
						src="${pageContext.request.contextPath}/images/members_pic/${user.pic}"
						alt="사진을 넣어주세요" style = "width:200px; height:200px; border-radius: 100%;"></td>
				</tr>
				<tr>
					<th>이름</th>
					<td>${user.name}</td>
				</tr>
				<tr>
					<th>권한</th>
					<c:choose>
						<c:when test="${user.author eq 'users'}">
							<td>일반유저</td>
						</c:when>
						<c:when test="${user.author eq 'sitterwait'}">
							<td>시터허가 대기중</td>
						</c:when>
						<c:when test="${user.author eq 'usersitter'}">
							<td>시터허가</td>
						</c:when>
						<c:when test="${user.author eq 'black'}">
							<td>불량유저</td>
						</c:when>
					</c:choose>
				</tr>
				<tr>
					<th>전화번호</th>
					<td>${user.tel}</td>
				</tr>
				<tr>
					<th>주소</th>
					<td>${user.location1} ${user.location2}</td>
				</tr>
			</table>
			<div class = "row">
				<div class= "ownerFormCheckButton">
					<button onclick="window.location.href='${pageContext.request.contextPath}/memberForm.do'">정보수정</button>
					&nbsp;&nbsp;
					<button onclick="window.location.href='${pageContext.request.contextPath}/profile.do?id=${user.id}'">내 후기</button>
				<c:if test="${user.author eq 'usersitter'}">
					&nbsp;&nbsp;
					<button onclick="window.location.href='${pageContext.request.contextPath}/environmentImg.do'">돌봄환경</button>
				</c:if>
				</div>
			</div>
		</div>

		<div class="mypagebox">
			<h1>마이펫</h1>
			<table class="table">
				<c:forEach var="pet" items="${pets}" end="2">
					<tr>
						<td rowspan=2 width=30%><img
							src="${pageContext.request.contextPath}/images/pet_pic/${pet.pic}"
							alt="사진을 넣어주세요" width=100px height=100px></td>
						<td>${pet.name}</td>
						<td>${pet.age}살</td>
					</tr>
					<tr>
						<td colspan=2>${pet.type} ${pet.detailType}</td>
					</tr>
				</c:forEach>
			</table>
			<button onclick="window.location.href='${pageContext.request.contextPath}/mypetForm.do'">새등록</button>
			&nbsp;&nbsp;
			<button onclick="window.location.href='${pageContext.request.contextPath}/mypetList.do'">더보기</button>
		</div>
	</div>

	<div class="row">
		<div class="mypagebox">
			<h1>거래내역</h1>

			<table class="table">
				<tr>
					<th>남은 포인트</th>
					<td>${user.point}p</td>
				</tr>
				<tr>
					<th>진행중인<br>거래</th>
					<td>${tcount.code}건</td>
				</tr>
				<tr>
					<th>거래내역</th>
					<td><button onclick="window.location.href='${pageContext.request.contextPath}/mytradeList.do'">더보기</button></td>
				</tr>
			</table>
		</div>

		<div class="mypagebox">
			<h1>즐겨찾기</h1>
			<table class="table">
				<tr>
					<td>이름</td>
					<td>나이</td>
					<td>상세정보</td>
				</tr>
				<c:forEach items="${memberlist}" var="v">
				<tr>
					<td>${v.id}</td>
					<td>${v.zoomin1}</td>
					<td><button onclick="window.location.href='${pageContext.request.contextPath}/profile.do?id=${v.id}'">더보기</button></td>	
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	
</body>
</html>