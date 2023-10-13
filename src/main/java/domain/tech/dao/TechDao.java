package domain.tech.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import domain.tech.dto.TechInfo;


public class TechDao {
	private JdbcTemplate jdbcTemplate;
	
	public TechDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<TechInfo> getTechByCategory(String category){
		
		String sql = "SELECT * FROM tech_info WHERE "+category+" = 'Y'"; 
		
		List<TechInfo> results = jdbcTemplate.query(sql, new RowMapper<TechInfo>() {

			@Override
			public TechInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				TechInfo nth = new TechInfo();
				
				nth.setTechName(rs.getString("techName"));
				nth.setTechIdx(rs.getString("techIdx"));
				nth.setFamous(rs.getString("famous"));
				nth.setFrontend(rs.getString("frontend"));
				nth.setBackend(rs.getString("backend"));
				nth.setMobile(rs.getString("mobile"));
				nth.setEtc(rs.getString("etc"));
				nth.setAllTech(rs.getString("allTech"));
				return nth;
			}
			
		} );
		
		return results.isEmpty() ? null : results;
	}
	
	public String getTechIdxByTechName(String techName) {
		String sql = "SELECT techIdx FROM tech_info WHERE techName=?";
		List<String> techIdx = jdbcTemplate.query(sql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("techIdx");
			}}, techName);
		
		return techIdx.isEmpty() ? null : techIdx.get(0);
	}
	
	public String getTechNameByTechIdx(String techIdx) {
		String sql = "SELECT techName FROM tech_info WHERE techIdx=?";
		List<String> techName = jdbcTemplate.query(sql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("techName");
			}}, techIdx);
		
		return techIdx.isEmpty() ? null : techName.get(0);
	}
	
}
