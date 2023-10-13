<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	

<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>소개글</title>
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
<link rel="stylesheet" href="../css/introduce_form.css">
<link href="../css/magicsuggest.css" rel="stylesheet"/>
<link href="../css/nice-select.css" rel="stylesheet">

</head>
<body>
	<%@ include file="../includes/header.jsp"%>

	<%@ include file="../includes/banner.jsp"%>


	<main>
	
		<c:if test="${empty myInfo }">
			<h1>등록된 소개글이 없습니다</h1>
			<hr>
		</c:if>
	
		<form action="">
			<section>
				<h2>나의 소개글</h2>
				<div>
					<section class="form_info1">
						<ul class="info_row">
							<li><span>Email</span> 
							<input class="nice-select wide" type="text" value="${loginInfo.email }" disabled="disabled"></li>

							<li><span>연락 방법</span>
								<div class="nice-select wide">
									
									<c:if test="${empty myInfo}">
										<span class="current" id="contactMethod">연락 방법</span>
									</c:if>
									<c:if test="${not empty myInfo}">
										<span class="current" id="contactMethod">${myInfo.contactMethod }</span>
									</c:if>
									<ul class="list">
										<li class="option">오프라인</li>
										<li class="option">카카오톡</li>
										<li class="option">ZOOM</li>
										<li class="option">디스코드</li>
										<li class="option">전화</li>
										<li class="option">다른 SNS</li>
									</ul>
								</div></li>

							<li><span>역할</span>
								<div class="nice-select wide">
									<c:if test="${empty myInfo}">
										<span class="current" id="role">역할</span>
									</c:if>
									<c:if test="${not empty myInfo}">
										<span class="current" id="role">${myInfo.role }</span>
									</c:if>
									<ul class="list">
										<li class="option">프론트 개발자</li>
										<li class="option">백앤드 개발자</li>
										<li class="option">풀스택 개발자</li>
										<li class="option">앱 개발자</li>
										<li class="option">게임 개발자</li>
										<li class="option">웹 개발자</li>
										<li class="option">그래픽 개발자</li>
										<li class="option">빅데이터 개발자</li>
									</ul>
								</div></li>


							<li>
								<span>기술 스택</span>
								<div class="nice-select wide tech_wrapper" id="technologyCategory">
								</div>
							</li>

						</ul>
					</section>

					<section class="form_info2">
						<div class="introduce_wrapper">
							<ul>
								<li>
									<div class="introduce_introduction">
										<span>소개글</span>
										<textarea name="" id="introduce" cols="60" rows="10" placeholder="소개글을 입력하세요">${myInfo.introduce }</textarea>
									</div>
								</li>
							</ul>
						</div>
					</section>

				</div>
			</section>
			
			
				<div>
					<c:if test="${not empty loginInfo and empty myInfo  }">
						<button type="submit" class="introduce_btn" id="introduce_add">등록하기</button>
					</c:if>
				
					<c:if test="${not empty loginInfo and loginInfo.memberIdx eq myInfo.memberIdx }">		
						<button type="submit" class="introduce_btn" id="introduce_update">수정하기</button>
					</c:if>
				</div>
			

		</form>

	</main>

	<script src="../js/jquery-3.6.0.min.js"></script>
    <script src="../js/magicsuggest.js"></script>
    <script src="../js/function.js"></script>
	<script src="../js/jquery.nice-select.js"></script>
	
	<script>
		// 셀렉함수
		$('').niceSelect();
		
	
		//기술 태그 입력란
		let ms1 = $('#technologyCategory').magicSuggest({
	        data: ["aws","c","django","firebase","git","go","java","javascript","kotlin","mongodb","mysql","oracle","python","react","spring","typescript","unity","vue"]	
	      });
		
		$("#technologyCategory input[type=text]").attr("disabled",true);
		$("#technologyCategory input[type=text]").attr("placeholder","기술 스택");
		
		
		// myInfo 등록 클릭시 
		$("#introduce_add").on("click",function(e){
			e.preventDefault();
			
			let contactMethod = $("#contactMethod").text();
			let role = $("#role").text();
			let technologyCategory = technologyCategoryValue();
			let introduce =  $("#introduce").val();
			
			if(!myInfoValidator(contactMethod) || contactMethod=='연락 방법') {
				alert("연락 방법을 입력하세요");
				return false;
			}else if(!myInfoValidator(role) || role == '역할' ) {
				alert("역할을 입력하세요");
				return false;
			}else if(!myInfoValidator(technologyCategory)) {
				alert("기술 스택을 입력하세요");
				return false;
			}else if(!myInfoValidator(introduce)) {
				alert("소개글을 입력하세요");
				return false;
			}
			
			$.ajax({
				url:"http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/my_info/add",
				type:"POST",
				data:"contactMethod="+contactMethod+"&role="+role+"&technologyCategory="+technologyCategory+"&introduce="+introduce,
				success:function(){
					location.href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/my_introduce";
				},
				error:function(){
					alert("등록에 실패했습니다");
				}
			});
		});
		
		
		// myInfo 수정 클릭시 
		$("#introduce_update").on("click",function(e){
			e.preventDefault();
			
			let updateCheck = confirm("수정을 진행하시겠습니까?");
			if(updateCheck){
				let contactMethod = $("#contactMethod").text();
				let role = $("#role").text();
				let technologyCategory = technologyCategoryValue();
				let introduce =  $("#introduce").val();
				
				
				
				if(!myInfoValidator(contactMethod)) {
					alert("연락 방법을 입력하세요");
					return false;
				}else if(!myInfoValidator(role)) {
					alert("역할을 입력하세요");
					return false;
				}else if(!myInfoValidator(technologyCategory)) {
					alert("기술 스택을 입력하세요");
					return false;
				}else if(!myInfoValidator(introduce)) {
					alert("소개글을 입력하세요");
					return false;
				}
				
				$.ajax({
					url:"http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/my_info/update",
					type:"POST",
					data:"contactMethod="+contactMethod+"&role="+role+"&technologyCategory="+technologyCategory+"&introduce="+introduce,
					success:function(){
						
						location.href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/my_introduce";
					},
					error:function(){
						alert("등록에 실패했습니다");
					}
				});
			}
		});				
			
		
		
	</script>
</body>
</html>