package domain.member.dao;

import java.time.LocalDateTime;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import domain.member.dto.WithdrawlMemberInfo;

public class WithdrawlMemberDao {

	private JdbcTemplate jdbcTemplate;
	
	public WithdrawlMemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public boolean insert(WithdrawlMemberInfo member) {
		boolean result = false;
		
		String sql = "INSERT INTO withdrawl_member_info(id,nickName,reason,reasonText,registDate,withdrawlDate) VALUES(?,?,?,?,?,?)";
		
		int num = jdbcTemplate.update(sql
				, member.getId()
				, member.getNickName()
				, member.getReason()
				, member.getReasonText()
				, member.getRegistDate().toString()
				, LocalDateTime.now().toString());

		if (num > 0) {
			result = true;
		}
		
		return result;
	}

}
