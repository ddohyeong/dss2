package domain.utils;

import org.apache.commons.lang3.RandomStringUtils;

import domain.vaildator.MemberValidator;

public class CreateTemporaryPassword {
	public String createPassword() {
		MemberValidator val = new MemberValidator();
			
		String pw = "";
		while(true) {
			pw = new CreateTemporaryPassword().getRandomPassword();
			pw = pw + "!";
			boolean result = val.pwValidator(pw);
			if(!result) break;
		}
		return pw;
	}
	
	public String getRandomPassword() {
		return RandomStringUtils.randomAlphanumeric(10);
	}
}
