package org.utn.frd.lsi.manager;

import static org.utn.frd.lsi.service.OfyService.ofy;

import java.util.List;

import com.google.appengine.api.NamespaceManager;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;

public class ObjectManager<T> {

	protected String namespace;
	private Class<T> clazz;
	 
    protected ObjectManager(Class<T> clazz) {
        this.clazz = clazz;
    }
    
	public Key<T> save(T entity){
		NamespaceManager.set(namespace);
        return ofy().save().entity(entity).now();
	}
	
	public T get(Long id){
		NamespaceManager.set(namespace);
		return ofy().load().type( clazz ).id( id ).now();
	}
	
	/*
		// Operators are >, >=, <, <=, in, !=, <>, =, ==
		List<Car> cars = ofy().load().type(Car.class).filter("year >", 1999).list();
		List<Car> cars = ofy().load().type(Car.class).filter("year >=", 1999).list();
		List<Car> cars = ofy().load().type(Car.class).filter("year !=", 1999).list();
		List<Car> cars = ofy().load().type(Car.class).filter("year in", yearList).list();
		
		// No operator means ==
		Car car = ofy().load().type(Car.class).filter("vin", "123456789").first().now();
	 */
	public List<T> filter(String field, String operator, String value){
		NamespaceManager.set(namespace);
		return ofy().load().type( clazz ).filter(field + operator, value).list();
	}
	
	//example getBy("email",email);
	public T getBy(String filter,String value){
		
		List<T> list;
		list = this.filter(filter, "", value);
		
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	public Query<T> query(String field, String operator, Object value) {
		// TODO: contemplar reemplazar este método en filter
		NamespaceManager.set(namespace);
		return ofy().load().type( clazz ).filter(field + operator, value);
	}
	
	public List<T> like(String field, String value, int page, int limit){
		NamespaceManager.set(namespace);
		return ofy().load().type( clazz ).filter(field+" >=", value).filter(field+" <", value + "\uFFFD").offset( limit*page ).limit( limit ).list();
	}
	
	public void delete(T entity){
		NamespaceManager.set(namespace);
		ofy().delete().entity(entity);
	}
	
	public List<T> getAll(){
		NamespaceManager.set(namespace);
		return ofy().load().type( clazz ).list();
	}
	
	public List<T> getAll(int limit, int page){
		NamespaceManager.set(namespace);
		return ofy().load().type( clazz ).order("").offset( limit*page ).limit( limit ).list();
	}
	
	public List<T> getAll(int page, String order){
		NamespaceManager.set(namespace);
		int limit = 10;
		return ofy().load().type( clazz ).order( order ).offset( limit*page ).limit( limit ).list();
	}

	public List<T> getAll(int limit, int page, String order){
		NamespaceManager.set(namespace);
		return ofy().load().type( clazz ).order( order ).offset( limit*page ).limit( limit ).list();
	}
	
	public int getPageAmount(int pageSize){
		NamespaceManager.set(namespace);
		return (ofy().load().type( clazz ).count()-1) / pageSize;
	}

	public int getPageAmount(){
		return getPageAmount( 10 );
	}
	
}
