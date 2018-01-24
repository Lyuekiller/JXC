package com.sxq.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sxq.entity.Menu;

/**
 * 菜单Repository接口
 * @author wzd
 *
 */
public interface MenuRepository extends JpaRepository<Menu, Integer>{
	/**
	 * 根据parentId(父节点)和roleId查询其下一层的菜单信息(子节点)
	 * @param parentId
	 * @param roleId
	 * @return
	 */
	@Query(value="select * from t_menu where p_id=?1 and id in (select menu_id from t_role_menu where role_id=?2)",nativeQuery=true)
	public List<Menu> findByParentIdAndRoleId(Integer parentId, Integer roleId);
}
