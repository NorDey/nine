package com.BYS.GWSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.BYS.GWSystem.model.Enterprise;
import com.BYS.GWSystem.model.Graduate;

@Controller
public class PublicController {

	@GetMapping("/GWsystem")
	public String mainPage(Model model) {
		return "Public/index";// 系统主页
	}

	// 登录页面
	@GetMapping("/switchLog")
	public String log(Model model) {
		// 跳转到公司登录页面的OBJ
		model.addAttribute("enterprise", new Enterprise());
		// 跳转到毕业生登录页面的OBJ
		model.addAttribute("graduate", new Graduate());
		return "Public/SwitchLogin";

	}

	// 注册页面
	@GetMapping("/switchRegister")
	public String register(Model model) {
		// 跳转到公司登录页面的OBJ
		model.addAttribute("enterprise", new Enterprise());
		// 跳转到毕业生登录页面的OBJ
		model.addAttribute("graduate", new Graduate());
		return "Public/SwitchRegister";

	}

}
