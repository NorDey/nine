package com.BYS.GWSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.BYS.GWSystem.dto.CompanyHiredInfoDto;
import com.BYS.GWSystem.dto.TypeWorkUJobs;
import com.BYS.GWSystem.model.Admin;
import com.BYS.GWSystem.model.Enterprise;
import com.BYS.GWSystem.model.Graduate;
import com.BYS.GWSystem.model.Post;
import com.BYS.GWSystem.service.IEnterpriseService;
import com.BYS.GWSystem.service.IJobsUTypeWorkUPsotService;
import com.BYS.GWSystem.service.IPostService;
import com.BYS.GWSystem.service.IResumeService;
import com.BYS.GWSystem.service.ITypeWorkService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class PublicController {
	@Autowired
	private IEnterpriseService iEnterpriseService;
	@Autowired
	private IPostService iPostService;
	@Autowired
	private IJobsUTypeWorkUPsotService ijobInfoService;
	@Autowired
	private ITypeWorkService itypeWork;
	@Autowired
	private IResumeService iresume;

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
		// 跳转到管理员登录页面的OBJ
		model.addAttribute("admin", new Admin());
		return "Public/SwitchLogin";

	}

	// 注册页面
	@GetMapping("/switchRegister")
	public String register(Model model) {
		// 跳转到公司登录页面的OBJ
		model.addAttribute("enterprise", new Enterprise());
		// 跳转到毕业生登录页面的OBJ
		model.addAttribute("graduate", new Graduate());
		// 跳转到管理员登录页面的OBJ
		model.addAttribute("admin", new Admin());
		return "Public/SwitchRegister";
	}
	
	@GetMapping("/CHIQ/{postId}") // 公司招聘的详情（可给未登录看）
	public String CHIQ(@PathVariable(name = "postId") String postId, Model model) {
		CompanyHiredInfoDto JobsInfo = ijobInfoService.searchOne(postId);// 招聘的详情
		Post postMsg = iPostService.selectOneHiredMsg(postId);
		Enterprise enterpriseInfo = iEnterpriseService.selectEnterpriseOne(postMsg.getRegistrationId().toString());
		model.addAttribute("enterprisesInfo", enterpriseInfo);// Cheader头部的信息刷新
		model.addAttribute("JobsInfo", JobsInfo);// 传入岗位信息
		model.addAttribute("postMsg", postMsg);// 传入岗位简要信息
		return "Company/CompanyHiredInfoQuality";
	}
	

	@GetMapping("/CHIS/{page}") // 公司招聘信息简要列表（可给未登录看）
	public String CHIS(@PathVariable(name = "page") int page, Model model) {
		if (page <= 0)
			page = 1;
		PageHelper.startPage(page, 5); // 第几页，每页几条
		PageInfo<Post> psotSimpleList = new PageInfo<>(iPostService.jobListAll());// 将原list转为page类型
		page = pageMax(page, psotSimpleList);
		List<TypeWorkUJobs> Professions = (itypeWork.AllPros());// 取出所有的岗位父类与所有的根据父岗位查询的岗位名称
		model.addAttribute("pros", Professions);
		model.addAttribute("psotSimpleList", psotSimpleList);
		model.addAttribute("pages", "第" + page + "页");
		model.addAttribute("page", page);
		return "Company/CompanyHiredInfoToShow";
	}
	
	
	@GetMapping("/CHIS/{postnames}/{page}") // 公司招聘信息简要列表（可给未登录看）按照岗位分类后
	public String CHISBypostnames(@PathVariable(name = "postnames") String postName,@PathVariable(name = "page") int page, Model model) {
		if (page <= 0)
			page = 1;
		PageHelper.startPage(page, 5); // 第几页，每页几条
		PageInfo<Post> psotSimpleList = new PageInfo<>(iPostService.jobListArrage(postName));// 将原list转为page类型
		page = pageMax(page, psotSimpleList);
		List<TypeWorkUJobs> Professions = (itypeWork.AllPros());// 取出所有的岗位父类与所有的根据父岗位查询的岗位名称
		model.addAttribute("postnamesArrage",postName);
		model.addAttribute("pros", Professions);
		model.addAttribute("psotSimpleList", psotSimpleList);
		model.addAttribute("pages", "第" + page + "页");
		model.addAttribute("page", page);
		return "Company/CompanyHiredInfoToShowArrage";
	}
	// form表单的页面输入式跳转
	@PostMapping("/CHIS") // 公司招聘信息简要列表（可给未登录看）
	public String CHISPageTurn(@RequestParam(value = "pagesTurn") Integer pagesTurn, Model model) {
		// @RequestParam(value="pagesTurn") value的值与form表单中的某个input的name值相同即可取其值()value
		if(pagesTurn == null)pagesTurn=1;
		int page = pageMinx(pagesTurn);
		PageHelper.startPage(page, 5); // 第几页，每页几条
		PageInfo<Post> psotSimpleList = new PageInfo<>(iPostService.jobListAll());// 将原list转为page类型
		if (page >= psotSimpleList.getLastPage())
			page = psotSimpleList.getLastPage();
		pageMax(page, psotSimpleList);
		List<TypeWorkUJobs> Professions = (itypeWork.AllPros());// 取出所有的岗位父类与所有的根据父岗位查询的岗位名称
		model.addAttribute("pros", Professions);
		model.addAttribute("psotSimpleList", psotSimpleList);
		model.addAttribute("pages", "第" + page + "页");
		model.addAttribute("page", page);
		return "Company/CompanyHiredInfoToShow";
	}
	// form表单的页面输入式跳转
		@PostMapping("/CHIS/{postnames}") // 公司招聘信息简要列表（可给未登录看）分类后
		public String CHISPageTurnArrage(@PathVariable(name = "postnames") String postName,@RequestParam(value = "pagesTurn") Integer pagesTurn, Model model) {
			// @RequestParam(value="pagesTurn") value的值与form表单中的某个input的name值相同即可取其值()value
			if(pagesTurn == null)pagesTurn=1;
			int page = pageMinx(pagesTurn);
			PageHelper.startPage(page, 5); // 第几页，每页几条
			PageInfo<Post> psotSimpleList = new PageInfo<>(iPostService.jobListArrage(postName));// 将原list转为page类型
			if (page >= psotSimpleList.getLastPage())
				page = psotSimpleList.getLastPage();
			pageMax(page, psotSimpleList);
			List<TypeWorkUJobs> Professions = (itypeWork.AllPros());// 取出所有的岗位父类与所有的根据父岗位查询的岗位名称
			model.addAttribute("postnamesArrage", postName);
			model.addAttribute("pros", Professions);
			model.addAttribute("psotSimpleList", psotSimpleList);
			model.addAttribute("pages", "第" + page + "页");
			model.addAttribute("page", page);
			return "Company/CompanyHiredInfoToShowArrage";
		}
		
		@PostMapping("/CHISLikeSearch/{page}") // 公司招聘信息简要列表！模糊查询！
		public String CHISLikeSearchs(@RequestParam(value = "search_keyword") String postNamesL,@PathVariable(name = "page") Integer pagesTurn, Model model) {
			/*---------------------------------------------------------*/
			// @RequestParam(value="pagesTurn") value的值与form表单中的某个input的name值相同即可取其值()value
			if(pagesTurn == null)pagesTurn=1;
			int page = pageMinx(pagesTurn);
			PageHelper.startPage(page, 5); // 第几页，每页几条
			PageInfo<Post> psotSimpleList = new PageInfo<>(iPostService.jobListLike(postNamesL));// 将原list转为page类型
			if (page >= psotSimpleList.getLastPage())
				page = psotSimpleList.getLastPage();
			pageMax(page, psotSimpleList);
			List<TypeWorkUJobs> Professions = (itypeWork.AllPros());// 取出所有的岗位父类与所有的根据父岗位查询的岗位名称
			model.addAttribute("postNamesL", postNamesL);
			model.addAttribute("pros", Professions);
			model.addAttribute("psotSimpleList", psotSimpleList);
			model.addAttribute("pages", "第" + page + "页");
			model.addAttribute("page", page);
			return "Company/CompanyHiredInfoToShow2Like";
		}
		
		@GetMapping("/CHISLikeSearchPageN/{page}/{postNamesL}") // 公司招聘信息简要列表！模糊查询！再分页
		public String CHISLikeSearchPageN(@PathVariable(name = "postNamesL") String postNamesL,@PathVariable(name = "page") Integer pagesTurn, Model model) {
			/*---------------------------------------------------------*/
			// @RequestParam(value="pagesTurn") value的值与form表单中的某个input的name值相同即可取其值()value
			int page = pageMinx(pagesTurn);
			PageHelper.startPage(page, 5); // 第几页，每页几条
			PageInfo<Post> psotSimpleList = new PageInfo<>(iPostService.jobListLike(postNamesL));// 将原list转为page类型
			if (page >= psotSimpleList.getLastPage())
				page = psotSimpleList.getLastPage();
			pageMax(page, psotSimpleList);
			List<TypeWorkUJobs> Professions = (itypeWork.AllPros());// 取出所有的岗位父类与所有的根据父岗位查询的岗位名称
			model.addAttribute("pros", Professions);
			model.addAttribute("psotSimpleList", psotSimpleList);
			model.addAttribute("pages", "第" + page + "页");
			model.addAttribute("page", page);
			return "Company/CompanyHiredInfoToShow2Like";
		}
		@PostMapping("/CHISLikeSearchPageTurns/{postNamesL}") // 公司招聘信息简要列表！模糊查询！再分页Post跳转
		public String CHISLikeSearchPageTurns(@PathVariable(name = "postNamesL") String postNamesL,@RequestParam(value = "pagesTurn") Integer pagesTurn, Model model) {
			
			/*---------------------------------------------------------*/
			// @RequestParam(value="pagesTurn") value的值与form表单中的某个input的name值相同即可取其值()value
			int page = pageMinx(pagesTurn);
			PageHelper.startPage(page, 5); // 第几页，每页几条
			PageInfo<Post> psotSimpleList = new PageInfo<>(iPostService.jobListLike(postNamesL));// 将原list转为page类型
			if (page >= psotSimpleList.getLastPage())
				page = psotSimpleList.getLastPage();
			pageMax(page, psotSimpleList);
			List<TypeWorkUJobs> Professions = (itypeWork.AllPros());// 取出所有的岗位父类与所有的根据父岗位查询的岗位名称
			model.addAttribute("pros", Professions);
			model.addAttribute("psotSimpleList", psotSimpleList);
			model.addAttribute("pages", "第" + page + "页");
			model.addAttribute("page", page);
			return "Company/CompanyHiredInfoToShow2Like";
		}	
		
	/*-----------------------页码控制函数-----------------------------*/
	public int pageMinx(Integer pagesTurn) {
		int page = 1;
		if (pagesTurn >= 1 && pagesTurn != null)
			page = pagesTurn;
		if (page <= 0)
			page = 1;
		return page;
	}

	public int pageMax(int page, PageInfo<Post> psotSimpleList) {

		if (page >= psotSimpleList.getLastPage())
			page = psotSimpleList.getLastPage();
		return page;
	}
	/*-----------------------页码控制函数-----------------------------*/

}
