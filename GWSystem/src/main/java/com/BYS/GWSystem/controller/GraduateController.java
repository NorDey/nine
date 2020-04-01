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

	@PostMapping("/login") // 毕业生登录
	public String login(@ModelAttribute Graduate graduate,Model model,HttpSession session) {
		String rest="";
		Long studentId = graduate.getStudentId();
		Graduate graduates = iGraduateService.queryStudentById(studentId);
		if(graduates!=null) {
			if(graduates.getPassword().equals(graduate.getPassword())) {
				model.addAttribute("graduate",graduates);
				session.setAttribute("studentId", studentId);
				session.setAttribute("password", graduates.getPassword());
				rest= "graduate/GraduateHome";
			}else {
				model.addAttribute("enterprise", new Enterprise());
				model.addAttribute("graduate",new Graduate());
				rest= "Public/SwitchLogin";
			}
		}else {
			model.addAttribute("enterprise", new Enterprise());
			model.addAttribute("graduate",new Graduate());
			rest= "Public/SwitchLogin";
		}
		return rest;

	}

	@GetMapping("/register") // 注册
	public String register() {
		return "graduate/Register";
	}

	@PostMapping("/uploadPictures") // 上传毕业生头像
	public String uploadPictures(@RequestParam(value = "avatar_file") MultipartFile file,
			@ModelAttribute Graduate graduate, Model model, HttpServletRequest request) {
		String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\headportrait";
		String name = file.getOriginalFilename(); // 图片名字
		int rest = iGraduateService.updatePicByid(graduate);
		System.out.println(rest);
		path = path + "\\" + name;
		try {
			file.transferTo(new File(path));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "graduate/updateInfo";
	}
}
