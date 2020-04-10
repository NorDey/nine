package com.BYS.GWSystem.service.impl;

import java.text.DecimalFormat;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.BYS.GWSystem.dto.PostDto;
import com.BYS.GWSystem.mapper.PostMapper;
import com.BYS.GWSystem.mapper.TypeWorkMapper;
import com.BYS.GWSystem.model.Post;
import com.BYS.GWSystem.service.IPostService;
import com.github.pagehelper.Page;

@Service
public class PostServiceImpl implements IPostService {

	@Autowired
	private PostMapper postMapper;
	@Autowired
	private TypeWorkMapper typeWorkMapper;

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
		return (Page<Post>) postMapper.selectJobSimpleList(registrationId);
	}

	// 删除工作岗位信息
	@Override
	public int deleteOneHired(String postId) {
		Post posts = new Post();
		posts.setPostId(postId);
		return postMapper.deletePost(posts) + typeWorkMapper.deletePost(posts);
	}

	// 查询某一个工作岗位详细信息
	@Override
	public Post selectOneHiredMsg(String postId) {
		// TODO Auto-generated method stub

		return postMapper.selectOneHiredMsg(postId);
	}

	// 统计post的数量来设计postId
	@Override
	public int count() {
		// TODO Auto-generated method stub
		return postMapper.selectPostNumber();
	}

	// 是否存在这个fatherID存在这个fatherID是多少
	@Override
	public List<Integer> seletExeists(String pro) {
		// TODO Auto-generated method stub
		return postMapper.seletExeists(pro);
	}

	// 分配一个FID的最大值
	@Override
	public int seletMaxFID() {
		// TODO Auto-generated method stub
		return postMapper.maxFid();
	}

	// 是否存在这个fatherID存在这个fatherID是多少//分配一个FID的最大值
	@Override
	public String toGetFid(String profession) {
		// TODO Auto-generated method stub
		int id = 0;
		if (seletExeists(profession).isEmpty())
			id = (seletMaxFID() + 1);// 不存在该工种则新建这个工种
		else {
			id = seletExeists(profession).get(1);// 是否存在该工种
		}
		return id + "";
	}

	// 是否存在这个TID存在这个fatherID是多少
	@Override
	public List<Integer> seletTExeists(String postNmae) {
		// TODO Auto-generated method stub
		return postMapper.seletTExeists(postNmae);
	}

	// 分配一个TID的最大值
	@Override
	public int seletMaxTID() {
		// TODO Auto-generated method stub
		return postMapper.maxTid();
	}

	// 是否存在这个TID存在这个fatherID是多少//分配一个TID的最大值
	@Override
	public Integer toGetTid(String postName) {
		Integer id = null;
		if (seletTExeists(postName).isEmpty())
			id = (seletMaxTID() + 1);// 不存在该工种则新建这个岗位名称
		else {
			id = seletTExeists(postName).get(1);// 是否存在该岗位名称
		}
		return id;
	}

	// 条件查询岗位详情
	@Override
	public List<PostDto> selectPostListByMore(PostDto postDto) {
		return postMapper.selectPostListByMore(postDto);
	}

	// 条件查询岗位详情加分页
	@Override
	public Page<PostDto> PagePostListByMore(PostDto postDto) {
		// TODO Auto-generated method stub
		return (Page<PostDto>) postMapper.selectPostListByMore(postDto);
	}

	// 查询工作岗位简要信息(全查)
	@Override
	public List<Post> jobListAll() {
		// TODO Auto-generated method stub
		return postMapper.jobListAll();
	}

	@Override
	public Page<Post> jobListAllPage() {
		// TODO Auto-generated method stub
		return (Page<Post>) postMapper.jobListAll();
	}

	// 新增加的岗位(七天)
	@Override
	public List<PostDto> doNewPostList() {
		return postMapper.doNewPostList();
	}

	public Page<PostDto> PageNewPostList() {
		return (Page<PostDto>) postMapper.doNewPostList();
	}

	// 条件查询岗位简要信息
	@Override
	public List<Post> jobListArrage(String postName) {
		// TODO Auto-generated method stub
		return postMapper.jobListArrage(postName);
	}

	@Override
	public Page<Post> jobListArragePage(String postName) {
		// TODO Auto-generated method stub
		return (Page<Post>) postMapper.jobListArrage(postName);
	}

	// 实时设置热门岗位
	@Override
	// 添加事务管理
	@Transactional
	public void setUpPopularPositions() {
		// 删除
		postMapper.deletePopularPost();
		// 更新
		postMapper.setUpPopularPositions();
	}

	@Override
	public int updatePost(Post post) {
		// TODO Auto-generated method stub
		return postMapper.updatePost(post);
	}

	// 模糊查询Post
	@Override
	public List<Post> jobListLike(String postNamesL) {
		// TODO Auto-generated method stub
		return postMapper.jobListLike(postNamesL);
	}

	@Override
	public Page<Post> jobListLikePage(String postNamesL) {
		// TODO Auto-generated method stub
		return (Page<Post>) postMapper.jobListLike(postNamesL);
	}

	@Override
	public PostDto selectPostListById(String postId) {
		PostDto post = postMapper.selectPostListById(postId);
		return post;
	}

}
