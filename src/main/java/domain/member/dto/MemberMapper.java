package domain.member.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

public class MemberMapper implements RowMapper<MemberInfo>{

	@Override
	public MemberInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		int memberIdx = rs.getInt("memberIdx");
		String id = rs.getString("id");
		String pw = rs.getString("pw");
		String name= rs.getString("name");
		String nickName= rs.getString("nickName");
		String tel= rs.getString("tel");
		String email= rs.getString("email");
		String code= rs.getString("code");
		int loginType= rs.getInt("loginType");
		Timestamp loginDateString= rs.getTimestamp("loginDate");
		LocalDateTime loginDate = null;
		if(loginDateString != null) {
			loginDate = loginDateString.toLocalDateTime();
		}
		LocalDateTime registDate= rs.getTimestamp("registDate").toLocalDateTime();
		LocalDateTime changePwDate= rs.getTimestamp("changePwDate").toLocalDateTime();
		String salt = rs.getString("salt");
		String dormant = rs.getString("dormant");
		
		MemberInfo memberInfo = new MemberInfo();
		
		memberInfo.setMemberIdx(memberIdx);
		memberInfo.setId(id);
		memberInfo.setPw(pw);
		memberInfo.setName(name);
		memberInfo.setNickName(nickName);
		memberInfo.setTel(tel);
		memberInfo.setEmail(email);
		memberInfo.setCode(code);
		memberInfo.setLoginType(loginType);
		memberInfo.setLoginDate(loginDate);
		memberInfo.setRegistDate(registDate);
		memberInfo.setChangePwDate(changePwDate);
		memberInfo.setSalt(salt);
		memberInfo.setDormant(dormant);
		
		return memberInfo;
	}

}
