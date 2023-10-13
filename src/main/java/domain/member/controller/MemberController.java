package domain.member.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import domain.exception.MemberValidatorException;
import domain.member.dto.MemberInfo;
import domain.member.dto.WithdrawlMemberInfo;
import domain.member.service.MemberService;
import domain.utils.LoginTypes;
import domain.vaildator.MemberInfoValidator;
import domain.vaildator.MemberValidator;


@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new MemberInfoValidator());
	}
	
	
	@PostMapping("/dormant_release")
	public void dormantRelease(@RequestParam(name="name") String name,@RequestParam(name="code") String code,@RequestParam(name="email") String email, HttpServletResponse response) {
		try {
			MemberValidator val = new MemberValidator();
			
			if(val.emailValidator(email))		throw new MemberValidatorException();
			else if(val.nameValidator(name))	throw new MemberValidatorException();
			
			MemberInfo memberInfo = new MemberInfo();
			
			memberInfo.setName(name);
			memberInfo.setEmail(email);
			memberInfo.setCode(code);
			
			if(!memberService.dormantRelease(memberInfo)) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
			
		}catch(MemberValidatorException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
	
	@GetMapping("/dormancy_check")
	public ResponseEntity<Void> dormancyCheck() {
		memberService.dormantLoginCheck();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	
	@PostMapping("/join")
	public void join(@Valid MemberInfo memberInfo,Errors errors,HttpServletResponse response) {
		if(errors.hasErrors()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		memberInfo.setLoginType(LoginTypes.BASIC);
		
		boolean isJoin;
		
		try {
			isJoin = memberService.join(memberInfo);

			if(isJoin) {
				response.setStatus(HttpServletResponse.SC_OK);
			}else {
				response.setStatus(HttpServletResponse.SC_CONFLICT);
			}
		
		} catch (NoSuchAlgorithmException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/view/home";
	}
	
	@GetMapping("/next_pw_update")
	public String nextPwUpdate(HttpSession session) {
		
		MemberInfo loginInfo = (MemberInfo) session.getAttribute("loginInfo");
		
		int memberIdx = loginInfo.getMemberIdx();
		memberService.pwChangeDatePlusTwoMonth(memberIdx);
		return "redirect:/view/home";
	}
	
	@PostMapping("/pw_update")
	public void pwUpdate(@RequestParam(name="pw") String pw,@RequestParam(name="newPw") String newPw,@RequestParam(name="newPwChk") String newPwChk,HttpSession session,HttpServletResponse response) {
		try {
			MemberInfo loginInfo = (MemberInfo)session.getAttribute("loginInfo");
			
			int memberIdx = loginInfo.getMemberIdx();
			
			MemberValidator val = new MemberValidator();
			
			if(val.pwValidator(pw))												throw new MemberValidatorException();
			else if (val.pwValidator(newPw) || !newPw.equals(newPwChk))			throw new MemberValidatorException();

			MemberInfo memberInfo = new MemberInfo();
			
			memberInfo.setMemberIdx(memberIdx);
			memberInfo.setPw(newPw);
			
			if(memberService.pwUpdate(loginInfo, memberInfo)) {
				loginInfo.setPw(newPw);
				session.setAttribute("loginInfo", loginInfo);
				response.setStatus(HttpServletResponse.SC_OK);
			}else {
				response.setStatus(HttpServletResponse.SC_CONFLICT);
			}

		} catch (MemberValidatorException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} catch (NoSuchAlgorithmException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
	
	@PostMapping("/send_code")
	public void sendCode(@RequestParam(name="name")String name , @RequestParam(name = "email") String email, HttpServletResponse response) throws IOException {
		try {
			MemberValidator val = new MemberValidator();

			if (val.nameValidator(name))				throw new MemberValidatorException();
			else if (val.emailValidator(email))			throw new MemberValidatorException();

			String code = memberService.sendCode(name, email);

			if (code != null) {
				response.setContentType("application/json;charset=UTF-8");

				PrintWriter output = response.getWriter();

				JSONObject json = new JSONObject();
				json.put("code", code);

				output.print(json);
				output.close();
			} else {
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			}
		} catch (MemberValidatorException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
	
	@PostMapping("/update")
	public void update(@RequestParam(name="name") String name,@RequestParam(name="nickName") String nickName,@RequestParam(name="tel") String tel,@RequestParam(name="email") String email, HttpSession session,HttpServletResponse response) {
		try {
			MemberInfo loginInfo = (MemberInfo)session.getAttribute("loginInfo");
			
			int memberIdx = loginInfo.getMemberIdx();
			
			String newName = name;
			String newNickName = nickName;
			String newTel = tel;
			String newEmail = email;
			
			MemberValidator val = new MemberValidator();
			
			if (val.nameValidator(newName)) 						throw new MemberValidatorException();
			else if (val.nickNameValidator(newNickName)) 			throw new MemberValidatorException();
			else if (val.telValidator(newTel)) 						throw new MemberValidatorException();
			else if (val.emailValidator(newEmail)) 					throw new MemberValidatorException();
		
			MemberInfo updateMemberInfo = new MemberInfo();
			
			updateMemberInfo.setId(loginInfo.getId());
			updateMemberInfo.setPw(loginInfo.getPw());
			updateMemberInfo.setMemberIdx(memberIdx);
			updateMemberInfo.setName(newName);
			updateMemberInfo.setNickName(newNickName);
			updateMemberInfo.setTel(newTel);
			updateMemberInfo.setEmail(newEmail);
			updateMemberInfo.setLoginType(loginInfo.getLoginType());
			
			
			if(memberService.update(loginInfo,updateMemberInfo)) {
				session.setAttribute("loginInfo", updateMemberInfo);
				response.setStatus(HttpServletResponse.SC_OK);
			}else {
				response.setStatus(HttpServletResponse.SC_CONFLICT);
			}
		}catch(MemberValidatorException e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
	
	@PostMapping("/find_id")
	public void findId(@RequestParam(name="name") String name , @RequestParam(name="tel") String tel, HttpServletResponse response) throws IOException{
		try {
			MemberValidator val = new MemberValidator();
			
			if(val.telValidator(tel)) throw new MemberValidatorException();

			MemberInfo findIdMemberInfo =memberService.findId(name, tel);
			
			if(findIdMemberInfo != null) {
				response.setContentType("application/json;charset=UTF-8");
				PrintWriter output =response.getWriter();
				JSONObject json = new JSONObject();
				json.put("id", findIdMemberInfo.getId());
				output.print(json);
				output.close();
			}else {
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			}
		}catch(MemberValidatorException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
	
	@PostMapping("/find_pw")
	public void findPw(@RequestParam(name="name") String name,@RequestParam(name="code") String code,@RequestParam(name="email") String email, HttpServletResponse response) throws IOException{
		try {
			MemberValidator val = new MemberValidator();
			
			if(val.emailValidator(email))		throw new MemberValidatorException();
			else if(val.nameValidator(name))	throw new MemberValidatorException();
			
			MemberInfo memberInfo = new MemberInfo();
			
			memberInfo.setName(name);
			memberInfo.setEmail(email);
			memberInfo.setCode(code);
			
			
			String temporaryPw = memberService.getTemporaryPw(memberInfo);
			
			if( temporaryPw != null ) {
				response.setContentType("application/json;charset=UTF-8");
				
				PrintWriter output =response.getWriter();
				
				JSONObject json = new JSONObject();
				json.put("pw", temporaryPw);
				
				output.print(json);
				output.close();
				
			}else {
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			}
		}catch(MemberValidatorException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} catch (NoSuchAlgorithmException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
	
	
	@PostMapping("/delete")
	public void delete(HttpSession session,HttpServletRequest request, HttpServletResponse response) {
		MemberInfo loginMemberInfo = (MemberInfo)session.getAttribute("loginInfo");
		
		WithdrawlMemberInfo withdrawlMemberInfo = new WithdrawlMemberInfo();
		
		String id = loginMemberInfo.getId();
		String nickName = loginMemberInfo.getNickName();
		String reason = request.getParameter("reason");
		String reasonText = request.getParameter("reasonText");
		LocalDateTime registDate = loginMemberInfo.getRegistDate();
		
		withdrawlMemberInfo.setId(id);
		withdrawlMemberInfo.setNickName(nickName);
		withdrawlMemberInfo.setReason(reason);
		withdrawlMemberInfo.setReasonText(reasonText);
		withdrawlMemberInfo.setRegistDate(registDate);
		
		if(memberService.delete(withdrawlMemberInfo,loginMemberInfo)) {
			session.invalidate();
			response.setStatus(HttpServletResponse.SC_OK);
		}else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
}

