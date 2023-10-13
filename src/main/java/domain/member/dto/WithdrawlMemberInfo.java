package domain.member.dto;

import java.time.LocalDateTime;

public class WithdrawlMemberInfo {
	private int withdrawlIdx;
	private String id;
	private String nickName;
	private String reason;
	private String reasonText;
	private LocalDateTime registDate;
	private LocalDateTime withdrawlDate;
	
	public int getWithdrawlIdx() {
		return withdrawlIdx;
	}
	public void setWithdrawlIdx(int withdrawlIdx) {
		this.withdrawlIdx = withdrawlIdx;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getReasonText() {
		return reasonText;
	}
	public void setReasonText(String reasonText) {
		this.reasonText = reasonText;
	}
	public LocalDateTime getRegistDate() {
		return registDate;
	}
	public void setRegistDate(LocalDateTime registDate) {
		this.registDate = registDate;
	}
	public LocalDateTime getWithdrawlDate() {
		return withdrawlDate;
	}
	public void setWithdrawlDate(LocalDateTime withdrawlDate) {
		this.withdrawlDate = withdrawlDate;
	}
	
	
}
