<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시터지원</title>
<style>
* {
  box-sizing: border-box;
}

input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  resize: vertical;
}

label {
  padding: 12px 12px 12px 0;
  display: inline-block;
}

input[type=submit] {
  background-color: #4CAF50;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  float: right;
}

input[type=submit]:hover {
  background-color: #45a049;
}

.col-25 {
  float: left;
  width: 25%;
  margin-top: 6px;
}

.col-75 {
  float: left;
  width: 75%;
  margin-top: 6px;
}

.wannabeSitterSubmit {
	margin-bottom: 100px;
}

/* Clear floats after the columns */
.row:after {
  content: "";
  display: table;
  clear: both;
}

/* Responsive layout - when the screen is less than 600px wide, make the two columns stack on top of each other instead of next to each other */
@media screen and (max-width: 600px) {
  .col-25, .col-75, input[type=submit] {
    width: 100%;
    margin-top: 0;
  }
}
</style>

<script type="text/javascript">
	$(()=>{
		$("#wannabeSitterSubmit").on({
			"click" : function(event) {
				confirm("신청이 완료되었습니다!!");
				location.href="${pageContext.request.contextPath}/sitterinsert.do";
			}
		});
		$("#cancel").on({
			"click" : function() {
				location.href="${pageContext.request.contextPath}/main.do";
			}
		})
	});
</script>
</head>
<body>

<div align="center"><h2>시터 지원하기</h2></div>
<br>

<div class="container">
  <form action="${pageContext.request.contextPath}/sitterinsert.do">
  <div class="row">
  <div class="col-25">
      <label >id</label>
    </div>
    <div class="col-75">
    <td><input text="type" readonly="readonly" name="id" value="${sessionScope.id}"></td>
    </div>
    <div class="col-25">
      <label >최대 돌봄 마리 수</label>
    </div>
    <div class="col-75">
      <select id="pet" name="pet">
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="3">4</option>
        <option value="3">5</option>
      </select>
    </div>
  </div>
  <div class="row">
    <div class="col-25">
      <label >지원내용</label>
    </div>
    <div class="col-75">
      <textarea id="comm" name="comm" placeholder="Write something.." style="height:200px"></textarea>
    </div>
  </div>
  <br>
  <div class="wannabeSitterSubmit" align="right">
    <button>제출하기</button>
  </div>
    <div class="calcel" align="right">
    <button>취소</button>
  </div>
  </form>
</div>

</body>
</html>