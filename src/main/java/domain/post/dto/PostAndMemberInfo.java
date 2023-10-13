package domain.post.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PostAndMemberInfo {
	private int postIdx;
	private int memberIdx;
	private String nickName;
	private String postName;
	private String postIntroduction;
	private String postCategory;
	private String process;
	private int period;
	private int numOfPeople;
	private String technologyCategory;
	private LocalDate expectedStartDate;
	private String contactMethod;
	private String postStatus;
	private String postLikeStatus;
	private int viewNum;
	private LocalDateTime registDate;
	
	public int getPostIdx() {
		return postIdx;
	}
	public void setPostIdx(int postIdx) {
		this.postIdx = postIdx;
	}
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
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public String getPostIntroduction() {
		return postIntroduction;
	}
	public void setPostIntroduction(String postIntroduction) {
		this.postIntroduction = postIntroduction;
	}
	public String getPostCategory() {
		return postCategory;
	}
	public void setPostCategory(String postCategory) {
		this.postCategory = postCategory;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public int getNumOfPeople() {
		return numOfPeople;
	}
	public void setNumOfPeople(int numOfPeople) {
		this.numOfPeople = numOfPeople;
	}
	public String getTechnologyCategory() {
		return technologyCategory;
	}
	public void setTechnologyCategory(String technologyCategory) {
		this.technologyCategory = technologyCategory;
	}
	public LocalDate getExpectedStartDate() {
		return expectedStartDate;
	}
	public void setExpectedStartDate(LocalDate expectedStartDate) {
		this.expectedStartDate = expectedStartDate;
	}
	public String getContactMethod() {
		return contactMethod;
	}
	public void setContactMethod(String contactMethod) {
		this.contactMethod = contactMethod;
	}
	public String getPostStatus() {
		return postStatus;
	}
	public void setPostStatus(String postStatus) {
		this.postStatus = postStatus;
	}
	public int getViewNum() {
		return viewNum;
	}
	public void setViewNum(int viewNum) {
		this.viewNum = viewNum;
	}
	public LocalDateTime getRegistDate() {
		return registDate;
	}
	public void setRegistDate(LocalDateTime registDate) {
		this.registDate = registDate;
	}
	public String getPostLikeStatus() {
		return postLikeStatus;
	}
	public void setPostLikeStatus(String postLikeStatus) {
		this.postLikeStatus = postLikeStatus;
	}
	
}
