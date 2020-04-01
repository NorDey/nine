package com.BYS.GWSystem.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BYS.GWSystem.dto.GraduateDto;
import com.BYS.GWSystem.mapper.GraduateMapper;
import com.BYS.GWSystem.mapper.ResumeMapper;
import com.BYS.GWSystem.model.Graduate;
import com.BYS.GWSystem.service.IGraduateService;
import com.github.pagehelper.Page;


@Service
public class GraduateServiceImpl implements IGraduateService {
	
	@Autowired
	private GraduateMapper graduateMapper;
	
	@Autowired
	private ResumeMapper resumeMapper;
	
	
	DecimalFormat formater = new DecimalFormat("#0.##");
	public Double StudentResumeEditingRate() {
		//学生总数
		int a=graduateMapper.selectGraduateNumber();
		//已编辑简历数
		int b=resumeMapper.selectEditedResumeNumber();
		float totalPrice= ((float)b/a*100);
		return Double.parseDouble(formater.format(totalPrice));
	}
	
	//未编辑简历学生
	public List<GraduateDto> selectNotFilled() {
		return graduateMapper.selectNotFilledStudent(); 
	}
	//未编辑简历学生分页
	public Page<GraduateDto> PageNotFilled() {
		
		return (Page<GraduateDto>) graduateMapper.selectNotFilledStudent(); 
	}

	//学生列表
	public List<GraduateDto> selectCheckingStudents() {
		return graduateMapper.selectCheckingStudents();
	}

	public Page<GraduateDto> PageCheckingStudents() {
		return (Page<GraduateDto>) graduateMapper.selectBestResumeStudents();
	}
	
	//最受欢迎学生列表
	public List<GraduateDto> selectBestResumeStudents() {
		return graduateMapper.selectBestResumeStudents();
	}


	public Page<GraduateDto> PageBestResumeStudents() {
		return (Page<GraduateDto>) graduateMapper.selectBestResumeStudents();
	}

	

}