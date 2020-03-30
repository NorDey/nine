package com.BYS.GWSystem.controller;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.BYS.GWSystem.dto.GraduateDto;
import com.BYS.GWSystem.dto.HomeDto;
import com.BYS.GWSystem.dto.ResumeDto;
import com.BYS.GWSystem.model.Enterprise;
import com.BYS.GWSystem.model.Graduate;
import com.BYS.GWSystem.model.Resume;
import com.BYS.GWSystem.service.IEnterpriseService;
import com.BYS.GWSystem.service.IGraduateService;
import com.BYS.GWSystem.service.IPostService;
import com.BYS.GWSystem.service.IResumeService;
import com.BYS.GWSystem.utils.GetPetAgeUtils;

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

	
	//主页加载页
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
		homeDto.setT4(100-homeDto.getT1()-homeDto.getT2()-homeDto.getT3()-homeDto.getT5());//没得法子了啊
		model.addAttribute("HomeDto", homeDto);
		return "admin/Home";
	}
	
	@GetMapping("/notFilled")
	public String showNotFilled(Model model) {
		List<GraduateDto> listGraduateDto= iGraduateService.selectNotFilled();
		model.addAttribute("listGraduate", listGraduateDto);
		return "admin/SeeStudent";
	}
	
	@GetMapping("/CheckingStudents")
	public String showCheckingStudents(Model model) {
		List<GraduateDto> listGraduateDto= iGraduateService.selectCheckingStudents();
		model.addAttribute("listGraduate", listGraduateDto);
		return "admin/SeeStudent";
	}
	
	//根据id查询简历
	@GetMapping("/viewResume/{id}")
	public String viewResume(@PathVariable(name = "id") Long id,Model model) {
		ResumeDto resumeDto=iResumeService.selectResumeById(id);
		if (resumeDto.getBirthday()!=null) {
			resumeDto.setAge(GetPetAgeUtils.getAgeByBirth(resumeDto.getBirthday()));//生日转年龄
		}
		model.addAttribute("resumeDto", resumeDto);
		return "admin/StudentDetails";
	}
	
	//最受欢迎简历
	@GetMapping("/bestResume")
	public String showBestResume(Model model) {
		List<GraduateDto> listGraduateDto= iGraduateService.selectBestResumeStudents();
		model.addAttribute("listGraduate", listGraduateDto);
		return "admin/SeeStudent";
	}
	
	//企业列表
	@GetMapping("/enterpriseList")
	public String showEnterpriseList(Model model) {
		Enterprise enterprise=new Enterprise();
		enterprise.setExamination(1);
		List<Enterprise> enterprises=iEnterpriseService.selectEnterpriseList(enterprise);
		model.addAttribute("enterprises", enterprises);
		return "admin/SeeEnterprise";
	}
	
	//企业详情
		@GetMapping("/companyDetails/{id}")
		public String showCompanyDetails(@PathVariable(name = "id") Long id,Model model) {
			Enterprise enterprise=iEnterpriseService.selectEnterprise(id);
			model.addAttribute("enterprise", enterprise);
			return "admin/EnterpriseDetails";
		}
}
