package domain.post.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import domain.post.dto.PostAndMemberInfo;
import domain.post.dto.PostAndMemberMapper;
import domain.post.dto.PostLike;
import domain.utils.Conversion;

public class PostAndMemberDao {
	private JdbcTemplate jdbcTemplate;
	
	public PostAndMemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Autowired
	private PostLikeDao postLikeDao;
	@Autowired
	private Conversion conversion;
	
	public List<PostAndMemberInfo> getPostInfoList(String postStatus, int memberIdx){
		String sql = "SELECT * FROM post_info AS post INNER JOIN member_info AS member ON post.memberIdx = member.memberIdx WHERE post.postStatus = 'T' ORDER BY post.registDate DESC";
		
		if(postStatus.equals("F")) {
			sql = "SELECT * FROM post_info AS post INNER JOIN member_info AS member ON post.memberIdx = member.memberIdx ORDER BY post.registDate DESC";
		}
		
		List<PostAndMemberInfo> results = jdbcTemplate.query(sql, new RowMapper<PostAndMemberInfo>() {

			@Override
			public PostAndMemberInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				PostAndMemberInfo nth = new PostAndMemberInfo();
				nth.setMemberIdx(rs.getInt("post.memberIdx"));
				nth.setNickName(rs.getString("member.nickName"));
				nth.setPostIdx(rs.getInt("post.postIdx"));
				nth.setPostName(rs.getString("post.postName"));
				nth.setPostIntroduction(rs.getString("post.postIntroduction"));
				nth.setPostCategory(rs.getString("post.postcategory"));
				nth.setProcess(rs.getString("post.process"));
				nth.setPeriod(rs.getInt("post.period"));
				nth.setNumOfPeople(rs.getInt("post.numOfPeople"));
				nth.setContactMethod(rs.getString("post.contactMethod"));
				String newTechnologyCategory=conversion.returnTechNameByTechIdx(rs.getString("post.technologyCategory"));
				nth.setTechnologyCategory(newTechnologyCategory);
				nth.setExpectedStartDate(rs.getDate("post.expectedStartDate").toLocalDate());
				nth.setViewNum(rs.getInt("post.viewNum"));
				nth.setPostStatus(rs.getString("post.postStatus"));
				nth.setRegistDate(rs.getTimestamp("post.registDate").toLocalDateTime());
				
				if(memberIdx != 0) {
					
					PostLike like = new PostLike();
					like.setPostIdx(rs.getInt("post.postIdx"));
					like.setMemberIdx(memberIdx);
					
					String postLikeStatus = postLikeDao.getLikeByMemberIdx(like);
					nth.setPostLikeStatus(postLikeStatus);
				}
				return nth;
			}});
		
