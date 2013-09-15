package com.crono.controlador.controladorBD.bd;

public class Propiedades {
    
	
    static String password="";
	static String url="jdbc/:mysql/://localhost/tesis1";
	static String usuario="root";
	static String driver="org.gjt.mm.mysql.Driver";

	
	public static void setPassword(String password) {
	    Propiedades.password = password;
	}
	
	public static void setUrl(String url) {
	    Propiedades.url = url;
	}
	
	public static void setUsuario(String usuario) {
	    Propiedades.usuario = usuario;
	}
	
	public static void setDriver(String driver) {
	    Propiedades.driver = driver;
	}


}
