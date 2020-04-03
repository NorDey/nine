package com.BYS.GWSystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.BYS.GWSystem.model.Enterprise;

@Mapper
public interface EnterpriseMapper {

	// 查询一部分企业
	public List<Enterprise> selectEnterpriseList(Enterprise enterprise);

	// 查询某个企业
	public Enterprise selectEnterpriseOne(@Param(value="registrationId")String registrationId);

	// 更改
	public int updateEnterprise(Enterprise enterprise);

	// 添加
	public int insertEnterprise(Enterprise enterprise);

	// 删除
	public int deleteEnterprise(Enterprise enterprise);

	// 查询企业总数
	public int selectEnterpriseNumber();

	// 查看公司详情
	public Enterprise selectEnterprise(String id);

	//模糊查询企业列表
	public List<Enterprise> selectEnterpriseListByMore(Enterprise enterprise);

}
