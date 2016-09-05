package org.utn.frd.lsi;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Roles {

	public enum Rol {
		ADMIN,
		READER,
		ALL
	}
	
	Rol[] value() default Rol.READER;

}