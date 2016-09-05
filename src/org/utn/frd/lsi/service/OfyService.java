package org.utn.frd.lsi.service;

import java.util.logging.Logger;

import org.utn.frd.lsi.domain.Consulta;
import org.utn.frd.lsi.domain.User;
import org.utn.frd.lsi.domain.UserAccount;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

public class OfyService {
	
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(OfyService.class.getName());

	static {
		
		factory().register(User.class);
		factory().register(Consulta.class);
		factory().register(UserAccount.class);
	}
	
	public static Objectify ofy() {
		return ObjectifyService.ofy();
	}

	public static ObjectifyFactory factory() {
		return ObjectifyService.factory();
	}
}
