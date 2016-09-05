package org.utn.frd.lsi.action;

import java.util.Date;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.utn.frd.lsi.Roles;
import org.utn.frd.lsi.Roles.Rol;
import org.utn.frd.lsi.domain.Consulta;
import org.utn.frd.lsi.manager.ConsultaManager;
import org.utn.frd.lsi.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

//@InterceptorRef("authStack")
//@ParentPackage("json-default")
@ParentPackage("user")
@Roles(value=Rol.READER)
public class ConsultaAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ConsultaAction.class.getName());

	private String nombre;
	private String email;
	private String telefono;
	private String nombreProyecto;
	private String productoServicio;
	private String descripcion;
	
	public void validate(){
		UserService.getCurrentUser();
		if(StringUtils.isEmpty(nombre)){
			addFieldError("nombre", "El nombre no puede ser nulo");
		}
		if(StringUtils.isEmpty(email)){
			addFieldError("email", "El Email debe tener valor.");
		}

		if(StringUtils.isEmpty(nombreProyecto)){
			addFieldError("nombreProyecto", "El nombre del proyecto debe tener valor.");
		}

		if(StringUtils.isEmpty(productoServicio)){
			addFieldError("productoServicio", "El Producto/Servicio debe tener valor.");
		}

		if(StringUtils.isEmpty(telefono)){
			addFieldError("telefono", "El teléfono debe tener valor.");
		}

		if(StringUtils.isEmpty(descripcion)){
			addFieldError("descripcion", "El texto de la consulta debe tener valor.");
		}

	}
	
	@Action(value="consulta", results={
			@Result(name="success", location="consultorioParaEmprendedores.jsp"),
			@Result(name="input", location="consultorioParaEmprendedores.jsp")
	})
	public String getConsultas() {
		try{
			Consulta consulta = new Consulta();

			consulta.setNombre(nombre);
			consulta.setFechaCreado(new Date());
			consulta.setEmail(email);
			consulta.setNombreProyecto(nombreProyecto);
			consulta.setProductoServicio(productoServicio);
			consulta.setTelefono(telefono);
			consulta.setDescripcion(descripcion);

			ConsultaManager m = new ConsultaManager();
			m.save(consulta);
			addActionMessage("Su consulta fue procesada con exito, en breve nos comunicaremos con Ud.");
		}catch(Exception e){
			LOG.error("No se pudo guardar la consulta...", e);
			addActionError("Ocurrio un error al guardar la consulta, por favor, vuelva a intentar. Si el problema persiste, no dude en comunicarse con nosotros.");
		}
		return "success";
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getNombreProyecto() {
		return nombreProyecto;
	}

	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}

	public String getProductoServicio() {
		return productoServicio;
	}

	public void setProductoServicio(String productoServicio) {
		this.productoServicio = productoServicio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
