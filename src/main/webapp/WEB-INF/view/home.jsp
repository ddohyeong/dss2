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
<link rel="stylesheet" href="../css/home.css">
<link href="../css/magicsuggest.css" rel="stylesheet"/>

<script src="../js/jquery-3.6.0.min.js"></script>

</head>

<body>

	<%@ include file="../includes/header.jsp"%>

	<nav>
		<div id="banner">
			<h1>소개합니다</h1>
			<h2>개발자들을 위한 스터디 모집 사이트 입니다</h2>
		</div>

	</nav>

	<section class="filter_wrapper">
		<ul class="filter_subjects">
			<%
				String[] filterCategorys = {"Famous","Frontend","Backend","Mobile","etc","AllTech"};
				request.setAttribute("filterCategorys", filterCategorys);

			%>
			<c:forEach items="${filterCategorys }" var ="filter">
				<c:if test="${filterCategory eq filter }">
					<li class="green_bar">${filter }</li>
				</c:if>
				<c:if test="${filterCategory ne filter }">
					<li>${filter }</li>
				</c:if>
			</c:forEach>
		</ul>

		<ul class="filter_techCategory">
			<c:forEach items="${techInfoList }" var="techInfo">
				<li>
 					<img src="../language/${techInfo.techName}.svg" alt=""><span>${techInfo.techName}</span>
 				</li> 
			</c:forEach>
		</ul>
		
	</section>
	<div class="nice-select wide tech_wrapper" id="tech_category">
			<ul class="tech_list">
				<c:forEach items="${techLists }" var="techs">
					<li class="tech">
						<span>${techs }</span><img src="../icon/x.svg" class="tech_delete">
					</li>
				</c:forEach>
			</ul>
			<input type="hidden" id="tech_list_input">
	</div>

	<main>
		<section class="main_nav">

			<div class="container-fluid search_wrapper">
				<div class="d-flex search_input">
					<input class="form-control me-2" type="search" placeholder="Search" id="search" aria-label="Search" value="${search }">
					<button class="btn btn-outline-success search_btn" type="button">search</button>
				</div>
			</div>

			<div>
				<ul class="main_category">
					<li class="all">
							<img src="../icon/coding.svg" alt=""> <span>전체</span>
					</li>
					<li class="project">
							<img src="../icon/archieve.svg" alt=""> <span>프로젝트</span>
					</li>
					<li class="study">
							<img src="../icon/education.svg" alt=""> <span>스터디</span>
					</li>
				</ul>
			</div>

			<div class="recruit_status">
				<img src="../icon/process.svg" alt=""> <span>모집 중</span>
				<div class="form-check form-switch">
					<c:if test="${postStatus eq true }">
						<c:set var="isPostStatus" value="checked"/>							
					</c:if>
					<c:if test="${postStatus eq false }">
						<c:set var="isPostStatus" value=""/>							
					</c:if>
					<input class="form-check-input status_check" type="checkbox" role="switch" id="flexSwitchCheckChecked" ${isPostStatus}>
				
				</div>
			</div>

		</section>

		<section class="recruitment_board">
			<h1>모집 게시판</h1>
			<div class="recruitment_add_button">
				<button type="button" id="post_add_btn">게시글 등록</button>
			</div>
			<ul class="recruitmentPost_wrapper">
				<c:forEach items="${postInfoList }" var="postInfo">
					<a href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/post/detail?postIdx=${postInfo.postIdx }&viewNum=${postInfo.viewNum}">
						<li class="recruitment_post">
							<div class="expected_startDate">
								<span>시작예정일 | </span> <span>${postInfo.expectedStartDate}</span>
								<div>
									<c:if test="${postInfo.postStatus eq 'T' }">
										<c:set var="t_status" value="style=\"color:#77D87D\""/>
									</c:if>
									<c:if test="${postInfo.postStatus eq 'F' }">
										<c:set var="t_status" value=""/>
									</c:if>
									<i class="bi bi-circle-fill" ${t_status }></i>
								</div>
							</div>

							<div class="recruitment_name">
								<h2>${postInfo.postName }</h2>
							</div>
		
							<div class="hashtag">
								<ul>
									<li>#${postInfo.process }</li>
									<li>#${postInfo.postCategory }</li>
									<li>#${postInfo.period }개월</li>
									<li>#${postInfo.numOfPeople }명</li>
								</ul>
							</div>
							<hr>
							<div class="techCategory">
								<c:set var="techs" value="${postInfo.technologyCategory }" scope="request"/>
								
								<%
									String techTemp = (String)request.getAttribute("techs");
									String[] techCategorys = techTemp.split(",");
									
									request.setAttribute("techCategorys", techCategorys);
								%>
								<ul>
									<c:forEach var="tech" items="${techCategorys }">
										<li><img src="../language/${tech }.svg" alt=""></li>
									</c:forEach>
								</ul>
							</div>
							<hr>
							<section class="recruitment_info">
								<span class="nickName"> ${postInfo.nickName } </span>
								<div class="viewNum_and_like">
									<div class="viewNum">
										<img src="../icon/view.svg" alt=""> <span>${postInfo.viewNum }</span>
									</div>
									<div class="like">
										<c:set var="postLikeStatus" value=""/>
										<c:if test="${postInfo.postLikeStatus eq 'T' }">
											<c:set var="postLikeStatus" value="T"/>	
										</c:if>
										<c:if test="${postInfo.postLikeStatus ne 'T' }">
											<c:set var="postLikeStatus" value="F"/>	
										</c:if>
										<img src="../icon/heart_${postLikeStatus }.svg" alt=""> 
										
										<input type="hidden" class="post_like" value="${postLikeStatus }">
			                    		<input type="hidden" class="postIdx" value="${postInfo.postIdx }"/>
									</div>
								</div>
							</section>
						</li>
					</a>
				
				</c:forEach>
			</ul>
		</section>
	</main>
	<script src="../js/function.js"></script>
    <script src="../js/magicsuggest.js"></script>
	
	<script>
		$(".tech_delete").on("click",function(){
			$(this).parents(".tech").remove();
			
			location.href = "http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/post_tech?postStatus="+isPostStatus()+"&techList="+encodeURIComponent(getTechList());
		});
		
		$(".filter_techCategory li").on("click",function(){
			let tech =$(this).children("span").text();
			
			let tag = '<li class="tech"><span>'+tech+'</span><img src="../icon/x.svg" class="tech_delete"></li>';
			
			$(".tech_list").prepend(tag);
			
			$(".tech_delete").on("click",function(){
				$(this).parents(".tech").remove();
				
				location.href = "http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/post_tech?postStatus="+isPostStatus()+"&techList="+encodeURIComponent(getTechList());
			});
			location.href = "http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/post_tech?postStatus="+isPostStatus()+"&techList="+encodeURIComponent(getTechList());
		});
		
		$(".filter_subjects li").on("click",function(){
			let category = $(this).text();
			
			location.href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/home?category="+category;
		});	
	
	
	
		$(".like").on("click",function(e){
			e.preventDefault();
			
			let postLikeStatus = $(this).children(".post_like").val();
			let postIdx = $(this).children(".postIdx").val();
			
			$.ajax({
				url:"http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/post/like_add",
				type:"GET",
				data:"postIdx="+postIdx+"&postLikeStatus="+postLikeStatus,
				success:function(){
					location.reload();
				},
				error:function(){
				}
			});
		
		});
		
		$("#post_add_btn").on("click",function(e){
			$.ajax({
				url:"http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/post_add",
				type:"GET",
				success:function(){
					location.href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/post_form";
				},
				error:function(){
					alert("로그인 후 사용해 주세요");
					location.href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/login";
				}
			});
		});
		
		$(".status_check").on("click",function(){
			location.href = "http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/home?postStatus="+isPostStatus();
		});
		
		$(".search_btn").on("click",function(e){
			let search = $("#search").val();
			location.href = "http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/post_search?search="+search+"&postStatus="+isPostStatus();
		});
		
		$(".all").on("click",function(e){
			location.href = "http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/home?postStatus="+isPostStatus();
		});
		
		$(".project").on("click",function(e){
			location.href = "http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/post_project?postStatus="+isPostStatus();
		});
		
		$(".study").on("click",function(e){
			location.href = "http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/post_study?postStatus="+isPostStatus();
		});
	</script>
</body>
</html>