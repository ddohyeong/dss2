package domain.postApplicationManagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import domain.postApplicationManagement.dto.PostApplicationManagementInfo;
import domain.postApplicationManagement.dto.PostApplicationMapper;


public class PostApplicationMangagementDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public PostApplicationMangagementDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public PostApplicationManagementInfo selectedByPostIdxAndApplication(PostApplicationManagementInfo applicaitonInfo) {
		String sql = "SELECT * FROM post_application_management_info WHERE postIdx = ? AND applicantMemberIdx = ?";
		
		List<PostApplicationManagementInfo> results = jdbcTemplate.query(sql, new PostApplicationMapper(), applicaitonInfo.getPostIdx(),applicaitonInfo.getApplicantMemberIdx());
		
		return results.isEmpty() ? null : results.get(0);
	}
	
	public boolean delete(int managementIdx) {
		String sql = "DELETE FROM post_application_management_info WHERE managementIdx = ?";

		int num = jdbcTemplate.update(sql,managementIdx);
		
		return num > 0 ? true : false;
	}

	public int insert(PostApplicationManagementInfo applicationInfo) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String sql = "INSERT INTO post_application_management_info(postIdx,applicantMemberIdx,registDate) VALUES(?,?,?)";
				
				String[] columnNames = {"managementIdx"};

				PreparedStatement pstmt = con.prepareStatement(sql, columnNames);
				pstmt.setInt(1, applicationInfo.getPostIdx());
				pstmt.setInt(2, applicationInfo.getApplicantMemberIdx());
				pstmt.setString(3, LocalDateTime.now().toString());			
				
				return pstmt;
			}
		},keyHolder);
		
		Number keyValue =  keyHolder.getKey();
		
		int managementIdx = keyValue.intValue();
		
		return managementIdx;
	}

	public List<PostApplicationManagementInfo> selectedByApplicationMemberIdx(int applicationMemberIdx) {
		String sql = "SELECT * FROM post_application_management_info WHERE applicantMemberIdx = ?";
		
		List<PostApplicationManagementInfo> results  = jdbcTemplate.query(sql, new PostApplicationMapper(),applicationMemberIdx);
		
		return results.isEmpty() ? null : results;
	}
	
	public List<PostApplicationManagementInfo> selectedByPostIdx(int postIdx) {
		String sql = "SELECT * FROM post_application_management_info WHERE postIdx = ?";
		
		List<PostApplicationManagementInfo> results  = jdbcTemplate.query(sql, new PostApplicationMapper(),postIdx);
		
		return results.isEmpty() ? null : results;
	}
	
	public PostApplicationManagementInfo getManagementInfoByManageIdx(int managementIdx) {
		String sql = "SELECT * FROM post_application_management_info WHERE managementIdx = ?";

		List<PostApplicationManagementInfo> results  = jdbcTemplate.query(sql, new PostApplicationMapper(),managementIdx);
		
		return results.isEmpty() ? null : results.get(0);
	}
	public int getRegistrantMemberIdxByManagementIdx(int managementIdx) {
		String sql = "SELECT b.memberIdx AS registrantMemberIdx FROM post_application_management_info AS a LEFT JOIN post_info AS b ON a.postIdx = b.postIdx WHERE managementIdx = ?";
		
		List<Integer> results = jdbcTemplate.query(sql, new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt("registrantMemberIdx");
			}},managementIdx);
		
		return results.isEmpty() ? 0 : results.get(0);
	}
	
	public boolean updateStatusByManageIdxAndApplicationIdx(PostApplicationManagementInfo applicationInfo) {
		String sql = "UPDATE post_application_management_info SET applicationStatus = ? WHERE managementIdx = ? AND applicantMemberIdx = ?";

		int num = jdbcTemplate.update(sql,applicationInfo.getApplicationStatus(),applicationInfo.getManagementIdx(),applicationInfo.getApplicantMemberIdx());
		
		return num > 0 ? true : false;
	}
	
}
