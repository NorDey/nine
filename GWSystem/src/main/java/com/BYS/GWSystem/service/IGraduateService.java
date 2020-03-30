package com.BYS.GWSystem.service;

import java.util.List;

import com.BYS.GWSystem.dto.GraduateDto;
import com.BYS.GWSystem.model.Graduate;
import com.github.pagehelper.Page;

public interface IGraduateService  {
	
	//学生简历编辑率
	public Double StudentResumeEditingRate();
	
	//未编辑简历学生
	public List<GraduateDto> selectNotFilled();
	public Page<GraduateDto> PageNotFilled();
	
	//学生列表
	public List<GraduateDto> selectCheckingStudents();
	public Page<GraduateDto> PageCheckingStudents();

	//简历最受欢迎学生列表
	public List<GraduateDto> selectBestResumeStudents();
	public Page<GraduateDto> PageBestResumeStudents();
	

}
