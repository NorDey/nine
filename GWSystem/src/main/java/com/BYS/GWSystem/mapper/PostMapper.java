package com.BYS.GWSystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.BYS.GWSystem.dto.PostDto;
import com.BYS.GWSystem.model.Post;
import com.github.pagehelper.Page;

@Mapper
public interface PostMapper {

	// 查询岗位
	public List<Post> selectPostList(Post post);

	// 更改
	public int updatePost(Post post);

	// 添加
	public int insertPost(Post post);

	// 删除
	public int deletePost(Post post);

	// 查询发布招聘的公司数
	public int selectRecruitmentCompaniesNumber();

	// 岗位数
	public int selectPostNumber();

	// 被关注岗位数
	public int selectFollowNumber();

	// 按公司查询简要的岗位
	public List<Post> selectJobSimpleList(@Param(value = "registrationId") String registrationId);

	// 查询在招岗位详细信息
	public Post selectOneHiredMsg(String postId);

	// 是否存在这个fatherID存在这个fatherID是多少
	public List<Integer> seletExeists(@Param(value ="pro")String pro);

	// 分配一个FID的最大值
	public int maxFid();
	
	// 是否存在这个TID存在这个TID是多少
	public Integer seletTExeists(@Param(value ="postNmae")String postNmae);
	
	// 分配一个TID的最大值
	public int maxTid();

	//条件查询岗位详细信息
	public List<PostDto> selectPostListByMore(PostDto postDto);
	
	// 查询工作岗位简要信息(全查)
	public List<Post> jobListAll();

	//新增加的岗位(七天)
	public List<PostDto> doNewPostList();
	
	//条件查询岗位简要信息
	public List<Post> jobListArrage(String postName);

	//删除热门
	public void deletePopularPost();
	//实时设置热门岗位
	public void setUpPopularPositions();
	

}
