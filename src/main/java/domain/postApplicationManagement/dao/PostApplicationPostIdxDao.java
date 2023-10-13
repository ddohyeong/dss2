package domain.postApplicationManagement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import domain.postApplicationManagement.dto.NoticeForPostApplicationInfo;


public class PostApplicationPostIdxDao {
	private JdbcTemplate jdbcTemplate;
	
	public PostApplicationPostIdxDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public boolean insert(NoticeForPostApplicationInfo nfInfo) {
		String sql = "INSERT INTO post_application_postidx_info(managementIdx,postIdx,applicantMemberIdx) VALUES(?,?,?)";
		
		int num = jdbcTemplate.update(sql,nfInfo.getManagementIdx(),nfInfo.getPostIdx(),nfInfo.getApplicantMemberIdx());
		
		return num > 0 ? true :false;
	}

	public NoticeForPostApplicationInfo getNoticeForPostInfoByManagementIdx(int managementIdx) {
		String sql = "SELECT * FROM post_application_postidx_info WHERE managementIdx = ?";
		
		List<NoticeForPostApplicationInfo> results = jdbcTemplate.query(sql, new RowMapper<NoticeForPostApplicationInfo>() {

			@Override
			public NoticeForPostApplicationInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				NoticeForPostApplicationInfo info = new NoticeForPostApplicationInfo();
				info.setManagementIdx(managementIdx);
				info.setPostIdx(rs.getInt("postIdx"));
				info.setApplicantMemberIdx(rs.getInt("applicantMemberIdx"));
				return info;
			}},managementIdx);
		
		return results.isEmpty() ? null : results.get(0);
		
	}

}
