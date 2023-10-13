<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
	
<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>탈퇴</title>
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
<link rel="stylesheet" href="../css/withdraw.css">
<link href="../css/nice-select.css" rel="stylesheet">
<link href="../css/tagify.css" rel="stylesheet">
<script src="../js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<%@ include file="../includes/header.jsp"%>

	<%@ include file="../includes/banner.jsp"%>

	<main>
		<%@ include file="../includes/my_page_sidebar.jsp"%>

		<form action="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/introduce_info" method="POST">
			<section>
				<h2>탈퇴하기</h2>
				
				<div>
					<section class="form_info1">
						<ul class="info_row">
							<li>
								<span>탈퇴 사유</span>
								<div class="nice-select wide">
			         	            <span class="current" id="reason">탈퇴사유</span>
			                         <ul class="list option_list">
			                             <li class="option">아이디 변경을 위해 탈퇴 후 재가입</li>
			                             <li class="option">컨텐츠 등 이용할 만한 서비스 부족</li>
			                             <li class="option">사이트 이용 불편</li>
			                             <li class="option">이용빈도 낮음</li>
			                             <li class="option">개인정보 누출 우려</li>
			                         </ul>
			                    </div>
							</li>
						</ul>
					</section>
					
					
					
					<section class="form_info2">
						<div class="introduce_wrapper">
							<ul>
								<li>
									<div class="introduce_introduction">
										<span>탈퇴사유 - 직접 입력</span>
										<textarea name="" id="reason_text" cols="60" rows="10" placeholder="탈퇴 사유를 적어주세요"></textarea>
									</div>
								</li>
							</ul>
						</div>
					</section>

				</div>
			</section>
			
			<input type="hidden" name="memberIdx" id="memberIdx" value="${loginInfo.memberIdx }">
			<div>
				<button type="button" class="btn_style" id="withdraw_btn">회원 탈퇴</button>
			</div>
		</form>
	</main>

	<script src="../js/jquery.nice-select.js"></script>

	<script>
		$('').niceSelect();
		
		$("#withdraw_btn").on("click",function(e){
			e.preventDefault();
			
			let check = confirm("회원 탈퇴를 진행하시겠습니까?");
			if(check){
				let reason = $("#reason").text();
				
				if(reason == '탈퇴사유'){
					alert("탈퇴사유를 고르세요");
					return false;
				}
				
				let reasonText = $("#reason_text").val();
				
				$.ajax({
					url:"http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/member/delete",
					type:"POST",
					data:"reason="+reason+"&reasonText="+reasonText,
					success:function(){
						alert("회원 탈퇴가 완료되었습니다");
						location.href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/home";
					},
					error:function(){
						alert("회원 탈퇴에 실패했습니다");
					}
				});
			}
		});	
					
			
	</script>
</body>
</html>