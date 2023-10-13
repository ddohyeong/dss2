package domain.view;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import domain.introduce.dao.IntroduceDao;
import domain.introduce.dto.IntroduceInfo;
import domain.member.dto.LoginCommand;
import domain.member.dto.MemberInfo;
import domain.post.dao.PostAndMemberDao;
import domain.post.dto.PostAndMemberInfo;
import domain.post.service.PostService;
import domain.tech.dao.TechDao;
import domain.tech.dto.TechInfo;


@Controller
@RequestMapping("/view")
public class ViewController {
	@Autowired
	private TechDao techDao;
	@Autowired
	private PostService postSvc;
	@Autowired
	private IntroduceDao itdDao;
	@Autowired
	private PostAndMemberDao pamDao;
	
	@GetMapping("/join")
	public String join(Model model) {
		MemberInfo memberInfo = new MemberInfo();
		model.addAttribute("memberInfo", memberInfo);
		return "join";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		LoginCommand loginCommand = new LoginCommand();
		model.addAttribute("loginCommand", loginCommand);
		return "login";
	}
	
	@GetMapping("/post_form")
	public String postForm(Model model) {
		model.addAttribute("page", "post_form");
		return "post_form";
	}
	@GetMapping("/my_page")
	public String myPage(Model model) {
		model.addAttribute("page", "my_page");
		return "my_page";
	}
	@GetMapping("/pw_change")
	public String pwChange(Model model) {
		model.addAttribute("page", "pw_change");
		return "pw_change";
	}
	
	
	@GetMapping("/home")
	public String viewHome(HttpSession session, HttpServletRequest request) {
		String postStatus = request.getParameter("postStatus");
		if (postStatus == null) {
			postStatus = "T";
		}

		MemberInfo loginInfo = (MemberInfo) session.getAttribute("loginInfo");

		int memberIdx = 0;
		
		if (loginInfo != null) {
			memberIdx = loginInfo.getMemberIdx();
		}

		List<PostAndMemberInfo> postInfoList = postSvc.getPostInfoList(postStatus,memberIdx);
		request.setAttribute("postInfoList", postInfoList);

		if (postStatus.equals("T")) {
			request.setAttribute("postStatus", true);
		} else {
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
	
	@GetMapping("/tech_category")
	public void viewTechCategory(@RequestParam(name = "category") String category,HttpServletRequest request) {
		List<TechInfo> info = techDao.getTechByCategory(category);
		
		request.setAttribute("techInfo", info);
	}
	
	@GetMapping("/post_add")
	public void viewPostAdd(HttpSession session, HttpServletResponse response) {
		MemberInfo loginInfo = (MemberInfo) session.getAttribute("loginInfo");
		
		if(loginInfo == null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}	
	}

	@GetMapping("/post_application/add")
	public void viewPostApplicationAdd(HttpSession session, HttpServletResponse response) {
		MemberInfo loginInfo = (MemberInfo) session.getAttribute("loginInfo");
		
		int memberIdx = loginInfo.getMemberIdx();
		IntroduceInfo myInfo = itdDao.getMyInfo(memberIdx);
		
		if(myInfo == null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}
	
	@GetMapping("/post_update")
	public String viewPostUpdate(Model model,@RequestParam(name="postIdx") int postIdx,HttpSession session,HttpServletResponse response) {
		MemberInfo loginInfo = (MemberInfo) session.getAttribute("loginInfo");
		
		PostAndMemberInfo postInfo = pamDao.getPostInfo(postIdx);
		
		int postMemberIdx = postInfo.getMemberIdx();
		
		int memberIdx = loginInfo.getMemberIdx(); 
		
		
		if(postMemberIdx == memberIdx) {
			model.addAttribute("postInfo", postInfo);
			return "post_update";
		}else {
			return "redirect:/view/registed_post";
		}
	
	}
	
	@PostMapping("/introduce_update")
	public String viewMyIntroduceUpdate(Model model,HttpSession session) {
		
		MemberInfo loginInfo = (MemberInfo) session.getAttribute("loginInfo");
		
		int memberIdx = loginInfo.getMemberIdx();
		
		
		IntroduceInfo myInfo = itdDao.getMyInfo(memberIdx);
		
		model.addAttribute("myInfo", myInfo);
		
		return "introduce_form";
	}
		
}
