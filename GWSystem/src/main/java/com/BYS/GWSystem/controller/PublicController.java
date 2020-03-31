package com.BYS.GWSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PublicController {
	
	@GetMapping("/GWsystem")
	public String mainPage(Model model) {
		return "admin/index";//系统主页
	}
	//登录页面
	@GetMapping("/switchLog")
	public String log(Model model) {
		return "admin/SwitchLogin";
	}
}
