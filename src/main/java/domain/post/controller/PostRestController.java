package domain.post.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import domain.member.dto.MemberInfo;
import domain.post.dao.PostLikeDao;
import domain.post.dto.PostInfo;
import domain.post.dto.PostLike;
import domain.post.service.PostService;

@RestController
public class PostRestController {
	@Autowired
	private PostService postSvc;
	@Autowired
	private PostLikeDao plDao;

	@PostMapping("/post/add")
	public void postAdd(@Valid PostInfo postInfo, HttpSession session) {
		MemberInfo loginInfo = (MemberInfo) session.getAttribute("loginInfo");

		if (loginInfo == null) {
			return;
		}
		int memberIdx = loginInfo.getMemberIdx();

		postInfo.setMemberIdx(memberIdx);
		postInfo.setPostStatus("T");
		
		postSvc.add(postInfo);
	}

	@GetMapping("/post/like_add")
	public void postLikeAdd(@RequestParam(name = "postIdx") int postIdx,
			@RequestParam(name = "postLikeStatus") String postLikeStatus, HttpSession session) {
		MemberInfo loginInfo = (MemberInfo) session.getAttribute("loginInfo");

		if (postLikeStatus.equals("F")) {
			postLikeStatus = "T";
		} else {
			postLikeStatus = "F";
		}
		PostLike likeInfo = new PostLike();

		likeInfo.setPostIdx(postIdx);
		likeInfo.setMemberIdx(loginInfo.getMemberIdx());
		likeInfo.setPostLikeStatus(postLikeStatus);

		if (plDao.getLikeByMemberIdx(likeInfo) != null) {
			plDao.updatelike(likeInfo);
		} else {
			plDao.insertlike(likeInfo);
		}
	}

	@PostMapping("/post/status_toggle")
	public void postStatusToggle(@RequestParam(name = "postIdx") int postIdx,
			@RequestParam(name = "postStatus") String postStatus, HttpSession session, HttpServletResponse response) {

		MemberInfo loginInfo = (MemberInfo) session.getAttribute("loginInfo");
		if (loginInfo != null) {
			int memberIdx = loginInfo.getMemberIdx();

			PostInfo postInfo = new PostInfo();

			postInfo.setMemberIdx(memberIdx);
			postInfo.setPostIdx(postIdx);
			postInfo.setPostStatus(postStatus);

			if (!postSvc.togglePostStatus(postInfo)) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
		}

	}
}
