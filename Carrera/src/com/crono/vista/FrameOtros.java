package com.crono.vista;

import javax.swing.*;

import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.crono.controlador.controladorBD.bd.ControladorBD;
import com.crono.dao.CronoDAO;
import com.crono.mail.JavaMail;
import com.crono.modelo.dto.AtletaDTO;
import com.crono.resultados.Diploma;
import com.crono.util.*;


public class FrameOtros extends Thread {

	private static final Logger logger = Logger.getLogger(FrameOtros.class);
    private Panel ctpOtros;
    public static JTabbedPane tbpAdmin;
    private JLabel lblCedula, lblCambiarNmero, lblDescalificar, lblLogo;
    private JTextField txfEliminar,txfCambiar, txfDescalificar;
    private int aux, num;
    private JButton btnBorrar, btnSalir, btnDiploma, btnEmail;
    
    public FrameOtros(){
		ctpOtros = new Panel();
		FramePrincipal.tbpPrincipal.addTab("Otros", null,ctpOtros, null);
		ctpOtros.setLayout(null);
		
		lblCedula = new JLabel("Eliminar Atleta:");
		lblCedula.setFont(Fonts.FONT_LABEL);
		lblCedula.setBounds(38, 57, 110, 20);
			
		txfEliminar = new JTextField("Cedula", 10);
		txfEliminar.setFont(Fonts.FONT_TEXT);
		txfEliminar.setBounds(175, 51, 122, 28);
			
		lblCambiarNmero = new JLabel("Cambiar N\u00FAmero:");
		lblCambiarNmero.setFont(Fonts.FONT_LABEL);
		lblCambiarNmero.setBounds(38, 120, 120, 20);
		
		
		txfCambiar = new JTextField("Cedula",10);
		txfCambiar.setFont(Fonts.FONT_TEXT);
		txfCambiar.setBounds(175, 114, 122, 28);
			
		lblDescalificar = new JLabel("Descalificar:");
		lblDescalificar.setFont(Fonts.FONT_LABEL);
		lblDescalificar.setBounds(38, 177, 100, 20);
			
		txfDescalificar = new JTextField();
		txfDescalificar.setText("Numero");
		txfDescalificar.setFont(Fonts.FONT_TEXT);
		txfDescalificar.setBounds(175, 171, 122, 28);	
		txfDescalificar.setColumns(10);
		
		btnBorrar = new JButton("Borrar Resultados");
		btnBorrar.setFont(Fonts.FONT_BOTON);
		btnBorrar.setIcon(new ImageIcon(Constantes.RUTA_ICONOS+"eraser.png"));
		btnBorrar.setToolTipText("Borra todos los resultados de la Base de Datos");
		btnBorrar.setBounds(245, 344, 142, 50);
		
		btnEmail = new JButton("Enviar Email");
		btnEmail.setFont(new Font("SansSerif", Font.PLAIN, 11));
		//btnEmail.setIcon(new ImageIcon(Constantes.RUTA_ICONOS+"eraser.png"));
		btnEmail.setToolTipText("Enviar email masivo con los resultados");
		btnEmail.setBounds(400, 344, 142, 50);

		btnDiploma = new JButton("Diplomas");
		btnDiploma.setFont(Fonts.FONT_BOTON);
		//btnEmail.setIcon(new ImageIcon(Constantes.RUTA_ICONOS+"eraser.png"));
		btnDiploma.setToolTipText("Generar los Diplomas");
		btnDiploma.setBounds(400, 274, 142, 50);
		
		btnSalir = new JButton("Salir");
		btnSalir.setFont(Fonts.FONT_BOTON);
		btnSalir.setIcon(new ImageIcon(Constantes.RUTA_ICONOS+"delete.png"));
		btnSalir.setBounds(565, 400, 120, 50);
		
		
		ctpOtros.add(lblCedula);
		ctpOtros.add(txfEliminar);
		ctpOtros.add(lblCambiarNmero);
		ctpOtros.add(txfCambiar);
		ctpOtros.add(lblDescalificar);
		ctpOtros.add(txfDescalificar);
		ctpOtros.add(btnBorrar);
		ctpOtros.add(btnEmail);
		ctpOtros.add(btnDiploma);
		ctpOtros.add(btnSalir);
		ctpOtros.add(lblLogo);
		
	
		txfEliminar.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent evt) {
				txfEliminar.setText("");
			}
		});	
		
		txfCambiar.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent evt) {
				txfCambiar.setText("");
			}
		});
		
		txfEliminar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
				    eliminarAtleta();
				}
			}
		});
		
		
		txfCambiar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
				    cambiarNumero();
				}
			}
		});
		
		txfDescalificar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
				    descalificarAtleta();
				}
			}
		});
		
		txfDescalificar.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent evt) {
				txfDescalificar.setText("");
			}
		});
		
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				borrarTiempos();
			}
		});
		
		btnSalir.addKeyListener(new KeyAdapter() {
		    public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
				    salir();
				}
		    }
		});
		
		
		btnSalir.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	salir();
		    }
		});
		
		btnEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				start();
				btnEmail.setEnabled(false);
			}
		});
		
		btnDiploma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				generarPdf();
				btnDiploma.setEnabled(false);
			}
		});
    }//fin contructor
		
	
    
    
    
    private void eliminarAtleta() {
    	if (validarCedula(txfEliminar.getText().trim())) {
			if (CronoDAO.usuarioExiste(txfEliminar.getText())) {
				CronoDAO.borrarAtleta(txfEliminar.getText());
			} else {
				JOptionPane.showMessageDialog(null, Constantes.CEDULA_NO_REGISTRADA, "ERROR",JOptionPane.ERROR_MESSAGE);		
			}//fin if
		}
    }//fin eliminar atleta
    
    
    
    private void cambiarNumero() {
    	if (validarCedula(txfCambiar.getText().trim())) {
			if (CronoDAO.usuarioExiste(txfCambiar.getText())) {
				int numero=0;
		    	Lectura le = new Lectura(); 	
		    	numero = le.leerInt("Nuevo N\u00FAmero");
		    	if(numero > 0){
					if(CronoDAO.numeroExiste(numero)) {
						JOptionPane.showMessageDialog(null, Constantes.NUMERO_REGISTRADO, "ERROR",JOptionPane.ERROR_MESSAGE);
					}else {
						CronoDAO.setNuevoNumero(numero, txfCambiar.getText());
					}
		    	}
			} else {
					JOptionPane.showMessageDialog(null, Constantes.CEDULA_NO_REGISTRADA, "ERROR",JOptionPane.ERROR_MESSAGE);		
			}//fin if
		}
    }//fin eliminar atleta
    
    
    private void  descalificarAtleta() {
    	if(validarNumero()){
	    	if(CronoDAO.numeroExiste(txfDescalificar.getText())) {
	    		CronoDAO.descalificarAtleta(txfDescalificar.getText());
	    	}else {
	    		JOptionPane.showMessageDialog(null, Constantes.NUMERO_NO_EXISTE, "ERROR",JOptionPane.ERROR_MESSAGE);
	    	}	
    	}
    }//fin descalificar atleta 
    
    
    private boolean validarCedula(String cedula){
    	aux=0;
    	try{
    		aux=Integer.parseInt(cedula);
    		if(aux < 1) {
    			JOptionPane.showMessageDialog(null, Constantes.CEDULA, "ERROR", JOptionPane.ERROR_MESSAGE);
        		return false;
    		}  			
    	}catch (Exception ex) {
    		JOptionPane.showMessageDialog(null,Constantes.CEDULA, "ERROR", JOptionPane.ERROR_MESSAGE);
    		logger.debug(ex);
    		return false;
    	}
    	
    	return true;
    }//fin Validar cedula
     

    private boolean validarNumero(){
    	num=0;
    	try {
    		num=Integer.parseInt(txfDescalificar.getText());
    		if(num < 1) {
    			JOptionPane.showMessageDialog(null, Constantes.NUMERO_POSITIVO, "ERROR", JOptionPane.ERROR_MESSAGE);
        		return false;
    		}  
    	}catch (Exception ex) {
			JOptionPane.showMessageDialog(null, Constantes.NUMERO_POSITIVO, "ERROR",JOptionPane.ERROR_MESSAGE);
			logger.debug(ex);
			return false;
		}
    	
    	return true;
    }//fin validar numero
  
    
    private void borrarTiempos() {

    	Lectura lec = new Lectura();
		String codR="";
		codR=lec.leerString("Ingrese C\u00F3digo Borrado");
		if(Constantes.CODIGO_AUTORIZACION.equals(codR)){
			CronoDAO.borrarTiempos();
		 }//fin if
    		
    }//fin borrar tiempos
    
    
    public void run(){
    	enviarEmail();
    }
    
    
    public void generarPdf(){
    	Thread hilo2 = new Thread() {
		public void run() { 
			
			List<AtletaDTO> listAtletas = buscarAtletas();
			
			for(Iterator<AtletaDTO> iterator = listAtletas.iterator(); iterator.hasNext();){
    			AtletaDTO a = iterator.next();
    			new Diploma(a);
    		}
			btnDiploma.setEnabled(true);		
		}
		
		};
		hilo2.start();
    }//generarPdf
    
    
    private void enviarEmail(){
    	
    	List<String> listEmail = getEmail();
    	List<String> listEmail2 = validarEmail(listEmail);
    	List<String> listEnviados = new ArrayList<String>();
    	
    	JavaMail email = new JavaMail();
    	try{
    		for(Iterator<String> iterator = listEmail2.iterator(); iterator.hasNext();){
    			String mail = iterator.next();
    			String cedula = buscarCedula(mail);
    			if (!cedula.isEmpty()){
    				email.send(mail,"Esto es una prueba masiva","Este correo fue enviado usando JavaMail e hilos", cedula+".pdf");
        			listEnviados.add(mail);
    			}
    		}
    		
    		if(!listEnviados.isEmpty()){
    			JTextArea textArea = new JTextArea(10, 25);
    			String aux = "";
    			for(Iterator<String> iterator = listEnviados.iterator(); iterator.hasNext();){
        			String mail = iterator.next();
        			aux+=mail+"\n";
        		}
    		    
    			textArea.setText(aux);
    		    textArea.setEditable(false);
    		    JScrollPane scrollPane = new JScrollPane(textArea);
    			
    			
    			JOptionPane.showMessageDialog(null, scrollPane, "Emails Enviados", JOptionPane.INFORMATION_MESSAGE);
    		}
    	}catch(Exception ex){
    		JOptionPane.showMessageDialog(null, "Ha ocurrido un Error Enviando los Email.","ERROR", JOptionPane.ERROR_MESSAGE);
    		logger.debug(ex);
    	}finally{
    		btnEmail.setEnabled(true);
    	}

    }//fin enviarEmail
    
    
    private List<String> getEmail(){
    	String sql = "SELECT email FROM atleta";
    	List<String> result = new ArrayList<String>();
    	
    	try {
    	    ControladorBD.addSql(sql);
    	    ControladorBD.execute();

    	    while (ControladorBD.finRecordSet()) {
		    	result.add(ControladorBD.getString("email"));
	 	    }
    	} catch (Exception ex) {
    	    JOptionPane.showMessageDialog(null, "Ha ocurrido un error inesperado.","ERROR", JOptionPane.ERROR_MESSAGE);
    	    logger.debug(ex);
    	}
    	return result;
    }
    
    
    private List<String> validarEmail(List<String> listEmail){
    	
    	List<String> result = new ArrayList<String>();
    	
        Pattern p = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");	// Establecer el patron
        
        for(Iterator<String> iterator = listEmail.iterator(); iterator.hasNext();){
        	String mail = iterator.next();
        	Matcher m = p.matcher(mail); // Asociar el string al patron
        	
        	if(m.matches()){// Comprobar si encaja
        		result.add(mail);
        	}
        }
        return result;    	
    }
    
    
    private String buscarCedula(String mail){
    	
    	String sql = "SELECT cedula FROM atleta WHERE email = '" + mail+ "'";
    	String result = "";
    	
    	try {
    	    ControladorBD.addSql(sql);
    	    ControladorBD.execute();

    	    while (ControladorBD.finRecordSet()) {
		    	result = (ControladorBD.getString("cedula"));
	 	    }
    	} catch (Exception ex) {
    	    JOptionPane.showMessageDialog(null, "Ha ocurrido un error inesperado.","ERROR", JOptionPane.ERROR_MESSAGE);
    	    logger.debug(ex);
    	}
    	return result;
    	
    }
    
    
    private List<AtletaDTO> buscarAtletas(){
       	
    	List<AtletaDTO> result = new ArrayList<AtletaDTO>();
    	
	    String sql = "SELECT nombre, apellido, cedula, (SELECT tiempo FROM tiempo WHERE num =  numero)  FROM atleta WHERE id < 1000 ";
	    
	    try {
    		ControladorBD.addSql(sql);
    		ControladorBD.execute();
    		 while(ControladorBD.finRecordSet()) {
    			 	AtletaDTO a = new AtletaDTO();
	    		    a.setNombres(ControladorBD.getString("nombre"));
	    		    a.setApellidos(ControladorBD.getString("apellido"));
	    		   // a.setCedula(ControladorBD.getInt("cedula"));
	    		  //  a.setTiempo(ControladorBD.getString("(SELECT tiempo FROM tiempo WHERE num =  numero)"));
	    		    result.add(a);
    		}//fin while
	    } catch (Exception ex) {
    		JOptionPane.showMessageDialog(null,"Ha Ocurrido un Error Inesperado.", "ERROR",JOptionPane.ERROR_MESSAGE);
    		logger.debug(ex);
	    }
	    return result;
    }
    
    
    public void salir() {
    	System.exit(0);
    }
    

}
