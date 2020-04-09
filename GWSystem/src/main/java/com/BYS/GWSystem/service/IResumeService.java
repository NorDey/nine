package com.BYS.GWSystem.service;

import java.util.List;

import com.BYS.GWSystem.dto.ResumeDto;
import com.BYS.GWSystem.dto.ResumeHiredDto;
import com.github.pagehelper.Page;

public interface IResumeService {
	//简历关注率
	public Double ResumeAttentionRate();

	//简历被关注大于某个值的占比
	public Double  BeConcernedAbout(Integer large,Integer Small);
	//简历未被关注的占比
	public Double  AttentionEquals();
	//未编辑简历占比
	public Double  NotInPlace();

	//简历查询
	public ResumeDto selectResumeById(Long id);
	//公司收到的投递的简历查询
	public List<ResumeHiredDto> selectResumeByErId(String registrationId);
	public Page<ResumeHiredDto> selectResumeByErIdPage(String registrationId);

}
