package com.sxq.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sxq.entity.Role;
/**
 * 角色Repository接口
 * @author wzd
 *
 */
public interface RoleRepository extends JpaRepository<Role, Integer>{
	/**
	 * 根据用户id查找他有什么角色
	 * ？1表示第一个参数也就是方法里面的id
	 * nativeQuery表示使用原生sql而非hql
	 * @param id
	 * @return
	 */
	@Query(value="select r.* from t_user u,t_role r,t_user_role ur where r.id=ur.role_id and u.id=ur.user_id and u.id=?1",nativeQuery=true)
	public List<Role> findRolesByUserId(Integer id);
}
