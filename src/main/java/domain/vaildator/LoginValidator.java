package domain.vaildator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import domain.member.dto.LoginCommand;

public class LoginValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return LoginCommand.class.isAssignableFrom(clazz);

	}

	@Override
	public void validate(Object target, Errors errors) {
		LoginCommand loginInfo = (LoginCommand) target;
		
		MemberValidator val = new MemberValidator();
		
		String id = loginInfo.getId();
		String pw = loginInfo.getPw();

		if(id.trim().length() == 0){
			errors.rejectValue("id", "required");
		}else if(!val.lengthCheck(id)){
			errors.rejectValue("id", "lengthCheck");
        }else if(!val.smallEngCheck(id)){
        	errors.rejectValue("id", "smallEngCheck");
        }else if(!val.numCheck(id)){
        	errors.rejectValue("id", "numCheck");
        }else if(val.largeEngCheck(id) || val.spcCheck(id)){
        	errors.rejectValue("id", "largeAndSpcCheck");
        }
		
		if(pw.trim().length() == 0){
			errors.rejectValue("pw", "required");
        }else if(!val.lengthCheck(pw)){
			errors.rejectValue("pw", "lengthCheck");
        }else if(!val.largeEngCheck (pw)){
			errors.rejectValue("pw", "largeEngCheck");
        }else if(!val.smallEngCheck (pw)){
			errors.rejectValue("pw", "smallEngCheck");
        }else if(!val.passwordNumSpcCheck (pw)){
			errors.rejectValue("pw", "passwordNumSpcCheck");
        }
		
	}

}
