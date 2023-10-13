package domain.introduce.dto;

import javax.validation.constraints.NotBlank;

public class IntroduceInfo {
	private int memberIdx;
	private String nickName;
	private String email;
	@NotBlank
	private String introduce;
	@NotBlank
	private String technologyCategory;
	@NotBlank
	private String contactMethod;
	@NotBlank
	private String role;

	public int getMemberIdx() {
		return memberIdx;
	}
	public void setMemberIdx(int memberIdx) {
		this.memberIdx = memberIdx;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getTechnologyCategory() {
		return technologyCategory;
	}
	public void setTechnologyCategory(String technologyCategory) {
		this.technologyCategory = technologyCategory;
	}
	public String getContactMethod() {
		return contactMethod;
	}
	public void setContactMethod(String contactMethod) {
		this.contactMethod = contactMethod;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
