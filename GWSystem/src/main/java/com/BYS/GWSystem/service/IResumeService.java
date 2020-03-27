package com.BYS.GWSystem.service;



public interface IResumeService {
	//简历关注率
	public Double ResumeAttentionRate();

	//简历被关注大于某个值的占比
	public Double  BeConcernedAbout(Integer large,Integer Small);
	//简历未被关注的占比
	public Double  AttentionEquals();
	//未编辑简历占比
	public Double  NotInPlace();

}
