<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시글 상세정보</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <link rel="icon" href="../logo/favicon.ico/ms-icon-144x144.png" sizes="144x144">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css">
    <link rel="stylesheet" href="../css/post_detail.css">
    <link href="../css/nice-select.css" rel="stylesheet">
    <link href="../css/tagify.css" rel="stylesheet">

</head>
<body>
    <%@ include file="../includes/header.jsp" %>

   	<%@ include file="../includes/banner.jsp"%>

    <main>
        <form action="">
            <div class="member_nickname">${postInfo.nickName } 님의 게시글 정보</div>
            
                    
            <section class="form_info2">
                <h2>프로젝트 소개</h2>
                <div class="recruitment_wrapper">
                    <ul>
                        <li>
                            <div class="recruit_name">
                                <span>프로젝트 제목</span>
                                <input class="nice-select wide recruit_ment_input" type="text" value="${postInfo.postName }" disabled="disabled">
                            </div>
                                
                        </li>

                        <li>
                                
                            <div class="recruit_introduction">
                                <span>프로젝트 소개글</span>
                                <textarea name="" id="" cols="60" rows="10" placeholder="소개글을 입력하세요" disabled="disabled">${postInfo.postIntroduction }</textarea>
                            </div>
                        </li>
                    </ul>
                </div>
            </section>


                <h2>프로젝트 기본 정보</h2>
                    <section class="form_info1">
                        <ul class="info_row">
                            <li>
                                <span>모집 구분</span>
                                <div class="info">
                                    <span class="current">${postInfo.postCategory }</span>
                                    
                                </div>
                            </li>
                            
                            <li>
                                <span>모집 인원</span>
                                <div class="info">
                                    <span class="current">${postInfo.numOfPeople } 명</span>
                                    
                                </div>
                            </li>
                       
                            <li>
                                <span>진행 방식</span>
                                <div class="info">
                                    <span class="current">${postInfo.process }</span>
                                    
                                    </div>
                            </li>
                            
                            <li>
                                <span>진행 기간</span>
                                <div class="info">
                                    <span class="current">${postInfo.period } 개월</span>
                                </div>
                            </li>
                            
                            <li>
                                <span>연락 방법</span>
                                <div class="info">
                                    <span class="current">${postInfo.contactMethod }</span>
                                </div>
                            </li>
    						 
    						<li>
                                <span>시작 예정일</span>
                                <div class="expectedStartDate_wrapper">
                                    <input class="nice-select wide" type="date" name="" id="expectedStartDate" value="${postInfo.expectedStartDate }" disabled="disabled">                                
                                </div>    
                            </li>
                       
                            <li>
                                <span>기술 스택</span>
                                <div class="tech info wide">
                                
                                	<c:set var="techs" value="${postInfo.technologyCategory }" scope="request"/>
									<%
										String techTemp = (String)request.getAttribute("techs");
										String[] techCategorys = techTemp.split(",");
										
										request.setAttribute("techCategorys", techCategorys);
									%>
                                
                                    <ul class="tech_wrapper">
                                        <c:forEach var="tech" items="${techCategorys }">
                                        	<li>
                                            	<img src="../language/${tech }.svg" alt="">
                                            	<span>${tech }</span>
                                        	</li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </li>
                            
                           
                        </ul>
                    </section>
					
					<input type="hidden" id="postIdx" value="${postInfo.postIdx }">
					
                    <div>
                    	<c:if test="${postInfo.postStatus eq 'T'}">
							<c:if test="${not empty loginInfo }">
		                        <button type="button" class="btn_style" id="postApplication_add">스터디 신청</button>                   		
	                   			<button type="button" class="btn_style" id="my_introduce_btn">나의 소개글</button>
	                   		</c:if>
	                   		<c:if test="${empty loginInfo }">
		                        <button type="button" class="btn_style" id="no_login_btn">스터디 신청</button>                   		
	                   		</c:if>                    	
                   			  
                    	</c:if>
                    	<c:if test="${postInfo.postStatus eq 'F'}">
                    		<button type="button" class="btn_style">지원 마감</button>
                    	</c:if>
                    </div>       
        </form>
    </main>
   
    <script src="../js/jquery-3.6.0.min.js"></script>
    <script src="../js/jquery.nice-select.js"></script>
    <script>
        $('').niceSelect();
        ///////////////////
        $("#no_login_btn").on("click",function(){
        	alert("로그인 후 이용해주세요");
        	location.href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/login";
        });
        
        
        $("#postApplication_add").on("click",function(e){
        	
        	$.ajax({
	        	url:"http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/post_application/add",
	        	type:"GET",
	        	success:function(){
	        		let check = confirm("현재 소개글로 신청 하시겠습니까?");
	        		
	        		if(check){
						let postIdx = $("#postIdx").val();
						
						$.ajax({
							url:"http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/post/application_add",
							type:"POST",
							data:"postIdx="+postIdx,
							success:function(data){
								alert("게시글 신청에 성공했습니다");
								location.href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/home";
							},
							error:function(data){
								if(data.responseJSON.message == 'appliedMember'){
									alert("이미 신청한 게시글 입니다");
								}else if(data.responseJSON.message == 'duplicate'){
									alert("본인의 게시글은 신청할 수 없습니다");
								}else if(data.responseJSON.message == 'error'){
									alert("게시글 신청에 실패했습니다");
								}							
							}						
						});
	        		}else{
	        			alert("게시글 신청을 하지 못했습니다");
	        		}
	        	},
	        	error:function(){
	        		alert("나의 소개글을 입력해주세요");
	        		location.href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/my_introduce";
	        	}
	        });
        });
        
        $("#my_introduce_btn").on("click",function(){
        	location.href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/my_introduce";
        });
        
    </script>
</body>
</html>