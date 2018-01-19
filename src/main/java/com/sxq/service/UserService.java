package com.sxq.service;


import com.sxq.entity.User;
/**
 * 用户Service接口
 * @author wzd
 *
 */
public interface UserService {
	/**
	 * 根据userName查找他有什么角色
	 * ？1表示第一个参数也就是方法里面的id
	 * nativeQuery表示使用原生sql而非hql
	 * @param id
	 * @return
	 */
	public User findByUserName(String userName);
}
