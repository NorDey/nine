package com.BYS.GWSystem.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.BYS.GWSystem.mapper.GraduateMapper;
import com.BYS.GWSystem.model.Enterprise;
import com.BYS.GWSystem.model.Graduate;
import com.BYS.GWSystem.service.IGraduateService;
import com.BYS.GWSystem.service.impl.GraduateServiceImpl;

@Controller
@RequestMapping("/graduate")
public class GraduateController {

	@Autowired
	private IGraduateService iGraduateService;

	@GetMapping("/home") // 毕业生主页
	public String home() {
		return "graduate/GraduateHome";
	}

	@GetMapping("/updateInfo") // 修改信息
	public String updateInfo() {
		return "graduate/UpdateInfo";
	}

	// 毕业生登录
	@PostMapping("/login")
	public String login(@ModelAttribute Graduate graduate, Model model, HttpSession session) {
		String rest = "";// 返回界面地址
		// 根据学号查询学生信息，看是否存在此学生
		Long studentId = graduate.getStudentId();
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
				// 传enterprise,graduate对象给前端显示数据
				model.addAttribute("enterprise", new Enterprise());
				model.addAttribute("graduate", new Graduate());
				// 回传登录失败错误信息
				model.addAttribute("errorInfo", "密码错误");
				rest = "Public/SwitchLogin";
			}
		} else {
			// 传enterprise,graduate对象给前端显示数据
			model.addAttribute("enterprise", new Enterprise());
			model.addAttribute("graduate", new Graduate());
			// 回传登录失败错误信息
			model.addAttribute("errorInfo", "学号错误");
			rest = "Public/SwitchLogin";
		}
		return rest;

	}

	// 注册
	@GetMapping("/register")
	public String register() {
		return "graduate/Register";
	}

	// 上传毕业生头像
	@PostMapping("/uploadPictures/{studentId}")
	public String uploadPictures(@RequestParam(value = "avatar_file") MultipartFile file,
			@ModelAttribute Graduate graduate, Model model, @PathVariable String studentId) {
		// 获取项目中储存头像的文件夹的绝对路径
		String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\headportrait";
		// 获得上传的图片名字
		String name = file.getOriginalFilename();
		//如果未选择文件，则赋值为初始头像
		if(name.equals("")) {
			name="cs.jpg";
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
}
