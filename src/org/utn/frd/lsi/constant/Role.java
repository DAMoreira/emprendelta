package org.utn.frd.lsi.constant;

import java.util.Arrays;
import java.util.List;

import org.utn.frd.lsi.Roles.Rol;

public class Role {
	
	public static List<Rol> getAll() {
		return Arrays.asList(Rol.ADMIN, Rol.READER);
	}
	
}