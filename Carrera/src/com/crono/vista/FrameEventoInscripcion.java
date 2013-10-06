package com.crono.vista;

import java.awt.Color;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import org.apache.log4j.Logger;

import com.crono.modelo.dto.AtletaDTO;
import com.crono.modelo.administrar.usuario.*;
import com.crono.dao.CronoDAO;
import com.crono.util.*;

public class FrameEventoInscripcion {
	
	private static final Logger logger = Logger.getLogger(FrameEventoInscripcion.class);
	private Panel ctpInscripcion;
	private JTextField txfCedula, txfNombres, txfApellidos, txfCategoria, txfNumero, txfEvento;
	private JLabel lblCedula, lblNombres, lblApellidos, lblCategoria, lblEvento, lblNumero, lblElite, lblLogo;
	private JRadioButton rdbtnNo, rdbtnSi;
	private JButton btnInscribir, btnBorrar, btnListar, btnSalir;
	private ButtonGroup btgElite;
	private int cedula, fecha, temp, idCat, num, idEvento;
	private Calendar cal;
	private Date date;
	private String msg2;
    private StringBuilder msg;
	
	public FrameEventoInscripcion() {
		ctpInscripcion = new Panel();
		ctpInscripcion.setLayout(null);
		FrameEvento.tbpEvento.addTab("Inscripci\u00F3n", null,ctpInscripcion, null);
		
		lblCedula = new JLabel("Cedula");
		lblCedula.setFont(Fonts.FONT_LABEL);
		lblCedula.setBounds(60, 30, 50, 25);
			
		txfCedula = new JTextField();
		txfCedula.setFont(Fonts.FONT_TEXT);
		txfCedula.setBounds(140, 28, 120, 28);
		
		lblNombres = new JLabel("Nombres");
		lblNombres.setFont(Fonts.FONT_LABEL);
		lblNombres.setBounds(60, 70, 60, 25);
		
		txfNombres = new JTextField();
		txfNombres.setFont(Fonts.FONT_TEXT);
		txfNombres.setBounds(140, 68, 200, 28);
		txfNombres.setEnabled(false);
			
		lblApellidos = new JLabel("Apellidos");
		lblApellidos.setFont(Fonts.FONT_LABEL);
		lblApellidos.setBounds(60, 110, 70, 25);
			
		txfApellidos = new JTextField();
		txfApellidos.setFont(Fonts.FONT_TEXT);
		txfApellidos.setBounds(140, 108, 200, 28);
		txfApellidos.setEnabled(false);
		
		lblCategoria = new JLabel("Categor\u00EDa");
		lblCategoria.setFont(Fonts.FONT_LABEL);
		lblCategoria.setBounds(60, 150, 70, 25);
			
		txfCategoria = new JTextField();
		txfCategoria.setFont(Fonts.FONT_TEXT);
		txfCategoria.setBounds(140, 148, 120, 28);
		txfCategoria.setEnabled(false);
		
		lblNumero = new JLabel("N\u00FAmero");
		lblNumero.setFont(Fonts.FONT_LABEL);
		lblNumero.setBounds(60, 190, 55, 25);
		
		txfNumero = new JTextField();
		txfNumero.setFont(Fonts.FONT_TEXT);
		txfNumero.setBounds(140, 188, 120, 28);
				
					
		lblEvento = new JLabel("Evento");
		lblEvento.setFont(Fonts.FONT_LABEL);
		lblEvento.setBounds(60, 230, 55, 25);
			
		txfEvento = new JTextField();
		txfEvento.setEnabled(false);
		txfEvento.setFont(Fonts.FONT_TEXT);
		txfEvento.setBounds(140, 228, 200, 28);	
				
		lblElite = new JLabel("Elite");
		lblElite.setFont(Fonts.FONT_LABEL);
		lblElite.setBounds(60, 270, 55, 25);
			
		rdbtnNo = new JRadioButton("No");
		rdbtnNo.setFont(Fonts.FONT_TEXT);
		rdbtnNo.setBounds(140, 270, 40, 25);
		rdbtnNo.setSelected(true);
		
		rdbtnSi = new JRadioButton("Si");
		rdbtnSi.setFont(Fonts.FONT_TEXT);
		rdbtnSi.setBounds(190, 270, 40, 25);
		
		btgElite = new ButtonGroup();
		btgElite.add(rdbtnSi);
		btgElite.add(rdbtnNo);
		
		btnInscribir = new JButton("Inscribir");
		btnInscribir.setFont(Fonts.FONT_BOTON);
		btnInscribir.setIcon(new ImageIcon(Constantes.RUTA_ICONOS+"guardar.png"));
		btnInscribir.setBounds(140, 371, 120, 50);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setFont(Fonts.FONT_BOTON);
		btnBorrar.setIcon(new ImageIcon(Constantes.RUTA_ICONOS+"eraser.png"));
		btnBorrar.setBounds(280, 371, 120, 50);
		
		btnListar = new JButton("Listar");
		btnListar.setFont(Fonts.FONT_BOTON);
		btnListar.setIcon(new ImageIcon(Constantes.RUTA_ICONOS+"list_user.png"));
		btnListar.setBounds(420, 371, 120, 50);
		
		btnSalir = new JButton("Salir");
		btnSalir.setFont(Fonts.FONT_BOTON);
		btnSalir.setIcon(new ImageIcon(Constantes.RUTA_ICONOS+"delete.png"));
		btnSalir.setBounds(565, 371, 120, 50);
		
		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(Constantes.RUTA_ICONOS+"logo.png"));
		lblLogo.setBounds(440, 61, 200, 200);
		
