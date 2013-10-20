package com.crono.util;

/**
 * @(#)input.java
 *
 * @ esta clase sirve para pedir al usuario datos de tipo string, char, int, double, float, long, short, byte.
 *   mediante inputbox, sele puede pasar el titulo de la ventana por parametro o no.
 *
 * @author Freilin Manzano
 * @version 1.00 2011/6/4
 */

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

import org.apache.log4j.Logger;


public class Lectura {
	
	private static final Logger logger = Logger.getLogger(Lectura.class);
	private boolean paso;
	private String resultado;
	
    public Lectura() {
		paso=false;
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
				    UIManager.setLookAndFeel(info.getClassName());
				    break;
				}
		    }
		} catch (Exception ex) {
		    JOptionPane.showMessageDialog(null, Constantes.LOOK_FEEL,"ERROR", JOptionPane.ERROR_MESSAGE);
		    logger.info(ex);
		    // si Nimbus no esta disponible se puede escojer otro look and feel
		}
    }


	public void error(String msg){ //mensaje de error por si hay una excepcion (tipo de dato invalido)
		JOptionPane.showMessageDialog(null, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
	}
	
	@SuppressWarnings("finally")
	public String leerString(String titulo){
		try{
			resultado =	JOptionPane.showInputDialog(titulo); //lee el dato
			if(resultado == null || resultado.isEmpty())
				resultado = "";
		}catch(NullPointerException np){
			resultado = "";
		}catch(Exception ex){
			logger.info(ex);
		} finally {
			return resultado;
		}
	}//fin leer String
	
	@SuppressWarnings("finally")
	public String leerString(){
		try{
			resultado =	JOptionPane.showInputDialog("Cadena Texto"); //lee el dato
			if(resultado == null || resultado.isEmpty())
				resultado = "";	
		}catch(NullPointerException np){
			resultado = "";
		}catch(Exception ex){
			logger.info(ex);;
		} finally {
			return resultado;
		}
	}//fin leer String
	
	
	public char leerCaracter(String titulo){   //convierte en caracter el string leido
	  	return leerString(titulo).charAt(0);
	}
	
	public char leerCaracter(){   //convierte en caracter el string leido
	  	return leerString().charAt(0);
	}
	
	//************************************************
	
	public int leerInt(){   //convierte en entero el string leido
	
		paso=false;
		int i=0;
		//do{
	  		try{
	  			i=Integer.parseInt(leerString("N\u00FAmero de tipo Entero"));
	  			paso=true;
	  		}catch(NumberFormatException e){
	  			paso=false;
	  			error("*** Error: tipo de dato incorrecto");
	  		}
	  	//}while(!paso);
	
	  	return i;
	}
	
	
	public double leerDouble(){   //convierte en doble el string leido
	
		paso=false;
		double d=0;
		do{
	  		try{
	  			d=Double.parseDouble(leerString("N\u00FAmero de tipo Double"));
	  			paso=true;
	  		}catch(NumberFormatException e){
	  			paso=false;
	  			error("*** Error: tipo de dato incorrecto");
	  		}
	  	}while(!paso);
	
	  	return d;
	}
	
	
	public float leerFloat(){   //convierte en flotante el string leido
	
	    paso=false;
		float f=0;
		do{
	  		try{
	  			f=Float.parseFloat(leerString("N\u00FAmero de tipo Float"));
	  			paso=true;
	  		}catch(NumberFormatException e){
	  			paso=false;
	  			error("*** Error: tipo de dato incorrecto");
	  		}
	  	}while(!paso);
	
	  	return f;
	}
	
	
	public long  leerLong(){   //convierte en largo el string leido
	
	    paso=false;
		long l=0;
		do{
	  		try{
	  			l=Long.parseLong(leerString("N\u00FAmero de tipo Long"));
	  			paso=true;
	  		}catch(NumberFormatException e){
	  			paso=false;
	  			error("*** Error: tipo de dato incorrecto");
	  		}
	  	}while(!paso);
	
	  	return l;
	}
	
	
	public short leerShort(){   //convierte en short el string leido
	
	    paso=false;
		short s=0;
		do{
	  		try{
	  			s=Short.parseShort(leerString("N\u00FAmero de tipo Short"));
	  			paso=true;
	  		}catch(NumberFormatException e){
	  			paso=false;
	  			error("*** Error: tipo de dato incorrecto");
	  		}
	  	}while(!paso);
	
	  	return s;
	}
	
	
	public byte leerByte(){   //convierte en byte el string leido
	
	    paso=false;
		byte b=0;
		do{
	  		try{
	  			b=Byte.parseByte(leerString("N\u00FAmero de tipo Byte"));
	  			paso=true;
	  		}catch(NumberFormatException e){
	  			paso=false;
	  			error("*** Error: tipo de dato incorrecto");
	  		}
	  	}while(!paso);
	
	  	return b;
	}
	
	
	
	//***** metodos que reciben titulo de parametro
	
	
	
	public int leerInt(String titulo){   //convierte en entero el string leido
	
		paso=false;
		int i=0;
		//do{
	  		try{
	  			i=Integer.parseInt(leerString(titulo));
	  			paso=true;
	  		}catch(NumberFormatException e){
	  		    i=0;
	  			paso=false;
	  			error("*** Error: tipo de dato incorrecto");
	  		}
	  	//}while(!paso);
	
	  	return i;
	}
	
	
	public double leerDouble(String titulo){   //convierte en doble el string leido
	
		paso=false;
		double d=0;
		do{
	  		try{
	  			d=Double.parseDouble(leerString(titulo));
	  			paso=true;
	  		}catch(NumberFormatException e){
	  			paso=false;
	  			error("*** Error: tipo de dato incorrecto");
	  		}
	  	}while(!paso);
	
	  	return d;
	}
	
	
	public float leerFloat(String titulo){   //convierte en flotante el string leido
	
	    paso=false;
		float f=0;
		do{
	  		try{
	  			f=Float.parseFloat(leerString(titulo));
	  			paso=true;
	  		}catch(NumberFormatException e){
	  			paso=false;
	  			error("*** Error: tipo de dato incorrecto");
	  		}
	  	}while(!paso);
	
	  	return f;
	}
	
	
	public long  leerLong(String titulo){   //convierte en largo el string leido
	
	    paso=false;
		long l=0;
		do{
	  		try{
	  			l=Long.parseLong(leerString(titulo));
	  			paso=true;
	  		}catch(NumberFormatException e){
	  			paso=false;
	  			error("*** Error: tipo de dato incorrecto");
	  		}
	  	}while(!paso);
	
	  	return l;
	}
	
	
	public short leerShort(String titulo){   //convierte en short el string leido
	
	    paso=false;
		short s=0;
		do{
	  		try{
	  			s=Short.parseShort(leerString(titulo));
	  			paso=true;
	  		}catch(NumberFormatException e){
	  			paso=false;
	  			error("*** Error: tipo de dato incorrecto");
	  		}
	  	}while(!paso);
	
	  	return s;
	}
	
	
	public byte leerByte(String titulo){   //convierte en byte el string leido
	
	    paso=false;
		byte b=0;
		do{
	  		try{
	  			b=Byte.parseByte(leerString(titulo));
	  			paso=true;
	  		}catch(NumberFormatException e){
	  			paso=false;
	  			error("*** Error: tipo de dato incorrecto");
	  		}
	  	}while(!paso);
	
	  	return b;
	}

	
	public String leerCodigo(String titulo){
		resultado = "";
		try{
			JLabel jPassword = new JLabel("Password");
	        JTextField password = new JPasswordField();
	        Object[] ob = { jPassword, password};
	        int result = JOptionPane.showConfirmDialog(null, ob, titulo,JOptionPane.OK_CANCEL_OPTION);
	 
	        if (result == JOptionPane.OK_OPTION) {
	            resultado = password.getText();
	            System.out.println(resultado);
	            if( resultado == null || resultado.isEmpty())
					resultado = "";
	        }
		}catch(Exception ex){
			logger.info(ex);
			resultado = "";
		}
		
		return resultado;
		
	}//fin leer String

}
