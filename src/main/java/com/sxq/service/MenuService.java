package com.sxq.service;

import java.util.List;

import com.sxq.entity.Menu;

/**
 * 菜单Service接口
 * @author wzd
 *
 */
public interface MenuService {
	public List<Menu> findByParentIdAndRoleId(Integer parentId, Integer roleId);
}