		getTxfEvento();
			
		ctpInscripcion.add(lblCedula);
		ctpInscripcion.add(txfCedula);
		ctpInscripcion.add(lblNombres);
		ctpInscripcion.add(txfNombres);
		ctpInscripcion.add(lblApellidos);
		ctpInscripcion.add(txfApellidos);
		ctpInscripcion.add(lblCategoria);
		ctpInscripcion.add(txfCategoria);
		ctpInscripcion.add(lblNumero);
		ctpInscripcion.add(txfNumero);
		ctpInscripcion.add(lblEvento);
		ctpInscripcion.add(txfEvento);
	//	ctpInscripcion.add(lblElite);
	//	ctpInscripcion.add(rdbtnNo);
	//	ctpInscripcion.add(rdbtnSi);
		ctpInscripcion.add(btnInscribir);
		ctpInscripcion.add(btnBorrar);
		ctpInscripcion.add(btnListar);
		ctpInscripcion.add(btnSalir);
		ctpInscripcion.add(lblLogo);
		
		
		txfCedula.addKeyListener(new KeyAdapter() {
		    public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					buscarDatos();
				}
		    }
		});	
		
		btnInscribir.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	validarCampos();
		    }
		});
		
		btnInscribir.addKeyListener(new KeyAdapter() {
		    public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					validarCampos();
				}
		    }
		});	
	
		btnBorrar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	borrarContenido();
		    }
		});
		
		btnBorrar.addKeyListener(new KeyAdapter() {
		    public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					borrarContenido();
				}
		    }
		});
		
		btnListar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	new ListarAtletas("participantes", "eventoId", CronoDAO.getIdEvento());
		    }
		});
		
		btnListar.addKeyListener(new KeyAdapter() {
		    public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					new ListarAtletas("participantes", "eventoId", CronoDAO.getIdEvento());
				}
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
		
	}// fin del constructor
		
	
	private void getTxfEvento() {
		txfEvento.setText("");
		String result = CronoDAO.getEvento();
		
		if (result.length() > 1)
			txfEvento.setText(result);
		
	}// fin metodo getTxfEvento
	
	
	private void buscarDatos() {		
		txfNombres.setText("");
    	txfApellidos.setText("");
    	txfCategoria.setText("");
    	
		if (validarCedula()) {
			AtletaDTO atleta = CronoDAO.getDatosAtleta(cedula);
			logger.info(atleta);
			if( atleta != null){
				txfNombres.setText(atleta.getNombres());
				txfApellidos.setText(atleta.getApellidos());
				txfCedula.setEnabled(false);
			    int year = Integer.parseInt(atleta.getFechaNacimiento());
			    buscarCategoria(year);
			}
		}//fin if 	
	}// fin buscarDatos
		
	
	private void buscarCategoria(int year) {		
		date = new Date();
		cal = Calendar.getInstance();
		cal.setTime(date);
		fecha = cal.get(Calendar.YEAR);
		temp = fecha - year;
		idCat = 0;
		System.out.println(temp);
		
		List<String> result = CronoDAO.getCategorias(); 
		
		for(String categoria : result){	
			String ini = categoria.substring(0, 2);
			String fin = categoria.substring(categoria.length()-2, categoria.length());
			
			if(temp >= new Integer (ini) && temp <= new Integer (fin)){
				
				idCat = CronoDAO.getIdCategoria(categoria);
				logger.info(idCat);
				if(idCat != 0)
					txfCategoria.setText(categoria);	
			}
		}//fin for
		
	}// buscarCategoria


	private boolean validarCedula(){
    	try{
    		cedula=Integer.parseInt(txfCedula.getText().trim());
    		if(cedula < 1) {
    			JOptionPane.showMessageDialog(null,Constantes.CEDULA, "ERROR", JOptionPane.ERROR_MESSAGE);
        		return false;
    		}
    	}catch (NumberFormatException nfe){
    		JOptionPane.showMessageDialog(null, Constantes.CEDULA, "ERROR", JOptionPane.ERROR_MESSAGE);
    		logger.debug(nfe);
    		return false;
    	}catch (Exception ex) {
    		JOptionPane.showMessageDialog(null, Constantes.CEDULA, "ERROR", JOptionPane.ERROR_MESSAGE);
    		logger.debug(ex);
    		return false;
    	}
    	 	return true;
    }//fin Validar cedula


	private void validarCampos() {
		colorText();
    	msg = new StringBuilder();	
    	
    	if(txfCedula.getText().trim().length() < 6 || txfCedula.getText().trim().length() > 10) {
    		txfCedula.setBackground(Fonts.COLOR_ERROR);
    		msg.append(Constantes.E_CEDULA);
    	}
    	
    	if (!validarNumero()) {
    		txfNumero.setBackground(Fonts.COLOR_ERROR);
    		msg.append(Constantes.NUMERO_POSITIVO);
    	}
    		
    	if(txfEvento.getText().length() < 3)
    		msg.append(Constantes.E_EVENTO);
    	
    	if(txfCedula.isEnabled())
    		msg.append(Constantes.E_BUSCAR_DATOS);
    	
    	msg2 = msg.toString();
    	
    	if(!msg2.isEmpty()) {
    		JOptionPane.showMessageDialog(null, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
    	}else { 
    		logger.info(idEvento);
    		if (!CronoDAO.validarParticipantexEvento(txfCedula.getText().trim())) {		
				if (!CronoDAO.isNumeroExiste(new Integer(txfNumero.getText().trim()))) {
					enviarContenido();
				}else { 
					txfNumero.setBackground(Fonts.COLOR_ERROR);
					JOptionPane.showMessageDialog(null,Constantes.NUMERO_REGISTRADO, "ERROR",JOptionPane.ERROR_MESSAGE);
				}	
			}else{
				JOptionPane.showMessageDialog(null,Constantes.PARTICIPANTE_REGISTRADO, "ERROR",JOptionPane.ERROR_MESSAGE);
			}//fin if 
    	}
    	
	}//fin validar campos
	
	
	private boolean validarNumero(){
    	num=0;
    	try {
    		num=Integer.parseInt(txfNumero.getText().trim());
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
	
	
    private void enviarContenido() {   	
    	if(CronoDAO.registrarParticipante(txfCedula.getText().trim(), txfNumero.getText().trim(), idCat, (rdbtnSi.isSelected() ? 1 : 0))) {
    		JOptionPane.showMessageDialog(null, "Participante Registrado.");
    		borrarContenido();
    	}	
    }//fin enviar contenido
	
	
	private void colorText() {		
    	msg = null;
		msg2 = "";
		txfCedula.setBackground(Color.WHITE);
		txfNumero.setBackground(Color.WHITE);
    }//fin color text
	
	
	 private void borrarContenido() {
	    	txfNombres.setText("");
	    	txfApellidos.setText("");
	    	txfCedula.setEnabled(true);
	    	txfCedula.setText("");
	    	txfCategoria.setText("");
	    	txfNumero.setText("");
			rdbtnSi.setSelected(false);
			rdbtnNo.setSelected(true);
			colorText();
	 }//fin borrar contenido
	 
	 public void salir() {
	    	System.exit(0);
	 }
}
