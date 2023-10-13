<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script src="../js/jquery-3.6.0.min.js"></script>
	<script>
		let url = window.location.href;
		
		let s =url.split("#");
		
		let parameter = s[1].split("&");
		
		let token ='';
		
		for(let i = 0; i<parameter.length; i++){
			
			let param = parameter[i].split("=");
			if(param[0] == 'access_token'){
				token = param[1];
			}
		}

		$.ajax({
			url:"http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/member/naver_login",
			type:"POST",
			data:"access_token="+token,
			success:function(data){
				if(data.view == 'dormant'){
					location.href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/dormant";
				}else{
					location.href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/home";	
				}
			},
			error:function(){
				location.href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/login";
			}
		});
		
		
		
	</script>
</body>
</html>