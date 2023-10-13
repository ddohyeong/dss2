package domain.post.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import domain.post.dto.PostNameInfo;

public class PostNameDao {
	private JdbcTemplate jdbcTemplate;
	
	public PostNameDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public PostNameInfo getPostNameByPostIdx(int postIdx) {
		String sql = "SELECT * FROM post_name_info WHERE postIdx = ?";
		
		List<PostNameInfo> results = jdbcTemplate.query(sql, new RowMapper<PostNameInfo>() {

			@Override
			public PostNameInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				PostNameInfo info = new PostNameInfo();
				
				info.setPostIdx(postIdx);
				info.setPostName(rs.getString("postName"));
				info.setRegistedMemberIdx(rs.getInt("registedMemberIdx"));
				
				return info;
			}},postIdx);
	
		return results.isEmpty() ? null : results.get(0);
	}
	
	public boolean insert(int postIdx, String postName, int registedMemberIdx) {
		String sql = "INSERT INTO post_name_info(postIdx,postName,registedMemberIdx) VALUES(?,?,?)";
		
		int num = jdbcTemplate.update(sql,postIdx,postName,registedMemberIdx);
		
		return num > 0 ? true : false;
	}
}
