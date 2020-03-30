package com.BYS.GWSystem.service;

import java.util.List;

import com.BYS.GWSystem.model.Enterprise;

public interface IEnterpriseService  {

	//企业招聘发布率
	public Double EnterpriseRecruitmentReleaseRate();

	//查看企业列表
	public List<Enterprise> selectEnterpriseList(Enterprise enterprise);

	//查看公司详情
	public Enterprise selectEnterprise(Long id);
	

}
