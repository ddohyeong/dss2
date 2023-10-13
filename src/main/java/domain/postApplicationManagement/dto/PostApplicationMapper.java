package domain.postApplicationManagement.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PostApplicationMapper implements RowMapper<PostApplicationManagementInfo>{

	@Override
	public PostApplicationManagementInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		PostApplicationManagementInfo nth = new PostApplicationManagementInfo();
		
		nth.setManagementIdx(rs.getInt("managementIdx"));
		nth.setPostIdx(rs.getInt("postIdx"));
		nth.setApplicantMemberIdx(rs.getInt("applicantMemberIdx"));
		nth.setRegistDate(rs.getTimestamp("registDate").toLocalDateTime());
		nth.setApplicationStatus(rs.getString("applicationStatus"));
		
		return nth;
	}
	
}
