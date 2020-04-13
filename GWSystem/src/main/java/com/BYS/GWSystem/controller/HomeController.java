package com.BYS.GWSystem.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.BYS.GWSystem.dto.GraduateDto;
import com.BYS.GWSystem.dto.HomeDto;
import com.BYS.GWSystem.dto.PostDto;
import com.BYS.GWSystem.dto.ResumeDto;
import com.BYS.GWSystem.model.Admin;
import com.BYS.GWSystem.model.Enterprise;
import com.BYS.GWSystem.model.Graduate;
import com.BYS.GWSystem.model.Post;
import com.BYS.GWSystem.service.IAdminService;
import com.BYS.GWSystem.service.IEnterpriseService;
import com.BYS.GWSystem.service.IGraduateService;
import com.BYS.GWSystem.service.IPostService;
import com.BYS.GWSystem.service.IResumeService;
import com.BYS.GWSystem.utils.ExportExcel;
import com.BYS.GWSystem.utils.GetPetAgeUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin")
public class HomeController {
	@Autowired
	private IGraduateService iGraduateService;

	@Autowired
	private IEnterpriseService iEnterpriseService;

	@Autowired
	private IPostService iPostService;

	@Autowired
	private IResumeService iResumeService;

	@Autowired
	private IAdminService iAdminService;
	// 存放查询条件
	public String lookup;

	PostDto post = new PostDto();

	@PostMapping("/adminLogin")
	public ModelAndView adminLogin(@ModelAttribute Admin admin, Model model, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		int a = iAdminService.selectAdmin(admin);
		if (a != 1) {
			modelAndView.addObject("wrongCodePWD", "登录错误,账号或密码错误");
			modelAndView.setViewName("redirect:/switchLog");
		} else {
			session.setAttribute("adminUser", admin);
			modelAndView.setViewName("redirect:/admin/home");
		}
		return modelAndView;// 返回列表
	}

	@GetMapping("/Logout")
	public ModelAndView Logout(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		session.removeAttribute("adminUser");
		modelAndView.setViewName("redirect:/admin/switchLog");
		return modelAndView;
	}

