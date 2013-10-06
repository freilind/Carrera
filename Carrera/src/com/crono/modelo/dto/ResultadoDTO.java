package com.crono.modelo.dto;

import java.io.Serializable;

public class ResultadoDTO implements Serializable{
	

	public ResultadoDTO(){}
	
	private static final long serialVersionUID = 1L;
	private String nombres;
	private String apellidos;
	private String cedula;
	private String categoria;
	private String genero;
	private String numero;
	private String tiempo;
	
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
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getTiempo() {
		return tiempo;
	}
	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
			builder.append("ResultadoDTO [nombres=");
			builder.append(nombres);
			builder.append(", apellidos=");
			builder.append(apellidos);
			builder.append(", cedula=");
			builder.append(cedula);
			builder.append(", categoria=");
			builder.append(categoria);
			builder.append(", genero=");
			builder.append(genero);
			builder.append(", numero=");
			builder.append(numero);
			builder.append(", tiempo=");
			builder.append(tiempo);
			builder.append("]");
		return builder.toString();
	}
	
	

}
