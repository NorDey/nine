package com.BYS.GWSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CompanyMainCtroller {
	
	@GetMapping("/CC")//公司收藏
	public String CC(Model model) {
		return "admin/CompanyCollection";
	}
	
	@GetMapping("/CHIC")//公司修改招聘信息
	public String CHIC(Model model) {
		return "admin/CompanyHiredInfoChange";
	}
	
	@GetMapping("/CHIQ")//公司招聘的详情（给未登录看）
	public String CHIQ(Model model) {
		return "admin/CompanyHiredInfoQuality";
	}
	
	@GetMapping("/CHIS")//公司招聘信息简要列表（给未登录看）
	public String CHIS(Model model) {
		return "admin/CompanyHiredInfoToShow";
	}
	
	@GetMapping("/CH")//公司浏览简历的历史记录
	public String CH(Model model) {
		return "admin/CompanyHistory";
	}
	
	@GetMapping("/CI")//公司的主页信息（可以修改）
	public String CI(Model model) {
		return "admin/CompanyInfo";
	}
	
	@GetMapping("/CIS")//公司的主页信息（给别人看，不可以跳转修改）
	public String CIS(Model model) {
		return "admin/CompanyInfoToShow";
	}
	
	@GetMapping("/CM")//公司招聘信息简要列表（可以修改）
	public String CM(Model model) {
		return "admin/CompanyManger";
	}
	
	@GetMapping("/CNH")//公司新建招聘信息
	public String CNH(Model model) {
		return "admin/CompanyNewHired";
	}
	
	@GetMapping("/CRCI")//公司主页信息修改页面
	public String CRCI(Model model) {
		return "admin/CompanyRebuildCompanyInfo";
	}
	
}
