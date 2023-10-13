package domain.postApplicationManagement.dto;

public class NoticeForPostApplicationInfo {
	private int managementIdx;
	private int postIdx;
	private int applicantMemberIdx;
	
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
	
	
}
