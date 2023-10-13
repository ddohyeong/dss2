<%@page import="domain.member.dto.MemberInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>나의 정보</title>
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
<link rel="stylesheet" href="../css/my_page.css">
<link rel="stylesheet" href="../css/member_info.css">
<link href="../css/nice-select.css" rel="stylesheet">
<link href="../css/magicsuggest.css" rel="stylesheet"/>

</head>
<body>
	<%@ include file="../includes/header.jsp"%>

	<%@ include file="../includes/banner.jsp"%>


	<main>
		<%@ include file="../includes/my_page_sidebar.jsp"%>

			<section>
				<div class="info_wrapper">
			        <div class="info_content">
			            <form action="">
			                <div class="id_box info_box">
			                    <h4>
			                        <label for="id">아이디</label>
			                    </h4>
			                    <span class="inputBox">
			                    	<c:if test="${loginInfo.loginType eq 2}">
			                    		<input type="text" name="id" id="id" value="네이버 계정입니다" disabled="disabled">
			                    	</c:if>
			                    	<c:if test="${loginInfo.loginType eq 1}">
			                    		<input type="text" name="id" id="id" value="${loginInfo.id }" disabled="disabled">
			                    	</c:if>
			                    
			                    </span>
			                </div>
			
			                <div class="password_box info_box">
			                    <h4>
			                        <label for="password">비밀번호</label>
			                    </h4>
			                    <div class="pw_input_btn">
			                        <span class="inputBox">
			                            <input type="password" name="password" id="password" value="passowrd" disabled="disabled">    
			                        </span>
			    
			                        <button type="button" class="pw_change_btn">비밀번호 수정</button>
			                    </div>
			                </div>
			
			               
			                
			                <div class="name_box info_box">
			                    <h4>
			                        <label for="nickName">이름</label>
			                    </h4>
			                    <span class="inputBox">
			                        <input type="text" name="name" id="name" value="${loginInfo.name }">    
			                    </span>
			                </div>
			
			                <div class="nickName_box info_box">
			                    <h4>
			                        <label for="nickName">닉네임</label>
			                    </h4>
			                    <span class="inputBox">
			                        <input type="text" name="nickName" id="nickName" value="${loginInfo.nickName }">    
			                    </span>
			                </div>
			
			                <div class="email_box info_box">
			                    <h4>
			                        <label for="email">이메일</label>
			                    </h4>
			                    <div class="email_input_btn">
			                        <span class="inputBox">
			                            <input type="text" name="email" id="email" value="${loginInfo.email }">    
			                        </span>
			    
<!-- 			                        <button type="button" class="send_code">인증 코드</button> -->
			                    </div>
			                    
<!-- 			                    <div class="code_input"> -->
<!-- 			                        <span class="inputBox"> -->
<!-- 			                            <input type="text" name="code" id="code">     -->
<!-- 			                        </span> -->
<!-- 			                        <button type="button">확인</button> -->
<!-- 			                    </div> -->
			
			                </div>
			
			                <div class="tel_box info_box">
			                    <h4>
			                        <label for="tel">연락처</label>
			                    </h4>
			                    
			                    <%
			                    	HttpSession ss = request.getSession();
			                        MemberInfo loginInfo = (MemberInfo) ss.getAttribute("loginInfo");
			                       	
			                        String tel = loginInfo.getTel();
			                        
			                        String[] telTemp = tel.split("-");

									request.setAttribute("telTemp", telTemp);
			                    %>
			                    
			                    <div class="tel_wrap">
			                        <div class="tel_front">
			                            <span class="ps_box">
			                                <input type="text" id="front" value="${telTemp[0] }" placeholder="010" class="int" maxlength="3">
			                            </span>
			                        </div>
			                        <div class="tel_middle">
			                            <span class="ps_box">
			                                <input type="text" id="mid" value="${telTemp[1] }" placeholder="-" class="int" maxlength="4">
			                                <label for="mid" class="lbl"></label>
			                            </span>
			                        </div>
			                        <div class="tel_back">
			                            <span class="ps_box">
			                                <input type="text" id="back" value="${telTemp[2] }" placeholder="-" class="int" maxlength="4">
			                                <label for="back" class="lbl"></label>
			                            </span>
			                        </div>
			                    </div>
			                </div>
			                <button type="submit" id="update_btn">회원 정보 수정</button>
			                <hr>
			                <div>
			                	<button type="button" id="withdraw_btn">회원 탈퇴</button>
			                </div>
							
			            </form>
			        </div>
		    
		    </div>
				
			</section>

	</main>

	<script src="../js/jquery-3.6.0.min.js"></script>
	<script src="../js/jquery.nice-select.js"></script>

	<script>
		$('').niceSelect();
		///////////////////
		
		$("#withdraw_btn").on("click",function(){
			location.href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/withdraw"
		});
		
		$(".pw_change_btn").on("click",function(){
			location.href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/pw_change"
		});
		
		$("#update_btn").on("click",function(e){
			e.preventDefault();
			
			let check = confirm("회원 정보를 수정하시겠습니까?");
			
			
			if(check){
				let name = $("#name").val();
				
				let nickName = $("#nickName").val();
				
				let front = $("#front").val();
				let mid = $("#mid").val();
				let back = $("#back").val();
				let tel = front +"-"+mid+"-"+back;
				
				let email = $("#email").val();
				
				$.ajax({
					url:"http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/member/update",
					type:"POST",
					data:"name="+name+"&nickName="+nickName+"&tel="+tel+"&email="+email,
					success:function(){
						location.href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/my_page";
					},
					error:function(){}
				});	
			}
		});
	</script>
</body>
</html>