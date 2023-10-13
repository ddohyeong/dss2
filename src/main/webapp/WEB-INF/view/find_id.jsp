<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" href="../logo/favicon.ico/ms-icon-144x144.png"
	sizes="144x144">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="../css/find_id.css">
<title>아이디 찾기</title>
</head>
<body>
	<div class="find_wrapper">
		<div class="header">
			<a href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/home">
				<img src="../logo/logo2.svg" alt="">
			</a>
		</div>

		<div class="find_content">
			<div class="title_button">
				<a href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/find_id" class="find_btn id_find" >아이디 찾기</a>
				<a href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/find_pw" class="find_btn pw_find" >비밀번호 찾기</a>
			</div>

			<form action="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/member/find_id" method="POST">
				<div class="name_box info_box">
					<h4>
						<label for="name">이름</label>
					</h4>
					<span class="inputBox"> 
					<input type="text" name="name" id="name">
					</span>
				</div>

				<div class="tel_box info_box">
					<h4>
						<label for="tel">연락처</label>
					</h4>

					<div class="tel_wrap">
						<div class="tel_front">
							<span class="ps_box"> 
							<input type="text" id="front" name="front" value="010" class="int" maxlength="3">
							</span>
						</div>
						<div class="tel_middle">
							<span class="ps_box"> 
							<input type="text" id="mid" name="mid" placeholder="-" class="int" maxlength="4"> 
							<label for="mid" class="lbl"></label>
							</span>
						</div>
						<div class="tel_back">
							<span class="ps_box"> 
							<input type="text" id="back" name="back" placeholder="-" class="int" maxlength="4"> 
							<label for="back" class="lbl"></label>
							</span>
						</div>
					</div>
				</div>

				<div class="finded_wrap">
					<img src="../icon/search.svg" alt=""> 
					<span id="finded_id"></span>
				</div>
				<button class="btn" type="submit" id="id_find_btn">아이디 찾기</button>
				<a class="btn login_btn" href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/login" >로그인</a>
			</form>

		</div>
	</div>
	<script src="../js/jquery-3.6.0.min.js"></script>
	<script>
	
	$("#id_find_btn").on("click",function(e){
		e.preventDefault();
		
		let name = $("#name").val();
		
		let front = $("#front").val();
		let mid = $("#mid").val();
		let back = $("#back").val();
		
		let tel = front+"-"+mid+"-"+back;

		$.ajax({
			url:"http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/member/find_id",
			type:"POST",
			data:"name="+name+"&tel="+tel,
			success:function(data){
				$("#finded_id").text("아이디는"+data.id+" 입니다");
			},
			error:function(){
				alert("입력값이 잘 못 되었습니다");
			}
		});
	});
		
	</script>
</body>
</html>