<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	

<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>알림 정보</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"	rel="stylesheet"	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"	crossorigin="anonymous">
<script	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"	crossorigin="anonymous"></script>
<link rel="icon" href="../logo/favicon.ico/ms-icon-144x144.png"	sizes="144x144">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css">
<link rel="stylesheet" href="../css/chat.css">
<link href="../css/nice-select.css" rel="stylesheet">

</head>

<body>
	<%@ include file="../includes/header.jsp"%>

	<%@ include file="../includes/banner.jsp"%>


	<main>
		<form action="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/send_message" method="POST">
			<div class="member_nickname">
				${recipientNickName } 님의 대화
				<input type="hidden" value="${recipientMemberIdx }" name="recipientMemberIdx" class="recipient_memberIdx">
				<input type="hidden" value="${messageInfoSize }" name="messageInfoSize" class="messageInfoSize">
				<button type="button" class="chat_close">대화방 나가기</button>
			</div>
			<section>

				<section class="chat_box">
					<div class="chat_area">
						<c:forEach items="${messageInfoList }" var="messageInfo">
							<div class="message_wrap">
								<c:if test="${messageInfo.senderMemberIdx eq loginInfo.memberIdx }">
									<p class="sender">${messageInfo.message }</p>
									<span class="sender_registdate message_registDate">${messageInfo.registDate }</span>
								</c:if>
								<c:if test="${messageInfo.senderMemberIdx ne loginInfo.memberIdx }">
									<p class="recipient">${messageInfo.message }</p>									
								<span class="message_registDate">${messageInfo.registDate }</span>
								</c:if>

							</div>	
						</c:forEach>
					</div>
				</section>
				<section class="input_message info">
					<input class="input_chat info wide" name="message" type="text" placeholder="최대 100글자" autofocus>
					<button type="submit" class="check_btn btn_style">확인</button>	
				</section>
				
				<section>
					<div id="map" style="width:100%;height:350px;"></div>

					<input class="input_chat" type="text" id="location_search" placeholder="위치 검색"> 
				   <button type="button" class="search_btn btn_style">검색</button>
						   
				    <div id="clickLatlng">
				    	<div id="kakao_url"></div>
				    	<button type="button" class="location_transfer_btn btn_style">지정한위치 전송하기</button>
				    </div>			
				</section>
			</section>
		</form>

	</main>

	<script src="../js/jquery-3.6.0.min.js"></script>
	<script src="../js/jquery.nice-select.js"></script>
    <script type="text/javascript" src="http://dapi.kakao.com/v2/maps/sdk.js?appkey=d03bbde3fc205f9b219cb0dcca469528&libraries=services,clusterer,drawing"></script>
	<script src="../js/kakao_map.js"></script>
	<script>
		$('').niceSelect();
		
		$(".chat_close").on("click",function(){
			history.back();
		});
		
		// 채팅 맨 아래로 위치하게 (임의로 큰 숫자로 지정)
		$(".chat_area").scrollTop(10000000);
		
		setInterval(function () {
			let recipientMemberIdx = $(".recipient_memberIdx").val();
			let messageInfoSize = $(".messageInfoSize").val();
			
			$.ajax({
				url:"http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/reload/chat",
				type:"GET",
				data:"recipientMemberIdx="+recipientMemberIdx+"&messageInfoSize="+messageInfoSize,
				success:function(){
				},
				error:function(){
					location.href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/chat?recipientMemberIdx="+recipientMemberIdx;
				}
			});
		}, 3000)
		
		
		$(".check_btn").on("click",function(e){
			e.preventDefault();
			
				let recipientMemberIdx = $(".recipient_memberIdx").val();
				let message = $(".input_chat").val();
				if(message.trim().length == 0){
					alert("메시지를 입력하세요");
					return false;
				}
				$.ajax({
					url:"http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/send_message",
					type:"POST",
					data:"recipientMemberIdx="+recipientMemberIdx+"&message="+message,
					success:function(){
						location.reload();
					},
					error:function(){
						alert("메시지 전송에 실패했습니다");
					}
				});	
		});
		
		$(".location_transfer_btn").on("click",function(e){
			e.preventDefault();
			
				let recipientMemberIdx = $(".recipient_memberIdx").val();
				
				let kakaoUrl = $("#kakao_url").html();
				
				if(kakaoUrl == ''){
					alert("위치를 지정하세요")
				}else{
					let check = confirm("위치를 전송할까요?")
					
					if(check){
						let message = "위치를 전송합니다 여기서 만나요 : "+kakaoUrl;
						
						$.ajax({
							url:"http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/send_message",
							type:"POST",
							data:"recipientMemberIdx="+recipientMemberIdx+"&message="+message,
							success:function(){
								location.reload();
							},
							error:function(){
							}
						});			
					}
				}
		});
		
	</script>
</body>
</html>