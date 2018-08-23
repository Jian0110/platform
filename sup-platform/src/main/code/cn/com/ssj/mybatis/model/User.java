package cn.com.ssj.mybatis.model;

import java.util.Date;

import org.apache.shiro.authz.SimpleAuthorizationInfo;

public class User {
    
    private int userId;
    private String username;
    private String password;
    private String nickname;
    private String realname;
    private int roleId;
    private String roleName;
    private String departmentName;
    private int departmentId;
    private String enableText;
    private Date lastLoginTs;
    private boolean enable;
    private String image;
    private SimpleAuthorizationInfo authorizationInfo;
    
    
    
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public SimpleAuthorizationInfo getAuthorizationInfo() {
		return authorizationInfo;
	}
	public void setAuthorizationInfo(SimpleAuthorizationInfo authorizationInfo) {
		this.authorizationInfo = authorizationInfo;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getEnableText() {
		return enableText;
	}
	public void setEnableText(String enableText) {
		this.enableText = enableText;
	}
	public Date getLastLoginTs() {
		return lastLoginTs;
	}
	public void setLastLoginTs(Date lastLoginTs) {
		this.lastLoginTs = lastLoginTs;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	
    
    
    
}
