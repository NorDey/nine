package com.BYS.GWSystem.Testcontroller;



import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BYS.GWSystem.model.Admin;
import com.BYS.GWSystem.service.IAdminService;
import com.BYS.GWSystem.service.impl.AdminServiceImpl;


@RestController
public class TestController {
	
	@Autowired
	private IAdminService adminService;
	
	@RequestMapping("caonima")
		public String show() {
			return "wo cao ni ma de bi";
		}
	
	@RequestMapping("aaa")
	public String data() {
		List<Admin> admins= adminService.selectAdminList();
		Long number = null;
		String password = null;
		for (Iterator iterator = admins.iterator(); iterator.hasNext();) {
			Admin admin = (Admin) iterator.next();
			number=admin.getNumber();
			password=admin.getPassword();
		}
		return "账号:"+number+"     密码:"+password;
	}

}

