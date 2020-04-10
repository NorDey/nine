package com.BYS.GWSystem.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.BYS.GWSystem.dto.CompanyHiredInfoDto;
import com.BYS.GWSystem.dto.PostDto;
import com.BYS.GWSystem.dto.ResumeDto;
import com.BYS.GWSystem.dto.TypeWorkUJobs;
import com.BYS.GWSystem.model.Admin;
import com.BYS.GWSystem.model.Enterprise;
import com.BYS.GWSystem.model.Graduate;
import com.BYS.GWSystem.model.Post;
import com.BYS.GWSystem.model.StudentHistory;
import com.BYS.GWSystem.service.IEnterpriseService;
import com.BYS.GWSystem.service.IGraduateService;
import com.BYS.GWSystem.service.IJobsUTypeWorkUPsotService;
import com.BYS.GWSystem.service.IPostService;
import com.BYS.GWSystem.service.IResumeService;
import com.BYS.GWSystem.service.ITypeWorkService;
import com.BYS.GWSystem.utils.GetPetAgeUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/graduate")
public class GraduateController {

	@Autowired
	private IGraduateService iGraduateService;

	@Autowired
	private IEnterpriseService iEnterpriseService;

	@Autowired
	private IPostService iPostService;

	@Autowired
	private IResumeService iResumeService;

	@Autowired
	private ITypeWorkService itypeWork;

	@Autowired
	private IJobsUTypeWorkUPsotService ijobInfoService;

	// 当前登录的毕业生对象
	private Graduate graduate;

	// 岗位查询条件
	PostDto post = new PostDto();

	// 岗位编号
	private String postId;

	// 头部毕业生名，跳转首页
	@GetMapping("/home")
	public String home(HttpServletRequest request, Model model) {
		String studentId = request.getParameter("studentId");// 获取学生的学号
		Graduate graduate = iGraduateService.queryStudentById(studentId);// 查询学生信息
		model.addAttribute("graduate", graduate);
		return "graduate/GraduateHome";
	}

	// 毕业生登录
	@PostMapping("/login")
	public String login(@ModelAttribute Graduate graduate, Model model, HttpSession session) {
		String rest = "";// 返回界面地址
		// 根据学号查询学生信息，看是否存在此学生
		String studentId = graduate.getStudentId();
		Graduate graduates = iGraduateService.queryStudentById(studentId);
		if (graduates != null) {
			// 验证登录密码是否正确
			if (graduates.getPassword().equals(graduate.getPassword())) {
				// 未上传头像时，给默认头像
				if (graduates.getAvatarPath() != null) {
					if (graduates.getAvatarPath().equals("")) {
						graduates.setAvatarPath("cs.jpg");
					}
				} else {
					graduates.setAvatarPath("cs.jpg");
				}
				session.setAttribute("graduateUser", graduates);
				// 传graduate对象给前端显示数据
				model.addAttribute("graduate", graduates);
				rest = "graduate/GraduateHome";
			} else {
				// 传enterprise,graduate,admin对象给前端显示数据
				model.addAttribute("enterprise", new Enterprise());
				model.addAttribute("graduate", new Graduate());
				model.addAttribute("admin", new Admin());
				// 回传登录失败错误信息
				model.addAttribute("errorInfo", "密码错误");
				rest = "Public/SwitchLogin";
			}
		} else {
			// 传enterprise,graduate,admin对象给前端显示数据
			model.addAttribute("enterprise", new Enterprise());
			model.addAttribute("graduate", new Graduate());
			model.addAttribute("admin", new Admin());
			// 回传登录失败错误信息
			model.addAttribute("errorInfo", "学号错误");
			rest = "Public/SwitchLogin";
		}
		return rest;
	}

	@GetMapping("/Logout")
	public ModelAndView Logout(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		session.removeAttribute("graduateUser");
		modelAndView.setViewName("redirect:/admin/switchLog");
		return modelAndView;
	}

	// 注册
	@PostMapping("/register")
	public String register(@ModelAttribute Graduate graduate, Model model) {
		int flag = iGraduateService.insertGraduate(graduate);
		if (flag == 1) {
			System.out.println("注册成功");
		} else {
			System.out.println("注册失败");
		}
		// 跳转到公司登录页面的OBJ
		model.addAttribute("enterprise", new Enterprise());
		// 跳转到毕业生登录页面的OBJ
		model.addAttribute("graduate", new Graduate());
		// 跳转到管理员登录页面的OBJ
		model.addAttribute("admin", new Admin());
		return "Public/SwitchLogin";
	}

