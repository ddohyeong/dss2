package domain.chat.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import domain.chat.dto.ChatInfo;

public class ChatDao {
	private JdbcTemplate jdbcTemplate;
	
	public ChatDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public boolean insert(ChatInfo chatInfo) {
		String sql = "INSERT INTO chat_info(senderMemberIdx,recipientMemberIdx,message) VALUES(?,?,?)";
		
		int num = jdbcTemplate.update(sql,chatInfo.getSenderMemberIdx(),chatInfo.getRecipientMemberIdx(),chatInfo.getMessage());
	
		return num > 0 ? false: true;
	}
	
	public List<ChatInfo> selectedMessageInfoBySendAndRecive(ChatInfo chatInfo){
		String sql = "SELECT * FROM chat_info WHERE (senderMemberIdx =? AND recipientMemberIdx =?) OR (senderMemberIdx =? AND recipientMemberIdx = ?) ORDER BY registDate";
		
		List<ChatInfo> results = jdbcTemplate.query(sql, new RowMapper<ChatInfo>() {

			@Override
			public ChatInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				ChatInfo nth = new ChatInfo();
				
				int messageIdx = rs.getInt("messageIdx");
				int senderMemberIdx = rs.getInt("senderMemberIdx");
				int recipientMemberIdx = rs.getInt("recipientMemberIdx");
				String message =rs.getString("message"); 
				LocalDateTime registDate = rs.getTimestamp("registDate").toLocalDateTime();
				
				nth.setSenderMemberIdx(senderMemberIdx);
				nth.setRecipientMemberIdx(recipientMemberIdx);
				nth.setMessageIdx(messageIdx);
				nth.setMessage(message);
				nth.setRegistDate(registDate);
				
				return nth;
			}
			
		},chatInfo.getSenderMemberIdx(),chatInfo.getRecipientMemberIdx(),chatInfo.getRecipientMemberIdx(),chatInfo.getSenderMemberIdx());
		
		return results;
	}

}
