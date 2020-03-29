package com.BYS.GWSystem.service;

import java.util.List;

import com.BYS.GWSystem.dto.GraduateDto;
import com.BYS.GWSystem.model.Graduate;

public interface IGraduateService  {
	
	//学生简历编辑率
	public Double StudentResumeEditingRate();
	
	//未编辑简历学生
	public List<GraduateDto> selectNotFilled();
	
	//学生列表
	public List<GraduateDto> selectCheckingStudents();
	
	

}
