<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>비밀번호 수정</title>
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
			                        <label for="pw">현재 비밀번호</label>
			                    </h4>
			                    <div class="pw_input_btn">
			                        <span class="inputBox">
			                            <input type="password" name="pw" id="pw" >    
			                        </span>
								</div>
								
								
								<h4>
			                        <label for="newPw">새로운 비밀번호</label>
			                    </h4>			                        
		                        <span class="inputBox">
		                            <input type="password" name="newPw" id="newPw" >
		                        </span>
			                        
			                     <h4>
			                        <label for="newPwChk">새로운 비밀번호 확인</label>
		                    	 </h4>   
		                        <span class="inputBox">
		                            <input type="password" name="newPwChk" id="newPwChk">    
		                        </span>
			    						
			                </div>

							<c:if test="${pwChangeNotice eq true }">
				                	<button type="button" class="next_change_btn">다음에 변경하기</button>
			                </c:if>			
			                <button type="button" class="pw_change_btn">비밀번호 수정</button>
			                
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
		
		$(".next_change_btn").on("click",function(e){
			e.preventDefault();
			location.href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/member/next_pw_update";
		});
		
		$(".pw_change_btn").on("click",function(e){
			e.preventDefault();
			
			let check = confirm("비밀번호를 수정하시겠습니까?");
			
			
			
			if(check){
				let pw = $("#pw").val();
				
				let newPw = $("#newPw").val();
				
				let newPwChk = $("#newPwChk").val();
				
				
				// 비밀번호 검증 코드 넣기 
				
				if(newPw != newPwChk){
					alert("새로운 비밀번호 확인을 다시 입력해주세요");
					return false;
				}				
				
				$.ajax({
					url:"http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/member/pw_update",
					type:"POST",
					data:"pw="+pw+"&newPw="+newPw+"&newPwChk="+newPwChk,
					success:function(){
						alert("비밀번호 수정이 완료되었습니다");
						location.href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/my_page";
					},
					error:function(){
						alert("비밀번호 수정에 실패했습니다");
					}
				});	
			}
		});
	</script>
</body>
</html>