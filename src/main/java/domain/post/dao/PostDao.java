package domain.post.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import domain.post.dto.PostInfo;
import domain.post.dto.PostMapper;
import domain.utils.Conversion;


public class PostDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public PostDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	@Autowired
	private Conversion conversion;
	
	public int insert(PostInfo postInfo) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String sql = "INSERT INTO post_info(memberIdx,postName,postIntroduction,postcategory,process,period,technologyCategory,expectedStartDate,contactMethod,postStatus,registDate,numOfPeople) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
				
				String[] columnNames = {"postIdx"};
				
				PreparedStatement pstmt = con.prepareStatement(sql,columnNames);
				pstmt.setInt(1, postInfo.getMemberIdx());
				pstmt.setString(2, postInfo.getPostName());
				pstmt.setString(3, postInfo.getPostIntroduction());
				pstmt.setString(4, postInfo.getPostCategory());
				pstmt.setString(5, postInfo.getProcess());
				pstmt.setInt(6, postInfo.getPeriod());
				String newTechnologyCategory = conversion.returnTechIdxByTechName(postInfo.getTechnologyCategory());
				pstmt.setString(7, newTechnologyCategory);
				pstmt.setString(8, postInfo.getExpectedStartDate().toString());
				pstmt.setString(9, postInfo.getContactMethod());
				pstmt.setString(10, postInfo.getPostStatus());
				pstmt.setString(11,LocalDateTime.now().toString());
				pstmt.setInt(12,postInfo.getNumOfPeople());
				
				return pstmt;
			}
		},keyHolder);
		
		Number keyValue = keyHolder.getKey();
		
		int postIdx = keyValue.intValue();
		
		return postIdx;
	}
	
	public boolean update(PostInfo postInfo) {
		String sql = "UPDATE post_info SET postName = ?, postIntroduction = ?,postCategory = ?,process = ?,contactMethod = ?,period = ? ,technologyCategory = ?,expectedStartDate = ?,postStatus = ?,numOfPeople = ? WHERE postIdx = ? AND memberIdx = ?";
		
		String newTechnologyCategory = conversion.returnTechIdxByTechName(postInfo.getTechnologyCategory());
		postInfo.setTechnologyCategory(newTechnologyCategory);
		
		int num = jdbcTemplate.update(sql
				, postInfo.getPostName()
				, postInfo.getPostIntroduction()
				, postInfo.getPostCategory()
				, postInfo.getProcess()
				, postInfo.getContactMethod()
				, postInfo.getPeriod()
				, postInfo.getTechnologyCategory()
				, postInfo.getExpectedStartDate().toString()
				, postInfo.getPostStatus()
				, postInfo.getNumOfPeople()
				, postInfo.getPostIdx()
				, postInfo.getMemberIdx());
		
		return num > 0 ? true :false;
	}
	
	public boolean delete(PostInfo postInfo) {
		String sql = "DELETE FROM post_info WHERE postIdx = ? AND memberIdx = ?";
		
		int num = jdbcTemplate.update(sql, postInfo.getPostIdx(), postInfo.getMemberIdx());
		
		return num > 0 ? true :false;
	}
	
	public int getAmount() {
		String sql = "SELECT COUNT(*) AS amount FROM post_info";
		
		int amount  = jdbcTemplate.queryForObject(sql,Integer.class);

		return amount;
	}
	
	public List<PostInfo> getPostInfoListByMemberIdx(int memberIdx){
		String sql = "SELECT * FROM post_info WHERE memberIdx = ?";  
		
		List<PostInfo> results = jdbcTemplate.query(sql, new PostMapper(conversion),memberIdx);
		
		return results.isEmpty() ? null : results;
	}
	
	public boolean updateViewNum(PostInfo postInfo) {
		String sql = "UPDATE post_info SET viewNum = ? WHERE postIdx = ?";
		
		int num = jdbcTemplate.update(sql,postInfo.getViewNum(),postInfo.getPostIdx());
		
		return num > 0 ? true : false;
	}
	
	public boolean updatePostStatus(PostInfo postInfo) {
		String sql = "UPDATE post_info SET postStatus = ? WHERE postIdx = ? AND memberIdx = ?";
		
		int num = jdbcTemplate.update(sql,postInfo.getPostStatus(),postInfo.getPostIdx(),postInfo.getMemberIdx());
		
		return num > 0 ? true : false;
	}
	
	public boolean viewNumCheckByMemberIdx(PostInfo postInfo) {
		String sql = "SELECT * FROM viewnum_check WHERE postIdx = ? AND memberIdx = ?";
		
		List<Integer> results = jdbcTemplate.query(sql, new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				int result = rs.getInt("postIdx");
				return result;
			}},postInfo.getPostIdx(),postInfo.getMemberIdx());
		
		return results.isEmpty() ? false : true;
	}
	
	public boolean insertViewNum(PostInfo postInfo) {
		String sql = "INSERT INTO viewnum_check(postIdx,memberIdx) VALUES(?,?)";
		
		int num = jdbcTemplate.update(sql,postInfo.getPostIdx(),postInfo.getMemberIdx());
		
		return num > 0 ? true :false;
	}

 
	
}