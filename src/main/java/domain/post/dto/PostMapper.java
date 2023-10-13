package domain.post.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import domain.tech.dao.TechDao;
import domain.utils.Conversion;


public class PostMapper implements RowMapper<PostInfo> {
	
	private Conversion conversion;
	
	public PostMapper(Conversion conversion) {
		this.conversion = conversion;
	}
	
	@Override
	public PostInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		PostInfo nth = new PostInfo();
		nth.setMemberIdx(rs.getInt("memberIdx"));
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
