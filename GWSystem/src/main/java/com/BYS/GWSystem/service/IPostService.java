package com.BYS.GWSystem.service;
import java.util.List;

import com.BYS.GWSystem.model.*;
import com.github.pagehelper.Page;


public interface IPostService  {
	//招聘信息关注率
	public Double AttentionRateOfRecruitmentInformation();
	
	//查询工作岗位简要信息
	public  List<Post> jobList(String registrationId);
	public  Page<Post> jobListPage(String registrationId);
}
