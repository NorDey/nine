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

import com.BYS.GWSystem.dto.CompanyHiredInfoDto;
import com.BYS.GWSystem.dto.ResumeHiredDto;
import com.BYS.GWSystem.dto.TypeWorkUJobs;
import com.BYS.GWSystem.model.Admin;
import com.BYS.GWSystem.model.Enterprise;
import com.BYS.GWSystem.model.Graduate;
import com.BYS.GWSystem.model.Post;
import com.BYS.GWSystem.model.TypeWork;
import com.BYS.GWSystem.service.IEnterpriseService;
import com.BYS.GWSystem.service.IJobsUTypeWorkUPsotService;
import com.BYS.GWSystem.service.IPostService;
import com.BYS.GWSystem.service.IResumeService;
import com.BYS.GWSystem.service.ITypeWorkService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class CompanyMainCtroller {
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

	@GetMapping("/CC/{registrationId}/{page}") // 公司收藏--公司的简历收录
	public String CC(@PathVariable(name = "page") int page,@PathVariable(name = "registrationId") String registrationId,Model model) {
		Enterprise enterpriseInfo = iEnterpriseService.selectEnterpriseOne(registrationId);
		model.addAttribute("ResumeCount", iresume.ResumeCount(registrationId));// Cheader头部的信息刷新
		model.addAttribute("ResumePassCount", iresume.ResumePassCount(registrationId));// Cheader头部的信息刷新
		model.addAttribute("enterprises", enterpriseInfo);// Cheader头部的信息刷新
		if (page <= 0)
			page = 1;
		PageHelper.startPage(page, 5); // 第几页，每页几条
		PageInfo<ResumeHiredDto> resumeHird = new PageInfo<>(iresume.selectResumeByErId(registrationId));// 将原list转为page类型
		if (page >= resumeHird.getLastPage())
			page = resumeHird.getLastPage();
		model.addAttribute("pages", page);
		model.addAttribute("resumeHird", resumeHird);
		System.out.println("-----------------"+resumeHird.toString());
		return "Company/CompanyCollection";
	}
	
	@GetMapping("/CC/{registrationId}/{page}/{studentId}/{postId}/{updateCode}") // 公司收藏--公司的简历收录(修改录用状态)
	public String CCupdate(@PathVariable(name = "updateCode") int updateCode,@PathVariable(name = "postId") String postId,@PathVariable(name = "studentId") String studentId,@PathVariable(name = "page") int page,@PathVariable(name = "registrationId") String registrationId,Model model) {
		Enterprise enterpriseInfo = iEnterpriseService.selectEnterpriseOne(registrationId);
		model.addAttribute("ResumeCount", iresume.ResumeCount(registrationId));// Cheader头部的信息刷新
		model.addAttribute("ResumePassCount", iresume.ResumePassCount(registrationId));// Cheader头部的信息刷新
		model.addAttribute("enterprises", enterpriseInfo);// Cheader头部的信息刷新
		if (page <= 0)
			page = 1;
		PageHelper.startPage(page, 5); // 第几页，每页几条
		PageInfo<ResumeHiredDto> resumeHird = new PageInfo<>(iresume.selectResumeByErId(registrationId));// 将原list转为page类型
		iresume.updateHiredCollectionMsg(studentId,postId,updateCode);//通过前两个ID确认是哪个简历并更新该简历(1被打回去2录用3表示备选4投递)
		if (page >= resumeHird.getLastPage())
			page = resumeHird.getLastPage();
		model.addAttribute("pages", page);
		model.addAttribute("resumeHird", resumeHird);
		System.out.println("-----------------"+resumeHird.toString());
		return "Company/CompanyCollection";
	}
	
	@GetMapping("/CH/{registrationId}/{page}") // 公司浏览简历的历史记录--改为简历投递的接收
	public String CH(@PathVariable(name = "page") int page,@PathVariable(name = "registrationId") String registrationId,Model model) {
		Enterprise enterpriseInfo = iEnterpriseService.selectEnterpriseOne(registrationId);
		model.addAttribute("ResumeCount", iresume.ResumeCount(registrationId));// Cheader头部的信息刷新
		model.addAttribute("ResumePassCount", iresume.ResumePassCount(registrationId));// Cheader头部的信息刷新
		model.addAttribute("enterprises", enterpriseInfo);// Cheader头部的信息刷新
		if (page <= 0)
			page = 1;
		PageHelper.startPage(page, 5); // 第几页，每页几条
		PageInfo<ResumeHiredDto> resumeHird = new PageInfo<>(iresume.selectResumeByErId(registrationId));// 将原list转为page类型
		if (page >= resumeHird.getLastPage())
			page = resumeHird.getLastPage();
		model.addAttribute("pages", page);
		model.addAttribute("resumeHird", resumeHird);
		System.out.println("-----------------"+resumeHird.toString());
		return "Company/CompanyHistory";
	}
	
	@GetMapping("/CH/{registrationId}/{page}/{studentId}/{postId}/{updateCode}") // 公司浏览简历的历史记录--改为简历投递的接收(修改录用状态)
	public String CHupdate(@PathVariable(name = "updateCode") int updateCode,@PathVariable(name = "postId") String postId,@PathVariable(name = "studentId") String studentId,@PathVariable(name = "page") int page,@PathVariable(name = "registrationId") String registrationId,Model model) {
		Enterprise enterpriseInfo = iEnterpriseService.selectEnterpriseOne(registrationId);
		model.addAttribute("ResumeCount", iresume.ResumeCount(registrationId));// Cheader头部的信息刷新
		model.addAttribute("ResumePassCount", iresume.ResumePassCount(registrationId));// Cheader头部的信息刷新
		model.addAttribute("enterprises", enterpriseInfo);// Cheader头部的信息刷新
		if (page <= 0)
			page = 1;
		PageHelper.startPage(page, 5); // 第几页，每页几条
		PageInfo<ResumeHiredDto> resumeHird = new PageInfo<>(iresume.selectResumeByErId(registrationId));// 将原list转为page类型
		iresume.updateHiredCollectionMsg(studentId,postId,updateCode);//通过前两个ID确认是哪个简历并更新该简历(1被打回去2录用3表示备选4投递)
		if (page >= resumeHird.getLastPage())
			page = resumeHird.getLastPage();
		model.addAttribute("pages", page);
		model.addAttribute("resumeHird", resumeHird);
		System.out.println("-----------------"+resumeHird.toString());
		return "Company/CompanyHistory";
	}
	

	@GetMapping("/CHIC/{postId}/{registrationId}") // 公司修改招聘信息
	public String CHIC(@PathVariable(name = "registrationId") String registrationId,
			@PathVariable(name = "postId") String postId, Model model) {
		Enterprise enterpriseInfo = iEnterpriseService.selectEnterpriseOne(registrationId);
		model.addAttribute("ResumeCount", iresume.ResumeCount(registrationId));// Cheader头部的信息刷新
		model.addAttribute("ResumePassCount", iresume.ResumePassCount(registrationId));// Cheader头部的信息刷新
		model.addAttribute("enterprises", enterpriseInfo);// Cheader头部的信息刷新
		CompanyHiredInfoDto JobsInfo = ijobInfoService.searchOne(postId);
		model.addAttribute("JobsInfo", JobsInfo);// 传入正在修改的岗位信息
		return "Company/CompanyHiredInfoChange";
	}

	@PostMapping("/CHICupdate/{postId}/{registrationId}/1")
	public String CHICUpdate(@ModelAttribute CompanyHiredInfoDto greeting,
			@PathVariable(name = "registrationId") String registrationId, @PathVariable(name = "postId") String postId,
			Model model) {
		Enterprise enterpriseInfo = iEnterpriseService.selectEnterpriseOne(registrationId);
		model.addAttribute("ResumeCount", iresume.ResumeCount(registrationId));// Cheader头部的信息刷新
		model.addAttribute("ResumePassCount", iresume.ResumePassCount(registrationId));// Cheader头部的信息刷新
		model.addAttribute("enterprises", enterpriseInfo);// Cheader头部的信息刷新
		greeting.setPostId(postId);
		greeting.setRegistrationId(registrationId);
		greeting.setTypeId(iPostService.toGetTid(greeting.getPostName()));// 按照新的岗位名分配id
		greeting.setFatherTypeId(iPostService.toGetFid(greeting.getProfession()));// 系统安排一个fatherTypeID，父类ID
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%" + ijobInfoService.updateJobsHiredMSG(greeting));
		CompanyHiredInfoDto JobsInfo = ijobInfoService.searchOne(postId);
		PageHelper.startPage(1,3);
		PageInfo<Post> psotSimpleList = new PageInfo<>(iPostService.jobList(registrationId));// 将原list转为page类型
		model.addAttribute("JobsInfo", JobsInfo);// 传入正在修改的岗位信息
		model.addAttribute("psotSimpleList", psotSimpleList);
		model.addAttribute("pages", "第" + 1 + "页");
		model.addAttribute("page", 1);
		model.addAttribute("UpdateMsg", "岗位修改："+greeting.getPostName()+"修改完成");
		return "Company/CompanyManger";
	}

	@GetMapping("/CNH/{registrationId}") // 公司新建招聘信息
	public String CNH(@PathVariable(name = "registrationId") String registrationId, Model model) {
		Enterprise enterpriseInfo = iEnterpriseService.selectEnterpriseOne(registrationId);
		model.addAttribute("ResumeCount", iresume.ResumeCount(registrationId));// Cheader头部的信息刷新
		model.addAttribute("ResumePassCount", iresume.ResumePassCount(registrationId));// Cheader头部的信息刷新
		model.addAttribute("enterprises", enterpriseInfo);// Cheader头部的信息刷新
		model.addAttribute("JobsInfo", new CompanyHiredInfoDto());
		model.addAttribute("waringMSG", "务必确认无误！");
		return "Company/CompanyNewHired";
	}

	@PostMapping("/CNHinsert/{registrationId}") // 公司新建招聘信息
	public String CNHinsert(@ModelAttribute CompanyHiredInfoDto greeting,
			@PathVariable(name = "registrationId") String registrationId, Model model) {
		Enterprise enterpriseInfo = iEnterpriseService.selectEnterpriseOne(registrationId);
		model.addAttribute("ResumeCount", iresume.ResumeCount(registrationId));// Cheader头部的信息刷新
		model.addAttribute("ResumePassCount", iresume.ResumePassCount(registrationId));// Cheader头部的信息刷新
		model.addAttribute("enterprises", enterpriseInfo);// Cheader头部的信息刷新
		model.addAttribute("JobsInfo", greeting);
		greeting.setFatherTypeId(iPostService.toGetFid(greeting.getProfession()));// 系统安排一个fatherTypeID，父类ID
		greeting.setPostId(ijobInfoService.count());// 系统安排一个postID
		greeting.setTypeId(iPostService.toGetTid(greeting.getPostName()));
		model.addAttribute("waringMSG", "务必确认无误！");
		if (greeting.getProfession() == null || greeting.getProfession() == "") {
			greeting.setProfession("其他");
		}
		if (ijobInfoService.seletOne(greeting.getRegistrationId(),greeting.getPostName()) != null
				|| ijobInfoService.seletOne(greeting.getRegistrationId(),greeting.getPostName()) != "") {// 不可重复添加同一个岗位
			model.addAttribute("waringMSG", "无法重复添加同一招聘信息");
			//System.out.println(ijobInfoService.seletOne(greeting.getRegistrationId(),greeting.getPostName()));
			return "Company/CompanyNewHired";
		}
		System.out.println(greeting.toString() + "-----------------------");
		ijobInfoService.insertNewJobs(greeting);
		model.addAttribute("waringMSG", "已完成添加！！！返回其他页面，请使用头部导航");
		return "Company/CompanyNewHired";
	}

	@GetMapping("/CMDel/{postId}/{registrationId}/{page}") // 公司删除招聘信息
	public String CHDel(@PathVariable(name = "page") int page,
			@PathVariable(name = "registrationId") String registrationId, @PathVariable(name = "postId") String postId,
			Model model) {
		Enterprise enterpriseInfo = iEnterpriseService.selectEnterpriseOne(registrationId);
		model.addAttribute("ResumeCount", iresume.ResumeCount(registrationId));// Cheader头部的信息刷新
		model.addAttribute("ResumePassCount", iresume.ResumePassCount(registrationId));// Cheader头部的信息刷新
		model.addAttribute("enterprises", enterpriseInfo);// Cheader头部的信息刷新
		if (page <= 0)
			page = 1;
		PageHelper.startPage(page, 3); // 第几页，每页几条
		PageInfo<Post> psotSimpleList = new PageInfo<>(iPostService.jobList(registrationId));// 将原list转为page类型
		if (page >= psotSimpleList.getLastPage())
			page = psotSimpleList.getLastPage();
		model.addAttribute("psotSimpleList", psotSimpleList);
		model.addAttribute("pages", "第" + page + "页");
		model.addAttribute("page", page);
		model.addAttribute("UpdateMsg", "岗位删除完成");
		System.out.println("删除+" + postId + "===" + iPostService.deleteOneHired(postId));
		return "Company/CompanyManger";
	}

	
	/*------------------------------查看内容---------------------------------*/
	 
	@GetMapping("/CHISEntLogQuality/{postId}/{registrationId}") // 公司招聘的详情（公司未登录看）
	public String CHISEntLogQuality(@PathVariable(name = "registrationId") String registrationId,@PathVariable(name = "postId") String postId, Model model) {
		Enterprise enterpriseInfo = iEnterpriseService.selectEnterpriseOne(registrationId);
		model.addAttribute("ResumeCount", iresume.ResumeCount(registrationId));// Cheader头部的信息刷新
		model.addAttribute("ResumePassCount", iresume.ResumePassCount(registrationId));// Cheader头部的信息刷新
		model.addAttribute("enterprises", enterpriseInfo);// Cheader头部的信息刷新
		/*---------------------------------------------------------*/
		CompanyHiredInfoDto JobsInfo = ijobInfoService.searchOne(postId);// 招聘的详情
		Post postMsg = iPostService.selectOneHiredMsg(postId);
		Enterprise enterpriseInfos = iEnterpriseService.selectEnterpriseOne(postMsg.getRegistrationId().toString());
		model.addAttribute("enterprisesInfo", enterpriseInfos);// Cheader头部的信息刷新
		model.addAttribute("JobsInfo", JobsInfo);// 传入岗位信息
		model.addAttribute("postMsg", postMsg);// 传入岗位简要信息
		return "Company/CompanyHiredInfoQuality";
	}
	@GetMapping("/CHISEntLog/{registrationId}/{page}") // 公司招聘信息简要列表（公司登录后看）
	public String CHISEntLog(@PathVariable(name = "registrationId") String registrationId,@PathVariable(name = "page") int page, Model model) {
		Enterprise enterpriseInfo = iEnterpriseService.selectEnterpriseOne(registrationId);
		model.addAttribute("ResumeCount", iresume.ResumeCount(registrationId));// Cheader头部的信息刷新
		model.addAttribute("ResumePassCount", iresume.ResumePassCount(registrationId));// Cheader头部的信息刷新
		model.addAttribute("enterprises", enterpriseInfo);// Cheader头部的信息刷新
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
	
	@GetMapping("/CHISEntLog/{postnames}/{page}/{registrationId}") // 公司招聘信息简要列表（公司登录看）按照岗位分类后
	public String CHISEntLogBypostnames(@PathVariable(name = "registrationId") String registrationId,@PathVariable(name = "postnames") String postName,@PathVariable(name = "page") int page, Model model) {
		Enterprise enterpriseInfo = iEnterpriseService.selectEnterpriseOne(registrationId);
		model.addAttribute("ResumeCount", iresume.ResumeCount(registrationId));// Cheader头部的信息刷新
		model.addAttribute("ResumePassCount", iresume.ResumePassCount(registrationId));// Cheader头部的信息刷新
		model.addAttribute("enterprises", enterpriseInfo);// Cheader头部的信息刷新
		/*---------------------------------------------------------*/
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
		@PostMapping("/CHISEntLogPage/{registrationId}") // 公司招聘信息简要列表（公司登录后看）
		public String CHISEntLogPageTurn(@PathVariable(name = "registrationId") String registrationId,@RequestParam(value = "pagesTurn") Integer pagesTurn, Model model) {
			Enterprise enterpriseInfo = iEnterpriseService.selectEnterpriseOne(registrationId);
			model.addAttribute("ResumeCount", iresume.ResumeCount(registrationId));// Cheader头部的信息刷新
			model.addAttribute("ResumePassCount", iresume.ResumePassCount(registrationId));// Cheader头部的信息刷新
			model.addAttribute("enterprises", enterpriseInfo);// Cheader头部的信息刷新
			/*---------------------------------------------------------*/
			// @RequestParam(value="pagesTurn") value的值与form表单中的某个input的name值相同即可取其值()value
			if(pagesTurn==null)pagesTurn=1; 
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
				@PostMapping("/CHISEntLogPageTurnArrage/{postnames}/{registrationId}") // 公司招聘信息简要列表（公司登录看）分类后
				public String CHISEntLogPageTurnArrage(@PathVariable(name = "registrationId") String registrationId,@PathVariable(name = "postnames") String postName,@RequestParam(value = "pagesTurn") Integer pagesTurn, Model model) {
					Enterprise enterpriseInfo = iEnterpriseService.selectEnterpriseOne(registrationId);
					model.addAttribute("ResumeCount", iresume.ResumeCount(registrationId));// Cheader头部的信息刷新
					model.addAttribute("ResumePassCount", iresume.ResumePassCount(registrationId));// Cheader头部的信息刷新
					model.addAttribute("enterprises", enterpriseInfo);// Cheader头部的信息刷新
					/*---------------------------------------------------------*/
					// @RequestParam(value="pagesTurn") value的值与form表单中的某个input的name值相同即可取其值()value
					if(pagesTurn==null)pagesTurn=1;
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
				
				@PostMapping("/CHISEntLogLikeSearch/{registrationId}/{page}") // 公司招聘信息简要列表！模糊查询！（公司登录后看）
				public String CHISEntLogLikeSearchs(@RequestParam(value = "search_keyword") String postNamesL,@PathVariable(name = "registrationId") String registrationId,@PathVariable(name = "page") Integer pagesTurn, Model model) {
					Enterprise enterpriseInfo = iEnterpriseService.selectEnterpriseOne(registrationId);
					model.addAttribute("ResumeCount", iresume.ResumeCount(registrationId));// Cheader头部的信息刷新
					model.addAttribute("ResumePassCount", iresume.ResumePassCount(registrationId));// Cheader头部的信息刷新
					model.addAttribute("enterprises", enterpriseInfo);// Cheader头部的信息刷新
					/*---------------------------------------------------------*/
					// @RequestParam(value="pagesTurn") value的值与form表单中的某个input的name值相同即可取其值()value
					if(pagesTurn == null)pagesTurn=1;
					if(postNamesL==null||postNamesL=="")postNamesL="a";
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
				@GetMapping("/CHISEntLogLikeSearchPageN/{registrationId}/{page}/{postNamesL}") // 公司招聘信息简要列表！模糊查询！（公司登录后看）再分页
				public String CHISEntLogLikeSearchPageN(@PathVariable(name = "postNamesL") String postNamesL,@PathVariable(name = "registrationId") String registrationId,@PathVariable(name = "page") Integer pagesTurn, Model model) {
					Enterprise enterpriseInfo = iEnterpriseService.selectEnterpriseOne(registrationId);
					model.addAttribute("ResumeCount", iresume.ResumeCount(registrationId));// Cheader头部的信息刷新
					model.addAttribute("ResumePassCount", iresume.ResumePassCount(registrationId));// Cheader头部的信息刷新
					model.addAttribute("enterprises", enterpriseInfo);// Cheader头部的信息刷新
					/*---------------------------------------------------------*/
					// @RequestParam(value="pagesTurn") value的值与form表单中的某个input的name值相同即可取其值()value
					if(pagesTurn == null)pagesTurn=1;
					if(postNamesL==null||postNamesL=="")postNamesL="a";
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
				
				@PostMapping("/CHISEntLogLikeSearchPageTurns/{registrationId}/{postNamesL}") // 公司招聘信息简要列表！模糊查询！（公司登录后看）再分页再分页Post跳转
				public String CHISEntLogLikeSearchPageTurns(@PathVariable(name = "postNamesL") String postNamesL,@PathVariable(name = "registrationId") String registrationId,@RequestParam(value = "pagesTurn") Integer pagesTurn, Model model) {
					Enterprise enterpriseInfo = iEnterpriseService.selectEnterpriseOne(registrationId);
					model.addAttribute("ResumeCount", iresume.ResumeCount(registrationId));// Cheader头部的信息刷新
					model.addAttribute("ResumePassCount", iresume.ResumePassCount(registrationId));// Cheader头部的信息刷新
					model.addAttribute("enterprises", enterpriseInfo);// Cheader头部的信息刷新
					/*---------------------------------------------------------*/
					// @RequestParam(value="pagesTurn") value的值与form表单中的某个input的name值相同即可取其值()value
					if(pagesTurn == null)pagesTurn=1;
					int page = pageMinx(pagesTurn);
					PageHelper.startPage(page, 5); // 第几页，每页几条
					if(postNamesL==null||postNamesL=="")postNamesL="a";
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
				
	
	/*-----------------------页面控制部分---------------------------*/
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
	/*
	 * @GetMapping("/CI") public String CI(Model model) { return
	 * "Company/CompanyInfo"; }
	 */

	// 公司的主页信息（可以修改）
	// 客户get请求一个form页面时，返回的model里有一个空Enterprise实例，去binding页面的th:object，没有bingding则报错
	@GetMapping("/CI") // 这个案例不要删！！！！
	public String greetingForm(Model model) {
		model.addAttribute("enterprise", new Enterprise());
		return "Public/SwitchLogin"; // 返回表单输入页
	}

	// form提交映射到此处，@ModelAttribute映射页面的th:object，从而将form捕获并封装成Enterprise对象
	@PostMapping("/CI")
	public String CIgreetingSubmit(@ModelAttribute Enterprise greeting, Model model) {

		Enterprise enterpriseInfo = iEnterpriseService.selectEnterpriseOne(greeting.getRegistrationId());
		if (enterpriseInfo == null) {
			model.addAttribute("wrongCodePWD", "密码错误");
			model.addAttribute("graduate", new Graduate());
			model.addAttribute("admin", new Admin());
			return "Public/SwitchLogin"; // 密码验证失败
		}
		if (enterpriseInfo.getPassword().equals(greeting.getPassword())) {
			model.addAttribute("enterprises", enterpriseInfo);
			model.addAttribute("ResumeCount", iresume.ResumeCount(enterpriseInfo.getRegistrationId()));// Cheader头部的信息刷新
			model.addAttribute("ResumePassCount", iresume.ResumePassCount(enterpriseInfo.getRegistrationId()));// Cheader头部的信息刷新
			return "Company/CompanyInfo";// 提交表单后跳转的页面
		}
		model.addAttribute("wrongCodePWD", "密码错误");
		model.addAttribute("graduate", new Graduate());
		model.addAttribute("admin", new Admin());
		return "Public/SwitchLogin"; // 密码验证失败
	}

	@GetMapping("/CIS/{registrationId}") // 公司的主页信息（给别人看，不可以跳转修改）
	public String CIS(@PathVariable(name = "registrationId") String registrationId, Model model) {
		Enterprise enterpriseInfo = iEnterpriseService.selectEnterpriseOne(registrationId);
		model.addAttribute("enterprises", enterpriseInfo);
		return "Company/CompanyInfoToShow";
		//th:href="@{'/CIS/'+${enterprisesInfo.registrationId}}"
	}

	@GetMapping("/CM/{registrationId}/{page}") // 公司招聘信息简要列表（可以修改）
	public String CM(@PathVariable(name = "registrationId") String registrationId,
			@PathVariable(name = "page") int page, Model model) {
		Enterprise enterpriseInfo = iEnterpriseService.selectEnterpriseOne(registrationId);
		model.addAttribute("ResumeCount", iresume.ResumeCount(registrationId));// Cheader头部的信息刷新
		model.addAttribute("ResumePassCount", iresume.ResumePassCount(registrationId));// Cheader头部的信息刷新
		model.addAttribute("enterprises", enterpriseInfo);// Cheader头部的信息刷新
		if (page <= 0)
			page = 1;
		PageHelper.startPage(page, 3); // 第几页，每页几条
		PageInfo<Post> psotSimpleList = new PageInfo<>(iPostService.jobList(registrationId));// 将原list转为page类型
		if (page >= psotSimpleList.getLastPage())
			page = psotSimpleList.getLastPage();
		model.addAttribute("psotSimpleList", psotSimpleList);
		model.addAttribute("pages", "第" + page + "页");
		model.addAttribute("page", page);
		return "Company/CompanyManger";
	}

	// form表单的页面输入式跳转
	@PostMapping("/CM/{registrationId}") // 公司招聘信息简要列表（可以修改）
	public String CMPageTurn(@PathVariable(name = "registrationId") String registrationId,
			@RequestParam(value = "pagesTurn") Integer pagesTurn, Model model) {
		// @RequestParam(value="pagesTurn") value的值与form表单中的某个input的name值相同即可取其值()value
		int page = 1;
		if (pagesTurn >= 1 && pagesTurn != null)
			page = pagesTurn;
		System.out.println("=======================" + pagesTurn);
		Enterprise enterpriseInfo = iEnterpriseService.selectEnterpriseOne(registrationId);
		model.addAttribute("ResumeCount", iresume.ResumeCount(registrationId));// Cheader头部的信息刷新
		model.addAttribute("ResumePassCount", iresume.ResumePassCount(registrationId));// Cheader头部的信息刷新
		model.addAttribute("enterprises", enterpriseInfo);// Cheader头部的信息刷新
		if (page <= 0)
			page = 1;
		PageHelper.startPage(page, 3); // 第几页，每页几条
		PageInfo<Post> psotSimpleList = new PageInfo<>(iPostService.jobList(registrationId));// 将原list转为page类型
		if (page >= psotSimpleList.getLastPage())
			page = psotSimpleList.getLastPage();
		model.addAttribute("psotSimpleList", psotSimpleList);
		model.addAttribute("pages", "第" + page + "页");
		model.addAttribute("page", page);
		return "Company/CompanyManger";
	}

	/*
	 * @GetMapping("/CRCI/{enterprises.registrationId}") // 公司主页信息修改页面 public String
	 * CRCI(@PathVariable("enterprises.registrationId") String registrationId,Model
	 * model) { System.out.println(registrationId); return
	 * "Company/CompanyRebuildCompanyInfo"; }
	 */

	@GetMapping("/CRCI") // 公司主页信息修改页面
	public String CRCIs(Model model, HttpServletRequest request) {
		String registrationId = request.getParameter("registrationId");// 获取公司的注册ID
		Enterprise enterpriseInfo = iEnterpriseService.selectEnterpriseOne(registrationId);
		model.addAttribute("ResumeCount", iresume.ResumeCount(registrationId));// Cheader头部的信息刷新
		model.addAttribute("ResumePassCount", iresume.ResumePassCount(registrationId));// Cheader头部的信息刷新
		model.addAttribute("enterprises", enterpriseInfo);
		return "Company/CompanyRebuildCompanyInfo";
	}

	@PostMapping("/CRCI")
	public String CRCIgreetingSubmit(@ModelAttribute Enterprise greeting, Model model) {
		//System.out.println(greeting.toString());
		System.out.println(iEnterpriseService.updateCInfo(greeting));
		Enterprise enterpriseInfo = iEnterpriseService.selectEnterpriseOne(greeting.getRegistrationId());
		model.addAttribute("ResumeCount", iresume.ResumeCount(enterpriseInfo.getRegistrationId()));// Cheader头部的信息刷新
		model.addAttribute("ResumePassCount", iresume.ResumePassCount(enterpriseInfo.getRegistrationId()));// Cheader头部的信息刷新
		model.addAttribute("enterprises", enterpriseInfo);
		return "Company/CompanyInfo";
	}

}
