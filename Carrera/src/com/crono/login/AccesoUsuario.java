package com.crono.login;

import java.util.List;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

import com.crono.controlador.encriptar.*;
import com.crono.util.Constantes;
import com.crono.dao.CronoDAO;

public class AccesoUsuario {
	
	private static final Logger logger = Logger.getLogger(AccesoUsuario.class);
    private String usuario, clave;

    public void setUsuario(String usuario) {
    	this.usuario = usuario;
    }

    public void setClave(String clave) {
    	this.clave = clave;
    }

    public String getUsuario() {
    	return this.usuario;
    }

    public String getClave() {
    	return this.clave;
    }

    public void setDatos(String usuario, char[] clave) throws Exception {
    	logger.info(usuario + " - "+clave.toString() ); 
		setUsuario(usuario);
		setClave(Encriptar.SHA1(clave));
		validarUsuario();
    }//fin set datos
    

    public void validarUsuario() throws Exception {
    	
    	List<Integer> rolUsuario = CronoDAO.validarUsuario(getUsuario(), getClave());
    	logger.info(rolUsuario);
    	
    	if(!rolUsuario.isEmpty() && rolUsuario.size() > 0){
    		Main.principal(rolUsuario.get(0), rolUsuario.get(1));
    	}else{
    		JOptionPane.showMessageDialog(null, Constantes.ACCESO_DENEGADO,"ERROR", JOptionPane.ERROR_MESSAGE);
    	}
    }// fin de validarUsuario

}
