package domain.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import domain.exception.DormantMemberException;
import domain.member.dao.MemberDao;
import domain.member.dto.LoginCommand;
import domain.member.dto.MemberInfo;
import domain.member.service.MemberService;
import domain.notice.dao.NoticeDao;
import domain.notice.dto.ViewNoticeInfo;
import domain.utils.LoginTypes;
import domain.utils.NaverApi;
import domain.utils.RandomString;
import domain.vaildator.LoginValidator;

@Controller
@RequestMapping("/member")
public class LoginController {
	@Autowired
	private MemberService memberSvc;
	@Autowired
	private MemberDao memberDao;
	@Autowired 
	private NoticeDao nDao;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new LoginValidator());
	}
	
	@PostMapping("/login")
	public String login(@Valid LoginCommand loginCommand, Errors errors,Model model,HttpSession session) {
		
		if(errors.hasErrors()) {
			return "login";
		}
		
		MemberInfo loginInfo;
		try {
			loginInfo = memberSvc.login(loginCommand);
			
			if( loginInfo != null ) {
				session.setAttribute("loginInfo", loginInfo);
				// 알림 정보가 있는지 체크 있다면 요청정보 영역에 저장
				
				List<ViewNoticeInfo> viewNoticeInfoList = nDao.selectedByMemberIdxAndNoticeCheckN(loginInfo.getMemberIdx());
				if(!viewNoticeInfoList.isEmpty()) {
					session.setAttribute("viewNoticeInfoList", viewNoticeInfoList);
				}
				
				if(loginInfo.getDormant().equals("Y")) throw new DormantMemberException();
				
//				90 일 지나면 변경 알림 
				if(!memberSvc.PwChangeNotice(loginInfo)) {
					model.addAttribute("pwChangeNotice",true);
					return "pw_change";
				}else {
					return "redirect:/view/home";
				}
			}else {
				errors.rejectValue("id", "not_registed");
				return "login";
			}
		} catch (NoSuchAlgorithmException e) {
			return "login";
		} catch(DormantMemberException e) {
			return "dormant";
		}
	}
	
	@PostMapping("/naver_login")
	public void naverLogin(@RequestParam(name = "access_token") String token, HttpSession session,HttpServletResponse response) throws NoSuchAlgorithmException, IOException {
		MemberInfo naverInfo = NaverApi.naverInfoByAccesToken(token);
		String email = naverInfo.getEmail();
		
		MemberInfo memberInfo = memberDao.selectByEmail(email);
		
		// 이메일이 등록된 아이디가 아닐 때 회원가입 진행 
		if(memberInfo == null) {
			// 아이디 와 비밀번호 임의로 생성 
	        String id = "";
	        
	        while(true) {
	        	
	        	id = RandomString.generateString();
		        
	        	if(memberDao.selectById(id) == null ) break;
	        }
	        
	        String pw = RandomString.generateString();
	        
	        
	        // 아이디 와 비밀번호 임의로 생성 -end
	        
	        String name = naverInfo.getName();
			String nickName = naverInfo.getNickName();
			
			// 중복 닉네임이면 랜덤 문자열로 생성 
			if(memberDao.selectByNickName(nickName) != null) {
				nickName = RandomString.generateString();
			}
			
			String tel = naverInfo.getTel();
			
			memberInfo = new MemberInfo();
			
			memberInfo.setId(id);
			memberInfo.setPw(pw);
			memberInfo.setName(name);
			memberInfo.setEmail(email);
			memberInfo.setNickName(nickName);
			memberInfo.setTel(tel);
			memberInfo.setLoginType(LoginTypes.NAVER);

			try {
				memberSvc.join(memberInfo);
			} catch (NoSuchAlgorithmException e) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
		}
		
		LoginCommand loginCommand = new LoginCommand();
		loginCommand.setId(memberInfo.getId());
		loginCommand.setPw(memberInfo.getPw());
		
		MemberInfo loginInfo = memberSvc.login(loginCommand);
		
		if(loginInfo != null) {
			if(loginInfo.getDormant().equals("Y")) {
				response.setContentType("application/json;charset=UTF-8");

				PrintWriter output = response.getWriter();

				JSONObject json = new JSONObject();
				json.put("view", "dormant");

				output.print(json);
				output.close();
			}else {
				session.setAttribute("loginInfo", loginInfo);
				
				List<ViewNoticeInfo> viewNoticeInfoList = nDao.selectedByMemberIdxAndNoticeCheckN(loginInfo.getMemberIdx());
				if(!viewNoticeInfoList.isEmpty()) {
					session.setAttribute("viewNoticeInfoList", viewNoticeInfoList);
				}
			}
		}else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}



}
