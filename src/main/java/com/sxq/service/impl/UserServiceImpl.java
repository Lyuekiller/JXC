package com.sxq.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sxq.entity.User;
import com.sxq.repository.UserRepository;
import com.sxq.service.UserService;
/**
 * 用户Service实现类
 * @author wzd
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService{

	@Resource
	private UserRepository userRepository;
	@Override
	public User findByUserName(String userName) {
		// TODO Auto-generated method stub
		return userRepository.findByUserName(userName);
	}

}
