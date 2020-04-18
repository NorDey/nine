package com.BYS.GWSystem.service.impl;

import java.awt.List;
import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BYS.GWSystem.dto.ResumeDto;
import com.BYS.GWSystem.dto.ResumeHiredDto;
import com.BYS.GWSystem.mapper.GraduateMapper;
import com.BYS.GWSystem.mapper.ResumeMapper;
import com.BYS.GWSystem.model.Resume;
import com.BYS.GWSystem.service.IResumeService;
import com.github.pagehelper.Page;

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
	public Double BeConcernedAbout(Integer large, Integer Small) {
		// 未编辑简历数(学生总数-已编辑简历数)+简历数
		int M = (graduateMapper.selectGraduateNumber() - resumeMapper.selectEditedResumeNumber())
				+ resumeMapper.selectResumeNumber();

		int s = 0;// 被关注数>a的数目
		if (resumeMapper.ConcernedAbout(large, Small) != null) {
			s = resumeMapper.ConcernedAbout(large, Small);
		}
		float totalPrice = ((float) s / M * 100);
		return (double) Math.round(totalPrice);
	}

	// 未被关注占比
	public Double AttentionEquals() {
		int e = resumeMapper.selectResumeNumber() - resumeMapper.selectFollowNumber();// 简历数-被关注数
		int M = (graduateMapper.selectGraduateNumber() - resumeMapper.selectEditedResumeNumber())
				+ resumeMapper.selectResumeNumber();
		float totalPrice = ((float) e / M * 100);
		return (double) Math.round(totalPrice);
	}

	// 未编辑简历占比
	public Double NotInPlace() {
		// 未编辑简历数
		int notEdited = graduateMapper.selectGraduateNumber() - resumeMapper.selectEditedResumeNumber();
		// 总
		int M = (graduateMapper.selectGraduateNumber() - resumeMapper.selectEditedResumeNumber())
				+ resumeMapper.selectResumeNumber();
		float totalPrice = ((float) notEdited / M * 100);
		return (double) Math.round(totalPrice);
	}

	// 简历查询
	public ResumeDto selectResumeById(Long id) {
		return resumeMapper.selectResumeById(id);
	}


	@Override
	public int updateResume(ResumeDto resume) {
		int rest = resumeMapper.updateResume(resume);
		return rest;
	}
	// 公司收到的投递的简历查询
	@Override
	public java.util.List<ResumeHiredDto> selectResumeByErId(String registrationId) {
		// TODO Auto-generated method stub
		return resumeMapper.selectResumeByErId(registrationId);
	}

	@Override
	public Page<ResumeHiredDto> selectResumeByErIdPage(String registrationId) {
		// TODO Auto-generated method stub
		return (Page<ResumeHiredDto>) resumeMapper.selectResumeByErId(registrationId);

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
	//通过前两个ID确认是哪个简历并更新该简历(1被打回去2录用3表示备选4投递)
	@Override
	public int updateHiredCollectionMsg(String studentId, String postId, int updateCode) {
		// TODO Auto-generated method stub
		return resumeMapper.updateHiredCollectionMsg(studentId,postId,updateCode);
	}
	//计算投递的简历数量，collection=4
	@Override
	public Integer ResumeCount(String registrationId) {
		// TODO Auto-generated method stub
		return resumeMapper.ResumeCount(registrationId);
	}

	@Override
	public Integer ResumePassCount(String registrationId) {
		// TODO Auto-generated method stub
		return resumeMapper.ResumePassCount(registrationId);
	}
	//简历通过的话插入enterpriseHistory
	@Override
	public int insertENHistory(String registrationId, String resumeIds,int updateCode) {
		// TODO Auto-generated method stub
		return resumeMapper.insertENHistory(registrationId,resumeIds,updateCode);
	}
	//简历通过的话插入enterpriseHistory插入前判断是否已经插入
	@Override
	public boolean notexitresumeId(String registrationId,String resumeIds) {
		// TODO Auto-generated method stub
		String results = resumeMapper.notexitresumeId(registrationId,resumeIds);
		boolean flag = false;
		if(results==""||results==null)flag=true;
		return flag;
	}
	//简历通过的话插入enterpriseHistory判断是否已经插入过了如果已经插入，就只做修改
	@Override
	public int updateENHistory(String registrationId, String resumeIds, int updateCode) {
		// TODO Auto-generated method stub
		return resumeMapper.updateENHistory(registrationId,resumeIds,updateCode);
	}

	
}
