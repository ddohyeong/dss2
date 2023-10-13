<%@page import="domain.introduce.dto.IntroduceInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
	
<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>DSS</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<link rel="icon" href="../logo/favicon.ico/ms-icon-144x144.png"
	sizes="144x144">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css">
<link rel="stylesheet" href="../css/member_introduce.css">
<link href="../css/nice-select.css" rel="stylesheet">

</head>
<body>
	<%@ include file="../includes/header.jsp"%>

	<%@ include file="../includes/banner.jsp"%>

	<main>
		<form action="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/introduce_info" method="POST">
			<section>
				<h2>${myInfo.nickName }님의 소개글</h2>
				<div>
					<section class="form_info1">
						<ul class="info_row">
							<li><span>Email</span> <input class="nice-select wide"
								type="text" value="${myInfo.email}" disabled="disabled"></li>

							<li><span>연락 방법</span> <input class="nice-select wide"
								type="text" value="${myInfo.contactMethod}" disabled="disabled"></li>

							<li><span>역할</span> <input class="nice-select wide"
								type="text" value="${myInfo.role}" disabled="disabled"></li>
							<li><span>기술 스택</span>
								<div class="tech info wide">
									<ul class="tech_wrapper">
										<%
											IntroduceInfo info = (IntroduceInfo) request.getAttribute("myInfo");
												
											String technologyCategory = info.getTechnologyCategory();
											String[] techCategory = technologyCategory.split(",");
											
											request.setAttribute("techCategory", techCategory);
										%>
										<c:forEach items="${techCategory }" var="tech">
											<li>
												<img src="../language/${tech }.svg" alt=""> <span>${tech }</span>
											</li>
										</c:forEach>
									</ul>
								</div></li>
						</ul>
					</section>

					<section class="form_info2">
						<div class="introduce_wrapper">
							<ul>
								<li>
									<div class="introduce_introduction">
										<span>소개글</span>
										<textarea name="" id="" cols="60" rows="10" placeholder="소개글을 입력하세요" disabled>${myInfo.introduce}</textarea>
									</div>
								</li>
							</ul>
						</div>
					</section>

				</div>
			</section>
			
			<div>
				<button type="button" class="btn_style" id="back_page">뒤로 가기</button>
			</div>
		</form>
	</main>

	<script src="../js/jquery-3.6.0.min.js"></script>
	<script src="../js/jquery.nice-select.js"></script>

	<script>
		$("#back_page").on("click",function(){
			history.back();
		});
	</script>
</body>
</html>