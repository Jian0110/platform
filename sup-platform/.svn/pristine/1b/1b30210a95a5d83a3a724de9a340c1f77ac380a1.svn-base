package cn.com.ssj.dto;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
	
	@NotBlank(message = "{login.username.null}")
	private String username;
	@NotBlank(message = "{login.password.null}")
	private String password;
	
	private boolean rememberMe;

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

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
	
	

}
