package org.utn.frd.lsi.action;

import java.util.logging.Logger;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.utn.frd.lsi.Roles;
import org.utn.frd.lsi.Roles.Rol;

import com.opensymphony.xwork2.ActionSupport;

//@InterceptorRef("authStack")
@ParentPackage("json-default")
@Roles(value=Rol.ALL)
public class MainAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(MainAction.class.getName());

	private String nombre;
	private String email;
	private String telefono;
	private String nombreProyecto;
	private String productoServicio;
	private String descripcion;
	
	@Action(value="consultorio-para-emprendedores", results={
			@Result(name="success", location="consultorioParaEmprendedores.jsp")
	})
	public String consultorio() {
		return SUCCESS;
	}

	@Action(value="manual-del-emprendedor", results={
			@Result(name="success", location="manualDelEmprendedor.jsp")
	})
	public String manual() {
		return SUCCESS;
	}

	@Action(value="material-de-interes", results={
			@Result(name="success", location="materialDeInteres.jsp")
	})
	public String material() {
		return SUCCESS;
	}

	@Action(value="novedades", results={
			@Result(name="success", location="novedades.jsp")
	})
	public String novedades() {
		return SUCCESS;
	}

	@Action(value="registro-de-emprendedores", results={
			@Result(name="success", location="registroDeEmprendedores.jsp")
	})
	public String registroDeEmprendedores() {
		return SUCCESS;
	}

	@Action(value="contacto", results={
			@Result(name="success", location="contacto.jsp")
	})
	public String contacto() {
		return SUCCESS;
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
