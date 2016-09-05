package org.utn.frd.lsi.domain.pojo;

import java.io.Serializable;
import java.util.Set;

import org.utn.frd.lsi.domain.User;
import org.utn.frd.lsi.service.UserService;

/**
 * Usuario que se guarda en la session.
 */
public class UserSession implements Serializable{
	private static final long serialVersionUID = 1L;

	private String name;
	private String email;
	private String image;
	private String namespace;
	private String provider;
	private String role;
	private Set<String> allowedMenues;
	
	public UserSession(){}
	
	public UserSession(User u){
		this.name = u.getName();
		this.email = u.getEmail();
		this.image = u.getProfileImage();
		this.namespace = u.getNamespace();
		this.provider = u.getProvider();
		this.role = u.getRole();
		this.allowedMenues = UserService.userAllowedMenues( this.role ); 
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public Set<String> getAllowedMenues() {
		return allowedMenues;
	}

	public void setAllowedMenues(Set<String> allowedMenues) {
		this.allowedMenues = allowedMenues;
	}
}
