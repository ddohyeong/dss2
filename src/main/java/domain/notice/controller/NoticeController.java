package domain.notice.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import domain.member.dto.MemberInfo;
import domain.notice.dao.NoticeDao;
import domain.notice.dto.NoticeInfo;
import domain.notice.dto.ViewNoticeInfo;
import domain.notice.service.NoticeService;

@Controller
public class NoticeController {

	@Autowired
	private NoticeDao nDao;
	
	@Autowired
	private NoticeService nSvc;
	
	@GetMapping("/check/notice")
	public void checkNotice(HttpSession session,HttpServletResponse response) {
		MemberInfo loginInfo = (MemberInfo) session.getAttribute("loginInfo");
		
		if(loginInfo != null) {
			int memberIdx = loginInfo.getMemberIdx();
			
			List<ViewNoticeInfo> viewNoticeInfoList = nDao.selectedByMemberIdxAndNoticeCheckN(memberIdx);
			
			session.setAttribute("viewNoticeInfoList", viewNoticeInfoList);
		
			if(viewNoticeInfoList.isEmpty()) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
		}
		
	}
	
	@GetMapping("/notice_check_status")
	public void noticeCheckStatus(@RequestParam(name="noticeIdx") int noticeIdx,HttpSession session,HttpServletResponse response) {
		MemberInfo loginInfo = (MemberInfo) session.getAttribute("loginInfo");
		if(loginInfo != null) {
			int memberIdx = loginInfo.getMemberIdx();
			
			NoticeInfo info = new NoticeInfo();
			info.setMemberIdx(memberIdx);
			info.setNoticeIdx(noticeIdx);
			
			if(!nDao.statusYUpdate(info)) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}else {
				List<ViewNoticeInfo> viewNoticeInfoList = nDao.selectedByMemberIdxAndNoticeCheckN(loginInfo.getMemberIdx());
				
				session.setAttribute("viewNoticeInfoList", viewNoticeInfoList);
			}
		}

	}
	
	@GetMapping("/view/notice_list")
	public String noticeList(Model model,HttpSession session) {
		MemberInfo loginInfo = (MemberInfo) session.getAttribute("loginInfo");
		if(loginInfo != null) {
			int memberIdx = loginInfo.getMemberIdx();
			List<ViewNoticeInfo> viewNoticeInfoList = nSvc.getNoticeList(memberIdx);
			
			model.addAttribute("viewNoticeInfoList", viewNoticeInfoList);
		}
		model.addAttribute("page", "notice_list");
		return "notice";
	}
	
	@PostMapping("/notice_delete")
	public void noticeStatusYDelete(HttpSession session,HttpServletResponse response) {
		MemberInfo loginInfo = (MemberInfo) session.getAttribute("loginInfo");
		int memberIdx = loginInfo.getMemberIdx();

		if(!nSvc.statusYDeleteAndInsertData(memberIdx)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
	
}
