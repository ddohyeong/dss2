package domain.postApplicationManagement.dto;

import java.time.LocalDateTime;

public class PostApplicationManagementInfo {
	private int managementIdx;
	private int postIdx;
	private int applicantMemberIdx;
	private String applicationStatus;
	private LocalDateTime registDate;
	
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
	public LocalDateTime getRegistDate() {
		return registDate;
	}
	public void setRegistDate(LocalDateTime registDate) {
		this.registDate = registDate;
	}
	
	
	

	
	
}
