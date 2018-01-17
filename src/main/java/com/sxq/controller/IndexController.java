package com.sxq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页控制器
 * @author wzd
 *
 */
@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String boot() {
		return "redirect:/login.html";
	}
}
