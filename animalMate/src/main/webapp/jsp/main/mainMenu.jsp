<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<title>메인</title>
<style type="text/css">
	.dd {
		width: 100%; 
		height: auto;
	
	}
	

</style>
<script>
	
</script>
</head>
<body>
	<img class="dd" alt="이미지 없어!" src="${pageContext.request.contextPath}/jsp/main/images/사진6.jpg">
	<video class="dd" id="video" loop="loop" autoplay="autoplay" muted="muted">
		<source class="dd" src="${pageContext.request.contextPath}/jsp/main/video/mainVideo.mp4" type="video/mp4">
	</video>
</body>
</html> 