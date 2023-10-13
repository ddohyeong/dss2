package domain.notice.dto;

public class NoticeCodes {
	public static int APPLY_TO_THE_REGISTRANT = 1;
	public static int ACCEPTED_TO_APPLICANT = 2;
	public static int REFUSAL_TO_THE_APPLICANT = 3;
	public static int DELETE_APPLICATION_TO_REGISTRANT = 4;
	public static int DELETE_POST_TO_APPLICANT = 5;
}

//알림 코드
//코드 1: 등록자에게 신청 알림
//코드 2: 신청자에게 수락 알림
//코드 3: 신청자에게 거절 알림
//코드 4: 등록자에게 신청자가 신청  삭제 알림
//코드 5: 신청자에게 게시글 삭제 알림