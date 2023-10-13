package domain.notice.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ViewNoticeMapper implements RowMapper<ViewNoticeInfo>{

	@Override
	public ViewNoticeInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		ViewNoticeInfo nth = new ViewNoticeInfo();
		nth.setNoticeIdx(rs.getInt("noticeIdx"));
		nth.setMemberIdx(rs.getInt("memberIdx"));
		nth.setManagementIdx(rs.getInt("managementIdx"));
		nth.setNoticeCheckStatus(rs.getString("noticeCheckStatus"));
		nth.setNoticeCode(rs.getInt("noticeCode"));
		nth.setRegistDate(rs.getTimestamp("registDate").toLocalDateTime());
		
		return nth;
	}

}
