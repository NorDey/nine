package com.BYS.GWSystem.service;
import java.util.List;

import com.BYS.GWSystem.dto.*;
public interface IJobsUTypeWorkUPsotService {
	//联表更新招聘信息
	public int updateJobsHiredMSG(CompanyHiredInfoDto jobs);
	//联表查询某一个招聘信息
	public CompanyHiredInfoDto searchOne(String postId);
	//新建一个招聘信息
	public int insertNewJobs(CompanyHiredInfoDto greeting);
	//统计数目
	public String count();
	//按名字搜索一次,一个公司只能一个同名岗位
	public List<String> seletOne(String registrationId,String postName);
	
}
