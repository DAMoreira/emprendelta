package org.utn.frd.lsi.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;
import java.util.logging.Logger;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.utn.frd.lsi.domain.User;
import org.utn.frd.lsi.domain.pojo.UserSession;
import org.utn.frd.lsi.manager.UserManager;

import com.opensymphony.xwork2.ActionContext;

public class UserService {
	
	private static final String USER = "user";
	private static final String REGISTERED_USER_EMAIL = "registeredUserEmail";
	
	private static final Logger log = Logger.getLogger(UserService.class.getName());
	private static Map<String, Set<String>> roleActions = new HashMap<String, Set<String>>();

	public static UserSession getCurrentUser() {
		return (UserSession) session().get(USER);
	}
	
	public static void setCurrentUser(User user) {
		session().put(USER, new UserSession( user ));
	}
	
	public static void updateCurrentUser(User user) {
		User u = (new UserManager()).get(user.getId());
		session().put(USER, new UserSession(u));
	}

	public static String getCurrentEmail() {
		if (getCurrentUser() == null) return null;
		return getCurrentUser().getEmail();
	}

	public static void setRegisteredUserEmail(String email) {
		session().put(REGISTERED_USER_EMAIL, email);
	}
	
	public static String getRegisteredUserEmail() {
		return (String) session().get(REGISTERED_USER_EMAIL);
	}
	
	public static Set<String> userAllowedMenues(String role) {
		return roleActions.get( role );
	}
	
	private static Map<String, Object> session() {
		return ActionContext.getContext().getSession();
	}

	public static void initialize() {
    	// inicializar accesos por roles
    	Properties prop = new Properties();
    	InputStream input = null;
    	try {
    		input = new FileInputStream("WEB-INF/menues.properties");
    		// load a properties file
    		prop.load(input);
    		for(Entry<Object, Object> entry : prop.entrySet()){
    			Set<String> s = new HashSet<String>(Arrays.asList( ((String)entry.getValue()).split(",") ));
    			String key = entry.getKey().toString();
    			roleActions.put(key, s);
    		}
    	} catch (IOException ex) {
    		log.severe("ERROR al leer el archivo menues.properties");
    	} catch (Exception ex) {
    		log.severe("ERROR no identificado al cargar los menues por roles: "+ex.getLocalizedMessage());
    	} finally {
    		if (input != null) {
    			try {
    				input.close();
    			} catch (IOException e) {}
    		}
    	}

	}
	
}
