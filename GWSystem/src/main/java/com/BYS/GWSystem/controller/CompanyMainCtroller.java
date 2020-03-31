package com.BYS.GWSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Controller
public class CompanyMainCtroller {
	
	@GetMapping("/CompanySwitchLog")
	public String Log(Model model) {
		return "admin/SwitchLogin";
	}
	
}
