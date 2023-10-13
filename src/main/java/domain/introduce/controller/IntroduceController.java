package domain.introduce.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import domain.introduce.dao.IntroduceDao;
import domain.introduce.dto.IntroduceInfo;
import domain.member.dto.MemberInfo;

@Controller
public class IntroduceController {
	@Autowired
	private IntroduceDao introduceDao;
	
	@PostMapping("/my_info/add")
	public void introduceInfoAdd(@Valid IntroduceInfo introduceInfo,HttpSession session) {
		
		MemberInfo loginInfo = (MemberInfo) session.getAttribute("loginInfo");
		
		int memberIdx = loginInfo.getMemberIdx();
		String nickName = loginInfo.getNickName();
		String email = loginInfo.getEmail();
		
		introduceInfo.setMemberIdx(memberIdx);
		introduceInfo.setNickName(nickName);
		introduceInfo.setEmail(email);
		
		introduceDao.insert(introduceInfo);
		
	}
	
	@GetMapping("/view/my_introduce")
	public String introduceInfoDetail(Model model,HttpSession session) throws ServletException, IOException {
		
		MemberInfo loginInfo = (MemberInfo) session.getAttribute("loginInfo");
		
		int memberIdx = loginInfo.getMemberIdx();
		
		IntroduceInfo myInfo = introduceDao.getMyInfo(memberIdx);
		
		model.addAttribute("page", "introduce_form");
		if(myInfo == null) {
			return "introduce_form";
		}else {
			model.addAttribute("myInfo", myInfo);
			
			return "my_introduce";
		}
	}
	
	@PostMapping("/my_info/update")
	public void introduceInfoUpdate(@Valid IntroduceInfo introduceInfo,HttpSession session) {
		
		MemberInfo loginInfo = (MemberInfo) session.getAttribute("loginInfo");
		
		int memberIdx = loginInfo.getMemberIdx();
		String nickName = loginInfo.getNickName();
		String email = loginInfo.getEmail();
		
		introduceInfo.setMemberIdx(memberIdx);
		introduceInfo.setNickName(nickName);
		introduceInfo.setEmail(email);
		
		introduceDao.update(introduceInfo);
	}
	
	@GetMapping("/view/introduce_info")
	public String viewIntroduceInfo(@RequestParam(value = "memberIdx") int memberIdx , Model model) {
		IntroduceInfo myInfo = introduceDao.getMyInfo(memberIdx);
		
		if(myInfo == null) {	
			return "redirect:/view/registed_post";
		}else {
			model.addAttribute("myInfo", myInfo);
			return "member_introduce";
		}
	}
	
}
