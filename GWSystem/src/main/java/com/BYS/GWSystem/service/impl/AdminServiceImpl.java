package com.BYS.GWSystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BYS.GWSystem.mapper.AdminMapper;
import com.BYS.GWSystem.model.Admin;
import com.BYS.GWSystem.service.IAdminService;

@Service
public class AdminServiceImpl implements IAdminService {

	@Autowired
	private AdminMapper adminMapper;
	
	public List<Admin> selectAdminList() {
		// TODO Auto-generated method stub
		return adminMapper.selectAdminList();
	}

	
	//登录验证
	@Override
	public int selectAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return adminMapper.selectAdmin(admin);
	}

}
