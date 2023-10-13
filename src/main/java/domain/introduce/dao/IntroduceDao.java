package domain.introduce.dao;

import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import domain.introduce.dto.IntroduceInfo;
import domain.introduce.dto.IntroduceMapper;


public class IntroduceDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public IntroduceDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public boolean insert(IntroduceInfo myInfo) {
		String sql = "INSERT INTO myform_info(memberIdx,nickName,email,technologyCategory,introduce,contactMethod,role) VALUES(?,?,?,?,?,?,?)";
		
		int num = jdbcTemplate.update(sql
				,myInfo.getMemberIdx()
				,myInfo.getNickName()
				,myInfo.getEmail()
				,myInfo.getTechnologyCategory()
				,myInfo.getIntroduce()
				,myInfo.getContactMethod()
				,myInfo.getRole());
		
		return num > 0 ? true : false;
		
	}
	
	public boolean update(IntroduceInfo myInfo) {
		String sql = "UPDATE myform_info SET nickName = ?, email = ?, technologyCategory = ?, introduce = ?, contactMethod = ?, role = ? WHERE memberIdx = ?";
		
		int num = jdbcTemplate.update(sql
				,myInfo.getNickName()
				,myInfo.getEmail()
				,myInfo.getTechnologyCategory()
				,myInfo.getIntroduce()
				,myInfo.getContactMethod()
				,myInfo.getRole()
				,myInfo.getMemberIdx());

		return num > 0 ? true : false;
	}
	
	public boolean delete(int memberIdx) {	
		String sql = "DELETE FROM myform_info WHERE memberIdx = ?";
		int num = jdbcTemplate.update(sql,memberIdx);

		return num > 0 ? true : false;
	}
	
	public IntroduceInfo getMyInfo(int memberIdx) {
		String sql = "SELECT * FROM myform_info WHERE memberIdx =? ";
		
		List<IntroduceInfo> results = jdbcTemplate.query(sql, new IntroduceMapper(),memberIdx);
		
		return results.isEmpty() ? null : results.get(0);
	
	}
}
