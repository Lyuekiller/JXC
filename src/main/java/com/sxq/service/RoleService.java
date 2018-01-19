package com.sxq.service;

import java.util.List;

import com.sxq.entity.Role;
/**
 * 角色Service接口
 * @author wzd
 *
 */
public interface RoleService {
	/**
	 * 根据用户id查找他有什么角色
	 * ？1表示第一个参数也就是方法里面的id
	 * nativeQuery表示使用原生sql而非hql
	 * @param id
	 * @return
	 */
	public List<Role> findRolesByUserId(Integer id);
}
