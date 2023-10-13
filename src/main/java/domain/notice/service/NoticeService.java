package domain.notice.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import domain.member.dao.MemberDao;
import domain.member.dto.MemberInfo;
import domain.notice.dao.NoticeDao;
import domain.notice.dto.NoticeInfo;
import domain.notice.dto.ViewNoticeInfo;
import domain.post.dao.PostNameDao;
import domain.post.dto.PostNameInfo;
import domain.postApplicationManagement.dao.PostApplicationPostIdxDao;
import domain.postApplicationManagement.dto.NoticeForPostApplicationInfo;


public class NoticeService {
	
	@Autowired
	private NoticeDao nDao;
	@Autowired
	private PostApplicationPostIdxDao papiDao;
	@Autowired
	private PostNameDao pnDao;
	@Autowired
	private MemberDao mDao;
	
	public boolean add(NoticeInfo noticeInfo){
		return nDao.insert(noticeInfo);
	}
	
	public List<ViewNoticeInfo> getNoticeList(int memberIdx){
		
		List<ViewNoticeInfo> viewNoticeInfoList = nDao.selectedByMemberIdx(memberIdx);
		
		if(!(viewNoticeInfoList == null)) {
			for(ViewNoticeInfo nth : viewNoticeInfoList) {
				int managementIdx = nth.getManagementIdx();
				
				// managementIdx 로 postIdx, applicantIdx 정보가 들어있는 NoticeForPostApplicationInfo 조회
				NoticeForPostApplicationInfo nfInfo = papiDao.getNoticeForPostInfoByManagementIdx(managementIdx);
				
				// 프로젝트 제목 구하는 코드
				int postIdx = nfInfo.getPostIdx();
				
				PostNameInfo pnInfo = pnDao.getPostNameByPostIdx(postIdx);
				
				String postName = pnInfo.getPostName();
				nth.setPostName(postName);
				
				// 닉네임을 구하는 코드
				boolean isRegistedNickName = (nth.getNoticeCode() == 2 || nth.getNoticeCode() == 3 || nth.getNoticeCode() == 5) ? true:false; 
				
				int applicantMemberIdx = 0;
				if(isRegistedNickName) {
					// 등록자의 닉네임을 보여주기 위해서
					applicantMemberIdx = pnInfo.getRegistedMemberIdx();
					
				}else {
					applicantMemberIdx =  nfInfo.getApplicantMemberIdx();
				}
				MemberInfo memberInfo = mDao.selectMemberIdx(applicantMemberIdx);
				if(memberInfo == null) {
					nth.setApplicantNickName("존재하지 않는 회원");
				}else {
					String applicantNickName = memberInfo.getNickName();
					nth.setApplicantNickName(applicantNickName);
				}
			}
		}
		return viewNoticeInfoList;
	}
	
	@Transactional(rollbackFor = {SQLException.class})
	public boolean statusYDeleteAndInsertData(int memberIdx) {
		List<ViewNoticeInfo> checkYNoticeList = nDao.selectedByMemberIdxAndNoticeCheckY(memberIdx);
	
		for(ViewNoticeInfo nth : checkYNoticeList) {
			nDao.noticeDataInsert(nth);
		}
		
		return nDao.statusYDelete(memberIdx);
	}
}
