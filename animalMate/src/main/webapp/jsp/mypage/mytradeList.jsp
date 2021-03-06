<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 거래 보기</title>
</head>
<body>
	<br>
	<h1>${user.name}님의 거래</h1>
	<div class="row">
		<div class="mypageTrade">
			<div>
				<dl>
					<dt>돌봄 예약</dt>
					<dd>${countSell} 건</dd>
				</dl>

			</div>
			<div>
				<dl>
					<dt>맡김 예약</dt>
					<dd>${countBuy} 건</dd>
				</dl>
			</div>
			<div>
				<dl>
					<dt>내 포인트</dt>
					<dd>${user.point}p</dd>
				</dl>
			</div>
			<div>
				<dl>
					<dt>포인트 충전</dt>
					<dd>
						<button onclick="window.location.href='${pageContext.request.contextPath}/pointChargingView.do'">충전하기</button>
					</dd>
				</dl>
			</div>
		</div>
	</div>
	
	
	
	<br>
	<h3>예약 승인 대기</h3>
	<div class="row">
		<div class="mypageTradeList">
			<table class="table">
				<tr>
					<th width = 8%>거래코드</th>
					<th>게시판분류</th>
					<th>상대</th>
					<th>거래금액</th>
					<th>예약시간</th>
					<th>거래상태</th>
				</tr>
				<c:forEach items="${applyList}" var="tb">
					<c:if test="${tb.status eq '거래 미정'}">
						<c:choose>
							<c:when test="${tb.ttype eq '돌봐줄게요'}">
							<!-- 돌봐줄게요 게시판 -->
								<!-- 내가 반려인(신청자)이고 상대방이 나를 선택하지 않았을 때 -->
									<tr onclick="location.href='${pageContext.request.contextPath}/sitterFormSelect.do?code=${tb.code}';"
										style='cursor: pointer;'>
										<td>${tb.code}</td>
										<td>${tb.ttype}</td>
										<td>${tb.seller}</td>
										<td>${tb.price}p</td>
										<td>${tb.sdate} ${tb.stime} ~ ${tb.edate} ${tb.etime}</td>
										<td>${tb.status}</td>
									</tr>
							</c:when>
							<c:otherwise>
								<!-- 돌봐주세요 게시판 -->
								<!-- 내가 시터(신청자)이고 반려인이 나를 선택하지 않았을때 -->
									<tr onclick="location.href='${pageContext.request.contextPath}/ownerFormSelect.do?code=${tb.code}';"
										style='cursor: pointer;'>
										<td>${tb.code}</td>
										<td>${tb.ttype}</td>
										<td>${tb.buyer}</td>
										<td>${tb.price}p</td>
										<td>${tb.sdate} ${tb.stime} ~ ${tb.edate} ${tb.etime}</td>
										<td>${tb.status}</td>
									</tr>
							</c:otherwise>
						</c:choose>
					</c:if>
				</c:forEach>
			</table>
		</div>
	</div>
	
	<hr>
	<br>
	
	
	
	
	
	
	<h3>펫 돌봄 예약</h3>
	<div class="row">
		<div class="mypageTradeList">
			<table class="table">
				<tr>
					<th width = 8%>거래코드</th>
					<th>게시판분류</th>
					<th>맡김이</th>
					<th>거래금액</th>
					<th>예약시간</th>
					<th>거래상태</th>
					<th>후기</th>
				</tr>
				<c:forEach items="${tbs}" var="tb">
					<c:choose>
						<c:when test="${tb.ttype eq '돌봐줄게요'}">
						<!-- 돌봐줄게요 게시판 -->
						<!-- 내가 시터(글쓴이)이고 상대방이 정해지지 않았을때 -->
							<c:if test="${tb.seller eq sid and tb.buyer eq null}">
								<tr onclick="location.href='${pageContext.request.contextPath}/sitterTradeCheck.do?code=${tb.code}';"
									style='cursor: pointer;'>
									<td>${tb.code}</td>
									<td>${tb.ttype}</td>
									<td>
										${tb.buyer}
										<c:if test="${tb.buyer eq null}">
											<font color="red">선택해주세요</font>
										</c:if>
									</td>
									<td>${tb.price}p</td>
									<td>${tb.sdate} ${tb.stime} ~ ${tb.edate} ${tb.etime}</td>
									<td>
										<c:if test="${tb.status eq '후기 미작성'}">
											거래 완료
										</c:if>
										<c:if test="${tb.status ne'후기 미작성'}">
											${tb.status}
										</c:if>
									</td>
									<td>
										<c:if test="${tb.status eq '거래 완료'}">
											<a href="${pageContext.request.contextPath}/commentView.do?code=${tb.code}">후기보기</a>
										</c:if>
									</td>
								</tr>
							</c:if>
						<!-- 내가 시터(글쓴이)이고 상대방이 정해졌을때 -->
							<c:if test="${tb.seller eq sid and tb.buyer ne null}">
								<tr onclick="location.href='${pageContext.request.contextPath}/sitterFormCheck.do?code=${tb.code}';"
									style='cursor: pointer;'>
									<td>${tb.code}</td>
									<td>${tb.ttype}</td>
									<td>
										${tb.buyer}
										<c:if test="${tb.buyer eq null}">
											<font color="red">선택해주세요</font>
										</c:if>
									</td>
									<td>${tb.price}p</td>
									<td>${tb.sdate} ${tb.stime} ~ ${tb.edate} ${tb.etime}</td>
									<td>
										<c:if test="${tb.status eq '후기 미작성'}">
											거래 완료
										</c:if>
										<c:if test="${tb.status ne'후기 미작성'}">
											${tb.status}
										</c:if>
									</td>
									<td>
										<c:if test="${tb.status eq '거래 완료'}">
											<a href="${pageContext.request.contextPath}/commentView.do?code=${tb.code}">후기보기</a>
										</c:if>
									</td>								
								</tr>
							</c:if>
						</c:when>
						<c:otherwise>
							<!-- 돌봐주세요 게시판 -->
							<!-- 내가 시터(신청자)이고 반려인이 나를 선택하지 않았을때  -->
							<c:if test="${tb.seller eq sid and tb.buyer eq null}">
								<tr onclick="location.href='${pageContext.request.contextPath}/*.do?code=${tb.code}';"
									style='cursor: pointer;'>
									<td>${tb.code}</td>
									<td>${tb.ttype}</td>
									<td>
										${tb.buyer}
										<c:if test="${tb.buyer eq null}">
											<font color="red">선택해주세요</font>
										</c:if>
									</td>
									<td>${tb.price}p</td>
									<td>${tb.sdate} ${tb.stime} ~ ${tb.edate} ${tb.etime}</td>
									<td>
										<c:if test="${tb.status eq '후기 미작성'}">
											거래 완료
										</c:if>
										<c:if test="${tb.status ne'후기 미작성'}">
											${tb.status}
										</c:if>
									</td>
									<td>
										<c:if test="${tb.status eq '거래 완료'}">
											<a href="${pageContext.request.contextPath}/commentView.do?code=${tb.code}">후기보기</a>
										</c:if>
									</td>
								</tr>
							</c:if>
							
							<!-- 내가 시터(신청자)이고 반려인이 나를 선택했을때-->
							<c:if test="${tb.seller eq sid and tb.buyer ne null}">
								<tr onclick="location.href='${pageContext.request.contextPath}/ownerFormCheck.do?code=${tb.code}';"
									style='cursor: pointer;'>
									<td>${tb.code}</td>
									<td>${tb.ttype}</td>
									<td>
										${tb.buyer}
										<c:if test="${tb.buyer eq null}">
											<font color="red">선택해주세요</font>
										</c:if>
									</td>
									<td>${tb.price}p</td>
									<td>${tb.sdate} ${tb.stime} ~ ${tb.edate} ${tb.etime}</td>
									<td>
										<c:if test="${tb.status eq '후기 미작성'}">
											거래 완료
										</c:if>
										<c:if test="${tb.status ne'후기 미작성'}">
											${tb.status}
										</c:if>
									</td>
									<td>
										<c:if test="${tb.status eq '거래 완료'}">
											<a href="${pageContext.request.contextPath}/commentView.do?code=${tb.code}">후기보기</a>
										</c:if>
									</td>
								</tr>
							</c:if>
							
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</table>
		</div>
	</div>
	
	<hr>
	<br>
	<h3>내 펫 맡김 거래</h3>
	<div class="row">
		<div class="mypageTradeList">
			<table class="table">
				<tr>
					<th width = 8%>거래코드</th>
					<th>게시판분류</th>
					<th>돌봄이</th>
					<th>거래금액</th>
					<th>예약시간</th>
					<th>거래상태</th>
					<th>후기</th>
				</tr>
				<c:forEach items="${tbs}" var="tb">
					<c:choose>
						<c:when test="${tb.ttype eq '돌봐줄게요'}">
						<!-- 돌봐줄게요 게시판 -->
						<!-- 내가 반려인(신청자)이고 상대방이 나를 선택하지 않았을 때 -->
							<c:if test="${tb.buyer eq sid and tb.seller eq null}">
								<tr onclick="location.href='${pageContext.request.contextPath}/*.do?code=${tb.code}';"
									style='cursor: pointer;'>
									<td>${tb.code}</td>
									<td>${tb.ttype}</td>
									<td>
										${tb.seller}
										<c:if test="${tb.seller eq null}">
											<font color="red">선택해주세요</font>
										</c:if>
									</td>
									<td>${tb.price}p</td>
									<td>${tb.sdate} ${tb.stime} ~ ${tb.edate} ${tb.etime}</td>
									<td>
										<c:if test="${tb.status eq '후기 미작성'}">
											거래 완료
										</c:if>
										<c:if test="${tb.status ne'후기 미작성'}">
											${tb.status}
										</c:if>
									</td>
									<td>
										<c:if test="${tb.status eq '후기 미작성'}">
											<a href="${pageContext.request.contextPath}/jsp/mypage/review.jsp?code=${tb.code}&buyer=${tb.buyer}&seller=${tb.seller}">후기쓰기</a>
										</c:if>
										<c:if test="${tb.status eq '거래 완료'}">
											<a href="${pageContext.request.contextPath}/commentView.do?code=${tb.code}">후기보기</a>
										</c:if>
									</td>
								</tr>
							</c:if>
						<!-- 내가 반려인(신청자)이고 상대방이 나를 선택했을때 -->
							<c:if test="${tb.buyer eq sid and tb.seller ne null}">
								<tr onclick="location.href='${pageContext.request.contextPath}/sitterFormCheck.do?code=${tb.code}';"
									style='cursor: pointer;'>
									<td>${tb.code}</td>
									<td>${tb.ttype}</td>
									<td>
										${tb.seller}
										<c:if test="${tb.seller eq null}">
											<font color="red">선택해주세요</font>
										</c:if>
									</td>
									<td>${tb.price}p</td>
									<td>${tb.sdate} ${tb.stime} ~ ${tb.edate} ${tb.etime}</td>
									<td>
										<c:if test="${tb.status eq '후기 미작성'}">
											거래 완료
										</c:if>
										<c:if test="${tb.status ne'후기 미작성'}">
											${tb.status}
										</c:if>
									</td>
									<td>
										<c:if test="${tb.status eq '후기 미작성'}">
											<a href="${pageContext.request.contextPath}/jsp/mypage/review.jsp?code=${tb.code}&buyer=${tb.buyer}&seller=${tb.seller}">후기쓰기</a>
										</c:if>
										<c:if test="${tb.status eq '거래 완료'}">
											<a href="${pageContext.request.contextPath}/commentView.do?code=${tb.code}">후기보기</a>
										</c:if>
									</td>
								</tr>
							</c:if>
							
						</c:when>
						<c:otherwise>
							<!-- 돌봐주세요 게시판 -->
							<!-- 내가 반려인(글쓴이)이고 상대방이 정해지지 않았을때 -->
							<c:if test="${tb.buyer eq sid and tb.seller eq null}">
								<tr onclick="location.href='${pageContext.request.contextPath}/ownerTradeCheck.do?code=${tb.code}';"
									style='cursor: pointer;'>
									<td>${tb.code}</td>
									<td>${tb.ttype}</td>
									<td>
										${tb.seller}
										<c:if test="${tb.seller eq null}">
											<font color="red">선택해주세요</font>
										</c:if>
									</td>
									<td>${tb.price}p</td>
									<td>${tb.sdate} ${tb.stime} ~ ${tb.edate} ${tb.etime}</td>
									<td>
										<c:if test="${tb.status eq '후기 미작성'}">
											거래 완료
										</c:if>
										<c:if test="${tb.status ne'후기 미작성'}">
											${tb.status}
										</c:if>
									</td>
									<td>
										<c:if test="${tb.status eq '후기 미작성'}">
											<a href="${pageContext.request.contextPath}/jsp/mypage/review.jsp?code=${tb.code}&buyer=${tb.buyer}&seller=${tb.seller}">후기쓰기</a>
										</c:if>
										<c:if test="${tb.status eq '거래 완료'}">
											<a href="${pageContext.request.contextPath}/commentView.do?code=${tb.code}">후기보기</a>
										</c:if>
									</td>
								</tr>
							</c:if>
							<!-- 내가 반려인(글쓴이)이고 상대방이 정해졌을때 -->
							<c:if test="${tb.buyer eq sid and tb.seller ne null}">
								<tr onclick="location.href='${pageContext.request.contextPath}/ownerFormCheck.do?code=${tb.code}';"
									style='cursor: pointer;'>
									<td>${tb.code}</td>
									<td>${tb.ttype}</td>
									<td>
										${tb.seller}
										<c:if test="${tb.seller eq null}">
											<font color="red">선택해주세요</font>
										</c:if>
									</td>
									<td>${tb.price}p</td>
									<td>${tb.sdate} ${tb.stime} ~ ${tb.edate} ${tb.etime}</td>
									<td>
										<c:if test="${tb.status eq '후기 미작성'}">
											거래 완료
										</c:if>
										<c:if test="${tb.status ne'후기 미작성'}">
											${tb.status}
										</c:if>
									</td>
									<td>
										<c:if test="${tb.status eq '후기 미작성'}">
											<a href="${pageContext.request.contextPath}/jsp/mypage/review.jsp?code=${tb.code}&buyer=${tb.buyer}&seller=${tb.seller}">후기쓰기</a>
										</c:if>
										<c:if test="${tb.status eq '거래 완료'}">
											<a href="${pageContext.request.contextPath}/commentView.do?code=${tb.code}">후기보기</a>
										</c:if>
									</td>
								</tr>
							</c:if>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</table>
		</div>
	</div>
	
	
</body>
</html>