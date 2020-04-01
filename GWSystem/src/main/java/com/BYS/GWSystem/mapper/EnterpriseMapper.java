package com.BYS.GWSystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.BYS.GWSystem.model.Enterprise;

@Mapper
public interface EnterpriseMapper {
	
	//查询企业
	public List<Enterprise> selectEnterpriseList(Enterprise enterprise);
	
	//更改
	public int updateEnterprise(Enterprise enterprise);
	
	//添加
	public int insertEnterprise(Enterprise enterprise);
	
	//删除
	public int deleteEnterprise(Enterprise enterprise);

	//查询企业总数
	public int selectEnterpriseNumber();

	//查看公司详情
	public Enterprise selectEnterprise(Long id);
	
}