package domain.member.dto;

import java.time.LocalDateTime;

public class MemberInfo {
	private int memberIdx;
	private String id;
	private String pw;
	private String name;
	private String nickName;
	private String tel;
	private String email;
	private String code;
	private int loginType;
	private LocalDateTime registDate;
	private LocalDateTime loginDate;
	private LocalDateTime changePwDate;
	private String salt;
	private String dormant;
	
	public int getMemberIdx() {
		return memberIdx;
	}
	public void setMemberIdx(int memberIdx) {
		this.memberIdx = memberIdx;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public int getLoginType() {
		return loginType;
	}
	public void setLoginType(int loginType) {
		this.loginType = loginType;
	}
	public LocalDateTime getRegistDate() {
		return registDate;
	}
	public void setRegistDate(LocalDateTime registDate) {
		this.registDate = registDate;
	}
	public LocalDateTime getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(LocalDateTime loginDate) {
		this.loginDate = loginDate;
	}
	public LocalDateTime getChangePwDate() {
		return changePwDate;
	}
	public void setChangePwDate(LocalDateTime changePwDate) {
		this.changePwDate = changePwDate;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getDormant() {
		return dormant;
	}
	public void setDormant(String dormant) {
		this.dormant = dormant;
	}
	
}
