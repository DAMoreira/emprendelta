package org.utn.frd.lsi.action.admin;

import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.utn.frd.lsi.domain.pojo.UserSession;
import org.utn.frd.lsi.manager.UserManager;
import org.utn.frd.lsi.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@Namespace("/admin")
@ParentPackage("user")
@InterceptorRef("authStack")
public class AdminAction extends ActionSupport implements Preparable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(AdminAction.class.getName());
	protected String namespace;
	protected String skin;
	
	@Override
	public void prepare() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		if( !session.containsKey("namespace") || StringUtils.isEmpty((String)session.get("namespace")) ){
			UserSession user = UserService.getCurrentUser();
			
			if(user!=null){
				namespace = (new UserManager()).getNamespace( user.getEmail() );
				if( StringUtils.isEmpty(namespace) ) throw new Exception( );
				session.put("namespace", namespace);
			}
		}else{
			namespace = (String) session.get("namespace");
		}
	}

	public String getSkin() {
		skin = (new UserManager()).getPreference("skin");
		if( StringUtils.isEmpty(skin) ){
			skin = "skin-blur-kiwi";
		}

		return skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}

	
}
