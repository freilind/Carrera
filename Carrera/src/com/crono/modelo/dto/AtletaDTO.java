package com.crono.modelo.dto;

import java.io.Serializable;

public class AtletaDTO implements Serializable {

	public AtletaDTO(){}
	
	private static final long serialVersionUID = 1L;
	private String nombres;
	private String apellidos;
	private String fechaNacimiento;
	private String cedula;
	private int sexo;
	private String telefono;
	private String email;
	private int discapacitado;
	private String aux;
	
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public int getDiscapacitado() {
		return discapacitado;
	}
	public void setDiscapacitado(int discapacitado) {
		this.discapacitado = discapacitado;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public int getSexo() {
		return sexo;
	}
	public void setSexo(int sexo) {
		this.sexo = sexo;
	}
	
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAux() {
		return aux;
	}
	public void setAux(String aux) {
		this.aux = aux;
	}
	
	
	@Override
	public String toString() {
		return "AtletaDTO [nombres=" + nombres + ", apellidos=" + apellidos
				+ ", fechaNacimiento=" + fechaNacimiento + ", cedula=" + cedula
				+ ", sexo=" + sexo + ", telefono=" + telefono + ", email="
				+ email + ", discapacitado=" + discapacitado + ", aux=" + aux
				+ "]";
	}
	


}
