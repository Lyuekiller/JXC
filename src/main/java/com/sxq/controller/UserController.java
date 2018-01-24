package com.sxq.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sxq.entity.Menu;
import com.sxq.entity.Role;
import com.sxq.entity.User;
import com.sxq.service.MenuService;
import com.sxq.service.RoleService;
import com.sxq.service.UserService;
import com.sxq.util.StringUtil;
/**
 * 用户Controller
 * @author wzd
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	@Resource
	private MenuService menuService;
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
			subject.login(token);//如果用户名和密码错误则会报错跳入catch的异常当中
			User currentUser = userService.findByUserName(user.getUserName());
			session.setAttribute("currentUser", currentUser);
			List<Role> roleList = roleService.findRolesByUserId(currentUser.getId());
			map.put("roleList", roleList);
			map.put("roleSize", roleList.size());
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
	/**
	 * 保存角色信息
	 * @param roleId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("saveRole")
	public Map<String,Object> saveRoLe(Integer roleId , HttpSession  session)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		Role currentRole =  roleService.findById(roleId);
		session.setAttribute("currentRole", currentRole);
		System.out.println(currentRole.getName());
		map.put("success", true);
		return map;
	}
	/**
	 * 加载当前用户信息
	 * @param session
	 * @return
	 */
	@ResponseBody
	@GetMapping("loadUserInfo")
	public String loadUserInfo(HttpSession session)throws Exception {
		User currentUser = (User)session.getAttribute("currentUser");
		Role currentRole = (Role)session.getAttribute("currentRole");
		return "欢迎您: "+currentUser.getTrueName()+"&nbsp:[&nbsp"+currentRole.getName()+"&nbsp]";
		
	}
	/**
	 * 加载权限菜单
	 * @param session
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@PostMapping("loadMenuInfo")
	public String loadUserInfo(HttpSession session , Integer parentId)throws Exception{
		System.out.println("parentId"+parentId);
		Role currentRole = (Role)session.getAttribute("currentRole");//获得当前的角色
		return getAllMenuByParentId(parentId,currentRole.getId()).toString();
		
	}
	/**
	 * 根据roleId以及parentId获取对应所有菜单信息
	 * @param parentId
	 * @param roleId
	 * @return
	 */
	public JsonArray getAllMenuByParentId(Integer parentId,Integer roleId) {
		JsonArray jsonArray = getMenuByParentId(parentId,roleId);//一层的JsonArray
		for(int i=0;i<jsonArray.size();i++) {
			JsonObject jsonObject = (JsonObject)jsonArray.get(i);
			if("open".equals(jsonObject.get("state").getAsString())) {//如果是子节点就已经到底了不需要再递归了
				continue;
			}else {
				jsonObject.add("children",getAllMenuByParentId(jsonObject.get("id").getAsInt(),roleId));
			}
		}
		return jsonArray;
	}
	/**
	 * 根据roleId以及parentId获取对应下一层的菜单信息
	 * 注意：只获得parentId对应的下一层而已并不是所有
	 * @param parentId
	 * @param roleId
	 * @return
	 */
	public JsonArray getMenuByParentId(Integer parentId,Integer roleId) {
		List<Menu> menuList = menuService.findByParentIdAndRoleId(parentId, roleId);
		JsonArray jsonArray = new JsonArray();
		for(Menu menu : menuList) {
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("id", menu.getId());
			jsonObject.addProperty("text", menu.getName());
			if(menu.getState()==1) {
				jsonObject.addProperty("state", "closed");//如果为根节点 state=closed
			}else
				jsonObject.addProperty("state", "open");//如果为叶子节点 state=open
			jsonObject.addProperty("iconCls", menu.getIcon());
			JsonObject attributeObject = new JsonObject();//扩展属性
			attributeObject.addProperty("url", menu.getUrl());
			jsonObject.add("attributes" , attributeObject);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
}