package com.BYS.GWSystem.service;

import com.BYS.GWSystem.dto.ResumeDto;
import com.BYS.GWSystem.model.Resume;

public interface IResumeService {
	// 简历关注率
	public Double ResumeAttentionRate();

	// 简历被关注大于某个值的占比
	public Double BeConcernedAbout(Integer large, Integer Small);

	// 简历未被关注的占比
	public Double AttentionEquals();

	// 未编辑简历占比
	public Double NotInPlace();

	// 简历查询
	public ResumeDto selectResumeById(Long id);

	// 更改
	public int updateResume(ResumeDto resume);

	// 添加
	public int insertResume(ResumeDto resume);

	// 查询简历是否存在
	public ResumeDto queryResumeById(String studentId);
}
