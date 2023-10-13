package domain.post.dto;

public class PostLike {
	private int likeIdx;
	private int postIdx;
	private int memberIdx;
	private String postLikeStatus;
	
	public int getLikeIdx() {
		return likeIdx;
	}
	public void setLikeIdx(int likeIdx) {
		this.likeIdx = likeIdx;
	}
	public int getPostIdx() {
		return postIdx;
	}
	public void setPostIdx(int postIdx) {
		this.postIdx = postIdx;
	}
	public int getMemberIdx() {
		return memberIdx;
	}
	public void setMemberIdx(int memberIdx) {
		this.memberIdx = memberIdx;
	}
	public String getPostLikeStatus() {
		return postLikeStatus;
	}
	public void setPostLikeStatus(String postLikeStatus) {
		this.postLikeStatus = postLikeStatus;
	}
	
	
}
