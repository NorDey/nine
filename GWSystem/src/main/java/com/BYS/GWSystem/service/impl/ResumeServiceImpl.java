package com.BYS.GWSystem.service.impl;

import java.awt.List;
import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BYS.GWSystem.dto.ResumeDto;
import com.BYS.GWSystem.mapper.GraduateMapper;
import com.BYS.GWSystem.mapper.ResumeMapper;
import com.BYS.GWSystem.model.Resume;
import com.BYS.GWSystem.service.IResumeService;

@Service
public class ResumeServiceImpl implements IResumeService {

	@Autowired
	private ResumeMapper resumeMapper;

	@Autowired
	private GraduateMapper graduateMapper;
	
	DecimalFormat formater = new DecimalFormat("#0.##");
	
	// 简历关注率
	public Double ResumeAttentionRate() {
		int jls = resumeMapper.selectResumeNumber();// 简历数
		int bgzs = resumeMapper.selectFollowNumber();// 被关注数
		float totalPrice = ((float) bgzs / jls * 100);
		return Double.parseDouble(formater.format(totalPrice));
	}

	// 简历被关注大于某个值的占比
	public Double  BeConcernedAbout(Integer large,Integer Small) {
		// 未编辑简历数(学生总数-已编辑简历数)+简历数
		int M = (graduateMapper.selectGraduateNumber() 
				- resumeMapper.selectEditedResumeNumber())
				+ resumeMapper.selectResumeNumber();
		
		int  s=0;// 被关注数>a的数目
		if (resumeMapper.ConcernedAbout(large,Small)!=null) {
			s=resumeMapper.ConcernedAbout(large,Small);
		}
		float totalPrice = ((float) s / M * 100);
		return (double) Math.round(totalPrice);
	}

	//未被关注占比
	public Double AttentionEquals() {
		int e= resumeMapper.selectResumeNumber()-resumeMapper.selectFollowNumber();//简历数-被关注数
		int M = (graduateMapper.selectGraduateNumber() 
				- resumeMapper.selectEditedResumeNumber())
				+ resumeMapper.selectResumeNumber();
		float totalPrice = ((float) e / M * 100);
		return (double) Math.round(totalPrice);
	}

	//未编辑简历占比
	public Double NotInPlace() {
	// 未编辑简历数
	 int notEdited =graduateMapper.selectGraduateNumber()-resumeMapper.selectEditedResumeNumber();
	 //总
	 int M = (graduateMapper.selectGraduateNumber() 
				- resumeMapper.selectEditedResumeNumber())
				+ resumeMapper.selectResumeNumber();
	 float totalPrice = ((float) notEdited / M * 100);
	 return (double) Math.round(totalPrice);
	}

	//简历查询
	public ResumeDto selectResumeById(Long id) {
		return resumeMapper.selectResumeById(id);
	}

	@Override
	public int updateResume(ResumeDto resume) {
		int rest = resumeMapper.updateResume(resume);
		return rest;
	}

	@Override
	public int insertResume(ResumeDto resume) {
		int rest = resumeMapper.insertResume(resume);
		return rest;
	}

	@Override
	public ResumeDto queryResumeById(String studentId) {
		ResumeDto rest = resumeMapper.queryResumeById(studentId);
		return rest;
	}

	
}