	// 主页加载页
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
		homeDto.setT4(100 - homeDto.getT1() - homeDto.getT2() - homeDto.getT3() - homeDto.getT5());// 没得法子了啊
		model.addAttribute("HomeDto", homeDto);
		return "admin/Home";
	}

	@GetMapping("/notFilled/{page}")
	public String showNotFilled(Model model, @PathVariable(name = "page") int page) {
		PageHelper.startPage(page, 10);
		PageInfo<GraduateDto> listGraduateDto = new PageInfo<>(iGraduateService.PageNotFilled());
		List<Integer> listPages = calculateOptionalPages(page, listGraduateDto.getPages());
		model.addAttribute("listPages", listPages);
		model.addAttribute("address", "notFilled");
		model.addAttribute("traversingList", listGraduateDto);
		return "admin/SeeStudent";
	}

	// 显示的可点击页数按钮
	public List<Integer> calculateOptionalPages(int page, int pages) {
		List<Integer> listPages = new ArrayList<Integer>();
		listPages.add(page);
		for (int i = 1; i <= 3; i++) {
			if (page + i <= pages) {
				listPages.add(page + i);
			}
			if (page - i > 0) {
				listPages.add(page - i);
			}
		}
		Collections.sort(listPages);
		return listPages;
	}

	// 学生列表
	/*
	 * @GetMapping("/CheckingStudents/{page}") public String
	 * showCheckingStudents(Model model, @PathVariable(name = "page") int page) {
	 * PageHelper.startPage(page, 10); PageInfo<GraduateDto> listGraduateDto = new
	 * PageInfo<>(iGraduateService.selectCheckingStudents()); List<Integer>
	 * listPages= calculateOptionalPages(page,listGraduateDto.getPages());
	 * model.addAttribute("listPages",listPages); model.addAttribute("address",
	 * "CheckingStudents"); model.addAttribute("listGraduate", listGraduateDto);
	 * return "admin/SeeStudent"; }
	 */

	@PostMapping("/seStudentsList/{page}")
	public String showCheckingStudentsByMore(@PathVariable(name = "page") int page, Model model,
			@RequestParam(value = "selectStudent", required = false) String look) {
		lookup = look;
		PageHelper.startPage(page, 10);
		PageInfo<GraduateDto> listGraduateDto = new PageInfo<>(iGraduateService.selectGraduateListByMore(lookup));
		List<Integer> listPages = calculateOptionalPages(page, listGraduateDto.getPages());
		model.addAttribute("listPages", listPages);
		model.addAttribute("returnDisplay", lookup);
		model.addAttribute("address", "seStudentsList");
		model.addAttribute("traversingList", listGraduateDto);
		return "admin/SeeStudent";
	}

	@GetMapping("/seStudentsList/{page}")
	public String showCheckingStudentsByMore(@PathVariable(name = "page") int page, Model model) {
		PageHelper.startPage(page, 10);
		PageInfo<GraduateDto> listGraduateDto = new PageInfo<>(iGraduateService.selectGraduateListByMore(lookup));
		List<Integer> listPages = calculateOptionalPages(page, listGraduateDto.getPages());
		model.addAttribute("listPages", listPages);
		model.addAttribute("returnDisplay", lookup);
		model.addAttribute("address", "seStudentsList");
		model.addAttribute("traversingList", listGraduateDto);
		return "admin/SeeStudent";
	}

	// 根据id查询简历
	@GetMapping("/viewResume/{id}")
	public String viewResume(@PathVariable(name = "id") Long id, Model model) {
		ResumeDto resumeDto = iResumeService.selectResumeById(id);
		if (resumeDto.getBirthday() != null) {
			resumeDto.setAge(GetPetAgeUtils.getAgeByBirth(resumeDto.getBirthday()));// 生日转年龄
		}
		model.addAttribute("resumeDto", resumeDto);
		return "admin/StudentDetails";
	}

	// 最受欢迎简历
	@GetMapping("/bestResume")
	public String showBestResume(Model model) {
		PageHelper.startPage(1, 10);
		PageInfo<GraduateDto> listGraduateDto = new PageInfo<>(iGraduateService.selectBestResumeStudents());
		List<Integer> listPages = calculateOptionalPages(1, listGraduateDto.getPages());
		model.addAttribute("listPages", listPages);
		model.addAttribute("address", "CheckingStudents");
		model.addAttribute("traversingList", listGraduateDto);
		return "admin/SeeStudent";
	}

	/*
	 * // 企业列表
	 * 
	 * @GetMapping("/enterpriseList/{page}") public String
	 * showEnterpriseList(@PathVariable(name = "page") int page,Model model) {
	 * Enterprise enterprise = new Enterprise(); enterprise.setExamination(1);
	 * PageHelper.startPage(page, 10); PageInfo<Enterprise> enterprises = new
	 * PageInfo<>(iEnterpriseService.selectEnterpriseList(enterprise));
	 * List<Integer> listPages= calculateOptionalPages(page,enterprises.getPages());
	 * model.addAttribute("listPages",listPages); model.addAttribute("address",
	 * "enterpriseList"); model.addAttribute("enterprises", enterprises); return
	 * "admin/SeeEnterprise"; }
	 */

	// 公司列表模糊查询/已发布公司查询
	@PostMapping(value = { "/seEnterpriseList/{page}", "/seEnterpriseList/{post}/{page}" })
	public String showEnterpriseListByMore(@PathVariable(name = "page") int page, Model model,
			@RequestParam(value = "selectEnterprise", required = false) String look,
			@PathVariable(name = "post", required = false) String post) {
		Enterprise enterprise = new Enterprise();
		lookup = look;
		enterprise.setExamination(2);
		PageHelper.startPage(page, 10);
		PageInfo<Enterprise> enterprises = null;
		if (post != null && post != " ") {
			model.addAttribute("address", "seEnterpriseList/1");
			if (lookup == null || lookup.length() <= 0) {
				enterprises = new PageInfo<>(iEnterpriseService.selectPostEnterpriseListByMore(enterprise));
			} else {
				enterprise.setEnterpriseName(lookup);// 将条件放到对象中
				enterprises = new PageInfo<>(iEnterpriseService.selectPostEnterpriseListByMore(enterprise));
			}
		} else {
			model.addAttribute("address", "seEnterpriseList");
			if (lookup == null || lookup.length() <= 0) {
				enterprises = new PageInfo<>(iEnterpriseService.selectEnterpriseListByMore(enterprise));
			} else {
				enterprise.setEnterpriseName(lookup);// 将条件放到对象中
				enterprises = new PageInfo<>(iEnterpriseService.selectEnterpriseListByMore(enterprise));
			}
		}
		List<Integer> listPages = calculateOptionalPages(page, enterprises.getPages());
		model.addAttribute("listPages", listPages);
		model.addAttribute("returnDisplay", lookup);
		model.addAttribute("traversingList", enterprises);
		return "admin/SeeEnterprise";
	}

	// 公司列表
	@GetMapping(value = { "/seEnterpriseList/{page}", "/seEnterpriseList/{post}/{page}" })
	public String showEnterpriseListByMore(@PathVariable(name = "page") int page, Model model,
			@PathVariable(name = "post", required = false) String post) {
		Enterprise enterprise = new Enterprise();
		enterprise.setExamination(2);
		PageHelper.startPage(page, 10);
		PageInfo<Enterprise> enterprises = null;
		if (post != null && post != " ") {
			model.addAttribute("address", "seEnterpriseList/1");
			if (lookup == null || lookup.length() <= 0) {
				enterprises = new PageInfo<>(iEnterpriseService.selectPostEnterpriseListByMore(enterprise));
			} else {
				enterprise.setEnterpriseName(lookup);// 将条件放到对象中
				enterprises = new PageInfo<>(iEnterpriseService.selectPostEnterpriseListByMore(enterprise));
			}
		} else {
			model.addAttribute("address", "seEnterpriseList");
			if (lookup == null || lookup.length() <= 0) {
				enterprises = new PageInfo<>(iEnterpriseService.selectEnterpriseListByMore(enterprise));
			} else {
				enterprise.setEnterpriseName(lookup);// 将条件放到对象中
				enterprises = new PageInfo<>(iEnterpriseService.selectEnterpriseListByMore(enterprise));
			}
		}
		List<Integer> listPages = calculateOptionalPages(page, enterprises.getPages());
		model.addAttribute("listPages", listPages);
		model.addAttribute("returnDisplay", lookup);
		model.addAttribute("traversingList", enterprises);
		return "admin/SeeEnterprise";
	}

	// 企业详情
	@GetMapping("/companyDetails/{id}")
	public String showCompanyDetails(@PathVariable(name = "id") String id, Model model) {
		Enterprise enterprise = iEnterpriseService.selectEnterprise(id);
		model.addAttribute("enterprise", enterprise);
		return "admin/EnterpriseDetails";
	}

	// 导出excel表
	@GetMapping("/poiExport")
	public String  exportExcel(HttpServletResponse response) throws Exception {
		// 定义表的标题
		String title = "毕业生列表一览";
		// 定义表的列名
		String[] rowsName = new String[] { "学号", "姓名", "性别", "电话", "家庭住址", "毕业去向", "就业情况", "工作岗位", "就业单位" };

		// 定义表的内容
		List<Object[]> dataList = new ArrayList<Object[]>();
		Object[] objs = null;
		List<GraduateDto> listGraduate = iGraduateService.selectCheckingStudents();
		for (int i = 0; i < listGraduate.size(); i++) {
			GraduateDto per = listGraduate.get(i);
			objs = new Object[rowsName.length];
			objs[0] = per.getStudentId();
			objs[1] = per.getStudentName();
			objs[2] = per.getSex().equals("1") ? "男" : "女";
			objs[3] = per.getPhonenumber() == null ? "-" : per.getPhonenumber();
			objs[4] = per.getHomeAddress() == null ? "-" : per.getHomeAddress();
			objs[5] = per.getWhereabouts() == null ? "-" : per.getWhereabouts();
			if (per.getCause() != null) {
				objs[6] = per.getCause().equals("1") ? "已就业" : "未就业";
			} else {
				objs[6] = "-";
			}
			objs[7] = per.getPost() == null ? "-" : per.getPost();
			objs[8] = per.getCompany() == null ? "-" : per.getCompany();
			dataList.add(objs);
		}

		// 创建ExportExcel对象
		ExportExcel ex = new ExportExcel(title, rowsName, dataList);

		// 输出Excel文件
		try {
			OutputStream output = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition",
					"attachment; filename=" + new String("学生表".getBytes("gbk"), "iso8859-1") + ".xls");
			response.setContentType("application/msexcel");
			response.setCharacterEncoding("utf-8");
			ex.export(output);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/admin/home";// 返回主页
	}

	@GetMapping("/poiImport")
	public ModelAndView importExcel() throws Exception {
		return new ModelAndView();
	}

	// 导入excel表中的数据
	@ResponseBody
	@PostMapping("/poiImport")
	public ModelAndView importExcel(@RequestParam(value = "file", required = false) MultipartFile file)
			throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		Graduate per = new Graduate();// 新建一个per对象
		try {
			@SuppressWarnings("resource")
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(file.getInputStream());// 将输入流对象存到工作簿对象里面

			// 循环工作表Sheet
			for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
				HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
				if (hssfSheet == null) {
					continue;
				}

				// 循环行Row
				for (int rowNum = 3; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
					HSSFRow hssfRow = hssfSheet.getRow(rowNum);
					if (hssfRow == null) {
						continue;
					}

					// 循环列Cell
					// "学号", "姓名", "性别", "电话"
					per.setStudentId(getValue(hssfRow.getCell(0)));
					per.setStudentName(getValue(hssfRow.getCell(1)));
					per.setSex(getValue(hssfRow.getCell(2)).equals("男") ? "1" : "0");
					per.setPhonenumber(getValue(hssfRow.getCell(3)));
					per.setPassword("123456");
					int a = iGraduateService.insertGraduate(per);// 写入到数据中
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		modelAndView.setViewName("redirect:/admin/home");
		return modelAndView;// 返回主页
	}

	/**
	 * 得到Excel表中的值
	 * 
	 * @param hssfCell Excel中的每一个格子
	 * @return Excel中每一个格子中的值
	 */
	@SuppressWarnings("static-access")
	private static String getValue(HSSFCell hssfCell) {
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			// 返回布尔类型的值
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			// 返回数值类型的值
			return String.valueOf(hssfCell.getNumericCellValue());
		} else {
			// 返回字符串类型的值
			return String.valueOf(hssfCell.getStringCellValue());
		}

	}

	// 公司申请列表
	@GetMapping("/companyApplicationList/{page}")
	public String companyApplicationList(@PathVariable(name = "page") int page, Model model) {
		Enterprise enterprise = new Enterprise();
		enterprise.setExamination(1);
		PageHelper.startPage(page, 10);
		PageInfo<Enterprise> enterprises = new PageInfo<>(iEnterpriseService.selectEnterpriseListByMore(enterprise));
		List<Integer> listPages = calculateOptionalPages(page, enterprises.getPages());
		model.addAttribute("listPages", listPages);
		model.addAttribute("address", "companyApplicationList");
		model.addAttribute("traversingList", enterprises);
		return "admin/CompanyApproval";
	}

	// 审核
	@GetMapping("/enterpriseOperation/{id}/{operation}")
	public ModelAndView EnterpriseOperation(@PathVariable(name = "id") String id,
			@PathVariable(name = "operation") Integer operation) {
		ModelAndView modelAndView = new ModelAndView();
		Enterprise enterprise = new Enterprise();
		enterprise.setRegistrationId(id);
		enterprise.setExamination(operation);
		int a = iEnterpriseService.updateCInfo(enterprise);
		modelAndView.setViewName("redirect:/admin/companyApplicationList/1");
		return modelAndView;// 返回列表
	}

	// 待审核公司数
	@RequestMapping(value = "/toBeAudited", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Integer dodataList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Enterprise enterprise = new Enterprise();
		enterprise.setExamination(1);
		List<Enterprise> enterprises = iEnterpriseService.selectEnterpriseListByMore(enterprise);
		return enterprises.size();
	}
	
	//新增加的岗位(七天内)
	@RequestMapping(value = "/doNewPostSize", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Integer doNewPostSize(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<PostDto> postDtos = iPostService.doNewPostList();
		return postDtos.size();
	}
	

	// 岗位列表
	@GetMapping(value = {"/companyRecruitmentPosition/{page}" })
	public String CompanyRecruitmentPosition(Model model, @PathVariable(name = "page") int page,
			@PathVariable(name = "registrationId", required = false) String registrationId) {
		PageHelper.startPage(page, 8);
		PostDto postDto = post;
		postDto.setRegistrationId(registrationId);
		PageInfo<PostDto> psotSimpleList = new PageInfo<>(iPostService.selectPostListByMore(postDto));// 将原list转为page类型
		List<Integer> listPages = calculateOptionalPages(page, psotSimpleList.getPages());
		model.addAttribute("postDto", post);
		model.addAttribute("listPages", listPages);
		model.addAttribute("address", "companyRecruitmentPosition");
		model.addAttribute("traversingList", psotSimpleList);
		return "admin/Post";
	}
	
	
	
	// 单个公司岗位列表
		@GetMapping(value = {"/{registrationId}/{page}"})
		public String RecruitmentPosition(Model model,@PathVariable(name = "page",required = false) int page,
				@PathVariable(name = "registrationId", required = false) String registrationId) {			
			PostDto postDto = post;
			postDto.setRegistrationId(registrationId);
			List<PostDto> psotSimpleList = iPostService.selectPostListByMore(postDto);
			model.addAttribute("traversingList", psotSimpleList);
			model.addAttribute("address", registrationId);
			return "admin/CompanyPost";
		}

	// 岗位列表
	@PostMapping(value = {"/companyRecruitmentPosition/{page}" })
	public String CompanyRecruitmentPosition(Model model, @PathVariable(name = "page") int page,
			@PathVariable(name = "registrationId", required = false) String registrationId,
			@ModelAttribute PostDto postDto) {
		post = postDto;
		PageHelper.startPage(page, 8);
		postDto.setRegistrationId(registrationId);
		PageInfo<PostDto> psotSimpleList = new PageInfo<>(iPostService.selectPostListByMore(postDto));// 将原list转为page类型
		List<Integer> listPages = calculateOptionalPages(page, psotSimpleList.getPages());
		model.addAttribute("postDto", post);
		model.addAttribute("listPages", listPages);
		model.addAttribute("address", "companyRecruitmentPosition");
		model.addAttribute("traversingList", psotSimpleList);
		return "admin/Post";
	}

	// 岗位列表
	@PostMapping(value = { "/homepageSearchPost/{page}" })
	public String HomepageSearchPost(Model model, @PathVariable(name = "page") int page,
			@RequestParam(value = "search_keyword", required = false) String postName) {
		post.setPostName(postName);
		PageHelper.startPage(page, 8);
		PageInfo<PostDto> psotSimpleList = new PageInfo<>(iPostService.selectPostListByMore(post));// 将原list转为page类型
		List<Integer> listPages = calculateOptionalPages(page, psotSimpleList.getPages());
		model.addAttribute("postDto", post);
		model.addAttribute("listPages", listPages);
		model.addAttribute("address", "companyRecruitmentPosition");
		model.addAttribute("traversingList", psotSimpleList);
		return "admin/Post";
	}
	
	//最新岗位
	@GetMapping("/seNewPostList/{page}")
	public String seNewPostList(Model model,@PathVariable(name = "page") int page) {
		PageHelper.startPage(page, 8);
		PageInfo<PostDto> psotSimpleList = new PageInfo<>(iPostService.doNewPostList());
		List<Integer> listPages = calculateOptionalPages(page, psotSimpleList.getPages());
		model.addAttribute("listPages", listPages);
		model.addAttribute("address", "seNewPostList");
		model.addAttribute("traversingList", psotSimpleList);
		return "admin/Post";
	}
	
	//热门岗位
	@GetMapping("/seHotPosts/{page}")
	public String seHotPosts(Model model,@PathVariable(name = "page") int page) {
		PageHelper.startPage(page, 8);
		PostDto postDto=new PostDto(); 
		postDto.setPopular(2);
		PageInfo<PostDto> psotSimpleList = new PageInfo<>(iPostService.selectPostListByMore(postDto));
		List<Integer> listPages = calculateOptionalPages(page, psotSimpleList.getPages());
		model.addAttribute("listPages", listPages);
		model.addAttribute("address", "seHotPosts");
		model.addAttribute("traversingList", psotSimpleList);
		return "admin/Post";
	}

	//热门修改
	@GetMapping("/setUpPopular/{popular}/{postId}/{address}")
	public ModelAndView setUpPopular(@PathVariable(name = "popular") int popular,
			@PathVariable(name = "postId") String postId,
			@PathVariable(name = "address") String address) {
		ModelAndView modelAndView=new ModelAndView();
		Post post=new Post();
		post.setPopular(popular);
		post.setPostId(postId);
		int a=iPostService.updatePost(post);
		modelAndView.setViewName("redirect:/admin/"+address+"/1");
		return modelAndView;
	}
	
	//密码修改
	@GetMapping("/changePassword")
	public String changePassword() {
		return "admin/ChangePassword";
	}
	
	
	//密码修改
		@PostMapping("/changePassword")
		public String changePassword(@RequestParam(value = "exampleInputPassword", required = false) String Password,
				@RequestParam(value = "exampleInputPassword1", required = false) String Password1,
				@RequestParam(value = "exampleInputPassword2", required = false) String Password2,
			HttpSession session,Model model) {
			
			if(Password1 == null || Password1.length() <= 0) {
				model.addAttribute("error", "密码为空,请输入密码");
				return "admin/ChangePassword";
			}
			//获取当前登录用户
			Admin admin=(Admin) session.getAttribute("adminUser");
			if (Password1.equals(Password2)||Password1==Password2) {
				if (admin.getPassword().equals(Password)||admin.getPassword()==(Password)) {
					admin.setPassword(Password1);
					iAdminService.updateAdmin(admin);
					session.removeAttribute("adminUser");
				}else {
					model.addAttribute("error", "原密码错误");
					return "admin/ChangePassword";
				}
			}else {
				model.addAttribute("error", "两次输入密码不一");
				return "admin/ChangePassword";
			}		
			return "redirect:/admin/switchLog";
		}
}
