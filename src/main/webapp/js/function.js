/**
 * 
 */


// myInfo 검증  
function myInfoValidator(info){
	// undefinded 가 아니고 공백이고 길이가 0 이면 안됨 
	if(info == undefined || info.trim().length == 0){
		return false;		
	}else{
		return true;
	}
}
 
 // 기술 스택들을 문자열로 반환 해주는 메서드
 function technologyCategoryValue(){
	let cnt = $(".ms-sel-item").length;
        technology = "";
        for(let i= 1; i<=cnt; i++){
            let tech = $(".ms-sel-item:nth-child("+i+")").text();
            if(i < cnt){
                technology = technology + tech+",";
            }else{
                technology = technology + tech;
            }
        }
        return technology;	
} 
 
function isPostStatus(){
	if($(".status_check").prop("checked")){
		return "T";
	}else{
		return "F";
	}
}


function getTechList() {
	let techCnt = $(".tech_list li").length;

	let techList = "";

	for(let i =0; i<techCnt; i++){
		if(i>0){
			techList = techList +'|'+ $(".tech span").eq(i).text();
		}else{
			techList = techList + $(".tech span").eq(i).text();	
		}
	}
	
	return techList;
}