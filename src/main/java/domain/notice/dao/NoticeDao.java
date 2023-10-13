package domain.notice.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import domain.notice.dto.NoticeInfo;
import domain.notice.dto.ViewNoticeInfo;
import domain.notice.dto.ViewNoticeMapper;


public class NoticeDao {
	private JdbcTemplate jdbcTemplate;
	
	public NoticeDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<ViewNoticeInfo> selectedByMemberIdxAndNoticeCheckN(int memberIdx){
		String sql = "SELECT * FROM notice_info WHERE memberIdx = ? AND noticeCheckStatus = 'N' ORDER BY registDate DESC";
		
		List<ViewNoticeInfo> results = jdbcTemplate.query(sql, new ViewNoticeMapper(),memberIdx);
		
		return results;
	}
	
	public List<ViewNoticeInfo> selectedByMemberIdxAndNoticeCheckY(int memberIdx){
		String sql = "SELECT * FROM notice_info WHERE memberIdx = ? AND noticeCheckStatus = 'Y'";
		
		List<ViewNoticeInfo> results = jdbcTemplate.query(sql, new ViewNoticeMapper(),memberIdx);
		
		return results;
	}

	
	public List<ViewNoticeInfo> selectedByMemberIdx(int memberIdx){
		String sql = "SELECT * FROM notice_info WHERE memberIdx = ? ORDER BY registDate DESC";
		
		List<ViewNoticeInfo> results = jdbcTemplate.query(sql, new ViewNoticeMapper(),memberIdx);
		
		return results.isEmpty() ? null : results;
	}
	
	public boolean insert(NoticeInfo noticeInfo) {
		String sql = "INSERT INTO notice_info(memberIdx,managementIdx,registDate,noticeCode) VALUES(?,?,?,?)";
		
		int num = jdbcTemplate.update(sql,noticeInfo.getMemberIdx(),noticeInfo.getManagementIdx(),LocalDateTime.now().toString(),noticeInfo.getNoticeCode());
		
		return num > 0 ? true : false;
	}
	public boolean statusYUpdate(NoticeInfo noticeInfo) {
		String sql = "UPDATE notice_info SET noticeCheckStatus = 'Y' WHERE noticeIdx = ? AND memberIdx =?";
		
		int num = jdbcTemplate.update(sql,noticeInfo.getNoticeIdx(),noticeInfo.getMemberIdx());
		
		return num > 0 ? true : false;
	}
	
	public boolean statusYDelete(int memberIdx) {
		String sql = "DELETE FROM notice_info WHERE memberIdx = ? AND noticeCheckStatus = 'Y'";
		
		int num = jdbcTemplate.update(sql,memberIdx);
		
		return num > 0 ? true : false;
	}
	
	public boolean noticeDataInsert(NoticeInfo noticeInfo) {
		String sql = "INSERT INTO notice_data(noticeIdx,memberIdx,managementIdx,registDate,noticeCode) VALUES(?,?,?,?,?)";
		
		int num = jdbcTemplate.update(sql,noticeInfo.getNoticeIdx(),noticeInfo.getMemberIdx(),noticeInfo.getManagementIdx(),LocalDateTime.now().toString(),noticeInfo.getNoticeCode());
		
		return num > 0 ? true : false;
	}
}	
