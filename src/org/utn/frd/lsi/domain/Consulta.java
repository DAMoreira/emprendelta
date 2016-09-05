package org.utn.frd.lsi.domain;

import java.util.Date;

import com.google.appengine.api.datastore.Text;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Consulta {

	@Id private Long id;
	private Date fechaCreado;
	private String nombre;
	private String email;
	private String telefono;
	private String nombreProyecto;
	private String productoServicio;
	private Text descripcion;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFechaCreado() {
		return fechaCreado;
	}
	public void setFechaCreado(Date fechaCreado) {
		this.fechaCreado = fechaCreado;
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
	public Text getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(Text descripcion) {
		this.descripcion = descripcion;
	}
	public void setDescripcion(String descripcion2) {
		setDescripcion(new Text(descripcion2));
	}


}
