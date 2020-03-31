package com.BYS.GWSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Controller
public class PublicController {
	@GetMapping("/GWsystem")//系统主页
	public String mainPage(Model model) {
		return "admin/index";
	}
	
	@GetMapping("/switchLog")//登录页面
	public String log(Model model) {
		return "admin/SwitchLogin";
	}
}
