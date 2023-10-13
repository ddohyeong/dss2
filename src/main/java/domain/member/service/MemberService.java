package domain.member.service;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import domain.member.dao.MemberDao;
import domain.member.dao.WithdrawlMemberDao;
import domain.member.dto.LoginCommand;
import domain.member.dto.MemberInfo;
import domain.member.dto.WithdrawlMemberInfo;
import domain.utils.CreateTemporaryPassword;
import domain.utils.LoginTypes;
import domain.utils.MailSend;
import domain.utils.Sha256;


public class MemberService {
	@Autowired
	private WithdrawlMemberDao withdrawlDao;
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private Sha256 sha;
	
	
	public MemberService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	
	@Transactional(rollbackFor = {SQLException.class})
	public boolean dormantRelease(MemberInfo memberInfo) {
		MemberInfo info =memberDao.selectedByEmailAndCode(memberInfo);
		boolean result = false;
		if(info != null) {
			result= memberDao.updateDormant(info.getMemberIdx(),"N");
		}
		return result;
	}
	
	@Transactional(rollbackFor = {SQLException.class})
	public void dormantLoginCheck() {
		List<MemberInfo> memberInfoList = memberDao.getMemberInfoListByDormant();
		LocalDateTime nowDate = LocalDateTime.now();
		if(memberInfoList != null) {
			for(MemberInfo nth : memberInfoList) {
				LocalDateTime nthLoginDate = nth.getLoginDate();

				if(nthLoginDate!= null) {
					Period diff = Period.between(nthLoginDate.toLocalDate(),nowDate.toLocalDate());
					if(diff.getMonths() > 5) {
						memberDao.updateDormant(nth.getMemberIdx(),"Y");
					}
				}
			}
		}
	}
	
	@Transactional(rollbackFor = {SQLException.class})
	public boolean pwChangeDatePlusTwoMonth(int memberIdx) {
		MemberInfo memberInfo = memberDao.selectMemberIdx(memberIdx);
		
		LocalDateTime changePwDate = memberInfo.getChangePwDate();
		changePwDate = changePwDate.plusMonths(2);
		
		return memberDao.updatePwChangeDate(memberIdx, changePwDate.toString());
	}
	
	
	public boolean PwChangeNotice(MemberInfo memberInfo) {
		
		MemberInfo selectedMemberInfo = memberDao.selectMemberIdx(memberInfo.getMemberIdx());
		LocalDateTime changePwDateTime = selectedMemberInfo.getChangePwDate();
		LocalDateTime nowDate = LocalDateTime.now();
		
		long diff = Duration.between(changePwDateTime, nowDate).toDays();
		
		if(diff<90) {
			return true;
		}else {
			return false;
		}
	
	}		
	
	@Transactional(rollbackFor = {SQLException.class})
	public String getTemporaryPw(MemberInfo memberInfo) throws NoSuchAlgorithmException {
		
		MemberInfo info = memberDao.selectedByEmailAndCode(memberInfo);
		
		String temporaryPw = null;
		if(info != null ) {
			CreateTemporaryPassword ctp = new CreateTemporaryPassword();
			temporaryPw=  ctp.createPassword();
			
			String encrypPw = sha.encrypt(temporaryPw, info.getSalt());
			
			info.setPw(encrypPw);
			memberDao.updatePw(info);
		}
		return temporaryPw;
	}
	
	public String sendCode(String name, String email) {
		MemberInfo memberInfo = memberDao.selectByEmail(email);
		
		if(memberInfo == null) {
			return null;
		}
		
		String code = String.valueOf((int) (Math.random() * 1000000));
		// code 가 6자리가 아닐때 나머지 자리에 0을 붙임 
		if(code.length() != 6){
            for(int i =code.length(); i<6; i++){
            	code = code + "0";
            }
        }
		
		if (name.equals(memberInfo.getName()) && memberDao.updateCodeByEmail(code, email)) {
			MailSend mailSend = new MailSend();
			mailSend.sendCode(code, email);
			return code;
		} else {
			return null;
		}
	}

	public MemberInfo findId(String name, String tel) {
		MemberInfo memberInfo = memberDao.selectByTel(tel);

		if(memberInfo != null) {
			if (memberInfo.getName().equals(name)) {
				return memberInfo;
			}
		}
		return null;
	}
	
	public boolean join(MemberInfo memberInfo) throws NoSuchAlgorithmException {

		String salt = sha.getSalt();
		
		String encryptPw = sha.encrypt(memberInfo.getPw(), salt);
		
		memberInfo.setPw(encryptPw);
		memberInfo.setSalt(salt);
		
		boolean isSelectById = memberDao.selectById(memberInfo.getId()) != null;
		boolean isSelectByTel = memberDao.selectByTel(memberInfo.getTel()) != null;
		boolean isSelectByNickName = memberDao.selectByNickName(memberInfo.getNickName()) != null;
		boolean isSelectByEmail = memberDao.selectByEmail(memberInfo.getEmail()) != null;

		// 아이디, 연락처, 닉네임, 이메일 중 하나라도 이미 사용중이면 false 리턴
		if (isSelectById || isSelectByTel || isSelectByNickName || isSelectByEmail) {
			return false;
		} else {
			return memberDao.insert(memberInfo);
		}

	}

	@Transactional
	public MemberInfo login(LoginCommand loginCommand) throws NoSuchAlgorithmException {
		
		MemberInfo info = memberDao.selectById(loginCommand.getId());
		
		if(info == null) return null;
		
		String salt = info.getSalt();

		if(info.getLoginType() == LoginTypes.BASIC) {
			String encryptPw = sha.encrypt(loginCommand.getPw(), salt);
			
			loginCommand.setPw(encryptPw);
		}
		MemberInfo loginMemberInfo = memberDao.selectByIdAndPw(loginCommand);

		if (loginMemberInfo != null && memberDao.updateLoginDate(loginMemberInfo.getId())) {
			return loginMemberInfo;
		} else {
			return null;
		}
	}

	public boolean update(MemberInfo loginInfo, MemberInfo updateMemberInfo) {

		String oldNickName = loginInfo.getNickName();
		String oldTel = loginInfo.getTel();
		String oldEmail = loginInfo.getEmail();

		String newNickName = updateMemberInfo.getNickName();
		String newTel = updateMemberInfo.getTel();
		String newEmail = updateMemberInfo.getEmail();

		if (!oldNickName.equals(newNickName) && memberDao.selectByNickName(newNickName) != null) {
			return false;
		} else if (!oldTel.equals(newTel) && memberDao.selectByTel(newTel) != null) {
			return false;
		} else if (!oldEmail.equals(newEmail) && memberDao.selectByEmail(newEmail) != null) {
			return false;
		}

		return memberDao.update(updateMemberInfo);
	}

	public boolean pwUpdate(MemberInfo oldMemberInfo, MemberInfo memberInfo) throws NoSuchAlgorithmException {
		String oldPw = oldMemberInfo.getPw();

		String newPw = memberInfo.getPw();

		if (oldPw.equals(newPw)) {
			return false;
		}
		
		MemberInfo selectInfo = memberDao.selectMemberIdx(oldMemberInfo.getMemberIdx());
		String salt = selectInfo.getSalt();
		
		String encryptPw = sha.encrypt(newPw, salt);
		memberInfo.setPw(encryptPw);
		
		return memberDao.updatePw(memberInfo);
	}

	public boolean delete(WithdrawlMemberInfo withdrawlMemberInfo, MemberInfo memberInfo) {


		if (memberDao.delete(memberInfo)) {
			return withdrawlDao.insert(withdrawlMemberInfo);
		} else {
			return false;
		}
	}
}
