package domain.vaildator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MemberValidator {
	// 검증이 false 로 나와야 검증을 통과
	
	public boolean idValidator(String id){
        if(id.trim().length() == 0){
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
    public boolean pwValidator(String pw){
        if(pw.trim().length() == 0){
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
    public boolean nameValidator(String name){
        if(name.trim().length() == 0){
            return true;
        }else if(!namelengthCheck(name)){
            return true;
        }else if(!korCheck(name)){
            return true;
        }
        return false;
    }
    // 닉네임 검증 (빈 문자열 / 길이 (2~10) / 특수문자 포함 X / 자음만 포함시 )
    public boolean nickNameValidator(String nickName){
        if(nickName.trim().length() == 0){
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
    public boolean telValidator(String tel){
        if(tel.trim().length() == 0){
            return true;
        }else if(!telCheck(tel)){
            return true;
        }
        return false;
    }

    // 이메일 검증 (빈문자열 / 길이 (5~40) /@과 .뒤에 도메인(2~3자리))
    public boolean emailValidator(String email){
        if(email.trim().length() == 0){
            return true;
        }else if(!emaillengthCheck(email)){
            return true;
        }else if(!emailCheck(email)){
            return true;
        }
        return false;
    }
    



    // 영어 대소문자 포함여부 확인 검증
    public boolean engCheck (String check) {
        Pattern pattern = Pattern.compile("(?=.*?[a-z])(?=.*?[A-Z])");
        Matcher matcher = pattern.matcher(check);

        return matcher.find();
    }

    // 영어 대문자 포함여부 확인 검증
    public boolean largeEngCheck (String check) {
        Pattern pattern = Pattern.compile("(?=.*?[A-Z])");
        Matcher matcher = pattern.matcher(check);

        return matcher.find();
    }

    // 영어 대문자 포함여부 확인 검증
    public boolean smallEngCheck (String check) {
        Pattern pattern = Pattern.compile("(?=.*?[a-z])");
        Matcher matcher = pattern.matcher(check);

        return matcher.find();
    }

    // 한글 포함여부 확인 검증
    public boolean korCheck(String name){

        Pattern pattern = Pattern.compile("(?=.*?^[가-힣]+$)");
        Matcher matcher = pattern.matcher(name);

        return matcher.find();
    }

    // 한글 자음만 포함여부 확인 검증
    public boolean kor_consonantCheck (String check) {
        Pattern pattern = Pattern.compile("(?=.*?[ㄱ-ㅎ])");
        Matcher matcher = pattern.matcher(check);

        return matcher.find();
    }

    // 숫자 특수문자 포함 여부 확인 하는 정규식
    public boolean passwordNumSpcCheck (String pw) {
        Pattern pattern = Pattern.compile("(?=.*?[0-9])(?=.*?[#?!@$%^&*-])");
        Matcher matcher = pattern.matcher(pw);

        return matcher.find();
    }
    // 숫자 여부 포함 확인 검증
    public boolean numCheck (String check) {
        Pattern pattern = Pattern.compile("(?=.*?[0-9])");
        Matcher matcher = pattern.matcher(check);

        return matcher.find();
    }

    // 특수문자 포함 여부 확인 하는 정규식
    public boolean spcCheck (String check) {
        Pattern pattern = Pattern.compile("(?=.*?[#?!@$%^&*-])");
        Matcher matcher = pattern.matcher(check);

        return matcher.find();
    }

    // 이메일 확인 하는 정규식
    public boolean emailCheck (String check) {
        String pattern = "^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$";
        return Pattern.matches(pattern, check);
    }

    // 아이디 / 비밀번호 길이 확인하는 정규식
    public boolean lengthCheck (String check) {
        String pattern = "^.{5,20}$";
        return Pattern.matches(pattern, check);
    }
    
    // 이름 길이 검증 확인
    public boolean namelengthCheck(String name){
        String pattern = "^.{2,5}$";
        return Pattern.matches(pattern, name);
    }

    // 닉네임 길이 확인하는 정규식
    public boolean nickNamelengthCheck (String nickName) {
        String pattern = "^.{2,10}$";
        return Pattern.matches(pattern, nickName);
    }
    // 이메일 길이 확인하는 정규식
    public boolean emaillengthCheck (String email) {
        String pattern = "^.{5,40}$";
        return Pattern.matches(pattern, email);
    }
    // 연락처 확인하는 정규식
    public boolean telCheck (String password) {
        String pattern =  "^010-?([0-9]{4})-?([0-9]{4})$";
        return Pattern.matches(pattern, password);
    }
}
