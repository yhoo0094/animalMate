<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>돌봐줄게요 - 등록</title>
<style>

table {
border : 1px solid black;
}

#userpic {
width: 50px;
heigth: 50px;
}

#epic {
width: 200px;
height: 200px;
border : 1px solid black;
}

#timetable {
width: 500px;
height: 200px;
border : 1px solid black;	
}

.check {
display: block;
}
</style>
</head>
<body>
	<h5>돌봐주세요 - 게시글 등록form</h5>
	
	<div>
		제목 :<input type="text" placeholder="제목을 입력하세요">
	</div>
	
	<div>
		<p><b>펫 정보</b></p>
	</div>
	<div>
		<table>
			<tr>
				<td rowspan="5"><img id="userpic" src="${pageContext.request.contextPath}/images/user.png"></td>
				<th>펫 이름</th>
				<td>
					<select>
						<option>형준몬</option>
						<option>알생몬</option>
						<option>차노몬</option>
						<option>짠규몬</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>펫 나이</th>
				<td><p>DBVALUE</p></td>
			</tr>
			<tr>
				<th>성별</th>
				<td><p>DBVALUE</p></td>
			</tr>
			<tr>
				<th>종류</th>
				<td><p>DBVALUE</p></td>
			</tr>
			<tr>
				<th>중성화 유무</th>
				<td><p>DBVALUE</p></td>
			</tr>
		</table>
		<button>-</button>
	</div>
	<button>+</button>
	
	
	<div>
		<p><b>세부조건</b></p>
	</div>
	<div>
		<table>
			<tr>
				<th>근무지역</th>
				<td><input type="text"></td>
			</tr>
			<tr>
				<th>근무날짜</th>
				<td><input type="date"></td>
			</tr>
			<tr>
				<th>근무시간</th>
				<td>
					<select>
						<option></option>
						<option></option>
						<option></option>
						<option></option>
						<option></option>
					</select>
				</td>
			</tr>
			<tr>
				<th>거래금액(마리)</th>
				<td><input type="text"></td>
			</tr>
		</table>
	</div>
	
	<div>
		<p><b>요청내용</b></p>
	</div>
	<div>
		<textarea>dd</textarea>
	</div>
	
	<div>
		<p><b>체크리스트</b></p>
	</div>
	<div>
	<div class="check">
		<input placeholder="요청사항을 입력하세요">
		<button>+</button>
		<button>-</button>
	</div>
	<div class="check">
		<input placeholder="요청사항을 입력하세요">
		<button>+</button>
		<button>-</button>
	</div>
	<div class="check">
		<input placeholder="요청사항을 입력하세요">
		<button>+</button>
		<button>-</button>
	</div>
	<div class="check">
		<input placeholder="요청사항을 입력하세요">
		<button>+</button>
		<button>-</button>
	</div>
	<div class="check">
		<input placeholder="요청사항을 입력하세요">
		<button>+</button>
		<button>-</button>
	</div>
		
		
	</div>
	
	<div>
		<p><b>반려인 참고사항</b></p>
	</div>
	<div>
		<p>○ 배드, 사료, 산책용품등은 반려인이 준비해주세요.</p>
		<p>○ 특이사항(질병등)은 사전에 펫시터에게 공지해주세요.</p>
		<p>○ 의뢰시 간단하게 계약서나 약정서를 작성하세요.</p>
		<p>○ 표준계약서는 '이용안내'에서 내려받을수 있습니다.</p>
	</div>
	
	<input type="checkbox"> 상기 본인은 참고 사항에 동의 합니다. 
	
	<div>
		<button type="submit">글 등록</button>
		<button type="reset">취소</button>
	</div>
	

</body>
</html>