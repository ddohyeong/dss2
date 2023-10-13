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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <link rel="icon" href="../logo/favicon.ico/ms-icon-144x144.png" sizes="144x144">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css">
    <link rel="stylesheet" href="../css/notice.css">
    <link href="../css/nice-select.css" rel="stylesheet">
    <link href="../css/tagify.css" rel="stylesheet">

</head>
<body>
   	<%@ include file="../includes/header.jsp" %>
   	
   	<%@ include file="../includes/banner.jsp"%>

    <main>
        <form action="">
            <div class="member_nickname">
                ${loginInfo.nickName} 님의 알림정보
                <button type="button" class="all_check_btn">확인 완료 알림삭제</button>
            </div>
                <div>
                    <section class="form_info1">
                        <ul class="notice_list">
                            <c:forEach items="${viewNoticeInfoList }" var="viewNoticeInfo">
								<c:if test="${viewNoticeInfo.noticeCode eq 1}">
									<c:set var="noticeCode" value="스터디를 신청했습니다"/>
									<c:set var="location_href" value="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/registed_post"/>
								</c:if>
								<c:if test="${viewNoticeInfo.noticeCode eq 2}">
									<c:set var="noticeCode" value="스터디 신청을 수락했습니다 메시지 확인하세요"/>
									<c:set var="location_href" value="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/applied_post"/>
								</c:if>
								<c:if test="${viewNoticeInfo.noticeCode eq 3}">
									<c:set var="noticeCode" value="스터디 신청을 거절했습니다"/>
									<c:set var="location_href" value="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/applied_post"/>
								</c:if>
								<c:if test="${viewNoticeInfo.noticeCode eq 4}">
									<c:set var="noticeCode" value="스터디 신청을 취소했습니다"/>
								</c:if>
								<c:if test="${viewNoticeInfo.noticeCode eq 5}">
									<c:set var="noticeCode" value="게시글을 삭제했습니다"/>
								</c:if>
								
								<li>
	                                <div class="info">
	                                    <p><a href="${location_href }">${viewNoticeInfo.applicantNickName } 님이 <span style="font-size: 17px; font-weight:bold;">${viewNoticeInfo.postName } </span>${noticeCode }</a></p>
	
	                                    <span>${viewNoticeInfo.registDate}</span>
	                                </div>
	                                <input type="hidden" class="noticeIdx" value="${viewNoticeInfo.noticeIdx }">
	                                <c:if test="${viewNoticeInfo.noticeCheckStatus eq 'N'}">
	                                	<button type="button" class="notice_checked check_btn">확인</button>
	                                </c:if>
	                                <c:if test="${viewNoticeInfo.noticeCheckStatus eq 'Y'}">
	                                	<button type="button" class="check_btn">확인 완료</button>
	                                </c:if>
	                                
                            	</li>
							</c:forEach>
                        </ul>
                    </section>
				</div>
        </form>

    </main>
   
    <script src="../js/jquery-3.6.0.min.js"></script>
    <script src="../js/jquery.nice-select.js"></script>
    <script>
    
    
    
	    $(".all_check_btn").on("click",function(){
			
			$.ajax({
				url:"http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/notice_delete",
				type:"POST",
				success:function(){
					location.reload();
				},
				error:function(){}
			});
		});
    
    	$(".notice_checked").on("click",function(){
    		let noticeIdx = $(this).prev().val();
    		
    		$.ajax({
    			url:"http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/notice_check_status",
    			type:"GET",
    			data:"noticeIdx="+noticeIdx,
    			success:function(){
    				location.reload();
    			},
    			error:function(){}
    		});
    	});
    	
        $('').niceSelect();
       
    </script>
</body>
</html>