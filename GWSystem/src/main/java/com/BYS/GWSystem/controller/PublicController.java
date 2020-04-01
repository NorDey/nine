package com.BYS.GWSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.BYS.GWSystem.model.Enterprise;

@Controller
public class PublicController {
	
	@GetMapping("/GWsystem")
	public String mainPage(Model model) {
		return "Public/index";//系统主页
	}
	//登录页面
	@GetMapping("/switchLog")
	public String log(Model model) {
		//跳转到公司登录界面的OBJ
		model.addAttribute("enterprise", new Enterprise());
		return "Public/SwitchLogin";
	}
}
