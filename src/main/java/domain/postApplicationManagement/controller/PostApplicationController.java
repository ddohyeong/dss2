package domain.postApplicationManagement.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import domain.member.dao.MemberDao;
import domain.member.dto.MemberInfo;
import domain.notice.dto.NoticeCodes;
import domain.post.dto.PostAndMemberInfo;
import domain.post.dto.PostInfo;
import domain.post.service.PostService;
import domain.postApplicationManagement.dao.PostApplicationMangagementDao;
import domain.postApplicationManagement.dto.PostAndApplicationInfo;
import domain.postApplicationManagement.dto.PostApplicationManagementInfo;
import domain.postApplicationManagement.dto.RegistedPostApplicationInfo;
import domain.postApplicationManagement.dto.RegistedPostInfo;
import domain.postApplicationManagement.service.PostApplicationManagementService;

@Controller
public class PostApplicationController {
	
	@Autowired
	private PostApplicationManagementService pamSvc;
	@Autowired
	private PostApplicationMangagementDao pamDao;
	@Autowired
	private PostService postSvc;
	@Autowired
	private MemberDao mbDao;
	
	@PostMapping("/confirm/application_status")
	public void confirmApplicationStatus(PostApplicationManagementInfo applicationInfo, HttpServletResponse response) {
		pamSvc.updateApplicationStatus(applicationInfo);
	}
	

	@PostMapping("/post/application_delete")
	public void postApplicationDelete(@RequestParam(name="managementIdx") int managementIdx, HttpSession session,HttpServletResponse response) {
		
		MemberInfo loginInfo = (MemberInfo)session.getAttribute("loginInfo");
		
		int applicantMemberIdx = loginInfo.getMemberIdx();
		
		int noticeCode = NoticeCodes.DELETE_APPLICATION_TO_REGISTRANT;
		
		if(!pamSvc.delete(managementIdx,applicantMemberIdx,noticeCode)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
	
	@GetMapping("/view/applied_post")
	public String viewAppliedPost(Model model, HttpSession session) {
		MemberInfo loginInfo = (MemberInfo) session.getAttribute("loginInfo");
		
		if (loginInfo != null) {
			int memberIdx = loginInfo.getMemberIdx();

			List<PostApplicationManagementInfo> managementInfoList = pamDao.selectedByApplicationMemberIdx(memberIdx);
			
			List<PostAndApplicationInfo> postAndApplicationInfoList = new ArrayList<>();
			
			if (managementInfoList != null) {

				for (PostApplicationManagementInfo nth : managementInfoList) {
					
					PostAndApplicationInfo info = new PostAndApplicationInfo();
					
					int postIdx = nth.getPostIdx();
					LocalDateTime applicationRegistDate = nth.getRegistDate();
					String applicationStatus = nth.getApplicationStatus();
					int managementIdx = nth.getManagementIdx();

					PostAndMemberInfo postInfo = postSvc.getPostInfo(postIdx);
					int registedMemberIdx = postInfo.getMemberIdx();
					String postName = postInfo.getPostName();
					String postStatus = postInfo.getPostStatus();
					
					info.setManagementIdx(managementIdx);
					info.setPostIdx(postIdx);
					info.setPostName(postName);
					info.setMemberIdx(registedMemberIdx);
					info.setPostStatus(postStatus);
					info.setApplicantMemberIdx(memberIdx);
					info.setApplicationStatus(applicationStatus);
					info.setApplicationRegistDate(applicationRegistDate);
					
					postAndApplicationInfoList.add(info);
				}
			}
			model.addAttribute("postAndApplicationInfoList", postAndApplicationInfoList);
		}
		model.addAttribute("page", "applied_post");
		return "my_application_info";
	}
	
	@GetMapping("/view/registed_post")
	public String viewRegistedPost(Model model,HttpSession session) {
		MemberInfo loginInfo = (MemberInfo) session.getAttribute("loginInfo");
		
		if(loginInfo != null ) {
			int memberIdx = loginInfo.getMemberIdx();
			
			List<PostInfo> postInfoList = postSvc.getPostInfListByMemberIdx(memberIdx);

			List<RegistedPostInfo> registedPostInfoList = new ArrayList<>();

			if (postInfoList != null) {

				for (PostInfo nth : postInfoList) {

					RegistedPostInfo registPostInfo = new RegistedPostInfo();

					int postIdx = nth.getPostIdx();
					String postName = nth.getPostName();
					LocalDateTime postRegistDate = nth.getRegistDate();
					String postStatus = nth.getPostStatus();

					registPostInfo.setPostIdx(postIdx);
					registPostInfo.setPostName(postName);
					registPostInfo.setPostRegistDate(postRegistDate);
					registPostInfo.setPostStatus(postStatus);

					List<PostApplicationManagementInfo> managementInfoList = pamDao.selectedByPostIdx(postIdx);

					List<RegistedPostApplicationInfo> registedPostApplicationList = new ArrayList<>();

					if (managementInfoList != null) {
						for (PostApplicationManagementInfo pamNth : managementInfoList) {

							RegistedPostApplicationInfo appliedInfo = new RegistedPostApplicationInfo();

							int managementIdx = pamNth.getManagementIdx();
							int applicationMemberIdx = pamNth.getApplicantMemberIdx();
							String applicationStatus = pamNth.getApplicationStatus();
							LocalDateTime applicationRegistDate = pamNth.getRegistDate();

							MemberInfo memberInfo = mbDao.selectMemberIdx(applicationMemberIdx);

							String applicationNickName = memberInfo.getNickName();

							appliedInfo.setManagementIdx(managementIdx);
							appliedInfo.setApplicantMemberIdx(applicationMemberIdx);
							appliedInfo.setApplicationStatus(applicationStatus);
							appliedInfo.setApplicantRegistDate(applicationRegistDate);
							appliedInfo.setApplicantNickName(applicationNickName);

							registedPostApplicationList.add(appliedInfo);

						}
					}
					registPostInfo.setRegistedPostApplicationInfoList(registedPostApplicationList);

					registedPostInfoList.add(registPostInfo);
				}
			}
			
			model.addAttribute("registedPostInfoList", registedPostInfoList);
		}
		model.addAttribute("page", "registed_post");
		return "my_post_info";
	}
	
}
