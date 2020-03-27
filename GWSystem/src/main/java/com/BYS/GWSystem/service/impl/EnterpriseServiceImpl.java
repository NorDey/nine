package com.BYS.GWSystem.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BYS.GWSystem.mapper.EnterpriseMapper;
import com.BYS.GWSystem.mapper.PostMapper;
import com.BYS.GWSystem.service.IEnterpriseService;


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

}
