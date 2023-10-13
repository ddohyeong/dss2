package domain.postApplicationManagement.dto;

import java.time.LocalDateTime;

public class PostAndApplicationInfo {
	private int managementIdx;
	private int postIdx;
	private String postName;
	private String postStatus;
	private int applicantMemberIdx;
	private String applicationStatus;
	private LocalDateTime applicationRegistDate;
	private int memberIdx;
	private int viewNum;
	
	public int getManagementIdx() {
		return managementIdx;
	}
	public void setManagementIdx(int managementIdx) {
		this.managementIdx = managementIdx;
	}
	public int getPostIdx() {
		return postIdx;
	}
	public void setPostIdx(int postIdx) {
		this.postIdx = postIdx;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public String getPostStatus() {
		return postStatus;
	}
	public void setPostStatus(String postStatus) {
		this.postStatus = postStatus;
	}
	public int getApplicantMemberIdx() {
		return applicantMemberIdx;
	}
	public void setApplicantMemberIdx(int applicantMemberIdx) {
		this.applicantMemberIdx = applicantMemberIdx;
	}
	public String getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	public LocalDateTime getApplicationRegistDate() {
		return applicationRegistDate;
	}
	public void setApplicationRegistDate(LocalDateTime applicationRegistDate) {
		this.applicationRegistDate = applicationRegistDate;
	}
	public int getMemberIdx() {
		return memberIdx;
	}
	public void setMemberIdx(int memberIdx) {
		this.memberIdx = memberIdx;
	}
	public int getViewNum() {
		return viewNum;
	}
	public void setViewNum(int viewNum) {
		this.viewNum = viewNum;
	}
	
}
