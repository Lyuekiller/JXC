package com.sxq.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sxq.entity.Role;
import com.sxq.repository.RoleRepository;
import com.sxq.service.RoleService;
/**
 * 角色Service实现类
 * @author wzd
 *
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService{

	@Resource
	private RoleRepository roleRepository;
	/**
	 * 
	 */
	@Override
	public List<Role> findRolesByUserId(Integer id) {
		// TODO Auto-generated method stub
		return roleRepository.findRolesByUserId(id);
	}

}
