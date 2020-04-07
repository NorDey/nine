package com.BYS.GWSystem.service.impl;

import java.util.List;

import javax.print.attribute.standard.RequestingUserName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BYS.GWSystem.mapper.EnterpriseMapper;
import com.BYS.GWSystem.mapper.PostMapper;
import com.BYS.GWSystem.model.Enterprise;
import com.BYS.GWSystem.service.IEnterpriseService;
import com.github.pagehelper.Page;

@Service
public class EnterpriseServiceImpl implements IEnterpriseService {

	@Autowired
	private EnterpriseMapper enterpriseMapper;

	@Autowired
	private PostMapper postMapper;

	// 企业招聘发布率
	public Double EnterpriseRecruitmentReleaseRate() {
		int a = enterpriseMapper.selectEnterpriseNumber();
		int b = postMapper.selectRecruitmentCompaniesNumber();
		float totalPrice = ((float) b / a * 100);

		return (double) (float) (Math.round(totalPrice * 100) / 100);
	}

	// 查看企业列表
	public List<Enterprise> selectEnterpriseList(Enterprise enterprise) {
		return enterpriseMapper.selectEnterpriseList(enterprise);
	}

	public Page<Enterprise> PageEnterpriseList(Enterprise enterprise) {
		return (Page<Enterprise>) enterpriseMapper.selectEnterpriseList(enterprise);
	}

	//模糊查询企业列表
	public List<Enterprise> selectEnterpriseListByMore(Enterprise enterprise) {
		return enterpriseMapper.selectEnterpriseListByMore(enterprise);
	}
	public Page<Enterprise> PageEnterpriseListByMore(Enterprise enterprise) {
		return (Page<Enterprise>)enterpriseMapper.selectEnterpriseListByMore(enterprise);
	}
	
	
	// 查看公司详情
	public Enterprise selectEnterprise(String id) {
		return enterpriseMapper.selectEnterprise(id);
	}

	// 企业登陆验证ID并返回信息
	@Override
	public Enterprise selectEnterpriseOne(String registrationId) {
		return enterpriseMapper.selectEnterpriseOne(registrationId);
	}
	// 更新企业信息
	@Override
	public int updateCInfo(Enterprise greeting) {
		return enterpriseMapper.updateEnterprise(greeting);
	}
	
	//已发布招聘信息的公司查询加模糊查询
	@Override
	public List<Enterprise> selectPostEnterpriseListByMore(Enterprise enterprise) {		
		return enterpriseMapper.selectPostEnterpriseListByMore(enterprise);
	}
	@Override
	public Page<Enterprise> PagePostEnterpriseListByMore(Enterprise enterprise) {
		return (Page<Enterprise>) enterpriseMapper.selectPostEnterpriseListByMore(enterprise);
	}

	

}
