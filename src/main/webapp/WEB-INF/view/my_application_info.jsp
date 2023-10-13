<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html lang="en">
<head>
    
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>신청한 게시글</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <link rel="icon" href="../logo/favicon.ico/ms-icon-144x144.png" sizes="144x144">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css">
    <link rel="stylesheet" href="../css/my_application_info.css">
    <link href="../css/nice-select.css" rel="stylesheet">
    <link href="../css/tagify.css" rel="stylesheet">

</head>
<body>
    <%@ include file="../includes/header.jsp" %>

   	<%@ include file="../includes/banner.jsp"%>
    
    <main>
        <%@ include file="../includes/my_page_sidebar.jsp" %>

        <form action="">
            <section>
				
				
                <h2><i class="bi bi-list-ul"></i> 내가 신청한 게시글</h2>
                <c:if test="${empty postAndApplicationInfoList }">
                	 <div class="post_info" >
	                      <h2>신청한 스터디/프로젝트가 없습니다</h2>
                    </div>
                </c:if>
                <c:if test="${not empty postAndApplicationInfoList }">
					<div>
                    <section class="my_post_list">
                        <ul>
                        	<c:forEach items="${postAndApplicationInfoList }" var="managementInfo">
	    						<li class="registed_post">
	                                <div class="post_info" >
	                                	<input type="hidden" class="postIdx" value="${managementInfo.postIdx}">
	                                    <h2><a href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/post/detail?postIdx=${managementInfo.postIdx }">${managementInfo.postName }</a></h2>
	                                    
	                                    <c:if test="${managementInfo.applicationStatus eq 'N'}">
	                                    	<c:set var ="applicationStatus" value="대기중"/>	
	                                    </c:if>
	                                    <c:if test="${managementInfo.applicationStatus eq 'T'}">
	                                    	<c:set var ="applicationStatus" value="수락"/>
	                                    </c:if>
	                                    <c:if test="${managementInfo.applicationStatus eq 'F'}">
	                                    	<c:set var ="applicationStatus" value="거절"/>	
	                                    </c:if>
	                                    <p class="status_check">${applicationStatus }</p>
	                                    
	                                    <p>${managementInfo.applicationRegistDate }</p>
	                                </div>
	                                <input type="hidden" class="managementIdx" value="${managementInfo.managementIdx }">
	                                <button type="button" class="check_btn delete_btn" value="${managementInfo.managementIdx }">삭제</button>
								  	<c:if test="${managementInfo.applicationStatus eq 'T'}">
										<a href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/chat?recipientMemberIdx=${managementInfo.memberIdx }" class="check_btn message_btn">메시지</a>                                    	
                                    </c:if>
	                            </li>
    						</c:forEach>
                        </ul>
                    </section>
                </div>
				</c:if>
                
            </section>
        </form>
    </main>
   
    <script src="../js/jquery-3.6.0.min.js"></script>
    <script src="../js/jquery.nice-select.js"></script>

    <script>
        $('').niceSelect();
        ///////////////////
        
//  $(".message_btn").parent().children(".post_info").children(".postIdx").val();
        
    	
        $(".delete_btn").on("click",function(){
        	let check = confirm("신청을 취소하겠습니까?");
        	
        	if(check){
				let managementIdx = $(this).val();       	
        		$.ajax({
        			url:"http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/post/application_delete",
        			type:"POST",
        			data:"managementIdx="+managementIdx,
        			success:function(){
        				location.reload();
        			},
        			error:function(){
        				alert("신청서를 삭제하지 못했습니다");
        			}
        		});
        	}
        });
    </script>
</body>
</html>