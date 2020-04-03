package com.BYS.GWSystem.service;
import com.BYS.GWSystem.dto.*;
public interface IJobsUTypeWorkUPsotService {
	//联表更新招聘信息
	public int updateJobsHiredMSG(CompanyHiredInfoDto jobs);
	//联表查询某一个招聘信息
	public CompanyHiredInfoDto searchOne(String postId);

	
}
