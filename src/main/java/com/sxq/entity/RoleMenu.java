package com.sxq.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 角色菜单关联实体，因为是多对多的关系，所以要额外建立一张表格来体现关联关系，如果是多对一则不用，
 * 直接在多的一方建立一个参数就可以了
 * @author wzd
 *
 */
@Entity
@Table(name="t_roleMenu")
public class RoleMenu {
	
	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne
	@JoinColumn(name="roleId")//角色，是角色的外键，属于多对一，这个参数是多，因为此表的记录中会出现很多相同的role，一定要理解
	private Role role;
	@ManyToOne
	@JoinColumn(name="menuId")//菜单，是菜单的外键，属于多对一，这个参数是多，因为此表的记录中会出现很多相同的menu，一定要理解
	private Menu menu;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
	
}
