package com.BYS.GWSystem.service.impl;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BYS.GWSystem.mapper.PostMapper;
import com.BYS.GWSystem.model.Post;
import com.BYS.GWSystem.service.IPostService;
import com.github.pagehelper.Page;

@Service
public class PostServiceImpl implements IPostService {

	@Autowired
	private PostMapper postMapper;

	DecimalFormat formater = new DecimalFormat("#0.##");

	// 招聘信息关注率
	public Double AttentionRateOfRecruitmentInformation() {
		int a = postMapper.selectPostNumber();// 岗位数
		int b = postMapper.selectFollowNumber();// 被关注数
		float totalPrice = ((float) b / a * 100);
		return Double.parseDouble(formater.format(totalPrice));
	}

	// 查询工作岗位简要信息
	@Override
	public List<Post> jobList(String registrationId) {
		// TODO Auto-generated method stub
		return postMapper.selectJobSimpleList(registrationId);
	}

	@Override
	public Page<Post> jobListPage(String registrationId) {
		// TODO Auto-generated method stub
		return (Page<Post>)postMapper.selectJobSimpleList(registrationId);
	}
	
	// 删除工作岗位信息
	@Override
	public int deleteOneHired(String postId) {
		Post posts = new Post();
		posts.setPostId(postId);
		return postMapper.deletePost(posts);
	}
	
	//查询某一个工作岗位详细信息
	@Override
	public Post selectOneHiredMsg(String postId) {
		// TODO Auto-generated method stub
		
		return postMapper.selectOneHiredMsg(postId);
	}

}
