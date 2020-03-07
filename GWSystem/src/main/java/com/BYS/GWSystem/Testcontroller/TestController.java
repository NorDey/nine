package com.BYS.GWSystem.Testcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	@RequestMapping("caonima")
		public String show() {
			return "wo cao ni ma de bi ";
		}

}

