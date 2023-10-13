/**
 * 
 */
// 아이디 검증 (빈문자열 / 길이(5~20) / 영어 소문자와 숫자 포함 / 영어 대문자와 특수문자 포함 X)
function idValidator(id){
	if(id.trim().length == 0){
		return true;
	}else if(!lengthCheck(id)){
		return true;
	}else if(!smallEngCheck(id)){
		return true;
	}else if(!numCheck(id)){
		return true;
	}else if(largeEngCheck(id) || spcCheck(id)){
		return true;
	}
	return false;
}

// 비밀번호 검증 (빈문자열 / 길이(5~20) / 영어대소문자와 특수문자 포함)
function pwValidator(pw){
	if(pw.trim().length == 0){
		return true;
	}else if(!lengthCheck(pw)){
		return true;
	}else if(!largeEngCheck (pw)){
		return true;
	}else if(!smallEngCheck (pw)){
		return true;
	}else if(!passwordNumSpcCheck (pw)){
		return true;
	}
	return false;
}

// 이름 검증 (빈문자열 / 길이 (2~5) / 한글만 포함 )
function nameValidator(name){
	if(name.trim().length == 0){
		return true;
	}else if(!namelengthCheck(name)){
		return true;
	}else if(!korCheck(name)){
		return true;
	}
	return false;
}
// 닉네임 검증 (빈 문자열 / 길이 (2~10) / 특수문자 포함 X / 자음만 포함시 )
function nickNameValidator(nickName){
	if(nickName.trim().length == 0){
		return true;
	}else if(!nickNamelengthCheck(nickName)){
		return true;
	}else if(spcCheck(nickName)){
		return true;
	}else if(kor_consonantCheck(nickName)){
		return true;
	}
	return false;
}

// 연락처 검증 ""010-4자리-4자리" 형식 확인 
function telValidator(tel){
	if(tel.trim().length == 0){
		return true;
	}else if(!telCheck(tel)){
		return true;
	}	
	return false;
}

// 이메일 검증 (빈문자열 / 길이 (5~40) /@과 .뒤에 도메인(2~3자리))
function emailValidator(email){
	if(email.trim().length == 0){
		return true;
	}else if(!emaillengthCheck(email)){
		return true;
	}else if(!emailCheck(email)){
		return true;
	}
	return false;
}

// 영어 대소문자 포함여부 확인 검증 
function engCheck (check) {
  let reg = /(?=.*?[a-z])(?=.*?[A-Z])/;
  return reg.test(check);
}

// 영어 대문자 포함여부 확인 검증
function largeEngCheck (check) {
  let reg = /(?=.*?[A-Z])/;
  return reg.test(check);
}

// 영어 대문자 포함여부 확인 검증
function smallEngCheck (check) {
  let reg = /(?=.*?[a-z])/;
  return reg.test(check);
}

// 한글 포함여부 확인 검증
function korCheck (check) {
  let reg = /(?=.*?^[가-힣]+$)/;
  return reg.test(check);
}

// 한글 자음만 포함여부 확인 검증
function kor_consonantCheck (check) {
  let reg = /(?=.*?[ㄱ-ㅎ])/;
  return reg.test(check);
}

// 숫자 특수문자 포함 여부 확인 하는 정규식 
function passwordNumSpcCheck (pw) {
  let reg = /(?=.*?[0-9])(?=.*?[#?!@$%^&*-])/;
  return reg.test(pw);
}
// 숫자 여부 포함 확인 검증 
function numCheck (check) {
  let reg = /(?=.*?[0-9])/;
  return reg.test(check);
}

// 특수문자 포함 여부 확인 하는 정규식 
function spcCheck (check) {
  let reg = /(?=.*?[#?!@$%^&*-])/;
  return reg.test(check);
}

// 이메일 확인 하는 정규식 
function emailCheck (check) {
  let reg = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
  return reg.test(check);
}

// 아이디 / 비밀번호 길이 확인하는 정규식
function lengthCheck (check) {
  let reg = /^.{5,20}$/;
  return reg.test(check);
}

// 이름 길이 확인하는 정규식 
function namelengthCheck (name) {
  let reg = /^.{2,5}$/;
  return reg.test(name);
}

// 닉네임 길이 확인하는 정규식 
function nickNamelengthCheck (nickName) {
  let reg = /^.{2,10}$/;
  return reg.test(nickName);
}
// 이메일 길이 확인하는 정규식 
function emaillengthCheck (email) {
  let reg = /^.{5,40}$/;
  return reg.test(email);
}
// 연락처 확인하는 정규식 
function telCheck (password) {
  let reg =  /^010-?([0-9]{4})-?([0-9]{4})$/;
  return reg.test(password);
}
