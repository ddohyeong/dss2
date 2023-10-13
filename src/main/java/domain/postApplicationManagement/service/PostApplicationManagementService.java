package domain.postApplicationManagement.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import domain.notice.dao.NoticeDao;
import domain.notice.dto.NoticeCodes;
import domain.notice.dto.NoticeInfo;
import domain.postApplicationManagement.dao.PostApplicationMangagementDao;
import domain.postApplicationManagement.dao.PostApplicationPostIdxDao;
import domain.postApplicationManagement.dto.NoticeForPostApplicationInfo;
import domain.postApplicationManagement.dto.PostApplicationManagementInfo;


public class PostApplicationManagementService {
	
	@Autowired
	private PostApplicationMangagementDao pamDao;
	@Autowired
	private NoticeDao nDao;
	@Autowired
	private PostApplicationPostIdxDao papiDao;
	
	
	
	@Transactional(rollbackFor = {SQLException.class})
	public boolean add(PostApplicationManagementInfo applicaitonInfo, int registrantMemberIdx) {
		
		int managementIdx = pamDao.insert(applicaitonInfo);
		
		NoticeForPostApplicationInfo nfInfo = new NoticeForPostApplicationInfo();
		// 삭제 되지 않는 management 정보 저장
		nfInfo.setManagementIdx(managementIdx);
		nfInfo.setApplicantMemberIdx(applicaitonInfo.getApplicantMemberIdx());
		nfInfo.setPostIdx(applicaitonInfo.getPostIdx());
		
		papiDao.insert(nfInfo);
		
		if(managementIdx != 0) {
		
			NoticeInfo noticeInfo = new NoticeInfo();
			
			noticeInfo.setManagementIdx(managementIdx);
			noticeInfo.setMemberIdx(registrantMemberIdx);
			noticeInfo.setNoticeCode(NoticeCodes.APPLY_TO_THE_REGISTRANT);
			
			return nDao.insert(noticeInfo);
			
		}else {
			return false;
		}
	}
	
	public boolean delete(int mangementIdx, int applicationMemberIdx, int noticeCode) {
		boolean isDelete = false;
			
		PostApplicationManagementInfo info =  pamDao.getManagementInfoByManageIdx(mangementIdx);
		
		int registrantMemberIdx = pamDao.getRegistrantMemberIdxByManagementIdx(mangementIdx);
		
		if(applicationMemberIdx == info.getApplicantMemberIdx()) {
			
			NoticeInfo noticeInfo = new NoticeInfo();
			
			noticeInfo.setManagementIdx(mangementIdx);

			if(noticeCode == NoticeCodes.DELETE_POST_TO_APPLICANT) {
				// 신청자에게 게시글을 삭제를 알릴 때는 notice에 memberIdx 를 applicationIdx (신청회원 식별값)
				noticeInfo.setMemberIdx(applicationMemberIdx);
			}else {
				noticeInfo.setMemberIdx(registrantMemberIdx);
			}
			
			noticeInfo.setNoticeCode(noticeCode);
			
			// 신청자가 신청 삭제시 등록자에게 알림 
			if(nDao.insert(noticeInfo)) {
				isDelete = pamDao.delete(mangementIdx);	
			}
				
		}
		return isDelete;
	}
	
	public boolean updateApplicationStatus(PostApplicationManagementInfo applicationInfo) {
		
		boolean isUpdatedStatus = false;
		
		if(pamDao.updateStatusByManageIdxAndApplicationIdx(applicationInfo)) {
			NoticeInfo noticeInfo = new NoticeInfo();
			noticeInfo.setManagementIdx(applicationInfo.getManagementIdx());
			noticeInfo.setMemberIdx(applicationInfo.getApplicantMemberIdx());
			
			// 스트링으로 들어온 T/F 를 알맞은 알림 코드로 변경 
			int noticeCode = 0;
			if(applicationInfo.getApplicationStatus().equals("T")) {
				noticeCode = NoticeCodes.ACCEPTED_TO_APPLICANT;
			}else {
				noticeCode = NoticeCodes.REFUSAL_TO_THE_APPLICANT;
			}
			
			noticeInfo.setNoticeCode(noticeCode);
			
			
			isUpdatedStatus = nDao.insert(noticeInfo);
		}
		
		return isUpdatedStatus;
	}
}
