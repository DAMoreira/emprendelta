package org.utn.frd.lsi.interceptor;

import org.utn.frd.lsi.domain.pojo.UserSession;

public interface UserAware {
	 
    public void setUser(UserSession user);
}