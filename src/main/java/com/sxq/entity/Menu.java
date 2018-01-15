package com.sxq.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 菜单权限（实际上就是shiro中的权限）
 * @author wzd
 *
 */
@Entity
@Table(name="t_menu")
public class Menu {
	@Id
	@GeneratedValue
	private Integer id;
	@Column(length=50)
	private String name;//菜单名称
	@Column(length=200)
	private String url;
	
	private Integer state;//菜单节点类型，1为根节点，2为子节点
	@Column(length=100)
	private String icon;
	
	private Integer pId;//父菜单Id

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}
	
	
	
}
