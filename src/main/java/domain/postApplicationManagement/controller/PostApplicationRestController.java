package domain.postApplicationManagement.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import domain.member.dto.MemberInfo;
import domain.post.dto.PostAndMemberInfo;
import domain.post.service.PostService;
import domain.postApplicationManagement.dao.PostApplicationMangagementDao;
import domain.postApplicationManagement.dto.PostApplicationManagementInfo;
import domain.postApplicationManagement.service.PostApplicationManagementService;
import domain.utils.ErrorResponse;

@RestController
public class PostApplicationRestController {
	
	@Autowired
	private PostApplicationManagementService pamSvc;
	@Autowired
	private PostApplicationMangagementDao pamDao;
	@Autowired
	private PostService postSvc;
	
	@PostMapping("/post/application_add")
	public ResponseEntity<Object> postApplicationAdd(@RequestParam(name="postIdx") int postIdx, HttpSession session,HttpServletResponse response) {
		
		PostAndMemberInfo postInfo = postSvc.getPostInfo(postIdx);
		
		int registrantMemberIdx =postInfo.getMemberIdx(); 
		
		MemberInfo loginInfo = (MemberInfo) session.getAttribute("loginInfo");
		
		int applicantMemberIdx = loginInfo.getMemberIdx();
		
		
		
		// 신청자와 게시글 등록자의 회원 식별값이 같을 경우 409 상태코드 리턴
		if(registrantMemberIdx == applicantMemberIdx) {
			return ResponseEntity
					.status(HttpServletResponse.SC_CONFLICT)
					.contentType(MediaType.APPLICATION_JSON)
					.body(new ErrorResponse("duplicate"));
		}
		PostApplicationManagementInfo applicationInfo = new PostApplicationManagementInfo();
		
		applicationInfo.setPostIdx(postIdx);
		applicationInfo.setApplicantMemberIdx(applicantMemberIdx);

		if(pamDao.selectedByPostIdxAndApplication(applicationInfo) != null) {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			return ResponseEntity
				.status(HttpServletResponse.SC_CONFLICT)
				.contentType(MediaType.APPLICATION_JSON)
				.body(new ErrorResponse("appliedMember"));
		}
		if(!pamSvc.add(applicationInfo, registrantMemberIdx)) {
			return ResponseEntity
					.status(HttpServletResponse.SC_BAD_REQUEST)
					.contentType(MediaType.APPLICATION_JSON)
					.body(new ErrorResponse("error"));
		}
		return ResponseEntity
				.status(HttpServletResponse.SC_OK)
				.contentType(MediaType.APPLICATION_JSON)
				.body(new ErrorResponse("ok"));
	
	}

}
