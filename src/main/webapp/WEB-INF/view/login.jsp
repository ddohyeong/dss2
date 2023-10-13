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
        <link rel="icon" href="../logo/favicon.ico/ms-icon-144x144.png" sizes="144x144">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
            <link rel="stylesheet" href="../css/login.css">
        <title>로그인</title>
    </head>
    <body>

        <div class="login_wrapper">
            <div class="header">
            	<a href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/home">
                    <img src="../logo/logo2.svg" alt="">
                </a>
            </div>
            <div class="login_content">
                
                <form:form action="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/member/login" method="POST" modelAttribute="loginCommand">
                    <div class="id_box info_box">
                        <h4>
                            <label for="id">아이디</label>
                        </h4>
                        <span class="inputBox">
                        	<form:input path="id"/>
                        </span>
						<form:errors path="id"/> 
                    </div>

                    <div class="password_box info_box">
                        <h4>
                            <label for="password">비밀번호</label>
                        </h4>
                        <span class="inputBox">
                        	<form:password path="pw"/>
                        </span>
						<form:errors path="pw"/>
                    </div>

                    <div class="login_status">
                        <i class="bi bi-check-circle" id="login_check"></i>
                        <span>아이디 저장</span>
                    </div>
                
                    
                    <button type="submit">로그인</button>
                    <button type="button" id="join_btn">회원가입</button>
                </form:form>
                <ul class="find_wrapper">
                    <li>
                        <a href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/find_id">아이디 찾기</a>
                    </li>
                    <li>
                        <a href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/find_pw">비밀번호 찾기</a>
                    </li>
                </ul>
                
                <div id="naverIdLogin">
                   <a id="naverIdLogin_loginButton" href="#">
                      <img src="https://static.nid.naver.com/oauth/big_g.PNG?version=js-2.0.1" height="60">
                   </a>
               </div>
            </div>
        </div>
            <script src="../js/jquery-3.6.0.min.js"></script>
            <script src="../js/naveridlogin_js_sdk_2.0.2.js"></script>
            
            <script>
            
            	$("#join_btn").on("click",function(){
            		location.href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/join";
            	});
            
            
               $("#login_check").on("click",function(){
                    let className =  $("#login_check").attr('class');
                    if(className.includes('fill')){
                        $("#login_check").removeClass('bi-check-circle-fill');
                        $("#login_check").addClass('bi-check-circle');
                    }else{
                        $("#login_check").removeClass('bi-check-circle');
                        $("#login_check").addClass('bi-check-circle-fill');
                    }
                });
            
                window.name='opener';

                // 네이버 로그인 API
                
                var naverLogin = new naver.LoginWithNaverId(
                    {
                        clientId: "QRpL7DcS9iwitS4yNmap",
                        callbackUrl:"http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com:80/dss/view/naver_login",
                        isPopup: false,
                        loginButton: {color: "white", type: 1, height: 60}
                    }
                );

                naverLogin.init();

                
                    
                $("#gnbLogin").click(function (e) {
                    e.preventDefault();
                    naverLogin.logout();
                    location.href='http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/member/logout';
                });
            </script>
    </body>
</html>