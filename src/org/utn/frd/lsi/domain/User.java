package org.utn.frd.lsi.domain;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.brickred.socialauth.Profile;
import org.brickred.socialauth.util.BirthDate;
import org.utn.frd.lsi.constant.State;
import org.utn.frd.lsi.domain.pojo.UserSession;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class User {

	@Id private Long id;
	@Index private String name;
	@Index private String email;
	@Index private String namespace;
	@Index private String provider;
	private String role;
	private String preferences;
	private Map<String, String> contactInfo;
	private String country;
	private BirthDate dob;
	private String gender;
	private String language;
	private String location;
	private String profileImage;
	private String description;
	private String state;
	
	public User(){}

	public User(com.google.appengine.api.users.User currentUser, String namespace, String role) {
		this.name = currentUser.getNickname();
		this.email = currentUser.getEmail();
		this.namespace = namespace;
		this.role = role;
		this.preferences = "{}";
	}

	public User(Profile p) {
		if( StringUtils.isNotEmpty(p.getFullName()) ){
			this.name = p.getFullName();
		}else if( StringUtils.isNotEmpty(p.getDisplayName()) ){
			this.name = p.getDisplayName();
		}else{
			this.name = p.getFirstName()+" "+p.getLastName();
		}
		
		if( StringUtils.isEmpty(this.email) && "twitter".equalsIgnoreCase(p.getProviderId()) )
			this.email = "@" + p.getDisplayName();
		else
			this.email = p.getEmail();
		
		this.contactInfo = p.getContactInfo();
		this.country = p.getCountry();
		this.dob = p.getDob();
		this.gender = p.getGender();
		this.language = p.getLanguage();
		this.location = p.getLocation();
		this.profileImage = p.getProfileImageURL().replace("http:", "https:");
		this.provider = p.getProviderId();
		p.getRawResponse();
		p.getValidatedId();//fb 1191221624 - tw 122209516
		
	}

	public void update(UserSession user) {
		if(StringUtils.isNotEmpty( user.getName() ))
			this.name = user.getName();
		if(StringUtils.isNotEmpty( user.getEmail() ))
			this.email = user.getEmail();
		if(StringUtils.isNotEmpty( user.getProvider() ))
			this.provider = user.getProvider();
		if(StringUtils.isNotEmpty( user.getNamespace() ))
			this.namespace = user.getNamespace();
		if(StringUtils.isNotEmpty( user.getRole() ))
			this.role = user.getRole();
		if(StringUtils.isNotEmpty( user.getImage() ))
			this.profileImage = user.getImage();
	}

	public void update(User user) {
		if(StringUtils.isNotEmpty( user.getNamespace() ))
			this.namespace = user.getNamespace();
		if(StringUtils.isNotEmpty( user.getRole() ))
			this.role = user.getRole();
		if(StringUtils.isNotEmpty( user.getName() ))
			this.name = user.getName();
		if(StringUtils.isNotEmpty( user.getEmail() ))
			this.email = user.getEmail();
		if( user.getContactInfo()!=null && !user.getContactInfo().isEmpty() )
			this.contactInfo = user.getContactInfo();
		if(StringUtils.isNotEmpty( user.getCountry() ))
			this.country = user.getCountry();
		if(user.getDob()!=null && StringUtils.isNotEmpty( user.getDob().toString() ))
			this.dob = user.getDob();
		if(StringUtils.isNotEmpty( user.getGender() ))
			this.gender = user.getGender();
		if(StringUtils.isNotEmpty( user.getLanguage() ))
			this.language = user.getLanguage();
		if(StringUtils.isNotEmpty( user.getLocation() ))
			this.location = user.getLocation();
		if(StringUtils.isNotEmpty( user.getProfileImage() ))
			this.profileImage = user.getProfileImage();
		if(StringUtils.isNotEmpty( user.getProvider() ))
			this.provider = user.getProvider();
	}
	
	public boolean isPending() {
		return State.PENDING.equalsIgnoreCase( this.state );
	}
	
	public boolean isActive() {
		return State.ACTIVE.equalsIgnoreCase( this.state );
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPreferences() {
		if( StringUtils.isEmpty(preferences) ) preferences = "{}";
		return preferences;
	}

	public void setPreferences(String preferences) {
		this.preferences = preferences;
	}

	public Map<String, String> getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(Map<String, String> contactInfo) {
		this.contactInfo = contactInfo;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public BirthDate getDob() {
		return dob;
	}

	public void setDob(BirthDate dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


}
