package org.utn.frd.lsi.action.admin;

import java.util.logging.Logger;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.utn.frd.lsi.Roles;
import org.utn.frd.lsi.Roles.Rol;
import org.utn.frd.lsi.manager.UserManager;

@SuppressWarnings("serial")
@Namespace("/")
@Roles(value=Rol.ALL)
public class UserAction extends AdminAction{

	private static final Logger log = Logger.getLogger(UserAction.class.getName());
	private String preferenceName;
	private String preferenceValue;

	@Action(value="savepreference", results={
			@Result(name="success", type="json")
	})
	public String savePreference(){
		(new UserManager()).setPreference(preferenceName, preferenceValue);
		return "success";
	}

	@Action(value="getpreference", results={
			@Result(name="success", type="json")
	})
	public String getPreference(){
		preferenceValue = (new UserManager()).getPreference(preferenceName);

		return "success";
	}

	public String getPreferenceName() {
		return preferenceName;
	}

	public void setPreferenceName(String preferenceName) {
		this.preferenceName = preferenceName;
	}

	public String getPreferenceValue() {
		return preferenceValue;
	}

	public void setPreferenceValue(String preferenceValue) {
		this.preferenceValue = preferenceValue;
	}

	



}




