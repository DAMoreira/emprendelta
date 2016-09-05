package org.utn.frd.lsi.manager;

import static org.utn.frd.lsi.service.OfyService.ofy;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.utn.frd.lsi.constant.State;
import org.utn.frd.lsi.domain.User;
import org.utn.frd.lsi.domain.pojo.UserSession;
import org.utn.frd.lsi.service.UserService;

import com.googlecode.objectify.Key;

public class UserManager extends ObjectManager<User> {

	public UserManager() {
		super(User.class);
	}
	
	@Override
	public Key<User> save(User user){
		User u=null;
		List<User> lu = ofy().load().type( User.class )
				.filter("email", user.getEmail())
				.filter("provider", user.getProvider())
				.list();
		if( lu.size()>0 ) u = lu.get(0);

		if(u!=null){
			u.update( user );
			return super.save(u);
		}else
			return super.save(user);
	}

	public String getNamespace(String email){
		List<User> list = journalsOf( email );
		String namespace;
		if(list.size()==0)
			namespace = "";
		else
			namespace = list.get(0).getNamespace();
		return namespace;
	}


	public List<User> journalsOf(String email) {
		return this.filter("email", "", email);
	}
	
	public JSONObject getPreferences(){
		JSONObject json;
		String email = UserService.getCurrentEmail();
		try {
			User user = getByEmail( email );
			String prefs = user.getPreferences();
			json = new JSONObject(prefs);
		} catch (JSONException e) {
			json = new JSONObject();
		}
		return json;
	}

	public void setPreferences(String email, JSONObject json ){
		User user = getByEmail( email );
		user.setPreferences( json.toString() );
		this.save(user);
	}


	public void setPreference( String preferenceName,
			String preferenceValue) {
		String email = UserService.getCurrentUser().getEmail();
		try {
			User user = getByEmail( email );
			JSONObject json = new JSONObject( user.getPreferences() );
			json.put(preferenceName, preferenceValue);
			user.setPreferences( json.toString() );
			this.save(user);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public String getPreference(String preferenceName) {
		String email = UserService.getCurrentUser().getEmail();
		try {
			User user = getByEmail( email );
			JSONObject json = new JSONObject( user.getPreferences() );
			if( json.has(preferenceName) )
				return json.getString(preferenceName);
			
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		return "";
	}


	public User getByEmail(String email) {
		List<User> usrs;
		usrs = this.filter("email", "", email);
		if(usrs!=null && usrs.size()>0) return usrs.get(0);
		return null;
	}

	public void update(UserSession userSession) {
		User u=null;
		List<User> lu = ofy().load().type( User.class )
				.filter("email", userSession.getEmail())
				.filter("provider", userSession.getProvider())
				.list();
		if( lu.size()>0 ) u = lu.get(0);

		if(u!=null){
			u.update( userSession );
			u.setState( State.ACTIVE );
			super.save(u);
		}
	}
	
	public void update(User user) {
		User entity = get(user.getId());
		
		entity.setName(user.getName());
		entity.setDescription(user.getDescription());
		entity.setProfileImage(user.getProfileImage());
		
		save(entity);
	}

	public List<User> getCollaborators(String namespace) {
		List<User> collaborators = ofy().load().type( User.class )
				.filter("namespace", namespace)
				.list();
		return collaborators;
	}

	public User saveNewUser(User u) {
		super.save(u);
		return u;
	}

}