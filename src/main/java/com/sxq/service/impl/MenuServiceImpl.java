package com.sxq.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sxq.entity.Menu;
import com.sxq.repository.MenuRepository;
import com.sxq.service.MenuService;
@Service("menuService")
public class MenuServiceImpl implements MenuService{

	@Resource
	private MenuRepository menuRepository;
	@Override
	public List<Menu> findByParentIdAndRoleId(Integer parentId, Integer roleId) {
		// TODO Auto-generated method stub
		return menuRepository.findByParentIdAndRoleId(parentId, roleId);
	}

}
