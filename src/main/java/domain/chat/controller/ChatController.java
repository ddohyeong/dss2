package domain.chat.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import domain.chat.dao.ChatDao;
import domain.chat.dto.ChatInfo;
import domain.member.dao.MemberDao;
import domain.member.dto.MemberInfo;


@Controller
public class ChatController {
	@Autowired
	private ChatDao chatDao;
	@Autowired
	private MemberDao memberDao;
	
	@GetMapping("/reload/chat")
	public void reloadChat(@RequestParam(name = "recipientMemberIdx") int recipientMemberIdx,@RequestParam(name = "messageInfoSize") int messageInfoSize,HttpSession session,HttpServletResponse response) throws IOException {
		MemberInfo loginInfo = (MemberInfo) session.getAttribute("loginInfo");
		
		int senderMemberIdx = loginInfo.getMemberIdx();
		
		ChatInfo info = new ChatInfo();
		info.setSenderMemberIdx(senderMemberIdx);
		info.setRecipientMemberIdx(recipientMemberIdx);
		
		List<ChatInfo> messageInfoList = chatDao.selectedMessageInfoBySendAndRecive(info);
		
		if(messageInfoSize != messageInfoList.size()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
	
	@PostMapping("/send_message")
	public String sendMessage(@RequestParam(name = "recipientMemberIdx") int recipientMemberIdx,@RequestParam(name = "message") String message,HttpSession session,HttpServletResponse response) {
		MemberInfo loginInfo = (MemberInfo) session.getAttribute("loginInfo");
		
		int senderMemberIdx = loginInfo.getMemberIdx();
		
		ChatInfo info = new ChatInfo();
		
		info.setSenderMemberIdx(senderMemberIdx);
		info.setRecipientMemberIdx(recipientMemberIdx);
		info.setMessage(message);
		
		if(!chatDao.insert(info)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		
		return "redirect:/view/chat?recipientMemberIdx="+recipientMemberIdx;
	}
	
	@GetMapping("/view/chat")
	public String viewChat(Model model,@RequestParam(name = "recipientMemberIdx") int recipientMemberIdx,HttpSession session,HttpServletResponse response) {
		MemberInfo loginInfo = (MemberInfo) session.getAttribute("loginInfo");
		
		int senderMemberIdx = loginInfo.getMemberIdx();
		
		MemberInfo recipientInfo = memberDao.selectMemberIdx(recipientMemberIdx);
		
		String recipientNickName ="";
		if(recipientInfo == null) {
			recipientNickName="존재하지 않는 회원";
		}else {
			recipientNickName = recipientInfo.getNickName();
		}
		
		ChatInfo info = new ChatInfo();
		info.setSenderMemberIdx(senderMemberIdx);
		info.setRecipientMemberIdx(recipientMemberIdx);
		
		List<ChatInfo> messageInfoList = chatDao.selectedMessageInfoBySendAndRecive(info);
		int messageInfoSize = 0;
		
		if(!messageInfoList.isEmpty()) {
			messageInfoSize = messageInfoList.size();	
		}
		model.addAttribute("messageInfoList", messageInfoList);
		model.addAttribute("recipientNickName", recipientNickName);
		model.addAttribute("recipientMemberIdx", recipientMemberIdx);
		model.addAttribute("messageInfoSize", messageInfoSize);
		model.addAttribute("page", "chat");
		
		return "chat";
	}


}

