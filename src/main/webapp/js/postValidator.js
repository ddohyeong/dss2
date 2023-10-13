/**
 * 
 */
 
function postValidator(info){
	// undefinded 가 아니고 공백이고 길이가 0 이면 안됨 
	if(info == undefined || info.trim().length == 0){
		return false;		
	}else{
		return true;
	}
}


function integerValidator(info){
	// 매개변수가 숫자가 이라면 true 를 리턴 
	return !isNaN(info);
}