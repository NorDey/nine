package com.BYS.GWSystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.BYS.GWSystem.model.Enterprise;

@Mapper
public interface EnterpriseMapper {
	
	//查询企业
	public List<Enterprise> selectEnterpriseList();
	
	//更改
	public int updateEnterprise(Enterprise enterprise);
	
	//添加
	public int insertEnterprise(Enterprise enterprise);
	
	//删除
	public int deleteEnterprise(Enterprise enterprise);
	
}
