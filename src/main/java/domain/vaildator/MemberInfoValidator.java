package domain.vaildator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import domain.member.dto.MemberInfo;


public class MemberInfoValidator implements  Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return MemberInfo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		MemberInfo memberInfo = (MemberInfo) target;
		
		MemberValidator val = new MemberValidator();
		
		if(val.idValidator(memberInfo.getId())) {
			errors.rejectValue("id", "required");
		}
		if (val.pwValidator(memberInfo.getPw())) {
			errors.rejectValue("pw", "required");
		}
		if (val.nameValidator(memberInfo.getName())) {
			errors.rejectValue("name", "required");
		}
		if (val.nickNameValidator(memberInfo.getNickName())) {
			errors.rejectValue("nickName", "required");
		}
		if (val.telValidator(memberInfo.getTel())) {
			errors.rejectValue("tel", "required");
		}
		if (val.emailValidator(memberInfo.getEmail())) {
			errors.rejectValue("email", "required");
		}
	}

	
}
