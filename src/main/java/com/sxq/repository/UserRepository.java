package com.sxq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sxq.entity.User;
/**
 * 用户Repository接口
 * @author wzd
 *
 */
public interface UserRepository extends JpaRepository<User, Integer>{
	/**
	 * 根据userName查找对象
	 * ？1表示第一个参数也就是方法里面的userName
	 * nativeQuery表示使用原生sql而非hql
	 * @param userName
	 * @return
	 */
	@Query(value="select * from t_user where user_name=?1",nativeQuery=true)
	public User findByUserName(String userName);
}
