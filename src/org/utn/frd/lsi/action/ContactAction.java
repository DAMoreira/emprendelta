package org.utn.frd.lsi.action;

import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.utn.frd.lsi.service.EmailService;
import org.utn.frd.lsi.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class ContactAction extends ActionSupport {

	private static final long serialVersionUID = -7467922092811822591L;
	private static final Logger log = Logger.getLogger(ContactAction.class.getName());
	
	private String senderEmail;
	private String message;
	private boolean isAnon;
	
	@Action(value="contact", results={
			@Result(name="success", location="contact.jsp")
	})
	public String contact() {
		senderEmail = UserService.getCurrentEmail();
		
		if ( !StringUtils.isEmpty(message) ) {
			EmailService. contactAdmin(message, senderEmail);
			addActionMessage( getText("contact.message.sent.admin") );
			if ( StringUtils.isEmpty(senderEmail) )
				senderEmail = null;
			message = null;
		}
		
		return "success";
	}

	public String getSenderEmail() {
		return senderEmail;
	}

	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isAnon() {
		return isAnon;
	}

	public void setAnon(boolean isAnon) {
		this.isAnon = isAnon;
	}

}
