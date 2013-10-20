package com.crono.vista.evento;

import java.awt.Color;
import java.awt.Panel;
import java.awt.event.*;
import javax.swing.*;
import org.apache.log4j.Logger;
import com.crono.controlador.Control;
import com.crono.dao.CronoDAO;
import com.crono.util.*;

public class FrameEventoValida {
	
	private static final Logger logger = Logger.getLogger(FrameEventoValida.class);
	private Panel ctpValida;
	private JTextField txfNombre, txfEvento;
	private JLabel lblNombre, lblCrear, lblDeshabilitar, lblEvento, lblLogo, lblBck;
	private JButton btnCrear, btnBorrar, btnDesh, btnSalir;
	private JSeparator separator;
	private Lectura lec;
	
	public FrameEventoValida() {
		ctpValida = new Panel();	
		ctpValida.setLayout(null);
		FrameEvento.tbpEvento.addTab("Evento", null,ctpValida, null);
		
		lblBck=new JLabel();
		lblBck.setIcon(new ImageIcon(getClass().getResource(Constantes.RUTA_BCK+"bck.png")));
		lblBck.setBounds(0, 0, 700, 525);
		
		lblCrear = new JLabel("Crear Evento");
		lblCrear.setFont(Fonts.FONT_LABEL);
		lblCrear.setBounds(190, 20, 120, 30);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setFont(Fonts.FONT_LABEL);
		lblNombre.setBounds(60, 70, 55, 25);
			
		txfNombre = new JTextField();
		txfNombre.setFont(Fonts.FONT_TEXT);
		txfNombre.setBounds(140, 68, 250, 28);
		
		btnCrear = new JButton("Crear");
		btnCrear.setFont(Fonts.FONT_BOTON);
		btnCrear.setIcon(new ImageIcon(getClass().getResource(Constantes.RUTA_ICONOS+"guardar.png")));
		btnCrear.setBounds(140, 120, 120, 50);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setFont(Fonts.FONT_BOTON);
		btnBorrar.setIcon(new ImageIcon(getClass().getResource(Constantes.RUTA_ICONOS+"eraser.png")));
		btnBorrar.setBounds(270, 120, 120, 50);
		
		separator = new JSeparator();
		separator.setBounds(50, 185, 370, 7);
		separator.setBackground(Color.BLACK);
		
		lblDeshabilitar = new JLabel("Deshabilitar Evento");
		lblDeshabilitar.setFont(Fonts.FONT_LABEL);
		lblDeshabilitar.setBounds(190, 200, 160, 30);
		
		lblEvento = new JLabel("Evento");
		lblEvento.setHorizontalAlignment(SwingConstants.LEFT);
		lblEvento.setFont(Fonts.FONT_LABEL);
		lblEvento.setBounds(60, 240, 50, 25);
		
		txfEvento = new JTextField();
		txfEvento.setEnabled(false);
		txfEvento.setFont(Fonts.FONT_TEXT);
		txfEvento.setBounds(140, 238, 250, 28);
		
		btnDesh = new JButton("Deshabilitar");
		btnDesh.setFont(Fonts.FONT_BOTON);
		btnDesh.setBounds(140, 290, 120, 50);
		
		btnSalir = new JButton("Salir");
		btnSalir.setFont(Fonts.FONT_BOTON);
		btnSalir.setIcon(new ImageIcon(getClass().getResource(Constantes.RUTA_ICONOS+"delete.png")));
		btnSalir.setBounds(565, 371, 120, 50);
		
		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(getClass().getResource(Constantes.RUTA_ICONOS+"logo.png")));
		lblLogo.setBounds(440, 61, 200, 200);
		
		getTxfEvento();
			
		ctpValida.add(lblCrear);
		ctpValida.add(lblNombre);
		ctpValida.add(txfNombre);
		ctpValida.add(separator);
		ctpValida.add(lblDeshabilitar);
		ctpValida.add(lblEvento);
		ctpValida.add(txfEvento);	
		ctpValida.add(btnCrear);
		ctpValida.add(btnBorrar);
		ctpValida.add(btnDesh);
		ctpValida.add(btnSalir);
		ctpValida.add(lblLogo);
		ctpValida.add(lblBck);
		
		btnCrear.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	validarCampos();
		    }
		});
		
		btnCrear.addKeyListener(new KeyAdapter() {
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
		
		btnDesh.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	deshabilitarEvento();
		    }
		});
		
		btnDesh.addKeyListener(new KeyAdapter() {
		    public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					deshabilitarEvento();
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
		
	}//fin del constructor
	
	
	private void getTxfEvento() {
		txfEvento.setText("");
		String result = CronoDAO.getEvento();
		
		if (result.length() > 1){
			txfEvento.setText(result);
			FrameEventoInscripcion.txfEvento.setText(result);
		}else{
			FrameEventoInscripcion.txfEvento.setText("");
		}
		
	}// fin metodo getTxfEvento
	

	private void validarCampos() {
		if (txfNombre.getText().trim().length() > 2 && txfNombre.getText().trim().length() < 100) {
			if(txfEvento.getText().length() < 1){
				registrarEvento();
			}else{
				JOptionPane.showMessageDialog(null, Constantes.EVENTO_UNICO, "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(null, Constantes.CAMPO_OBLIGATORIO, "ERROR", JOptionPane.ERROR_MESSAGE);
		}		
	}//fin validar campos
	
	
	private void registrarEvento() {	
		if(CronoDAO.registrarEvento(txfNombre.getText())) {
    		JOptionPane.showMessageDialog(null, "Evento Registrado.");
		    borrarContenido();
		    getTxfEvento();
    	}	  			
	}//fin enviar contenido
	 

	private void borrarContenido() {
		txfNombre.setText("");	
	}
	

	private void deshabilitarEvento() {
		lec = new Lectura();
		 String codR="";
		 codR=lec.leerCodigo("Ingrese C\u00F3digo Autorizaci\u00F3n");
		 if(Constantes.CODIGO_AUTORIZACION.equals(codR)) {
			if(txfEvento.getText().length() > 2) {
				enviarDesh();
			}else {
				JOptionPane.showMessageDialog(null, Constantes.SELECCION_EVENTO,"ERROR", JOptionPane.ERROR_MESSAGE);
			}
		 }else {
			JOptionPane.showMessageDialog(null, Constantes.CODIGO_ERROR,"ERROR", JOptionPane.ERROR_MESSAGE);
		 }	
	}//fin deshabilitarEvento


	private void enviarDesh() {	
		if(CronoDAO.deshabilitarEvento(txfEvento.getText())) {
    		JOptionPane.showMessageDialog(null, "Evento Deshabilitado.");
    		getTxfEvento();
		    borrarContenido();
    	}	 	  	
	}//fin enviarDesh
	
	
	public void salir() {
		Control.cerrarApp();
	}
	
}
