package org.utn.frd.lsi.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class UserAccount {
	@Id private Long id;
	@Index private String username;
	@Index private String email;
	@Index private String passwordResetCode;

	private String password;
	private String activationKey;
	private boolean active;
	
	public UserAccount(){}
	
	public UserAccount(String username, String password, String email){
		this.active = false;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getActivationKey() {
		if(activationKey==null) activationKey="";
		return activationKey;
	}
	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

	public String getPasswordResetCode() {
		return passwordResetCode;
	}

	public void setPasswordResetCode(String pendingResetCode) {
		this.passwordResetCode = pendingResetCode;
	}

	public void active() {
		active = true;
		activationKey = null;
	}

	public void resetPassword(String encrypt) {
		setPassword(encrypt);
		passwordResetCode=null;
	}
}
