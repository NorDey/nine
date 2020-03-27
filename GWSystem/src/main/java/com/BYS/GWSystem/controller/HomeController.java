package com.BYS.GWSystem.controller;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.BYS.GWSystem.dto.HomeDto;
import com.BYS.GWSystem.service.IEnterpriseService;
import com.BYS.GWSystem.service.IGraduateService;
import com.BYS.GWSystem.service.IPostService;
import com.BYS.GWSystem.service.IResumeService;

@Controller
public class HomeController {
	@Autowired
	private IGraduateService iGraduateService;

	@Autowired
	private IEnterpriseService iEnterpriseService;

	@Autowired
	private IPostService iPostService;

	@Autowired
	private IResumeService iResumeService;

	
	@GetMapping("/home")
	public String showHome(Model model) {
		HomeDto homeDto = new HomeDto();
		homeDto.setResumeRate(iGraduateService.StudentResumeEditingRate());// 学生简历编辑率
		homeDto.setPostReleaseRate(iEnterpriseService.EnterpriseRecruitmentReleaseRate());// 企业招聘发布率
		homeDto.setPostAttentionRate(iPostService.AttentionRateOfRecruitmentInformation());// 招聘信息关注率
		homeDto.setResumeAttentionRate(iResumeService.ResumeAttentionRate());// 学生简历关注率
		homeDto.setT1(iResumeService.BeConcernedAbout(null, 5));// 简历被关注大于某个值的占比
		homeDto.setT2(iResumeService.BeConcernedAbout(5, 2));
		homeDto.setT3(iResumeService.BeConcernedAbout(2, 0));
		homeDto.setT5(iResumeService.NotInPlace());
		homeDto.setT4(100-homeDto.getT1()-homeDto.getT2()-homeDto.getT3()-homeDto.getT5());//没得法子了
		model.addAttribute("HomeDto", homeDto);
		return "admin/Home";
	}

}
