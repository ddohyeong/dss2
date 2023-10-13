<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>     
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
<link rel="stylesheet" href="../css/join.css">
<title>회원가입</title>
</head>
<body>

	<div class="join_wrapper">
		<div class="header">
			<a href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/home">
				<img src="../logo/logo2.svg" alt="">
			</a>
		</div>
		<div class="join_content">
			<form:form action="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/member/join" method="POST" modelAttribute="memberInfo">
				<div class="id_box info_box">
					<h4>
						<label for="id">아이디</label>
					</h4>
					<span class="inputBox"> 
<!-- 						<input type="text" name="id" id="id" placeholder="5~20자리의 영소문자와 숫자만 입력 가능합니다"> -->
						<form:input path="id" placeholder="5~20자리의 영소문자와 숫자만 입력 가능합니다"/>
					</span>
                    <form:errors path="id"/>    
					
				</div>

				<div class="password_box info_box">
					<h4>
						<label for="password">비밀번호</label>
					</h4>
					<span class="inputBox"> 
<!-- 						<input type="password" name="pw" id="pw" placeholder="5~20자리의 영어대소문자와 특수문자를 포함해야합니다"> -->
						<form:password path="pw" placeholder="5~20자리의 영어대소문자와 특수문자를 포함해야합니다"/>
					</span>
				</div>

				<div class="passwordCheck_box info_box">
					<h4>
						<label for="passwordCheck">비밀번호 재확인</label>
					</h4>
					<span class="inputBox"> 
						<input type="password" name="pwChk" id="pwChk">
					</span>
				</div>

				<div class="name_box info_box">
					<h4>
						<label for="nickName">이름</label>
					</h4>
					<span class="inputBox">
<!-- 						 <input type="text" name="name" id="name"> -->
 						<form:input path="name"/>
					</span>
				</div>

				<div class="nickName_box info_box">
					<h4>
						<label for="nickName">닉네임</label>
					</h4>
					<span class="inputBox"> 
<!-- 					 	<input type="text" name="nickName" id="nickName"> -->
 						<form:input path="nickName"/>
					</span>
				</div>
				<div class="email_box info_box">
					<h4>
						<label for="email">이메일</label>
					</h4>
					<div class="email_input_btn">
						<span class="inputBox"> 
<!-- 							<input type="text" name="email" id="email" value=""> -->
							<form:input path="email"/>
						</span>
					</div>
				</div>

				<div class="tel_box info_box">
					<h4>
						<label for="tel">연락처</label>
					</h4>

					<div class="tel_wrap">
						<div class="tel_front">
							<span class="ps_box"> <input type="text" id="front"
								value="010" class="int" maxlength="3">
							</span>
						</div>
						<div class="tel_middle">
							<span class="ps_box"> <input type="text" id="mid"
								placeholder="-" class="int" maxlength="4"> <label
								for="mid" class="lbl"></label>
							</span>
						</div>
						<div class="tel_back">
							<span class="ps_box"> <input type="text" id="back"
								placeholder="-" class="int" maxlength="4"> <label
								for="back" class="lbl"></label>
							</span>
						</div>
					</div>
				</div>

				<button type="submit" class="join_btn">회원가입</button>
			</form:form>
		</div>
	</div>

<script src="../js/jquery-3.6.0.min.js"></script>
<script src="../js/memberValidator.js"></script>
<script>
	$(".join_btn").on("click",function(e){
		e.preventDefault();
		
		let id = $("#id").val();
		let pw = $("#pw").val();
		let pwChk = $("#pwChk").val();
		let name = $("#name").val();
		let nickName = $("#nickName").val();
		let front = $("#front").val();
		let mid = $("#mid").val();
		let back = $("#back").val();
		let tel = front+"-"+mid+"-"+back;
		let email = $("#email").val();
		
		if(idValidator(id)){
			alert("아이디는 5~20자리 영소문자와 숫자만 가능합니다");
			return false;
		}else if(pwValidator(pw)){
			alert("비밀번호는 5~20자리 영대소문자와 특수문자 숫자가 포함되어야 합니다");
			return false;
		}else if(!(pw == pwChk)){
			alert("비밀번호와 비밀번호 확인을 확인해주세요");
			return false;
		}else if(nameValidator(name)){
			alert("이름은 2~5자리 한글만 가능합니다");
			return false;
		}else if(nickNameValidator(nickName)){
			alert("닉네임은 2~10자리 영대소문자와 한글만 가능합니다");
			return false;
		
		}else if(emailValidator(email)){
			alert("이메일은 5~40자리 @과.(도메인2~3자리) 형식이여야 합니다");
			return false;
		}else if(telValidator(tel)){
			alert("연락처는 010-0000-0000 형식만 가능합니다");
			return false;
		}
		
		$.ajax({
			url:"http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/member/join",
			type:"POST",
			data:"id="+id+"&pw="+pw+"&pwChk="+pwChk+"&name="+name+"&nickName="+nickName+"&tel="+tel+"&email="+email,
			success:function(){
				alert("회원가입 완료");
				location.href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/login";
			},
			error:function(){
				alert("회원가입 실패");
			}
		});
		
	});
</script>
</body>
</html>