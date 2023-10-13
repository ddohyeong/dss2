<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="../css/find_pw.css">
<title>휴면 회원</title>
</head>
<body>

	<div class="find_wrapper">
		<div class="header">
			<a href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/home">
				<img src="../logo/logo2.svg" alt="">
			</a>
		</div>

		<div class="find_content">
			
			<p>6개월간 로그인 하지 않아 휴먼 회원으로 전환되었습니다 해당 계정의 이름과 이메일로 휴먼 해제 절차를 진행해 주세요 </p>

			<form action="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/" method="POST">
				<div class="id_box info_box">
					<h4>
						<label for="name">이름</label>
					</h4>
					<span class="inputBox"> 
						<input type="text" name="name" id="name">
					</span>
				</div>

				 <div class="email_box info_box">
                    <h4>
                        <label for="email">이메일</label>
                    </h4>
                    <div class="email_input_btn">
                        <span class="inputBox">
                            <input type="text" name="email" id="email">    
                        </span>
    
                        <button type="button" id="send_code" class="send_code_button">인증 코드</button>
                    </div>
                    
                    <div class="code_input">
                        <span class="inputBox">
                            <input type="text" name="code" id="code">    
                        </span>
                    </div>

                </div>

				<div class="finded_wrap">
					<img src="../icon/search.svg" alt=""> 
					<span id="finded_pw"></span>
				</div>


				<button class="btn" id="release_button" type="submit">휴먼 해제</button>
			</form>
		</div>
	</div>
	<script src="../js/jquery-3.6.0.min.js"></script>
	<script>
	// 인증코드 발송 버튼
	
	
	$("#send_code").on("click",function(){
		let name = $("#name").val();
		let email =$("#email").val();
		
		$.ajax({
			url:"http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/member/send_code",
			type:"POST",
			data:"name="+name+"&email="+email,
			success:function(){
				alert("이메일로 코드가 전송되었습니다");
			},
			error:function(){
				alert("오류가 발생했습니다");
			}
		});
		
	});
	
	// 휴먼해제 버튼
	$("#release_button").on("click",function(e){
		e.preventDefault();
		
		let name = $("#name").val();
		let email =$("#email").val();
		let code = $("#code").val();
		
		if(code.length == 0){
			alert("인증 코드를 입력하세요");
			return false;
		}
		
		$.ajax({
			url:"http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/member/dormant_release",
			type:"POST",
			data:"name="+name+"&email="+email+"&code="+code,
			success:function(){
				location.href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/login";
			},
			error:function(){
				alert("입력값이 잘 못 되었습니다");
			}
		});
	});
	</script>
</body>
</html>