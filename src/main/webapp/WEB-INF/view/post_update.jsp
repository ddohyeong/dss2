<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시글 수정</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <link rel="icon" href="../logo/favicon.ico/ms-icon-144x144.png" sizes="144x144">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css">
    <link rel="stylesheet" href="../css/post_form.css">
    <link href="../css/nice-select.css" rel="stylesheet">
	<link href="../css/magicsuggest.css" rel="stylesheet"/>

</head>
<body>
    <%@ include file="../includes/header.jsp" %>
	<%@ include file="../includes/banner.jsp"%>

	<main>
        <form action="">
            <section>
                <h2>프로젝트 기본 정보</h2>
                <div>
                    <section class="form_info1">
                        <ul class="info_row">
                            <li>
                                <input type="hidden" id="postIdx" value="${postInfo.postIdx }">
                                <input type="hidden" id="postStatus" value="${postInfo.postStatus }">
                                <span>모집 구분</span>
                                <div class="nice-select wide">
                                    <span class="current" id="postCategory">${postInfo.postCategory }</span>
                                    <ul class="list">
                                        <li class="option">스터디</li>
                                        <li class="option">프로젝트</li>
                                    </ul>
                                </div>
                            </li>
                            
                            <li>
                                <span>모집 인원</span>
                                <div class="nice-select wide">
                                    <span class="current" id="numOfPeople">${postInfo.numOfPeople }</span> 명
                                    <ul class="list">
                                        <li class="option">1</li>
                                        <li class="option">2</li>
                                        <li class="option">3</li>
                                        <li class="option">4</li>
                                        <li class="option">5</li>
                                        <li class="option">6</li>
                                        <li class="option">7</li>
                                        <li class="option">8</li>
                                        <li class="option">9</li>
                                        <li class="option">10</li>
                                    </ul>
                                </div>
                            </li>
                        </ul>
                        <ul class="info_row">
                            <li>
                                <span>진행 방식</span>
                                <div class="nice-select wide">
                                    <span class="current" id="process">${postInfo.process }</span>
                                    <ul class="list">
                                        <li class="option">온라인</li>
                                        <li class="option">오프라인</li>
                                    </ul>
                                    </div>
                            </li>
                            
                            <li>
                                <span>진행기간</span>
                                <div class="nice-select wide">
                                    <span class="current" id="period">${postInfo.period }</span> 개월
                                    <ul class="list">
                                        <li class="option" >1</li>
                                        <li class="option" >2</li>
                                        <li class="option" >3</li>
                                        <li class="option" >4</li>
                                        <li class="option" >5</li>
                                        <li class="option" >6</li>
                                        <li class="option" >7</li>
                                        <li class="option" >8</li>
                                        <li class="option" >9</li>
                                        <li class="option" >10</li>
                                   </ul>
                                    </div>
                            </li>
    
                        </ul>
                        <ul class="info_row">
                            <li>
                                <span>연락 방법</span>
                                <div class="nice-select wide">
                                    <span class="current" id="contactMethod">${postInfo.contactMethod }</span>
                                    <ul class="list">
                                    	<li class="option">오프라인</li>
                                        <li class="option">카카오톡</li>
                                        <li class="option">ZOOM</li>
                   	                    <li class="option">디스코드</li>
                                        <li class="option">전화</li>
                                       <li class="option">다른SNS</li>
                                    </ul>
                                </div>
                            </li>
                            <li>
                                <span>시작 예정일</span>
                                <div class="expectedStartDate_wrapper">
                                    <input class="nice-select wide" type="date" name="" id="expectedStartDate" value="${postInfo.expectedStartDate }">                                
                                </div>    
                            </li>
    
                        </ul>
                        
                        <ul class="info_row">
                            <li>
                                <span>기술 스택</span>
                                <div class="nice-select wide tech_wrapper" id="technologyCategory">
								
								</div>
                            </li>
                            
                        </ul>
                    </section>
                    
                    <section class="form_info2">
                        <h2>게시글 소개</h2>
                        <div class="recruitment_wrapper">
                            <ul>
                                <li>
                                    <div class="recruit_name">
                                        <span>게시글 제목</span>
                                        <input class="input_nice wide" id="postName" type="text" value="${postInfo.postName }">
                                    </div>
                                    
                                </li>
    
                                <li>
                                    
                                    <div class="recruit_introduction">
                                        <span>게시글 소개</span>
                                        <textarea name="" id="postIntroduction" cols="60" rows="10" placeholder="소개글을 입력하세요">${postInfo.postIntroduction }</textarea>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </section>
    
                </div>
            </section>

            <div>
                <button type="submit" class="btn_style" id="post_update">게시글 수정</button>
            </div>
        </form>
        


    </main>
   
    <script src="../js/jquery-3.6.0.min.js"></script>
    <script src="../js/jquery.nice-select.js"></script>
	<script src="../js/magicsuggest.js"></script>
    <script src="../js/function.js"></script>
	 <script src="../js/postValidator.js"></script>
    <script>
		 // 셀렉함수
		$('').niceSelect();
    
		 // 기술 태그 입력란
		let ms1 = $('#technologyCategory').magicSuggest({
	        data: ["aws","c","django","firebase","git","go","java","javascript","kotlin","mongodb","mysql","oracle","python","react","spring","typescript","unity","vue"]	
	      });
		
		$("#technologyCategory input[type=text]").attr("disabled",true);
		$("#technologyCategory input[type=text]").attr("placeholder","기술 스택");
		
		// 기술 스택들을 문자열로 반환 해주는 메서드
		
		$("#post_update").on("click",function(e){
			e.preventDefault();

			let check = confirm("게시글을 수정하시겠습니까?");
			if(check){
				let postIdx = $("#postIdx").val();
				let postName = $("#postName").val();
				let postIntroduction = $("#postIntroduction").val();
				let postCategory = $("#postCategory").text();
				let process = $("#process").text();
				let period = $("#period").text();
				let numOfPeople=$("#numOfPeople").text();
				let contactMethod = $("#contactMethod").text();
				let technologyCategory = technologyCategoryValue();
				let expectedStartDate = $("#expectedStartDate").val();
				let postStatus = $("#postStatus").val();
				
				if(!postValidator(postName)){
					alert("제목을 입력해주세요");
					return false;
				}else if(!postValidator(postIntroduction)){
					alert("소개글을 입력해주세요");
					return false;
				}else if(!postValidator(postCategory)){
					alert("모집구분을 입력해주세요");
					return false;
				}else if(!postValidator(process)){
					alert("진행방식을 입력해주세요");
					return false;
				}else if(!postValidator(period) || !integerValidator(period)){
					alert("진행 기간을 입력해주세요");
					return false;
				}else if(!postValidator(numOfPeople) || !integerValidator(numOfPeople)){
					alert("모집 인원 입력해주세요");
					return false;
				}else if(!postValidator(contactMethod)){
					alert("연락 방법 입력해주세요");
					return false;
				}else if(!postValidator(technologyCategory)){
					alert("기술 스택을 입력해주세요");
					return false;
				}else if(!postValidator(expectedStartDate)){
					alert("시작 날짜를 입력해주세요");
					return false;
				}
				
				
				
				$.ajax({
					url:"http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/post/update",
					type:"POST",
					data:"postIdx="+postIdx+"&postName="+postName+"&postIntroduction="+postIntroduction+"&postCategory="+postCategory+"&process="+process+"&period="+period
					+"&numOfPeople="+numOfPeople+"&contactMethod="+contactMethod+"&technologyCategory="+technologyCategory+"&expectedStartDate="+expectedStartDate+"&postStatus="+postStatus,
					success:function(){
						alert("수정이 완료되었습니다");
						location.href="http://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com/dss/view/registed_post";
					},
					error:function(){
						alert("수정을 실패했습니다");
					}
				});	
			}
		});
		
    </script>
</body>
</html>