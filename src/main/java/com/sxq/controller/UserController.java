package com.sxq.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sxq.entity.User;
import com.sxq.util.StringUtil;
/**
 * 用户Controller
 * @author wzd
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	/**
	 * 用户登录判断
	 * @param imageCode:用户输入的验证码
	 * @param user:包含输入的userName，password，@Valid是启用springboot的字段判断
	 * @param bindingResult：判断的返回值
	 * @param session，用于获取正确的验证码
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/login")
	public Map<String,Object> login(String imageCode,@Valid User user,BindingResult bindingResult,HttpSession session){
		Map<String,Object> map = new HashMap<String,Object>();
		if(StringUtil.isEmpty(imageCode)) {
			map.put("success", false);
			map.put("errorInfo", "请输入验证码");
			return map;
		}
		if(!session.getAttribute("checkcode").equals(imageCode)) {
			map.put("success", false);
			map.put("errorInfo", "验证码输入错误");
			return map;
		}
		if(bindingResult.hasErrors()) {//判断输入的用户名密码是否符合格式
			map.put("success", false);
			map.put("errorInfo", bindingResult.getFieldError().getDefaultMessage());
			return map;
		}
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getPassword());//封装成token
		try {
			subject.login(token);
			map.put("success", true);
			return map;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("success", false);
			map.put("errorInfo", "用户名或者密码错误");
			return map;
		}
		 
		
	}
}
