package com.BYS.GWSystem.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.BYS.GWSystem.model.Enterprise;
import com.BYS.GWSystem.model.Post;
import com.BYS.GWSystem.service.IEnterpriseService;
import com.BYS.GWSystem.service.IPostService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class CompanyMainCtroller {
	@Autowired
	private IEnterpriseService iEnterpriseService;
	@Autowired
	private IPostService iPostService;

	@GetMapping("/CC") // 公司收藏
	public String CC(Model model) {
		return "Company/CompanyCollection";
	}

	@GetMapping("/CHIC/{postId}/{registrationId}") // 公司修改招聘信息
	public String CHIC(@PathVariable(name = "registrationId") String registrationId,@PathVariable(name = "postId") String postId,Model model) {
		Enterprise enterpriseInfo = iEnterpriseService.selectEnterpriseOne(registrationId);
		model.addAttribute("enterprises",enterpriseInfo);//Cheader头部的信息刷新
		Post HiredMsgDetil = iPostService.selectOneHiredMsg(postId);
		model.addAttribute("post",HiredMsgDetil);//传入正在修改的岗位信息
		return "Company/CompanyHiredInfoChange";
	}
	
	@GetMapping("/CMDel/{postId}/{registrationId}/{page}") // 公司删除招聘信息
	public String CHDel(@PathVariable(name = "page") int page,@PathVariable(name = "registrationId") String registrationId,@PathVariable(name = "postId") String postId,Model model) {
		Enterprise enterpriseInfo = iEnterpriseService.selectEnterpriseOne(registrationId);
		model.addAttribute("enterprises",enterpriseInfo);//Cheader头部的信息刷新
		if(page<=0)page=1;
		PageHelper.startPage(page, 3);	//第几页，每页几条
		PageInfo<Post> psotSimpleList = new PageInfo<>(iPostService.jobList(registrationId));//将原list转为page类型
		if(page>=psotSimpleList.getLastPage())page=psotSimpleList.getLastPage();
		model.addAttribute("psotSimpleList",psotSimpleList);
		model.addAttribute("pages","第"+page+"页");
		model.addAttribute("page",page);
		System.out.println("删除+"+postId+"==="+iPostService.deleteOneHired(postId));
		return "Company/CompanyManger";
	}

	@GetMapping("/CHIQ") // 公司招聘的详情（给未登录看）
	public String CHIQ(Model model) {
		return "Company/CompanyHiredInfoQuality";
	}

	@GetMapping("/CHIS") // 公司招聘信息简要列表（给未登录看）
	public String CHIS(Model model) {
		return "Company/CompanyHiredInfoToShow";
	}

	@GetMapping("/CH") // 公司浏览简历的历史记录
	public String CH(Model model) {
		return "Company/CompanyHistory";
	}

	/*
	 * @GetMapping("/CI") public String CI(Model model) { return
	 * "Company/CompanyInfo"; }
	 */

	// 公司的主页信息（可以修改）
	// 客户get请求一个form页面时，返回的model里有一个空Enterprise实例，去binding页面的th:object，没有bingding则报错
	@GetMapping("/CI")
	public String greetingForm(Model model) {
		model.addAttribute("enterprise", new Enterprise());
		return "Public/SwitchLogin"; // 返回表单输入页
	}

	// form提交映射到此处，@ModelAttribute映射页面的th:object，从而将form捕获并封装成Enterprise对象
	@PostMapping("/CI")
	public String CIgreetingSubmit(@ModelAttribute Enterprise greeting,Model model) {
		Enterprise enterpriseInfo = iEnterpriseService.selectEnterpriseOne(greeting.getRegistrationId());
		if (enterpriseInfo.getPassword().equals(greeting.getPassword()) ) {
			model.addAttribute("enterprises", enterpriseInfo);
			return "Company/CompanyInfo";// 提交表单后跳转的页面
		}
		model.addAttribute("wrongCodePWD","密码错误");
		return "Public/SwitchLogin"; // 密码验证失败
	}

	@GetMapping("/CIS") // 公司的主页信息（给别人看，不可以跳转修改）
	public String CIS(Model model) {
		return "Company/CompanyInfoToShow";
	}

	@GetMapping("/CM/{registrationId}/{page}") // 公司招聘信息简要列表（可以修改）
	public String CM(@PathVariable(name = "registrationId") String registrationId,@PathVariable(name = "page") int page,Model model) {
		Enterprise enterpriseInfo = iEnterpriseService.selectEnterpriseOne(registrationId);
		model.addAttribute("enterprises",enterpriseInfo);//Cheader头部的信息刷新
		if(page<=0)page=1;
		PageHelper.startPage(page, 3);	//第几页，每页几条
		PageInfo<Post> psotSimpleList = new PageInfo<>(iPostService.jobList(registrationId));//将原list转为page类型
		if(page>=psotSimpleList.getLastPage())page=psotSimpleList.getLastPage();
		model.addAttribute("psotSimpleList",psotSimpleList);
		model.addAttribute("pages","第"+page+"页");
		model.addAttribute("page",page);
		return "Company/CompanyManger";
	}
	//form表单的页面输入式跳转
	@PostMapping("/CM/{registrationId}") // 公司招聘信息简要列表（可以修改）
	public String CMPageTurn(@PathVariable(name = "registrationId") String registrationId,@RequestParam(value="pagesTurn") Integer pagesTurn,Model model) {
		//@RequestParam(value="pagesTurn") value的值与form表单中的某个input的name值相同即可取其值()value
		int page=1;
		if(pagesTurn>=1&&pagesTurn!=null)page=pagesTurn;
		System.out.println("======================="+pagesTurn);
		Enterprise enterpriseInfo = iEnterpriseService.selectEnterpriseOne(registrationId);
		model.addAttribute("enterprises",enterpriseInfo);//Cheader头部的信息刷新
		if(page<=0)page=1;
		PageHelper.startPage(page, 3);	//第几页，每页几条
		PageInfo<Post> psotSimpleList = new PageInfo<>(iPostService.jobList(registrationId));//将原list转为page类型
		if(page>=psotSimpleList.getLastPage())page=psotSimpleList.getLastPage();
		model.addAttribute("psotSimpleList",psotSimpleList);
		model.addAttribute("pages","第"+page+"页");
		model.addAttribute("page",page);
		return "Company/CompanyManger";
	}
	

	@GetMapping("/CNH") // 公司新建招聘信息
	public String CNH(Model model) {
		return "Company/CompanyNewHired";
	}

	/*
	 * @GetMapping("/CRCI/{enterprises.registrationId}") // 公司主页信息修改页面 public String
	 * CRCI(@PathVariable("enterprises.registrationId") String registrationId,Model
	 * model) { System.out.println(registrationId); return
	 * "Company/CompanyRebuildCompanyInfo"; }
	 */
	
	@GetMapping("/CRCI") // 公司主页信息修改页面
	public String CRCIs(Model model, HttpServletRequest request) {
		String registrationId = request.getParameter("registrationId");//获取公司的注册ID
		Enterprise enterpriseInfo = iEnterpriseService.selectEnterpriseOne(registrationId);
		model.addAttribute("enterprises",enterpriseInfo);
		return "Company/CompanyRebuildCompanyInfo";
	}
	@PostMapping("/CRCI")
	public String CRCIgreetingSubmit(@ModelAttribute Enterprise greeting,Model model) {
		System.out.println(greeting.toString());
		System.out.println(iEnterpriseService.updateCInfo(greeting));
		model.addAttribute("enterprises",greeting);
		return "Company/CompanyRebuildCompanyInfo"; 
	}

}