		return results.isEmpty() ? null : results;
	}
	
	public List<PostAndMemberInfo> getPostInfoListByProject(String postStatus, int memberIdx){
		String sql = "SELECT * FROM post_info AS post INNER JOIN member_info AS member ON post.memberIdx = member.memberIdx WHERE post.postcategory = '프로젝트' AND post.postStatus = 'T' ORDER BY post.registDate DESC";  
		
		if(postStatus.equals("F")) {
			sql = "SELECT * FROM post_info AS post INNER JOIN member_info AS member ON post.memberIdx = member.memberIdx WHERE post.postcategory = '프로젝트' ORDER BY post.registDate DESC";
		}
		
		List<PostAndMemberInfo> results = jdbcTemplate.query(sql, new RowMapper<PostAndMemberInfo>() {

			@Override
			public PostAndMemberInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				PostAndMemberInfo nth = new PostAndMemberInfo();
				nth.setMemberIdx(rs.getInt("post.memberIdx"));
				nth.setNickName(rs.getString("member.nickName"));
				nth.setPostIdx(rs.getInt("post.postIdx"));
				nth.setPostName(rs.getString("post.postName"));
				nth.setPostIntroduction(rs.getString("post.postIntroduction"));
				nth.setPostCategory(rs.getString("post.postcategory"));
				nth.setProcess(rs.getString("post.process"));
				nth.setPeriod(rs.getInt("post.period"));
				nth.setNumOfPeople(rs.getInt("post.numOfPeople"));
				nth.setContactMethod(rs.getString("post.contactMethod"));
				String newTechnologyCategory=conversion.returnTechNameByTechIdx(rs.getString("post.technologyCategory"));
				nth.setTechnologyCategory(newTechnologyCategory);
				nth.setExpectedStartDate(rs.getDate("post.expectedStartDate").toLocalDate());
				nth.setViewNum(rs.getInt("post.viewNum"));
				nth.setPostStatus(rs.getString("post.postStatus"));
				nth.setRegistDate(rs.getTimestamp("post.registDate").toLocalDateTime());
				
				if(memberIdx != 0) {
					
					PostLike like = new PostLike();
					like.setPostIdx(rs.getInt("post.postIdx"));
					like.setMemberIdx(memberIdx);
					
					String postLikeStatus = postLikeDao.getLikeByMemberIdx(like);
					nth.setPostLikeStatus(postLikeStatus);
				}
				return nth;
			}});
		
		return results.isEmpty() ? null : results;
	}
	
	public List<PostAndMemberInfo> getPostInfoListByTech(String postStatus, int memberIdx,String techList){
		String sql = "SELECT * FROM post_info AS post INNER JOIN member_info AS member ON post.memberIdx = member.memberIdx WHERE technologyCategory REGEXP '"+techList+"' AND post.postStatus = 'T' ORDER BY post.registDate DESC";  
		
		if(postStatus.equals("F")) {
			sql = "SELECT * FROM post_info AS post INNER JOIN member_info AS member ON post.memberIdx = member.memberIdx WHERE technologyCategory REGEXP '"+techList+"' ORDER BY post.registDate DESC";
		}
		
		List<PostAndMemberInfo> results = jdbcTemplate.query(sql, new RowMapper<PostAndMemberInfo>() {

			@Override
			public PostAndMemberInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				PostAndMemberInfo nth = new PostAndMemberInfo();
				nth.setMemberIdx(rs.getInt("post.memberIdx"));
				nth.setNickName(rs.getString("member.nickName"));
				nth.setPostIdx(rs.getInt("post.postIdx"));
				nth.setPostName(rs.getString("post.postName"));
				nth.setPostIntroduction(rs.getString("post.postIntroduction"));
				nth.setPostCategory(rs.getString("post.postcategory"));
				nth.setProcess(rs.getString("post.process"));
				nth.setPeriod(rs.getInt("post.period"));
				nth.setNumOfPeople(rs.getInt("post.numOfPeople"));
				nth.setContactMethod(rs.getString("post.contactMethod"));
				String newTechnologyCategory=conversion.returnTechNameByTechIdx(rs.getString("post.technologyCategory"));
				nth.setTechnologyCategory(newTechnologyCategory);
				nth.setExpectedStartDate(rs.getDate("post.expectedStartDate").toLocalDate());
				nth.setViewNum(rs.getInt("post.viewNum"));
				nth.setPostStatus(rs.getString("post.postStatus"));
				nth.setRegistDate(rs.getTimestamp("post.registDate").toLocalDateTime());
				
				if(memberIdx != 0) {
					
					PostLike like = new PostLike();
					like.setPostIdx(rs.getInt("post.postIdx"));
					like.setMemberIdx(memberIdx);
					
					String postLikeStatus = postLikeDao.getLikeByMemberIdx(like);
					nth.setPostLikeStatus(postLikeStatus);
				}
				return nth;
			}});
		
		return results.isEmpty() ? null : results;
	}
	
	public List<PostAndMemberInfo> getPostInfoListByStudy(String postStatus, int memberIdx){
		String sql = "SELECT * FROM post_info AS post INNER JOIN member_info AS member ON post.memberIdx = member.memberIdx WHERE post.postcategory = '스터디' AND post.postStatus = 'T' ORDER BY post.registDate DESC";  
		if(postStatus.equals("F")) {
			sql = "SELECT * FROM post_info AS post INNER JOIN member_info AS member ON post.memberIdx = member.memberIdx WHERE post.postcategory = '스터디' ORDER BY post.registDate DESC";
		}
		
		List<PostAndMemberInfo> results = jdbcTemplate.query(sql, new RowMapper<PostAndMemberInfo>() {

			@Override
			public PostAndMemberInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				PostAndMemberInfo nth = new PostAndMemberInfo();
				nth.setMemberIdx(rs.getInt("post.memberIdx"));
				nth.setNickName(rs.getString("member.nickName"));
				nth.setPostIdx(rs.getInt("post.postIdx"));
				nth.setPostName(rs.getString("post.postName"));
				nth.setPostIntroduction(rs.getString("post.postIntroduction"));
				nth.setPostCategory(rs.getString("post.postcategory"));
				nth.setProcess(rs.getString("post.process"));
				nth.setPeriod(rs.getInt("post.period"));
				nth.setNumOfPeople(rs.getInt("post.numOfPeople"));
				nth.setContactMethod(rs.getString("post.contactMethod"));
				String newTechnologyCategory=conversion.returnTechNameByTechIdx(rs.getString("post.technologyCategory"));
				nth.setTechnologyCategory(newTechnologyCategory);
				nth.setExpectedStartDate(rs.getDate("post.expectedStartDate").toLocalDate());
				nth.setViewNum(rs.getInt("post.viewNum"));
				nth.setPostStatus(rs.getString("post.postStatus"));
				nth.setRegistDate(rs.getTimestamp("post.registDate").toLocalDateTime());
				
				if(memberIdx != 0) {
					
					PostLike like = new PostLike();
					like.setPostIdx(rs.getInt("post.postIdx"));
					like.setMemberIdx(memberIdx);
					
					String postLikeStatus = postLikeDao.getLikeByMemberIdx(like);
					nth.setPostLikeStatus(postLikeStatus);
				}
				return nth;
			}});
		
		return results.isEmpty() ? null : results;
	}
	
	public List<PostAndMemberInfo> getPostInfoListBySearch(String search,String postStatus,int memberIdx){
		String sql = "SELECT * FROM post_info AS post INNER JOIN member_info AS member ON post.memberIdx = member.memberIdx WHERE post.postStatus = 'T' AND postName LIKE '%"+search+"%' ORDER BY post.registDate DESC";  
		if(postStatus.equals("F")) {
			sql = "SELECT * FROM post_info AS post INNER JOIN member_info AS member ON post.memberIdx = member.memberIdx WHERE postName LIKE '%"+search+"%' ORDER BY post.registDate DESC";
		}
		
		List<PostAndMemberInfo> results = jdbcTemplate.query(sql, new RowMapper<PostAndMemberInfo>() {

			@Override
			public PostAndMemberInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				PostAndMemberInfo nth = new PostAndMemberInfo();
				nth.setMemberIdx(rs.getInt("post.memberIdx"));
				nth.setNickName(rs.getString("member.nickName"));
				nth.setPostIdx(rs.getInt("post.postIdx"));
				nth.setPostName(rs.getString("post.postName"));
				nth.setPostIntroduction(rs.getString("post.postIntroduction"));
				nth.setPostCategory(rs.getString("post.postcategory"));
				nth.setProcess(rs.getString("post.process"));
				nth.setPeriod(rs.getInt("post.period"));
				nth.setNumOfPeople(rs.getInt("post.numOfPeople"));
				nth.setContactMethod(rs.getString("post.contactMethod"));
				String newTechnologyCategory=conversion.returnTechNameByTechIdx(rs.getString("post.technologyCategory"));
				nth.setTechnologyCategory(newTechnologyCategory);
				nth.setExpectedStartDate(rs.getDate("post.expectedStartDate").toLocalDate());
				nth.setViewNum(rs.getInt("post.viewNum"));
				nth.setPostStatus(rs.getString("post.postStatus"));
				nth.setRegistDate(rs.getTimestamp("post.registDate").toLocalDateTime());
				
				if(memberIdx != 0) {
					
					PostLike like = new PostLike();
					like.setPostIdx(rs.getInt("post.postIdx"));
					like.setMemberIdx(memberIdx);
					
					String postLikeStatus = postLikeDao.getLikeByMemberIdx(like);
					nth.setPostLikeStatus(postLikeStatus);
				}
				return nth;
			}});
		
		return results.isEmpty() ? null : results;
	}
	
	public PostAndMemberInfo getPostInfo(int postIdx) {
		String sql = "SELECT * FROM post_info AS post INNER JOIN member_info AS member ON post.memberIdx = member.memberIdx WHERE postIdx = ?;";  
		
		List<PostAndMemberInfo> results = jdbcTemplate.query(sql, new PostAndMemberMapper(conversion),postIdx);
		
		return results.isEmpty() ? null : results.get(0);
	}

	
}
