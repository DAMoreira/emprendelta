package org.utn.frd.lsi.action.admin;

import java.util.List;
import java.util.logging.Logger;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Result;
import org.utn.frd.lsi.Roles;
import org.utn.frd.lsi.Roles.Rol;
import org.utn.frd.lsi.constant.Role;
import org.utn.frd.lsi.constant.State;
import org.utn.frd.lsi.domain.User;
import org.utn.frd.lsi.manager.UserManager;
import org.utn.frd.lsi.service.EmailService;

@Roles
public class CollaboratorsAction extends AdminAction {

	private static final long serialVersionUID = 5383040771515451549L;
	private static final Logger log = Logger.getLogger(CollaboratorsAction.class.getName()); 
	
	private List<User> listCollaborators;
	private String emailAddress;
	private List<Rol> roleList;
	private String contactRole;
	private String emailSubject;
	private String emailBody;
	private UserManager userManager = new UserManager();
	
	@Action(value="collaborators", results={
			@Result(name="success", location="collaborators.jsp")
	})
	public String collaborators() {
		setCollaboratorsView();
		return "success";
	}
	
	@Action(value="sendInvitation", results={
			@Result(name="success", location="collaborators.jsp"),
			@Result(name="error", location="collaborators.jsp"),
	})
	public String sendInvitation() {
		try {
			//EmailService.sendInvitation(emailAddress, emailSubject, emailBody);
		} catch (Exception e) {
			log.severe("Error en el envío de Email de invitación" + e.getMessage());
			addActionError( getText("invitation.send.error") );
			return "error";
		}
		addActionMessage( getText("invitation.send.success") );
		createPendingUser();
		setCollaboratorsView();
		return "success";
	}
	
	private void createPendingUser() {
		User pendingUser = new User();
		pendingUser.setState(State.PENDING);
		pendingUser.setRole(contactRole);
		pendingUser.setEmail(emailAddress);
		pendingUser.setNamespace(namespace);
		userManager.save(pendingUser);
	}
	
	private void setCollaboratorsView() {
		listCollaborators = userManager.getCollaborators(namespace);
		roleList = Role.getAll();
		emailAddress = null;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailContact) {
		this.emailAddress = emailContact;
	}

	public List<Rol> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Rol> roleList) {
		this.roleList = roleList;
	}

	public String getContactRole() {
		return contactRole;
	}

	public void setContactRole(String contactRole) {
		this.contactRole = contactRole;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getEmailBody() {
		return emailBody;
	}

	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}

	public List<User> getListCollaborators() {
		return listCollaborators;
	}

	public void setListCollaborators(List<User> listCollaborators) {
		this.listCollaborators = listCollaborators;
	}

}