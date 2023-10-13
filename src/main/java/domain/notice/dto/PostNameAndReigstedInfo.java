package domain.notice.dto;

public class PostNameAndReigstedInfo {
	private int postIdx;
	private int registedMemberIdx;
	private String postName;
	
	public int getPostIdx() {
		return postIdx;
	}
	public void setPostIdx(int postIdx) {
		this.postIdx = postIdx;
	}
	public int getRegistedMemberIdx() {
		return registedMemberIdx;
	}
	public void setRegistedMemberIdx(int registedMemberIdx) {
		this.registedMemberIdx = registedMemberIdx;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	
	
}
