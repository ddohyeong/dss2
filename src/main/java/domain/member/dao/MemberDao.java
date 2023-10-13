package domain.member.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import domain.member.dto.LoginCommand;
import domain.member.dto.MemberInfo;
import domain.member.dto.MemberMapper;

public class MemberDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public MemberInfo selectedByEmailAndCode(MemberInfo memberInfo) {
		String sql = "SELECT * FROM member_info WHERE email = ? AND code =?";
		
		List<MemberInfo> results = jdbcTemplate.query(sql,new MemberMapper(),memberInfo.getEmail(),memberInfo.getCode());
		
		return results.isEmpty() ? null : results.get(0);
	}
	
	public boolean updateCodeByEmail(String code, String email) {
		
		
		String sql = "UPDATE member_info SET code = ? WHERE email = ?";
		
		int num = jdbcTemplate.update(sql,code,email);
		
		return num > 0 ? true : false;
	}
	
	public boolean insert(MemberInfo memberInfo) {
		
		String sql = "INSERT INTO member_info(id,pw,name,nickName,tel,email,changePwDate,loginType,salt) VALUES(?,?,?,?,?,?,?,?,?)";
		
		int num = jdbcTemplate.update(sql
				,memberInfo.getId()
				,memberInfo.getPw()
				,memberInfo.getName()
				,memberInfo.getNickName()
				,memberInfo.getTel()
				,memberInfo.getEmail()
				,LocalDateTime.now().toString()
				,memberInfo.getLoginType()
				,memberInfo.getSalt());
		
		return num > 0 ? true : false;
	}
	
	public boolean delete(MemberInfo memberInfo) {
		String sql = "DELETE FROM member_info WHERE memberIdx = ?";
		
		int num = jdbcTemplate.update(sql,memberInfo.getMemberIdx());		
		
		return num > 0 ? true : false;
	}
	
	public boolean updatePw(MemberInfo memberInfo) {
		String sql = "UPDATE member_info SET pw = ?, changePwDate = ? WHERE memberIdx = ?";
		
		int num = jdbcTemplate.update(sql,memberInfo.getPw(),LocalDateTime.now().toString(),memberInfo.getMemberIdx());		
		
		return num > 0 ? true : false;
	}
	
	public boolean updateLoginDate(String id) {
		String sql = "UPDATE member_info SET loginDate = ? WHERE id = ?";
		
		int num = jdbcTemplate.update(sql,LocalDateTime.now().toString(),id);		
		
		return num > 0 ? true : false;
	}
	
	public boolean updatePwChangeDate(int memberIdx, String changePwDate) {
		String sql = "UPDATE member_info SET changePwDate = ? WHERE memberIdx = ?";
		
		int num = jdbcTemplate.update(sql,changePwDate, memberIdx);		
		
		return num > 0 ? true : false;
	}
	
	public boolean update(MemberInfo memberInfo) {
		String sql = "UPDATE member_info SET name = ?, nickName= ?, tel = ?, email = ? WHERE memberIdx = ?";
		
		int num = jdbcTemplate.update(sql
				,memberInfo.getName()
				,memberInfo.getNickName()
				,memberInfo.getTel()
				,memberInfo.getEmail()
				,memberInfo.getMemberIdx() );
		
		return num > 0 ? true : false;
	}
	
	public MemberInfo selectMemberIdx(int memberIdx) {
		
		String sql = "SELECT * FROM member_info WHERE memberIdx = ?";
		
		List<MemberInfo> results = jdbcTemplate.query(sql, new MemberMapper(),memberIdx);
		
		return results.isEmpty() ? null : results.get(0);
	}
	
	public MemberInfo selectByNickName(String nickName) {
		String sql = "SELECT * FROM member_info WHERE nickName = ?";
		
		List<MemberInfo> results = jdbcTemplate.query(sql, new MemberMapper(),nickName);
		
		return results.isEmpty() ? null : results.get(0);
	}
	
	public MemberInfo selectByEmail(String email) {
		String sql = "SELECT * FROM member_info WHERE email = ?";
		
		List<MemberInfo> results = jdbcTemplate.query(sql, new MemberMapper(),email);
		
		return results.isEmpty() ? null : results.get(0);
	}
	
	public MemberInfo selectByTel(String tel) {
		String sql = "SELECT * FROM member_info WHERE tel = ?";
		
		List<MemberInfo> results = jdbcTemplate.query(sql, new MemberMapper(),tel);
		
		return results.isEmpty() ? null : results.get(0);
	}
	
	public MemberInfo selectById(String id) {
		String sql = "SELECT * FROM member_info WHERE id = ?";
		
		List<MemberInfo> results = jdbcTemplate.query(sql, new MemberMapper(),id);
		
		return results.isEmpty() ? null : results.get(0);
	}
	
	public MemberInfo selectByIdAndPw(LoginCommand loginCommand) {
		String sql = "SELECT * FROM member_info WHERE id = ? AND pw = ?";
		
		List<MemberInfo> results = jdbcTemplate.query(sql, new MemberMapper(),loginCommand.getId(), loginCommand.getPw());
		
		return results.isEmpty() ? null : results.get(0);
	}

	public List<MemberInfo> getMemberInfoListByDormant(){
		String sql = "SELECT * FROM member_info WHERE dormant ='N'";
		
		List<MemberInfo> results = jdbcTemplate.query(sql, new MemberMapper());
		
		return results.isEmpty() ? null : results;
	}
	
	public boolean updateDormant(int memberIdx, String dormantStauts) {
		String sql = "UPDATE member_info SET dormant='"+dormantStauts+"' WHERE memberIdx = ?";
		
		int num = jdbcTemplate.update(sql,memberIdx);
		
		return num > 0 ? true : false;
		
	}

}
