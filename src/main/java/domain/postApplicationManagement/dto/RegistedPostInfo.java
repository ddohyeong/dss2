package domain.postApplicationManagement.dto;

import java.time.LocalDateTime;
import java.util.List;

public class RegistedPostInfo {
	private int postIdx;
	private String postName;
	private String postStatus;
	private int viewNum;
	private LocalDateTime postRegistDate;
	private List<RegistedPostApplicationInfo> registedPostApplicationInfoList;
	
	public int getPostIdx() {
		return postIdx;
	}
	public void setPostIdx(int postIdx) {
		this.postIdx = postIdx;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public String getPostStatus() {
		return postStatus;
	}
	public void setPostStatus(String postStatus) {
		this.postStatus = postStatus;
	}
	public int getViewNum() {
		return viewNum;
	}
	public void setViewNum(int viewNum) {
		this.viewNum = viewNum;
	}
	public LocalDateTime getPostRegistDate() {
		return postRegistDate;
	}
	public void setPostRegistDate(LocalDateTime postRegistDate) {
		this.postRegistDate = postRegistDate;
	}
	public List<RegistedPostApplicationInfo> getRegistedPostApplicationInfoList() {
		return registedPostApplicationInfoList;
	}
	public void setRegistedPostApplicationInfoList(List<RegistedPostApplicationInfo> registedPostApplicationInfoList) {
		this.registedPostApplicationInfoList = registedPostApplicationInfoList;
	}
	
}
