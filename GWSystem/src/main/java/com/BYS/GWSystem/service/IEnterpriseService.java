package com.BYS.GWSystem.service;

import java.util.List;

import com.BYS.GWSystem.dto.GraduateDto;
import com.BYS.GWSystem.model.Enterprise;
import com.github.pagehelper.Page;

public interface IEnterpriseService {

	// 企业招聘发布率
	public Double EnterpriseRecruitmentReleaseRate();

	// 查看企业列表
	public List<Enterprise> selectEnterpriseList(Enterprise enterprise);

	public Page<Enterprise> PageEnterpriseList(Enterprise enterprise);

	// 查看公司详情
	public Enterprise selectEnterprise(String id);

	// 企业登陆验证ID并返回信息
	public Enterprise selectEnterpriseOne(String registrationId);

}
