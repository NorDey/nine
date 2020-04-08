package com.BYS.GWSystem.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.BYS.GWSystem.dto.PostDto;
import com.BYS.GWSystem.model.Admin;
import com.BYS.GWSystem.model.Enterprise;
import com.BYS.GWSystem.model.Graduate;
import com.BYS.GWSystem.service.IGraduateService;
import com.BYS.GWSystem.service.IPostService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/graduate")
public class GraduateController {

	@Autowired
	private IGraduateService iGraduateService;

	@Autowired
	private IPostService iPostService;

	// 当前登录的毕业生对象
	private Graduate graduate;

	// 岗位查询条件
	PostDto post = new PostDto();

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
	public String login(@ModelAttribute Graduate graduate, Model model) {
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

	// 头部导航点击搜索岗位
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

	// 投递简历
	@GetMapping("/sendCV")
	public String sendCV(Model model, HttpServletRequest request) {

		return "graduate/SearchJobs";
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
		return "graduate/Resume";
	}
}
