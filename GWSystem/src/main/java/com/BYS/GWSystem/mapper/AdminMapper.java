package com.BYS.GWSystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.BYS.GWSystem.model.Admin;

@Mapper
public interface AdminMapper  {

	public List<Admin> selectAdminList();

	//登录验证
	public int selectAdmin(Admin admin);

	//修改密码
	public int updateAdmin(Admin admin);
	
}
