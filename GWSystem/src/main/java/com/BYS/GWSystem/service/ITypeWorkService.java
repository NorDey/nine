package com.BYS.GWSystem.service;

import java.util.List;

import com.BYS.GWSystem.dto.PostDto;
import com.BYS.GWSystem.dto.TypeWorkUJobs;
import com.BYS.GWSystem.model.TypeWork;
import com.github.pagehelper.Page;

public interface ITypeWorkService{

	//查询岗位的父类信息
	public List<TypeWorkUJobs>  AllPros();
}
