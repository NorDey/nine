package com.BYS.GWSystem.Test;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.BYS.GWSystem.model.Admin;
import com.BYS.GWSystem.service.IAdminService;
import com.BYS.GWSystem.service.IResumeService;
import com.BYS.GWSystem.service.impl.AdminServiceImpl;

import junit.framework.Test;

@ResponseBody
@Controller
public class TestController {

	@Autowired
	private static IResumeService iResumeService;
	@Autowired
	private IAdminService adminService;

	@RequestMapping("/caonima")
	public String show() {
		return "wo cao ni ma de bi";
	}

	@RequestMapping("/aaa")
	public String data() {
		List<Admin> admins = adminService.selectAdminList();
		Long number = null;
		String password = null;
		for (Iterator iterator = admins.iterator(); iterator.hasNext();) {
			Admin admin = (Admin) iterator.next();
			number = admin.getNumber();
			password = admin.getPassword();
		}
		return "账号:" + number + "     密码:" + password;
	}
	
	@org.junit.Test
	public void Test() {
		System.err.println(iResumeService.BeConcernedAbout(5, 2));
	}

	

}
