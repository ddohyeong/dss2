package domain.post.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import domain.post.dao.PostAndMemberDao;
import domain.post.dao.PostDao;
import domain.post.dao.PostNameDao;
import domain.post.dto.PostAndMemberInfo;
import domain.post.dto.PostInfo;


public class PostService {
	@Autowired
	private PostDao postDao;
	@Autowired
	private PostAndMemberDao pmDao;
	@Autowired
	private PostNameDao pnDao;
	
	public boolean add(PostInfo postInfo){
		int postIdx = postDao.insert(postInfo);
		
		return pnDao.insert(postIdx, postInfo.getPostName(),postInfo.getMemberIdx());
	}
	
	public boolean update(PostInfo postInfo) {
		return postDao.update(postInfo);
	}
	
	public boolean delete(PostInfo postInfo) {
		
		return postDao.delete(postInfo);
		
	}
	
	public int getAmount() {
		return postDao.getAmount();
	}
	
	public List<PostAndMemberInfo> getPostInfoList(String postStatus,int memberIdx){
		
		return pmDao.getPostInfoList(postStatus,memberIdx);
		
	}
	
	public List<PostInfo> getPostInfListByMemberIdx(int memberIdx){
		
		List<PostInfo> postInfoList = postDao.getPostInfoListByMemberIdx(memberIdx);
		
		return postInfoList;
	}
	
	public PostAndMemberInfo getPostInfo(int postIdx) {
		
		return pmDao.getPostInfo(postIdx);
	}
	
	public boolean togglePostStatus(PostInfo postInfo) {
		String postStatus = postInfo.getPostStatus();
		
		if(postStatus.equals("T")) {
			postStatus = "F";
		}else {
			postStatus = "T";
		}
		
		postInfo.setPostStatus(postStatus);
		
		
		return postDao.updatePostStatus(postInfo);
	}
}
