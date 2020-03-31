package com.BYS.GWSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.BYS.GWSystem.model.Enterprise;


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
	
	/*
	 * @GetMapping("/CI") public String CI(Model model) { return
	 * "admin/CompanyInfo"; }
	 */
	
	//公司的主页信息（可以修改）
	//客户get请求一个form页面时，返回的model里有一个空Enterprise实例，去binding页面的th:object，没有bingding则报错
    @GetMapping("/CI")
    public String greetingForm(Model model) {
        model.addAttribute("enterprise", new Enterprise());
        return "LogMsg"; //返回表单输入页
    }

    //form提交映射到此处，@ModelAttribute映射页面的th:object，从而将form捕获并封装成Enterprise对象
    @PostMapping("/CI")
    public String greetingSubmit(@ModelAttribute Enterprise greeting) {
    	System.out.println(greeting.getRegistrationId());
    	System.out.println(greeting.getPassword());
    	greeting.getRegistrationId();
    	greeting.getPassword();
        return "admin/CompanyHistory"; //提交表单后跳转的页面
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
