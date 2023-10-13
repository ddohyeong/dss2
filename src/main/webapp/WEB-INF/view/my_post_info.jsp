<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
	
<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>등록한 게시글</title>
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
<link rel="stylesheet" href="../css/my_post_info.css">
<link href="../css/nice-select.css" rel="stylesheet">
<link href="../css/tagify.css" rel="stylesheet">
<script src="../js/jquery-3.6.0.min.js"></script>

</head>
<body>
	<%@ include file="../includes/header.jsp"%>

	<%@ include file="../includes/banner.jsp"%>


	<main>
		<%@ include file="../includes/my_page_sidebar.jsp"%>

		<form action="">
			<section>
				<h2>
					<i class="bi bi-list-ul"></i> 내가 등록한 게시글
				</h2>
				<c:if test="${empty registedPostInfoList }">
					<div class="post_info" >
	                      <h2>등록한 스터디/프로젝트가 없습니다</h2>
                    </div>
				</c:if>
				<c:if test="${not empty registedPostInfoList }">
					<div>
					<section class="my_post_list">
						<ul>
							<c:forEach items="${registedPostInfoList }" var="registedPostInfo">
								<li class="registed_post">
								<div class="post_info">
									<div>
										<button type="button" class="check_btn post_update_btn">수정</button>
										<button type="button" class="check_btn post_delete_btn">삭제</button>
									</div>	
									<input type="hidden" class="postIdx" value="${registedPostInfo.postIdx }">
									<p>${registedPostInfo.postRegistDate }</p>
									<h2><a href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/post/detail?postIdx=${registedPostInfo.postIdx }">${registedPostInfo.postName }</a></h2>
									<div class="form-check form-switch">
										<c:if test="${registedPostInfo.postStatus eq 'T'}">
											<c:set var="postStatus" value="checked"/>
										</c:if>
										<c:if test="${registedPostInfo.postStatus ne 'T'}">
											<c:set var="postStatus" value=""/>
										</c:if>
										<input class="form-check-input status_check" type="checkbox" role="switch" ${postStatus }>
									</div>
								</div>
													
								<div class="applicant_list">
									<ul>
										<c:forEach items="${registedPostInfo.registedPostApplicationInfoList }" var="registedPostApplication">
										
											<li class="applicant">
												<img src="../icon/list-ul.svg">
												
												<a href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/introduce_info?memberIdx=${registedPostApplication.applicantMemberIdx}">${registedPostApplication.applicantNickName}</a>
												
												<c:if test="${registedPostApplication.applicationStatus eq 'N'}">
			                                    	<c:set var ="applicationStatus" value="대기중"/>	
			                                    </c:if>
			                                    <c:if test="${registedPostApplication.applicationStatus eq 'T'}">
			                                    	<c:set var ="applicationStatus" value="수락"/>	
			                                    </c:if>
	                                    		<c:if test="${registedPostApplication.applicationStatus eq 'F'}">
		                                    		<c:set var ="applicationStatus" value="거절"/>	
		                                    	</c:if>
												<span>${applicationStatus}</span>
												
												<input type="hidden" class="applicantIdx" value="${registedPostApplication.applicantMemberIdx }">												
												<input type="hidden" class="managementIdx" value="${registedPostApplication.managementIdx }">
												<div class="accpet_refuse_btn">
													<c:if test="${registedPostApplication.applicationStatus eq 'N'}">
														<button type="button" class="check_btn accept_btn">수락</button>
														<button type="button" class="check_btn refuse_btn">거절</button>
													</c:if>
													
													<c:if test="${registedPostApplication.applicationStatus eq 'T'}">
														<button type="button" class="check_btn message_btn">메시지</button>
													</c:if>
												</div>
											</li>
										</c:forEach>
									</ul>
								</div>
							</li>
							</c:forEach>
						</ul>
					</section>
				</div>
				</c:if>
				
			</section>
		</form>
	</main>
	
	<script src="../js/jquery.nice-select.js"></script>

	<script>
		$('').niceSelect();
		
		$(".status_check").on("click",function(){
			let postStatus = "";
			if($(this).prop("checked")){
				postStatus = "F";
			}else{
				postStatus = "T";
			}
			
			let postIdx = $(this).parents(".post_info").children(".postIdx").val();
			
			$.ajax({
				url:"http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/post/status_toggle",
				type:"POST",
				data:"postIdx="+postIdx+"&postStatus="+postStatus,
				success:function(){
					location.reload();					
				},
				error:function(){}
			});	
			
			
		});
		
		$(".post_update_btn").on("click",function(e){
			e.preventDefault();
			
			let postIdx = $(this).parent().next().val();
			location.href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/post_update?postIdx="+postIdx;
		});
		
		$(".message_btn").on("click",function(){
			let recipientMemberIdx = $(this).parent().prev().prev().val();
			
			location.href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/chat?recipientMemberIdx="+recipientMemberIdx;
		});
		
		$(".post_delete_btn").on("click",function(){
			let check = confirm("게시글을 삭제하시겠습까 바꿀 수 없습니다");
			if(check){
				
				let postIdx = $(this).parent().next().val();
				
				$.ajax({
					url:"http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/post/delete",
					type:"POST",
					data:"postIdx="+postIdx,
					success:function(){
						location.reload();					
					},
					error:function(){}
				});	
			}
		});
		
		$(".accept_btn").on("click",function(){
			let check = confirm("수락하시겠습까 바꿀 수 없습니다");
			if(check){
				let applicationStatus = "T";
				let applicantMemberIdx = $(this).parent().prev().prev().val();
				let managementIdx = $(this).parent().prev().val();
				
				$.ajax({
					url:"http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/confirm/application_status",
					type:"POST",
					data:"applicationStatus="+applicationStatus+"&applicantMemberIdx="+applicantMemberIdx+"&managementIdx="+managementIdx,
					success:function(){
						location.reload();					
					},
					error:function(){}
				});	
			}
		});
		
		$(".refuse_btn").on("click",function(){
			let check = confirm("거절하시겠습까 바꿀 수 없습니다");
			if(check){
				let applicationStatus = "F";
				let applicantMemberIdx = $(this).parent().prev().prev().val();
				let managementIdx = $(this).parent().prev().val();
				
				$.ajax({
					url:"http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/confirm/application_status",
					type:"POST",
					data:"applicationStatus="+applicationStatus+"&applicantMemberIdx="+applicantMemberIdx+"&managementIdx="+managementIdx,
					success:function(){
						location.reload();					
					},
					error:function(){}
				});	
			}
		});
		
	</script>
</body>
</html>