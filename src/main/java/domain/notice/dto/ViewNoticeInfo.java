package domain.notice.dto;

import java.time.LocalDateTime;

public class ViewNoticeInfo extends NoticeInfo{
	private String postName;
	private String applicantNickName;
	
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public String getApplicantNickName() {
		return applicantNickName;
	}
	public void setApplicantNickName(String applicantNickName) {
		this.applicantNickName = applicantNickName;
	}
	
	
}
