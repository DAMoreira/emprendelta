package org.utn.frd.lsi.manager;

import org.utn.frd.lsi.domain.Consulta;

public class ConsultaManager extends ObjectManager<Consulta> {

	protected ConsultaManager(Class<Consulta> clazz) {
		super(clazz);
	}

	public ConsultaManager() {
		this( Consulta.class );
	}
	
}