package com.BYS.GWSystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.BYS.GWSystem.model.Post;

@Mapper
public interface PostMapper  {
	
			//查询岗位
			public List<Post> selectPostList();
			
			//更改
			public int updatePost(Post post);
			
			//添加
			public int insertPost(Post post);
			
			//删除
			public int deletePost(Post post);

			
			//查询发布招聘的公司数
			public int selectRecruitmentCompaniesNumber();

			//岗位数
			public int selectPostNumber();
			//被关注岗位数
			public int selectFollowNumber();

}
