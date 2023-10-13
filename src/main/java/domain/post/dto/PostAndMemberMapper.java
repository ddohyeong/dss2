package domain.post.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import domain.utils.Conversion;


public class PostAndMemberMapper implements RowMapper<PostAndMemberInfo>{
	private Conversion conversion;
	
	public PostAndMemberMapper(Conversion conversion) {
		this.conversion = conversion;
	}
	
	@Override
	public PostAndMemberInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		PostAndMemberInfo nth = new PostAndMemberInfo();
		nth.setMemberIdx(rs.getInt("memberIdx"));
		nth.setNickName(rs.getString("nickName"));
		nth.setPostIdx(rs.getInt("postIdx"));
		nth.setPostName(rs.getString("postName"));
		nth.setPostIntroduction(rs.getString("postIntroduction"));
		nth.setPostCategory(rs.getString("postcategory"));
		nth.setProcess(rs.getString("process"));
		nth.setPeriod(rs.getInt("period"));
		nth.setNumOfPeople(rs.getInt("numOfPeople"));
		nth.setContactMethod(rs.getString("contactMethod"));
		String newTechnologyCategory = conversion.returnTechNameByTechIdx(rs.getString("technologyCategory"));
		nth.setTechnologyCategory(newTechnologyCategory);
		nth.setExpectedStartDate(rs.getDate("expectedStartDate").toLocalDate());
		nth.setViewNum(rs.getInt("viewNum"));
		nth.setPostStatus(rs.getString("postStatus"));
		nth.setRegistDate(rs.getTimestamp("registDate").toLocalDateTime());		
		
		return nth;
	
	}

}
