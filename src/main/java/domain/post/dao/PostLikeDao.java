package domain.post.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import domain.post.dto.PostLike;


public class PostLikeDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public PostLikeDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public String getLikeByMemberIdx(PostLike postInfo) {
		String sql = "SELECT * FROM like_check WHERE postIdx = ? AND memberIdx = ?";

		List<String> results = jdbcTemplate.query(sql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				return rs.getString("postLikeStatus");
				
			}},postInfo.getPostIdx(),postInfo.getMemberIdx() );
		
		return results.isEmpty() ? null : results.get(0);
	}
	
	public boolean insertlike(PostLike postInfo) {
		String sql = "INSERT INTO like_check(postIdx,memberIdx,postLikeStatus) VALUES(?,?,?)";
		
		int num = jdbcTemplate.update(sql,postInfo.getPostIdx(),postInfo.getMemberIdx(),postInfo.getPostLikeStatus());
		
		return num > 0 ? true : false;
	}

	public boolean updatelike(PostLike postInfo) {
		String sql = "UPDATE like_check SET postLikeStatus = ? WHERE postIdx = ? AND memberIdx = ?";
		
		int num = jdbcTemplate.update(sql,postInfo.getPostLikeStatus(),postInfo.getPostIdx(),postInfo.getMemberIdx());

		return num > 0 ? true : false;
	}
}
