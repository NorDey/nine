package com.BYS.GWSystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
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

}
