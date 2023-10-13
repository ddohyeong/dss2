package domain.chat.dto;

import java.time.LocalDateTime;

public class ChatInfo {
	private int messageIdx;
	private int senderMemberIdx;
	private int recipientMemberIdx;
	private String message;
	private LocalDateTime registDate;
	
	public int getMessageIdx() {
		return messageIdx;
	}
	public void setMessageIdx(int messageIdx) {
		this.messageIdx = messageIdx;
	}
	public int getSenderMemberIdx() {
		return senderMemberIdx;
	}
	public void setSenderMemberIdx(int senderMemberIdx) {
		this.senderMemberIdx = senderMemberIdx;
	}
	public int getRecipientMemberIdx() {
		return recipientMemberIdx;
	}
	public void setRecipientMemberIdx(int recipientMemberIdx) {
		this.recipientMemberIdx = recipientMemberIdx;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDateTime getRegistDate() {
		return registDate;
	}
	public void setRegistDate(LocalDateTime registDate) {
		this.registDate = registDate;
	}
	
	
}
