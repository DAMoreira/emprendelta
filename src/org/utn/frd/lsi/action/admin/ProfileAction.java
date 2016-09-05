package org.utn.frd.lsi.action.admin;

import java.util.logging.Logger;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.utn.frd.lsi.Roles;
import org.utn.frd.lsi.Roles.Rol;
import org.utn.frd.lsi.domain.User;
import org.utn.frd.lsi.manager.UserManager;
import org.utn.frd.lsi.service.UserService;

@SuppressWarnings("serial")
@Roles(value=Rol.ALL)
public class ProfileAction extends AdminAction {
	
	private static final Logger log = Logger.getLogger(ProfileAction.class.getName());
	private String userEmail;
	private User user;
	private String id;
	private String description;
	private String provider;
	
	@Action(value="myProfile", results={
			@Result(name="success", location="myProfile.jsp")
	})
	public String editProfile() {
		userEmail = UserService.getCurrentEmail();
		user = (new UserManager()).getByEmail(userEmail);
		id = "" + user.getId();
		description = user.getDescription();
		provider = user.getProvider();
		
		return "success";
	}
	
	@Action(value="saveProfile", results={
			@Result(name="success", type="redirectAction",
					params={"namespace", "/admin", "actionName", "dashboard"})
	})
	public String saveProfile() {
		user.setId(Long.parseLong(id));
		(new UserManager()).update(user);
		UserService.updateCurrentUser(user);
		
		return "success";
	}
	
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}
}
