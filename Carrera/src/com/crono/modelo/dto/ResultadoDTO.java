package com.crono.modelo.dto;

import java.io.Serializable;

public class ResultadoDTO implements Serializable{
	
	public ResultadoDTO(){}
	
	private static final long serialVersionUID = 1L;
	private String nombres;
	private String apellidos;
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
		return "ResultadoDTO [nombres=" + nombres + ", apellidos=" + apellidos
				+ ", categoria=" + categoria + ", genero=" + genero
				+ ", numero=" + numero + ", tiempo=" + tiempo + "]";
	}
	
	

}