	// 上传毕业生头像
	@PostMapping("/uploadPictures/{studentId}")
	public String uploadPictures(@RequestParam(value = "avatar_file") MultipartFile file,
			@ModelAttribute Graduate graduate, Model model, @PathVariable String studentId) {
		// 获取项目中储存头像的文件夹的绝对路径
		String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\headportrait";
		// 获得上传的图片名字
		String name = file.getOriginalFilename();
		// 如果未选择文件，则赋值为初始头像
		if (name.equals("")) {
			name = "cs.jpg";
		}
		// 添加新的头像的文件名
		graduate.setAvatarPath(name);
		// 根据前台传过来的毕业生学号，修改数据库图片名
		int rest = iGraduateService.updatePicByid(graduate);
		// 拼成头像的完整路径
		path = path + "\\" + name;
		try {
			// 上传文件
			file.transferTo(new File(path));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Graduate graduates = iGraduateService.queryStudentById(graduate.getStudentId());
		model.addAttribute("graduate", graduates);
		return "graduate/GraduateHome";
	}

	// 修改信息
	@GetMapping("/updateInfo")
	public String updateInfo(HttpServletRequest request, Model model) {
		String studentId = request.getParameter("studentId");// 获取学生的学号
		Graduate graduate = iGraduateService.queryStudentById(studentId);// 查询学生信息
		model.addAttribute("graduate", graduate);
		return "graduate/UpdateInfo";
	}

	// 保存个人信息
	@PostMapping("/saveInfo/{studentId}") // 通过表单路径传学生id过来
	public String saveInfo(@ModelAttribute Graduate graduate, Model model, @PathVariable String studentId) {
		Graduate graduates = iGraduateService.queryStudentById(studentId);// 查询学生信息
		graduate.setAvatarPath(graduates.getAvatarPath());
		int rest = iGraduateService.updateGraduate(graduate);
		model.addAttribute("graduate", graduate);
		return "graduate/GraduateHome";
	}

	// 头部导航点击搜索岗位（后台版）
	@GetMapping("/searchJobs")
	public String search(Model model, HttpServletRequest request) {
		String studentId = request.getParameter("studentId");// 获取学生的学号
		graduate = iGraduateService.queryStudentById(studentId);// 查询学生信息
		model.addAttribute("graduate", graduate);
		PostDto postDto = new PostDto();
		model.addAttribute("postDto", postDto);
		int page = 1;
		PageHelper.startPage(page, 8);
		PageInfo<PostDto> psotSimpleList = new PageInfo<>(iPostService.selectPostListByMore(postDto));// 将原list转为page类型
		List<Integer> listPages = calculateOptionalPages(page, psotSimpleList.getPages());
		model.addAttribute("listPages", listPages);
		model.addAttribute("traversingList", psotSimpleList);
		model.addAttribute("address", "graduate/Postpages");
		return "graduate/SearchJobs";
	}

	// 超链接分页查询岗位
	@GetMapping(value = { "/Postpages/{page}" })
	public String Postpage(Model model, @PathVariable(name = "page") int page) {
		model.addAttribute("graduate", graduate);
		PageHelper.startPage(page, 8);
		PostDto postDto = post;
		PageInfo<PostDto> psotSimpleList = new PageInfo<>(iPostService.selectPostListByMore(postDto));// 将原list转为page类型
		List<Integer> listPages = calculateOptionalPages(page, psotSimpleList.getPages());
		model.addAttribute("postDto", post);
		model.addAttribute("listPages", listPages);
		model.addAttribute("traversingList", psotSimpleList);
		model.addAttribute("address", "graduate/Postpages");
		return "graduate/SearchJobs";
	}

	// 表单条件查询岗位列表
	@PostMapping(value = { "/Postpages/{page}" })
	public String Postpages(Model model, @PathVariable(name = "page") int page, @ModelAttribute PostDto postDto) {
		model.addAttribute("graduate", graduate);
		post = postDto;
		PageHelper.startPage(page, 8);
		PageInfo<PostDto> psotSimpleList = new PageInfo<>(iPostService.selectPostListByMore(postDto));// 将原list转为page类型
		List<Integer> listPages = calculateOptionalPages(page, psotSimpleList.getPages());
		model.addAttribute("postDto", post);
		model.addAttribute("listPages", listPages);
		model.addAttribute("traversingList", psotSimpleList);
		model.addAttribute("address", "graduate/Postpages");
		return "graduate/SearchJobs";
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

	// 企业详情
	@GetMapping("/enterpriseDetails/{registrationId}/{postId}")
	public String showEnterpriseDetails(@PathVariable(name = "registrationId") String registrationId,
			@PathVariable(name = "postId") String postId, Model model) {
		// 接收超链接传过来的岗位编号
		this.postId = postId;
		model.addAttribute("graduate", graduate);
		Enterprise enterprise = iEnterpriseService.selectEnterprise(registrationId);
		model.addAttribute("enterprise", enterprise);
		// 判断是否已经投过这个岗位
		StudentHistory rest = iGraduateService.selectCV(graduate.getStudentId(), postId);
		// 是否投递界面返回值
		String posted = null;
		if (rest != null) {
			posted = "已投递";
		} else {
			posted = "投递简历";
		}
		model.addAttribute("posted", posted);
		return "graduate/EnterpriseDetails";
	}

	// 投递简历（老版）
	@GetMapping("/sendCV/{registrationId}")
	public String sendCV(@PathVariable(name = "registrationId") String id, Model model) {
		model.addAttribute("graduate", graduate);
		Enterprise enterprise = iEnterpriseService.selectEnterprise(id);
		model.addAttribute("enterprise", enterprise);
		// 判断是否已经投过这个岗位
		StudentHistory rest = iGraduateService.selectCV(graduate.getStudentId(), postId);
		// 是否投递界面返回值
		String posted = null;
		if (rest != null) {
			posted = "已投递";
		} else {
			// 判断学生是否填写简历
			if (graduate.getResumeId() != null) {
				// 没有投递过，则投递
				int sendCV = iGraduateService.sendCV(graduate.getStudentId(), postId);
				posted = "投递成功";
			} else {
				posted = "请先完成个人简历填写";
			}

		}
		model.addAttribute("posted", posted);
		return "graduate/EnterpriseDetails";
	}

	// 头部导航点击搜索毕业生信息
	@GetMapping("/searchGraduateInfo")
	public String searchGraduateInfo(Model model, HttpServletRequest request) {
		String studentId = request.getParameter("studentId");// 获取学生的学号
		Graduate graduate = iGraduateService.queryStudentById(studentId);// 查询学生信息
		model.addAttribute("graduate", graduate);
		return "graduate/SearchGraduateInfo";
	}

	// 表单条件查询岗位列表
	@PostMapping(value = { "/searchGraduateInfos/{studentId}" })
	public String searchGraduateInfos(@ModelAttribute Graduate graduate, Model model, @PathVariable String studentId) {
		Graduate graduates = iGraduateService.queryStudentById(graduate.getStudentId());// 查询学生信息
		if (graduates == null) {
			// 回传登录失败错误信息
			model.addAttribute("errorInfo", "学号不存在");
		}
		graduate = iGraduateService.queryStudentById(studentId);// 查询当前登录毕业生信息
		model.addAttribute("graduate", graduate);
		model.addAttribute("graduates", graduates);
		return "graduate/SearchGraduateInfo";
	}

	// 头部导航点击个人简历
	@GetMapping("/resume")
	public String resume(Model model, HttpServletRequest request) {
		String studentId = request.getParameter("studentId");// 获取学生的学号
		Graduate graduate = iGraduateService.queryStudentById(studentId);// 查询学生信息
		model.addAttribute("graduate", graduate);
		ResumeDto resumeDto = iResumeService.selectResumeById(Long.parseLong(studentId));
		if (resumeDto != null) {
			if (resumeDto.getBirthday() != null) {
				resumeDto.setAge(GetPetAgeUtils.getAgeByBirth(resumeDto.getBirthday()));// 生日转年龄
			}
		} else {
			resumeDto = new ResumeDto();
			resumeDto.setName(graduate.getStudentName());// 姓名
			resumeDto.setSex(graduate.getSex());// 性别
			resumeDto.setStudentId(graduate.getStudentId());// 学号
			resumeDto.setAvatarPath(graduate.getAvatarPath());// 头像路径
		}

		model.addAttribute("resumeDto", resumeDto);
		return "graduate/Resume";
	}

	// 简历详情点击修改简历
	@GetMapping("/updateResume")
	public String updateResume(Model model, HttpServletRequest request) {
		String studentId = request.getParameter("studentId");// 获取学生的学号
		Graduate graduate = iGraduateService.queryStudentById(studentId);// 查询学生信息
		model.addAttribute("graduate", graduate);
		ResumeDto resumeDto = iResumeService.selectResumeById(Long.parseLong(studentId));
		if (resumeDto == null) {
			resumeDto = new ResumeDto();
			resumeDto.setName(graduate.getStudentName());// 姓名
		}
		model.addAttribute("resumeDto", resumeDto);
		return "graduate/UpdateResume";
	}

	// 保存简历信息
	@PostMapping("/saveResumeInfo/{studentId}") // 通过表单路径传学生id过来
	public String saveResumeInfo(@ModelAttribute ResumeDto resumeDto, Model model, @PathVariable String studentId,
			@Param(value = "birthday") LocalDateTime birthdays) {
		// 显示页头信息
		Graduate graduates = iGraduateService.queryStudentById(studentId);// 查询学生信息
		model.addAttribute("graduate", graduates);
		ResumeDto dto = iResumeService.queryResumeById(studentId);
		String sex = resumeDto.getSex();
		sex = sex.replace("T", " ");
		/* sex = sex.substring(0, 13); */
		LocalDateTime birthday = LocalDateTime.parse(sex, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		resumeDto.setBirthday(birthday);
		resumeDto.setSex(graduates.getSex());// 性别
		resumeDto.setStudentId(graduates.getStudentId());// 学号
		resumeDto.setAvatarPath(graduates.getAvatarPath());// 头像路径
		if (dto != null) {
			// 修改学生简历
			int i = iResumeService.updateResume(resumeDto);
		} else {
			// 新增学生简历
			long second = (long) LocalDateTime.now().getSecond();
			second = new Random().nextInt(10000) % (10000 - 1000 + 1) + 1000 + second;
			resumeDto.setResumeId(second);
			int i = iResumeService.insertResume(resumeDto);
		}

		// 显示简历信息
		ResumeDto resumeDtos = iResumeService.selectResumeById(Long.parseLong(studentId));
		if (resumeDtos != null) {
			if (resumeDtos.getBirthday() != null) {
				resumeDtos.setAge(GetPetAgeUtils.getAgeByBirth(resumeDto.getBirthday()));// 生日转年龄
			}
		} else {
			resumeDtos = new ResumeDto();
		}
		model.addAttribute("resumeDto", resumeDtos);
		return "graduate/Resume";
	}

	// 头部导航点击搜索岗位（学生版） 首页，上一页，下一页
	@GetMapping("/searchJobs/{studentId}/{page}")
	public String searchs(Model model, HttpServletRequest request, @PathVariable(name = "studentId") String studentId,
			@PathVariable(name = "page") int page) {
		Graduate graduate = iGraduateService.queryStudentById(studentId);// 查询学生信息
		model.addAttribute("graduate", graduate);
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

	// form表单的页面输入式跳转，分页
	@PostMapping("/entLogPage/{studentId}") // 公司招聘信息简要列表（公司登录后看）
	public String entLogPageTurn(@PathVariable(name = "studentId") String studentId,
			@RequestParam(value = "pagesTurn") Integer pagesTurn, Model model) {
		Graduate graduate = iGraduateService.queryStudentById(studentId);// 查询学生信息
		model.addAttribute("graduate", graduate);
		if (pagesTurn == null) {
			pagesTurn = 1;
		}
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

	@PostMapping("/entLogLikeSearchs/{studentId}/{page}") // 中间搜索框，模糊查询
	public String entLogLikeSearchs(@RequestParam(value = "search_keyword") String postNamesL,
			@PathVariable(name = "studentId") String studentId, @PathVariable(name = "page") Integer pagesTurn,
			Model model) {
		Graduate graduate = iGraduateService.queryStudentById(studentId);// 查询学生信息
		model.addAttribute("graduate", graduate);
		/*---------------------------------------------------------*/
		// @RequestParam(value="pagesTurn") value的值与form表单中的某个input的name值相同即可取其值()value
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

	@GetMapping("/entLogLikeSearchPageN/{studentId}/{page}/{postNamesL}") // 中间搜索框，模糊查询，然后分页的，上一页，下一页
	public String entLogLikeSearchPageN(@PathVariable(name = "postNamesL") String postNamesL,
			@PathVariable(name = "studentId") String studentId, @PathVariable(name = "page") Integer pagesTurn,
			Model model) {
		Graduate graduate = iGraduateService.queryStudentById(studentId);// 查询学生信息
		model.addAttribute("graduate", graduate);
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

	@PostMapping("/entLogLikeSearchPageTurns/{studentId}/{postNamesL}") // 中间搜索框，模糊查询，然后分页的，表单页面输入值跳转
	public String entLogLikeSearchPageTurns(@PathVariable(name = "postNamesL") String postNamesL,
			@PathVariable(name = "studentId") String studentId, @RequestParam(value = "pagesTurn") Integer pagesTurn,
			Model model) {
		Graduate graduate = iGraduateService.queryStudentById(studentId);// 查询学生信息
		model.addAttribute("graduate", graduate);
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

	@GetMapping("/entLogQuality/{postId}/{studentId}") // 查看公司招聘的详情
	public String entLogQuality(@PathVariable(name = "studentId") String studentId,
			@PathVariable(name = "postId") String postId, Model model) {
		Graduate graduate = iGraduateService.queryStudentById(studentId);// 查询学生信息// 传入公司信息
		model.addAttribute("graduate", graduate);
		/*---------------------------------------------------------*/
		this.postId=postId;
		CompanyHiredInfoDto JobsInfo = ijobInfoService.searchOne(postId);// 招聘的详情
		Post postMsg = iPostService.selectOneHiredMsg(postId);
		Enterprise enterprisesInfo = iEnterpriseService.selectEnterpriseOne(postMsg.getRegistrationId().toString());
		model.addAttribute("enterprisesInfo", enterprisesInfo);// 传入公司信息
		model.addAttribute("JobsInfo", JobsInfo);// 传入岗位信息
		model.addAttribute("postMsg", postMsg);// 传入岗位简要信息
		return "Company/CompanyHiredInfoQuality";
	}

	@GetMapping("/sendCVs/{studentId}") // 投递简历(学生版)
	public String sendCVs(@PathVariable(name = "studentId") String studentId, Model model) {
		Graduate graduate = iGraduateService.queryStudentById(studentId);// 查询学生信息,头部显示
		model.addAttribute("graduate", graduate);
		/*---------------------------------------------------------*/
		CompanyHiredInfoDto JobsInfo = ijobInfoService.searchOne(postId);// 招聘的详情，
		Post postMsg = iPostService.selectOneHiredMsg(postId);
		Enterprise enterprisesInfo = iEnterpriseService.selectEnterpriseOne(postMsg.getRegistrationId().toString());
		model.addAttribute("enterprisesInfo", enterprisesInfo);// 传入公司信息
		model.addAttribute("JobsInfo", JobsInfo);// 传入岗位信息
		model.addAttribute("postMsg", postMsg);// 传入岗位简要信息
		// 判断是否已经投过这个岗位
		StudentHistory rest = iGraduateService.selectCV(graduate.getStudentId(), postId);
		// 是否投递界面返回值
		String posted = null;
		if (rest != null) {
			posted = "已投递";
		} else {
			// 判断学生是否填写简历
			if (graduate.getResumeId() != null) {
				// 没有投递过，则投递
				int sendCV = iGraduateService.sendCV(graduate.getStudentId(), postId);
				posted = "投递成功";
			} else {
				posted = "请先完成个人简历填写";
			}

		}
		model.addAttribute("posted", posted);

		return "Company/CompanyHiredInfoQuality";
	}

	@GetMapping("/entLog/{postnames}/{page}/{studentId}") // 左侧分类超链接，上一页，下一页
	public String entLogBypostnames(@PathVariable(name = "studentId") String studentId,
			@PathVariable(name = "postnames") String postName, @PathVariable(name = "page") int page, Model model) {
		Graduate graduate = iGraduateService.queryStudentById(studentId);// 查询学生信息
		model.addAttribute("graduate", graduate);
		/*---------------------------------------------------------*/
		if (page <= 0)
			page = 1;
		PageHelper.startPage(page, 5); // 第几页，每页几条
		PageInfo<Post> psotSimpleList = new PageInfo<>(iPostService.jobListArrage(postName));// 将原list转为page类型
		page = pageMax(page, psotSimpleList);
		List<TypeWorkUJobs> Professions = (itypeWork.AllPros());// 取出所有的岗位父类与所有的根据父岗位查询的岗位名称
		model.addAttribute("postnamesArrage", postName);
		model.addAttribute("pros", Professions);
		model.addAttribute("psotSimpleList", psotSimpleList);
		model.addAttribute("pages", "第" + page + "页");
		model.addAttribute("page", page);
		return "Company/CompanyHiredInfoToShowArrage";
	}

	// form表单的页面输入式跳转
	@PostMapping("/entLogPageTurnArrage/{postnames}/{studentId}") // 表单传值跳转
	public String entLogPageTurnArrage(@PathVariable(name = "studentId") String studentId,
			@PathVariable(name = "postnames") String postName, @RequestParam(value = "pagesTurn") Integer pagesTurn,
			Model model) {
		Graduate graduate = iGraduateService.queryStudentById(studentId);// 查询学生信息
		model.addAttribute("graduate", graduate);
		/*---------------------------------------------------------*/
		// @RequestParam(value="pagesTurn") value的值与form表单中的某个input的name值相同即可取其值()value
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

	// 查看投递记录
	@GetMapping("/deliveryRecord")
	public String deliveryRecord(Model model, HttpServletRequest request) {
		String studentId = request.getParameter("studentId");// 获取学生的学号
		graduate = iGraduateService.queryStudentById(studentId);// 查询学生信息
		model.addAttribute("graduate", graduate);
		// 查询当前用户投递的所有简历，并获取postId
		List<StudentHistory> allCV = iGraduateService.selectAllCV(studentId);
		List<PostDto> posts = new ArrayList<PostDto>();
		if (allCV.size() != 0) {
			for (StudentHistory cv : allCV) {
				// 查询岗位信息
				PostDto post = iPostService.selectPostListById(cv.getPostId());// 获取postId,cv.getPostId()
				// 借用字段popular来暂存collection,简历投递结果，
				// 1表示被打回去,2表示录用,3表示备选
				post.setPopular(Integer.parseInt(cv.getCollection()));
				posts.add(post);
			}
		}
		int page = 1;
		PageHelper.startPage(page, 8);
		PageInfo<PostDto> psotSimpleList = new PageInfo<>(posts);// 将原list转为page类型
		List<Integer> listPages = calculateOptionalPages(page, psotSimpleList.getPages());
		model.addAttribute("listPages", listPages);
		model.addAttribute("traversingList", psotSimpleList);
		model.addAttribute("address", "graduate/deliveryRecords");
		return "graduate/DeliveryRecord";
	}

	// 超链接分页查询岗位
	@GetMapping(value = { "/deliveryRecords/{page}" })
	public String deliveryRecords(Model model, @PathVariable(name = "page") int page) {
		model.addAttribute("graduate", graduate);
		// 查询当前用户投递的所有简历，并获取postId
		List<StudentHistory> allCV = iGraduateService.selectAllCV(graduate.getStudentId());
		List<PostDto> posts = new ArrayList<PostDto>();
		if (allCV.size() != 0) {
			for (StudentHistory cv : allCV) {
				// 查询岗位信息
				PostDto post = iPostService.selectPostListById(cv.getPostId());// 获取postId,cv.getPostId()
				// 借用字段popular来暂存collection,简历投递结果，
				// 1表示被打回去,2表示录用,3表示备选
				post.setPopular(Integer.parseInt(cv.getCollection()));
				posts.add(post);
			}
		}
		PageHelper.startPage(page, 8);
		PageInfo<PostDto> psotSimpleList = new PageInfo<>(posts);// 将原list转为page类型
		List<Integer> listPages = calculateOptionalPages(page, psotSimpleList.getPages());
		model.addAttribute("listPages", listPages);
		model.addAttribute("traversingList", psotSimpleList);
		model.addAttribute("address", "graduate/deliveryRecords");
		return "graduate/DeliveryRecord";
	}
}
