package com.BYS.GWSystem.service.impl;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BYS.GWSystem.mapper.GraduateMapper;
import com.BYS.GWSystem.mapper.ResumeMapper;
import com.BYS.GWSystem.service.IGraduateService;


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

}
