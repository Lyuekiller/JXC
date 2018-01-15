package com.sxq.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 用户角色关联实体，因为是多对多的关系，所以要额外建立一张表格来体现关联关系，如果是多对一则不用，
 * 直接在多的一方建立一个参数就可以了
 * @author wzd
 *
 */
@Entity
@Table(name="t_userRole")
public class UserRole {
	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;//用户，是用户的外键，属于多对一，这个参数是多，因为此表的记录中会出现很多相同的user，一定要理解
	@ManyToOne
	@JoinColumn(name="roleId")//角色，是角色的外键，属于多对一，这个参数是多，因为此表的记录中会出现很多相同的role，一定要理解
	private Role role;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
}
