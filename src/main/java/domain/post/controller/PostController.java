package domain.post.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import domain.member.dto.MemberInfo;
import domain.notice.dto.NoticeCodes;
import domain.post.dao.PostAndMemberDao;
import domain.post.dao.PostDao;
import domain.post.dto.PostAndMemberInfo;
import domain.post.dto.PostInfo;
import domain.post.service.PostService;
import domain.postApplicationManagement.dao.PostApplicationMangagementDao;
import domain.postApplicationManagement.dto.PostApplicationManagementInfo;
import domain.postApplicationManagement.service.PostApplicationManagementService;
import domain.tech.dao.TechDao;
import domain.tech.dto.TechInfo;
import domain.utils.Conversion;

@Controller
public class PostController {
	@Autowired
	private PostDao postDao;
	@Autowired
	private PostAndMemberDao pmDao;
	@Autowired
	private TechDao techDao;
	@Autowired
	public PostService postSvc;
	@Autowired
	public PostApplicationMangagementDao pamDao;
	@Autowired
	public PostApplicationManagementService pamSvc;
	@Autowired
	public Conversion conversion;
	
	@PostMapping("/post/delete")
	public void postDelete(@RequestParam(name="postIdx") int postIdx,HttpSession session,HttpServletResponse response) {
		
		MemberInfo loginInfo = (MemberInfo) session.getAttribute("loginInfo");
		
		PostInfo postInfo = new PostInfo();
		postInfo.setMemberIdx(loginInfo.getMemberIdx());
		postInfo.setPostIdx(postIdx);
		
		List<PostApplicationManagementInfo> managementInfoList = pamDao.selectedByPostIdx(postIdx);
		
		if(managementInfoList != null) {
			for(PostApplicationManagementInfo info : managementInfoList) {
				pamSvc.delete(info.getManagementIdx(),info.getApplicantMemberIdx(), NoticeCodes.DELETE_POST_TO_APPLICANT);
			}
		}	
		postDao.delete(postInfo);
		
		response.setStatus(HttpServletResponse.SC_OK);
	}
	
	
	
	@GetMapping("/post/detail")
	public String postDetail(Model model,@RequestParam(name = "postIdx")int postIdx,@RequestParam(name="viewNum",required = false) Integer viewNum,HttpSession session) {
		MemberInfo loginInfo = (MemberInfo) session.getAttribute("loginInfo");
		
		if(loginInfo != null) {
			
			PostInfo postInfo = new PostInfo();
			postInfo.setPostIdx(postIdx);
			postInfo.setMemberIdx(loginInfo.getMemberIdx());
			if(viewNum != null) {
				if(!postDao.viewNumCheckByMemberIdx(postInfo)) {
					postDao.insertViewNum(postInfo);
					viewNum = viewNum + 1;
					
					postInfo.setViewNum(viewNum);
					postDao.updateViewNum(postInfo);
				}
			}
			
		}
		
		PostAndMemberInfo postMemberInfo = pmDao.getPostInfo(postIdx);
		model.addAttribute("postInfo", postMemberInfo);
		model.addAttribute("page", "post_detail");
		return "post_detail";
	}
	
	
	
	@GetMapping("/view/post_project")
	public String postListByProject(@RequestParam(name="postStatus") String postStatus ,HttpSession session,HttpServletRequest request) {
		MemberInfo loginInfo = (MemberInfo) session.getAttribute("loginInfo");

		int memberIdx = 0;
		
		if (loginInfo != null) {
			memberIdx = loginInfo.getMemberIdx();
		}
		
		List<PostAndMemberInfo> postInfoList = pmDao.getPostInfoListByProject(postStatus,memberIdx);
		request.setAttribute("postInfoList", postInfoList);
		if(postStatus.equals("T")) {
			request.setAttribute("postStatus", true);
		}else {
			request.setAttribute("postStatus", false);
		}
		
		String category = request.getParameter("category");
		if(category == null) {
			category ="AllTech";
		}
		
		List<TechInfo> techInfoList = techDao.getTechByCategory(category);
		
		request.setAttribute("techInfoList", techInfoList);
		request.setAttribute("filterCategory", category);
		
		return "home";
	}
	
