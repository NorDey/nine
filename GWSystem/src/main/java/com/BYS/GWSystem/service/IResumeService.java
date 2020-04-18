package com.BYS.GWSystem.service;

import java.util.List;

import com.BYS.GWSystem.dto.ResumeDto;
import com.BYS.GWSystem.dto.ResumeHiredDto;
import com.github.pagehelper.Page;

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

	// 公司收到的投递的简历查询
	public List<ResumeHiredDto> selectResumeByErId(String registrationId);

	public Page<ResumeHiredDto> selectResumeByErIdPage(String registrationId);

	// 添加
	public int insertResume(ResumeDto resume);

	// 查询简历是否存在
	public ResumeDto queryResumeById(String studentId);

	// 通过前两个ID确认是哪个简历并更新该简历(1被打回去2录用3表示备选4投递)
	public int updateHiredCollectionMsg(String studentId, String postId, int updateCode);

	// 计算投递的简历数量，collection=4
	public Integer ResumeCount(String registrationId);

	// 计算通过的简历数量，collection=3
	public Integer ResumePassCount(String registrationId);
	//简历通过的话插入enterpriseHistory
	public int insertENHistory(String registrationId, String resumeIds,int updateCode);
	//简历通过的话插入enterpriseHistory判断是否已经插入过了
	public boolean notexitresumeId(String registrationId,String resumeIds);
	//简历通过的话插入enterpriseHistory判断是否已经插入过了如果已经插入，就只做修改
	public int updateENHistory(String registrationId, String resumeIds, int updateCode);
}
