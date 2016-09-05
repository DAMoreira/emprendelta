package org.utn.frd.lsi.interceptor;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Map;
import java.util.logging.Logger;

import org.utn.frd.lsi.Roles;
import org.utn.frd.lsi.Roles.Rol;
import org.utn.frd.lsi.domain.pojo.UserSession;
import org.utn.frd.lsi.service.UserService;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
 
public class AuthenticationInterceptor implements Interceptor {

    private static final long serialVersionUID = -5011962009065225959L;
	private static final Logger log = Logger.getLogger(AuthenticationInterceptor.class.getName()); 
    
    @Override
    public void init() {
    	UserService.initialize();
    }
 
    @Override
    public String intercept(ActionInvocation actionInvocation)
            throws Exception {
        Map<String, Object> sessionAttributes = actionInvocation.getInvocationContext().getSession();

        UserSession user = (UserSession) sessionAttributes.get("user");
        if(user == null)
            return Action.LOGIN;

        Action action = (Action) actionInvocation.getAction();
        
        String redirectUrl = createActionUrl(actionInvocation);
        
        sessionAttributes.put("redirectAction", redirectUrl);
        
    	if( !"unauthorized".equalsIgnoreCase(redirectUrl) && !Rol.ADMIN.toString().equalsIgnoreCase(user.getRole()) ){
            // check roles
        	Class<? extends Object> actionClass = actionInvocation.getAction().getClass();
        	
        	if( actionClass.isAnnotationPresent(Roles.class) ){
        		Annotation annotation = actionClass.getAnnotation(Roles.class);
        		Roles rolesPermitidos = (Roles) annotation;
        		
        		if( !Arrays.asList( rolesPermitidos.value() ).contains( Roles.Rol.ALL ) 
        				&& !Arrays.asList( rolesPermitidos.value() ).contains( Roles.Rol.valueOf( user.getRole() ) ) ){
                	log.severe("Unauthorized: "+user.getEmail()+" to: "+redirectUrl );
                	return "unauthorized";
        		}
        	}else{
        		throw new Exception("No role defined!");
        	}
            // end check roles
    	}

        if(action instanceof UserAware){
            ((UserAware) action).setUser(user);
        }
        return actionInvocation.invoke();
    }
    
	@Override
	public void destroy() {}
 
	private String createActionUrl(ActionInvocation actionInvocation){
		String namespace= actionInvocation.getProxy().getNamespace();
		String redirectUrl = null;
		
		if(!"/".equals(namespace)){
			redirectUrl = namespace + "/"+ actionInvocation.getProxy().getActionName();
	        if(actionInvocation.getInvocationContext().getParameters().size() > 0){
	        	boolean first = true;
	        	redirectUrl = redirectUrl.concat("?");
	        	for(Map.Entry<String, Object> entry : actionInvocation.getInvocationContext().getParameters().entrySet()){
	            	if(!first){
	            		redirectUrl = redirectUrl.concat("&");
	            	}else{
	            		first = false;
	            	}
	            	redirectUrl = redirectUrl.concat((String)entry.getKey() + "=" + ((String[])entry.getValue())[0]);
	            }
	        }
		}else
			redirectUrl = actionInvocation.getProxy().getActionName();
        
        return redirectUrl;
	}
}
