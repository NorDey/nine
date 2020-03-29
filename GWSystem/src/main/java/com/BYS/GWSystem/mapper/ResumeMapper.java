package com.BYS.GWSystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.BYS.GWSystem.dto.ResumeDto;
import com.BYS.GWSystem.model.Resume;

@Mapper
public interface ResumeMapper  {
	//查询岗位
	public List<Resume> selectResumeList();
	
	//更改
	public int updateResume(Resume resume);
	
	//添加
	public int insertResume(Resume resume);
	
	//删除
	public int deleteResume(Resume resume);

	//查询已编辑简历学生数量
	public Integer selectEditedResumeNumber();
	
	//简历数量
	public Integer selectResumeNumber();
	//被关注简历数量
	public Integer selectFollowNumber();
	//被关注数>a的数目
	public Integer ConcernedAbout(@Param("large")Integer large,@Param("Small")Integer Small);

	//根据id查询简历详情
	public ResumeDto selectResumeById(Long id);

	

}
