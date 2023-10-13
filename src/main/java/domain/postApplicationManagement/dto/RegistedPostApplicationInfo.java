package domain.postApplicationManagement.dto;

import java.time.LocalDateTime;

public class RegistedPostApplicationInfo {
	private int managementIdx;
	private int postIdx;
	private int applicantMemberIdx;
	private String applicantNickName;
	private String applicationStatus;
	private LocalDateTime applicantRegistDate;
	
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
	public String getApplicantNickName() {
		return applicantNickName;
	}
	public void setApplicantNickName(String applicantNickName) {
		this.applicantNickName = applicantNickName;
	}
	public LocalDateTime getApplicantRegistDate() {
		return applicantRegistDate;
	}
	public void setApplicantRegistDate(LocalDateTime applicantRegistDate) {
		this.applicantRegistDate = applicantRegistDate;
	}
	
	
	
	

	
	
}
