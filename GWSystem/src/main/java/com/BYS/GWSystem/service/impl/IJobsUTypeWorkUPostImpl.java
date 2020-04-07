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
	//新建一个招聘信息
	@Override
	public int insertNewJobs(CompanyHiredInfoDto greeting) {
		// TODO Auto-generated method stub 一个针对post第二个针对type_work的insert
		return jobsMapper.insertNewJobs(greeting)+jobsMapper.insertNewJobs2(greeting);
	}
	//统计数目
	@Override
	public String count() {
		// TODO Auto-generated method stub
		return jobsMapper.count();
	}

	// 按名字查询 
	@Override
	public String seletOne(String postName) {
		// TODO Auto-generated method stub
		return jobsMapper.selectOne(postName);
	}
	
}
