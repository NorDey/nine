package com.BYS.GWSystem.service;

import java.util.List;

import com.BYS.GWSystem.model.Admin;

public interface IAdminService  {

	public List<Admin> selectAdminList();
	
	//登录验证
	public int selectAdmin(Admin admin);

	//更改密码
	public int  updateAdmin(Admin admin);
}