	@GetMapping("/view/post_study")
	public String postListByStudy(Model model,@RequestParam(name="postStatus") String postStatus ,@RequestParam(name="category") String category,HttpSession session,HttpServletRequest request) {
		MemberInfo loginInfo = (MemberInfo) session.getAttribute("loginInfo");

		int memberIdx = 0;
		
		if (loginInfo != null) {
			memberIdx = loginInfo.getMemberIdx();
		}
		
		List<PostAndMemberInfo> postInfoList = pmDao.getPostInfoListByStudy(postStatus,memberIdx);

		model.addAttribute("postInfoList", postInfoList);
		if(postStatus.equals("T")) {
			model.addAttribute("postStatus", true);
		}else {
			model.addAttribute("postStatus", false);
		}
		
		if(category == null) {
			category ="AllTech";
		}
		List<TechInfo> techInfoList = techDao.getTechByCategory(category);
		
		model.addAttribute("techInfoList", techInfoList);
		model.addAttribute("filterCategory", category);
		return "home";
	}
	
	@GetMapping("/view/post_tech")
	public String postListByTech(@RequestParam(name="postStatus") String postStatus,@RequestParam(name="techList") String techList,@RequestParam(name="category" ,required = false) String category,
			HttpSession session,Model model) {
		MemberInfo loginInfo = (MemberInfo) session.getAttribute("loginInfo");

		int memberIdx = 0;
		
		if (loginInfo != null) {
			memberIdx = loginInfo.getMemberIdx();
		}
		
		//
		String[] techLists = {};
		if(techList != "") {
			techLists = techList.split("\\|");
		}
		//
		
		String techIdxList=conversion.returnTechIdxsByTechNameList(techLists);
		
		List<PostAndMemberInfo> postInfoList = pmDao.getPostInfoListByTech(postStatus,memberIdx,techIdxList);
		
		model.addAttribute("postInfoList", postInfoList);
		if(postStatus.equals("T")) {
			model.addAttribute("postStatus", true);
		}else {
			model.addAttribute("postStatus", false);
		}
		
		if(category == null) {
			category ="AllTech";
		}
		
		List<TechInfo> techInfoList = techDao.getTechByCategory(category);
		
		model.addAttribute("techInfoList", techInfoList);
		model.addAttribute("filterCategory", category);
		
		
		model.addAttribute("techLists", techLists);
		
		return "home";
	}
	
	@GetMapping("/view/post_search")
	public String postListBySearch(@RequestParam(name="search")String search,@RequestParam(name="postStatus") String postStatus,@RequestParam(name="category",required = false) String category,
			HttpSession session,HttpServletRequest request) {
		
		MemberInfo loginInfo = (MemberInfo) session.getAttribute("loginInfo");

		int memberIdx = 0;
		
		if (loginInfo != null) {
			memberIdx = loginInfo.getMemberIdx();
		}
		
		
		List<PostAndMemberInfo> postInfoList = pmDao.getPostInfoListBySearch(search,postStatus,memberIdx);
		request.setAttribute("postInfoList", postInfoList);
		if(postStatus.equals("T")) {
			request.setAttribute("postStatus", true);
		}else {
			request.setAttribute("postStatus", false);
		}
		
		if(category == null) {
			category ="AllTech";
		}
		
		List<TechInfo> techInfoList = techDao.getTechByCategory(category);
		
		request.setAttribute("techInfoList", techInfoList);
		request.setAttribute("filterCategory", category);
		
		request.setAttribute("search", search);
		
		return "home";
	}
	
	@PostMapping("/post/update")
	public void postUpdate(@Valid PostInfo postInfo, HttpSession session, HttpServletResponse response) {
		MemberInfo loginInfo = (MemberInfo) session.getAttribute("loginInfo");
		
		
		int memberIdx = loginInfo.getMemberIdx();
		postInfo.setMemberIdx(memberIdx);
		postSvc.update(postInfo);
		
		response.setStatus(HttpServletResponse.SC_OK);
	}
}
