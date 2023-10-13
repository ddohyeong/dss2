package domain.vaildator;

import java.time.LocalDate;

public class PostValidator {
	
	public boolean postNameValidator(String postName) {
		if(postName == null || postName.trim().length() == 0) {
			return true;
		}
		return false;
	}
	
	public boolean postIntroductionValidator(String postIntroduction) {
		return false;
	}
	public boolean postCategoryValidator(String postCategory) {
		return false;
	}
	public boolean processValidator(String process) {
		return false;
	}
	public boolean periodValidator(int period) {
		return false;
	}
	public boolean technologyValidator(String technologyCategory) {
		return false;
	}
	public boolean expectedStartDateValidator(LocalDate expectedStartDate) {
		return false;
	}
	
	public boolean contactMethodValidator(String contactMethod) {
		return false;
	}
	
	public boolean postStatusValidator(String postStatus) {
		return false;
	}
	
	public boolean numOfPeopleValidator(int numOfPeople) {
		return false;
	}
	
}
