package cn.com.ssj.dto;

import java.util.List;

public class MenuObject {
	private int menuId;
	private String title;
	private String href;
	private String permission;
	private int parentId;
	private String icon;
	private List<MenuObject> children;
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public List<MenuObject> getChildren() {
		return children;
	}
	public void setChildren(List<MenuObject> children) {
		this.children = children;
	}

	
}
