<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	

<!DOCTYPE html>
<link rel="stylesheet" href="../css/header.css">
<header>
	<div class="container">
		<div class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4">
			<a href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/home" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
				<img src="../logo/logo.png" alt="" width="150px">
			</a>
			<div class="btn_wrap col-md-3 text-end navbar_info" >
				
				<c:if test="${empty loginInfo }">
					<a href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/login"> 
						<i class="bi bi-box-arrow-in-right font_40 color_green"></i>
					</a>	 
				</c:if>
				 
				
				<c:if test="${not empty loginInfo}">
					<a href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/my_page"> 
						<i class="bi bi-person-fill font_40 color_green"></i>
					</a>
					<div class="nav_icons">
						<a href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/notice_list"> 
						<i class="bi bi-bell-fill font_40 color_green"></i>
							<c:if test="${not empty sessionScope.viewNoticeInfoList }">
								<c:set var="badge" value="red_count_badge"/>
							</c:if>
							<span class="${badge }" id="red_count_badge"></span>
						</a>
					</div>
					
					
					<a href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/member/logout">
						<span class="line_h_50 color_green font_bold">로그아웃</span>
					</a>
					
					<span class="line_h_50">${loginInfo.nickName} 님</span>
					
					<script>
						setInterval(function () {
							$.ajax({
								url:"http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/check/notice",
								type:"GET",
								success:function(){
									$("#red_count_badge").addClass("red_count_badge");
								},
								error:function(){
									$("#red_count_badge").removeClass("red_count_badge");									

								}
							});
						}, 3000)
					</script>
				</c:if>

			</div>
		</div>
	</div>
</header>
<script src="../js/jquery-3.6.0.min.js"></script>
