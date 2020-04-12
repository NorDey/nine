package com.BYS.GWSystem.service;

import java.util.List;

import com.BYS.GWSystem.dto.PostDto;
import com.BYS.GWSystem.model.*;
import com.github.pagehelper.Page;

public interface IPostService {
	// 招聘信息关注率
	public Double AttentionRateOfRecruitmentInformation();

	// 查询工作岗位简要信息
	public List<Post> jobList(String registrationId);

	public Page<Post> jobListPage(String registrationId);

	// 查询工作岗位简要信息(全查)
	public List<Post> jobListAll();

	public Page<Post> jobListAllPage();

	// 删除岗位信息
	public int deleteOneHired(String postId);

	// 按postID查询一条
	public Post selectOneHiredMsg(String postId);

	// 查询一共多少post
	public int count();

	// 是否存在这个fatherID存在这个fatherID是多少
	public List<Integer> seletExeists(String pro);

	// 分配一个FID的最大值
	public int seletMaxFID();

	// 分配一个FID的最大值//是否存在这个fatherID存在这个fatherID是多少
	public String toGetFid(String profession);

	// 是否存在这个TID存在这个TID是多少
	public List<Integer> seletTExeists(String postNmae);

	// 分配一个TID的最大值
	public int seletMaxTID();

	// 分配一个TID的最大值//是否存在这个TID存在这个TID是多少
	public Integer toGetTid(String postName);

	// 条件查询岗位详细信息
	public List<PostDto> selectPostListByMore(PostDto postDto);

	public Page<PostDto> PagePostListByMore(PostDto postDto);

	// 新增加的岗位(七天)
	public List<PostDto> doNewPostList();

	public Page<PostDto> PageNewPostList();

	// 条件查询岗位简要信息
	public List<Post> jobListArrage(String postName);

	public Page<Post> jobListArragePage(String postName);

	// 设置热门岗位
	public void setUpPopularPositions();

	// 更改岗位(热门)
	public int updatePost(Post post);

	// 模糊查询岗位简要信息
	public List<Post> jobListLike(String postNamesL);

	public Page<Post> jobListLikePage(String postNamesL);

	// 通过学生编号查询所有已投递岗位的详细信息
	public List<PostDto> selectPostListByStudentId(String studentId);
}
