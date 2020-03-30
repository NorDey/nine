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
public class EnterpriseServiceImpl  implements IEnterpriseService {

	@Autowired
	private EnterpriseMapper enterpriseMapper;
	
	@Autowired
	private PostMapper postMapper;
	
	//企业招聘发布率
	public Double EnterpriseRecruitmentReleaseRate() {
		 int a= enterpriseMapper.selectEnterpriseNumber();
		 int b= postMapper.selectRecruitmentCompaniesNumber();
		 float totalPrice= ((float)b/a*100);
			
		return (double)(float)(Math.round(totalPrice*100)/100);
	}
	
	//查看企业列表
	public List<Enterprise> selectEnterpriseList( Enterprise enterprise) {
		return enterpriseMapper.selectEnterpriseList(enterprise);
	}
	
	public Page<Enterprise> PageEnterpriseList(Enterprise enterprise) {
		return (Page<Enterprise>) enterpriseMapper.selectEnterpriseList(enterprise);
	}

	//查看公司详情
	public Enterprise selectEnterprise(Long id) {
		return enterpriseMapper.selectEnterprise(id);
	}

	

}
