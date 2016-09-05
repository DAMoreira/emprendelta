package org.utn.frd.lsi.action;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.Profile;
import org.brickred.socialauth.SocialAuthConfig;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.util.SocialAuthUtil;
import org.utn.frd.lsi.Roles.Rol;
import org.utn.frd.lsi.constant.State;
import org.utn.frd.lsi.domain.User;
import org.utn.frd.lsi.domain.UserAccount;
import org.utn.frd.lsi.domain.pojo.UserSession;
import org.utn.frd.lsi.manager.UserAccountManager;
import org.utn.frd.lsi.manager.UserManager;
import org.utn.frd.lsi.service.EmailService;
import org.utn.frd.lsi.service.SecurityService;
import org.utn.frd.lsi.service.UserService;
import org.utn.frd.lsi.util.EmailFormatValidator;
import org.utn.frd.lsi.util.PasswordHash;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends ActionSupport implements SessionAware, ModelDriven<UserSession> {
	
	private static final Logger log = Logger.getLogger(LoginAction.class.getName());
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private Map<String, Object> sessionAttributes = null;
	private String url;
	private String id;
	private UserSession userSession = new UserSession();
	//for redirection
	private String redirectAction;
	//For registering
	private User user;
	private String password;
	private String username;
	private String passwordConfirmation;
	//For logging in
	//private String logInPassword;
	//private String logInUsername;
	//For activating account
	private String activationKey;
	private String activationEmail;
	private String resetPasswordEmail;
	private String resetCode;
	private String boxActive;
	
	@Action(value="login", results={
			@Result(name="success", location="/login.jsp")
	})
	public String login() {
		boxActive="login";
		return "success";
	}
	
	@Action(value="authenticate", results={
			@Result(name="success", type="redirect", location="%{redirectAction}"),
			@Result(name="inactive", location="/activationPending.jsp"),
			@Result(name="error", location="/login.jsp")
	})
	public String authenticate(){
		UserAccount userAccount = null;
		
		//check missing data
		if( StringUtils.isEmpty(this.username) || StringUtils.isEmpty(this.password) ) {
			addActionError( getText("login.genericError") );
			return "error";
		}
			
		try {
			EmailFormatValidator emailValidator = new EmailFormatValidator();
			//Recover account
			userAccount = (new UserAccountManager()).getBy(emailValidator.validate(this.username)?"email":"username",username);
			
			//Check password
			if( userAccount == null || !PasswordHash.validatePassword(password, userAccount.getPassword()) ) {
				addActionError( getText("login.invalidCredentials") );
				return "error";
			}
		} catch (Exception e) {
			addActionError( getText("login.genericError") );
			log.severe("Error en autenticación:" + e.getLocalizedMessage());
			return "error";
		}
			
		if(!userAccount.isActive()) {
			UserService.setRegisteredUserEmail(userAccount.getEmail());
			return "inactive";
		}
		
		UserManager um = new UserManager();
		User user = um.getByEmail(userAccount.getEmail());

		//account is active - put user into session
		ActionContext.getContext().getSession().put("user", new UserSession(user));
		
		// Make NON-ACTIVE user ACTIVE
		if ( !user.isActive() ) {
			user.setState(State.ACTIVE);
			um.save(user);
		}
		
		// check if redirect
		this.redirectAction = (String) ActionContext.getContext().getSession().get("redirectAction");
		
		if(StringUtils.isEmpty(redirectAction))
			redirectAction = "/";
		
		return "success";
	}
	
	@Action(value="activate", results={
			@Result(name="success", location="/activationCompleted.jsp"),
			@Result(name="error", location="/activation.jsp")
	})
	public String activate(){
		//if activationKey, activation email are not null -> activate account
		if(StringUtils.isEmpty(activationKey) || StringUtils.isEmpty(activationEmail))
			return "error";
			
		UserAccountManager userAccountManager = new UserAccountManager();

		//look for the account related to the given key and activate it
		UserAccount userAccount = userAccountManager.getBy("email", activationEmail);
		if( userAccount == null || !userAccount.getActivationKey().equals(activationKey)) {
			addActionError( getText( "activation.genericError" ) );
			return "error";
		}

		userAccount.active();
		userAccountManager.save(userAccount);
		EmailService.sendWelcome((new UserManager()).getBy("email", userAccount.getEmail()));
		
		//account is active - put user into session
		User user = (new UserManager()).getByEmail(userAccount.getEmail());
		ActionContext.getContext().getSession().put("user", new UserSession(user));

		return "success";
	}
	
	@Action(value="sendActivation", results={
			@Result(name="error", location="/login.jsp"),
			@Result(name="success", location="/activation.jsp")
	})
	public String sendActivation(){
		if ( activationEmail == null ) {
			addActionError( getText("activation.email.required") );
			return "success";
		}
		
		try{
			UserAccountManager userAccountManager = new UserAccountManager();
			UserAccount userAccount = userAccountManager.getBy("email", activationEmail);
			String activationKey = userAccount.getActivationKey();
			if ( StringUtils.isEmpty( activationKey ) ) {
				addActionError( getText("activation.pending.error") );
				return "error";
			}
			
			EmailService.sendActivation(activationEmail, activationKey);
		} catch(Exception e) {
			log.severe(e.getLocalizedMessage());
			addActionError( getText("activation.genericError") );
			return "error";
		}
		
		addActionMessage( getText("activation.newCode.sent") + activationEmail );
		return "success";
	}
		
	@Action(value="/register", results={
			@Result(name="success", location="/activation.jsp"),
			@Result(name="error", location="/login.jsp")
	})
	public String register() {
		//TODO define password and user limitations and validate
		try{
			// Form fields validation
			if( !checkFieldsNotEmpty() ) {
				//required data is missing
				addActionError( getText("register.genericError") );
				log.severe("Error while trying to register. Missing data.");
				return "error";
			}
			
			// Password validation
			if ( !checkPasswords() ) {
				addActionError( getText("register.differentPasswords") );
				return "error";
			}
			
			EmailFormatValidator emailValidator = new EmailFormatValidator();
			// email validation
			if ( !emailValidator.validate(user.getEmail()) ) {
				addActionError( getText("register.error.email") );
				return "error";
			}
			
			// User existence validation
			UserManager userManager = new UserManager();
			User u = userManager.getByEmail( this.user.getEmail() );
			
			if ( u!=null ) {
				if ( u.isPending() ) {
					// user has been invited and needs to create a new account
					user.setNamespace(u.getNamespace());
					user.setRole(u.getRole());
				} else {
					//user already exists and is not pending -> set error msg
					addActionError( getText("register.emailAlreadyUsed") );
					log.severe("Error while trying to register. Email already used.");
					return "error";
				}
			}
			
			//save user and add it to current session
			user.setProfileImage("/img/profile-pics/1.jpg");
			if(user.getRole()==null) user.setRole(Rol.ADMIN.toString());
			userManager.save(user);
			
			//generate account
			UserAccountManager userAccountManager = new UserAccountManager();
			UserAccount userAccount = new UserAccount(this.username, SecurityService.encrypt(this.password), user.getEmail());
			userAccount.setActivationKey( SecurityService.generateActivationKey() );
			userAccountManager.save(userAccount);
			
			// Needed for activation email
			//UserService.setRegisteredUserEmail(user.getEmail());
			
			//send activation email
			EmailService.sendActivation(userAccount.getEmail(), userAccount.getActivationKey());
			addActionMessage( getText("activation.code.sent") + activationEmail );
			
			activationEmail = user.getEmail();
			
		} catch(Exception e) {
			addActionError( getText("register.genericError") );
			log.severe("Error while trying to register." + e.getLocalizedMessage());
			return "error";
		}

		return "success";
	}
	
	private boolean checkFieldsNotEmpty() {
		return StringUtils.isNotEmpty(this.user.getEmail()) 
				&& StringUtils.isNotEmpty(this.user.getName()) 
				&& StringUtils.isNotEmpty(this.password) 
				&& StringUtils.isNotEmpty(this.username) 
				&& StringUtils.isNotEmpty(this.passwordConfirmation);
	}
	
	private boolean checkPasswords() {
		return this.password.equals(this.passwordConfirmation);
	}
	
	@Action(value="/socialauth", results={
			@Result(name="success", type="redirect", location="%{url}")
	})
	public String socialAuth() {
		try{
			//Create an instance of SocialAuthConfgi object
			SocialAuthConfig config = SocialAuthConfig.getDefault();

			//load configuration. By default load the configuration from oauth_consumer.properties. 
			//You can also pass input stream, properties object or properties file name.
			config.load();
			
			//Create an instance of SocialAuthManager and set config
			SocialAuthManager manager = new SocialAuthManager();
			manager.setSocialAuthConfig(config);
			
			//URL of YOUR application which will be called after authentication
			HttpServletRequest request = ServletActionContext.getRequest();
			String successUrl = request.getScheme()+"://" + request.getHeader("host") + "/oauth2callback";
			// get Provider URL to which you should redirect for authentication.
			// id can have values "facebook", "twitter", "yahoo" etc. or the OpenID URL
			url = manager.getAuthenticationUrl(id, successUrl);
			
			// Store in session
			ActionContext.getContext().getSession().put("authManager", manager);
		}catch(Exception e){
			log.severe("Error al authenticar: " + e.getLocalizedMessage() );
		}
		return "success";
	}

	@Action(value="/oauth2callback", results={
			@Result(name="success", type="redirect", location="%{redirectAction}")
	})
	public String callback() {
		try{
			// get the auth provider manager from session
			SocialAuthManager manager = (SocialAuthManager)ActionContext.getContext().getSession().get("authManager");

			HttpServletRequest request = ServletActionContext.getRequest();
			// call connect method of manager which returns the provider object. 
			// Pass request parameter map while calling connect method.
			Map<String, String> paramsMap = SocialAuthUtil.getRequestParametersMap(request); 
			AuthProvider provider = manager.connect(paramsMap);

			// get profile
			Profile p = provider.getUserProfile();

			User u = new User( p );
			UserManager userManager = new UserManager();
			User existingUser = userManager.getByEmail( u.getEmail() );
			if ( existingUser != null ) {
				if ( existingUser.isPending() ) {
					existingUser.setState( State.ACTIVE );
					userManager.save(existingUser);
				}
			}else{
				existingUser = userManager.saveNewUser(u);
			}
			
			ActionContext.getContext().getSession().put("user", new UserSession( existingUser ));

			// OR also obtain list of contacts
			//List<Contact> contactsList = provider.getContactList();
			
			//authentication successful - check if redirect
			this.redirectAction = (String) ActionContext.getContext().getSession().get("redirectAction");
			if(StringUtils.isEmpty(redirectAction))
				this.redirectAction = "/dashboard";
			
		}catch(Exception e){
			log.severe("Error despues de authenticar: " + e.getLocalizedMessage() );
		}
		return "success";
	}
	
	@Action(value="logout", results={
			@Result(name="success", type="redirectAction", params = {"namespace", "", "actionName", ""})
	})
	public String logout() {
		try{
			// get the auth provider manager from session
			SocialAuthManager manager = (SocialAuthManager)ActionContext.getContext().getSession().get("authManager");
	
			HttpServletRequest request = ServletActionContext.getRequest();
			// call connect method of manager which returns the provider object. 
			// Pass request parameter map while calling connect method.
			Map<String, String> paramsMap = SocialAuthUtil.getRequestParametersMap(request); 
			AuthProvider provider = manager.connect(paramsMap);
	
			manager.disconnectProvider( provider.getProviderId() );
		}catch(Exception e){}
		
		ActionContext.getContext().getSession().remove("user");
		ActionContext.getContext().getSession().remove("namespace");

		return "success";
	}
	
	@Action(value="passwordReset", results={
			@Result(name="success", location="codeSent.jsp"),
			@Result(name="error", location="login.jsp")
	})
	public String sendPasswordResetCode() {
		UserAccountManager userAccountManager = new UserAccountManager();
		UserAccount ua = userAccountManager.getByEmail( resetPasswordEmail );
		if (ua == null) {
			addActionError( getText("resetPassword.noSuchEmail") );
			return "error";
		}
		String code = SecurityService.generateRecoverPasswordCode();
		ua.setPasswordResetCode( code );
		userAccountManager.save( ua );
		EmailService.sendPasswordReset( resetPasswordEmail, code );
		return "success";
	}
	
	@Action(value="setPassword", results={
			@Result(name="success", location="passwordReset.jsp"),
			@Result(name="error", location="login.jsp")
	})
	public String defineNewPassword() {
		UserAccountManager userAccountManager = new UserAccountManager();
		UserAccount ua = userAccountManager.getByPendingResetCode( resetCode );
		
		if ( ua == null){
			addActionError( getText("resetPassword.genericError") );
			return "error";
		}
		
		return "success";
	}
	
	@Action(value="resetPassword", results={
			@Result(name="success", type="redirectAction", location="login"),
			@Result(name="error", location="login.jsp")
	})
	public String resetPassword() {
		UserAccountManager userAccountManager = new UserAccountManager();
		UserAccount ua = userAccountManager.getByPendingResetCode( resetCode );
		
		if ( ua==null || !ua.getEmail().equalsIgnoreCase(resetPasswordEmail) ){
			addActionError( getText("resetPassword.genericError") );
			return "error";
		}
		
		if ( checkPasswords() ) {
			try {
				ua.resetPassword( SecurityService.encrypt( password ) );
				userAccountManager.save( ua );
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
				addActionError( getText("resetPassword.genericError") );
				log.severe("Error al resetear password del usuario: \n" + e.getMessage());
				return "error";
			}
			return "success";
		} else {
			addActionError( getText("register.differentPasswords") );
			return "error";
		}
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
    public void setSession(Map<String, Object> sessionAttributes) {
        this.sessionAttributes = sessionAttributes;
    }

	@Override
	public UserSession getModel() {
		return this.userSession;
	}

	public User getUser(){
		return this.user;
	}
	
	public void setUser(User user){
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String repassword) {
		this.passwordConfirmation = repassword;
	}
/*	
	public String getLogInPassword() {
		return logInPassword;
	}

	public void setLogInPassword(String logInPassword) {
		this.logInPassword = logInPassword;
	}

	public String getLogInUsername() {
		return logInUsername;
	}

	public void setLogInUsername(String logInUsername) {
		this.logInUsername = logInUsername;
	}
*/
	public String getActivationKey() {
		return activationKey;
	}

	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}

	public String getRedirectAction() {
		return redirectAction;
	}

	public void setRedirectAction(String redirectAction) {
		this.redirectAction = redirectAction;
	}

	public String getResetPasswordEmail() {
		return resetPasswordEmail;
	}

	public void setResetPasswordEmail(String resetPasswordEmail) {
		this.resetPasswordEmail = resetPasswordEmail;
	}

	public String getResetCode() {
		return resetCode;
	}

	public void setResetCode(String resetCode) {
		this.resetCode = resetCode;
	}

	public String getActivationEmail() {
		return activationEmail;
	}

	public void setActivationEmail(String activationEmail) {
		this.activationEmail = activationEmail;
	}

	public String getBoxActive() {
		return boxActive;
	}

	public void setBoxActive(String boxActive) {
		this.boxActive = boxActive;
	}
}
