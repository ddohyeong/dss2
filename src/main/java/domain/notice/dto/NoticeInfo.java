package domain.notice.dto;

import java.time.LocalDateTime;

public class NoticeInfo {
	private int noticeIdx;
	private int memberIdx;
	private int managementIdx;
	private String noticeCheckStatus;
	private LocalDateTime registDate;
	private int noticeCode;
	
	public int getNoticeIdx() {
		return noticeIdx;
	}
	public void setNoticeIdx(int noticeIdx) {
		this.noticeIdx = noticeIdx;
	}
	public int getMemberIdx() {
		return memberIdx;
	}
	public void setMemberIdx(int memberIdx) {
		this.memberIdx = memberIdx;
	}
	public int getManagementIdx() {
		return managementIdx;
	}
	public void setManagementIdx(int managementIdx) {
		this.managementIdx = managementIdx;
	}
	public String getNoticeCheckStatus() {
		return noticeCheckStatus;
	}
	public void setNoticeCheckStatus(String noticeCheckStatus) {
		this.noticeCheckStatus = noticeCheckStatus;
	}
	public LocalDateTime getRegistDate() {
		return registDate;
	}
	public void setRegistDate(LocalDateTime registDate) {
		this.registDate = registDate;
	}
	public int getNoticeCode() {
		return noticeCode;
	}
	public void setNoticeCode(int noticeCode) {
		this.noticeCode = noticeCode;
	}
	
	

	
}
