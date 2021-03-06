<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보낸쪽지함</title>
<style>
.pagination, li.active, .pagination li {
	display: inline-block;
}

.pagination a, li.active {
	color: black;
	padding: 8px 16px;
	text-decoration: none;
	border: 1px solid #ddd;
}

.pagination li.active {
	background-color: rgb(255,136,38);
	color: black;
	border: 1px solid #ddd;
}

.pagination a:hover:not(.active) {
	background-color: #ddd;
}

.pagination a, li.active {
	border-radius: 5px;
	border-bottom-left-radius: 5px;
}

.btnSpan {
	display: inline;
	float: right;
}

.messageListA {
	color: gray;
}

.col1_title, .col1_target, .col3_comm {
	width: 20px;
}

.col2_title, .col2_target, .col3_comm {
	width: 300px;
}

.col3_comm {
	height: 300px;
}
</style>
<script type="text/javascript">
	$(()=>{
		$("#sendMessageBtn").on({
			"click" : function(){
				location.href = "${pageContext.request.contextPath}/goMessageListPage.do"
			} 
		})
		
		$(".messageListA").on({
			"click" : function(){
				var msgCode = $(this).parent().prev().text()
				$(this).parent().next().next().next().text("확인");
				$.ajax({
					url:'${pageContext.request.contextPath}/ajax/messageInfo.do',
					data : {code : msgCode},
					dataType:'json',
					error:function(xhr,status,msg){
    					alert("상태값 :" + status + " Http에러메시지 :"+msg);
    				},
    				success:function(msg){
    					$("#receiveTitle").val(msg.title);
    					$("#receivecomm").val(msg.comm);
    					$("#receiveID").val(msg.receive);
    				}
				})
			}
		})
		
		//메세지 보내기 버튼
		$("#submitBtn2").on({
			"click" : function(){
				$("#messageFrm2").submit();
			}
		})
	})
</script>
</head>
<body>
	<br>
	<h3 style="display: inline;">보낸 쪽지함</h3>
	<span class="btnSpan">
		<button type="button" class="writeMessage" id="writeMessage"
			data-toggle="modal" data-target="#exampleModal2">쪽지 쓰기</button>
		&nbsp;&nbsp;
		<button class="sendMessageBtn" id="sendMessageBtn" type="button">받은
			쪽지함</button>
	</span>
	<br>
	<br>
	<div align="center">
		<table class="table">
			<thead>
				<tr>
					<th scope="col">메세지번호</th>
					<th scope="col">제목</th>
					<th scope="col">받은사람</th>
					<th scope="col">발송일자</th>
					<th scope="col">확인여부</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${messageList}" var="v">
					<tr>
						<td scope="row">${v.code}</td>
						<td scope="row"><a class="messageListA" data-toggle="modal"
							data-target="#exampleModal1"> ${v.title}</a></td>
						<td scope="row">${v.receive}</td>
						<td scope="row">${v.ttime}</td>
						<td scope="row">${v.status}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<script>
			function goPage(p) {
				location.href="goSendMessageListPage.do?p="+p;
			}
		</script>
		<my:paging paging="${paging}" jsfunc="goPage" />
		<br>
	</div>

	<!-- Modal1 보낸 쪽지 내용 -->
	<div class="modal fade" id="exampleModal1" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<p class="modal-title" id="exampleModalLabel">보낸 쪽지</p>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="reportDiv" align="center">
						<table class="reportTd">
							<tr>
								<td class="col1_title">제목</td>
								<td><input class="col2_title" type="text" id="receiveTitle"
									name="receiveTitle" readonly="readonly"></td>
							</tr>
							<tr>
								<td class="col1_target">받은 사람</td>
								<td><input class="col2_target" type="text" id="receiveID"
									name="receive" readonly="readonly"></td>
							</tr>
							<tr>
								<td class="col3_comm">내용</td>
								<td><textarea rows="20" cols="50" class="col3_comm"
										id="receivecomm" name="receivecomm" readonly="readonly"></textarea></td>
							</tr>
						</table>
						<br>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal1 보낸 쪽지 내용 -->

	<!-- Modal2 쪽지쓰기 -->
	<div class="modal fade" id="exampleModal2" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<p class="modal-title" id="exampleModalLabel">쪽지 보내기</p>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="reportDiv" align="center">
						<form id="messageFrm2" name="messageFrm" method="post"
							action="${pageContext.request.contextPath}/submitMessage.do">
							<table class="reportTd">
								<tr>
									<td class="col1_title">제목</td>
									<td><input class="col2_title" type="text" id="title"
										name="title"></td>
								</tr>
								<tr>
									<td class="col1_target">받을 사람</td>
									<td><input class="col2_target" type="text" id="receive"
										name="receive"></td>
								</tr>
								<tr>
									<td class="col3_comm">내용</td>
									<td><textarea rows="20" cols="50" class="col3_comm"
											id="comm" name="comm"></textarea></td>
								</tr>
							</table>
							<br>
						</form>
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" id="submitBtn2" class="btn btn-primary">보내기</button>
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">취소</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal2 쪽지쓰기 -->
	
</body>
</html>