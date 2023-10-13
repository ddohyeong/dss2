package domain.introduce.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class IntroduceMapper implements RowMapper<IntroduceInfo>{

	@Override
	public IntroduceInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		int memberIdx = rs.getInt("memberIdx");
		String nickName = rs.getString("nickName");
		String email = rs.getString("email");
		String technologyCategory = rs.getString("technologyCategory");
		String introduce = rs.getString("introduce");
		String contactMethod = rs.getString("contactMethod");
		String role = rs.getString("role");
		
		IntroduceInfo myInfo = new IntroduceInfo();
		myInfo.setMemberIdx(memberIdx);
		myInfo.setNickName(nickName);
		myInfo.setEmail(email);
		myInfo.setTechnologyCategory(technologyCategory);
		myInfo.setIntroduce(introduce);
		myInfo.setContactMethod(contactMethod);
		myInfo.setRole(role);
		
		return myInfo;
	}

}
