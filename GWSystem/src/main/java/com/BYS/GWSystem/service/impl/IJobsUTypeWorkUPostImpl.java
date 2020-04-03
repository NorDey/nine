package com.BYS.GWSystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BYS.GWSystem.dto.CompanyHiredInfoDto;
import com.BYS.GWSystem.mapper.JobsUTypeWorkUPsotMapper;
import com.BYS.GWSystem.service.IJobsUTypeWorkUPsotService;

@Service
public class IJobsUTypeWorkUPostImpl implements IJobsUTypeWorkUPsotService {
	@Autowired
	private JobsUTypeWorkUPsotMapper jobsMapper;
	
	//联表更新某一个招聘信息
	@Override
	public int updateJobsHiredMSG(CompanyHiredInfoDto job) {
		// TODO Auto-generated method stub
		return jobsMapper.updateOneMSG(job);
	}
	//联表查询某一个招聘信息
	@Override
	public CompanyHiredInfoDto searchOne(String postId) {
		// TODO Auto-generated method stub
		return jobsMapper.searchOne(postId);
	}

}
