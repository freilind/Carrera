package com.crono.vista;

import java.awt.Color;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import org.apache.log4j.Logger;

import com.crono.dao.CronoDAO;
import com.crono.util.*;

public class FrameEventoValida {
	
	private static final Logger logger = Logger.getLogger(FrameEventoValida.class);
	private Panel ctpValida;
	private JTextField txfNombre;
	private JLabel lblNombre, lblCrear, lblDeshabilitar, lblEvento, lblLogo;
	private JButton btnCrear, btnBorrar, btnDesh, btnSalir;
	private JSeparator separator;
	private JComboBox<String> cbxEvento;
	private Lectura lec;
	
	public FrameEventoValida() {
		ctpValida = new Panel();	
		ctpValida.setLayout(null);
		FrameEvento.tbpEvento.addTab("V\u00E1lida", null,ctpValida, null);
		
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
		btnCrear.setIcon(new ImageIcon(Constantes.RUTA_ICONOS+"guardar.png"));
		btnCrear.setBounds(140, 120, 120, 50);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setFont(Fonts.FONT_BOTON);
		btnBorrar.setIcon(new ImageIcon(Constantes.RUTA_ICONOS+"eraser.png"));
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
		
		cbxEvento = new JComboBox<String>();
		cbxEvento.setFont(Fonts.FONT_TEXT);
		cbxEvento.setBounds(140, 238, 250, 28);
		
		btnDesh = new JButton("Deshabilitar");
		btnDesh.setFont(Fonts.FONT_BOTON);
		btnDesh.setBounds(140, 290, 120, 50);
		
		btnSalir = new JButton("Salir");
		btnSalir.setFont(Fonts.FONT_BOTON);
		btnSalir.setIcon(new ImageIcon(Constantes.RUTA_ICONOS+"delete.png"));
		btnSalir.setBounds(565, 371, 120, 50);
		
		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(Constantes.RUTA_ICONOS+"logo.png"));
		lblLogo.setBounds(440, 61, 200, 200);
		
		setCbxEvento();
			
		ctpValida.add(lblCrear);
		ctpValida.add(lblNombre);
		ctpValida.add(txfNombre);
		ctpValida.add(separator);
		ctpValida.add(lblDeshabilitar);
		ctpValida.add(lblEvento);
		ctpValida.add(cbxEvento);	
		ctpValida.add(btnCrear);
		ctpValida.add(btnBorrar);
		ctpValida.add(btnDesh);
		ctpValida.add(btnSalir);
		ctpValida.add(lblLogo);
		
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
	
	
	private void setCbxEvento() {
		cbxEvento.removeAllItems();
		List<String> eventos = CronoDAO.getEventos();
		logger.info(eventos);
		for(String e: eventos)
			cbxEvento.addItem(e);		
			
	}// fin metodo setCbxEvento
	

	private void validarCampos() {
		if (txfNombre.getText().trim().length() > 2 && txfNombre.getText().trim().length() < 100) {
			registrarEvento();
		}else {
			JOptionPane.showMessageDialog(null, Constantes.CAMPO_OBLIGATORIO, "ERROR", JOptionPane.ERROR_MESSAGE);
		}		
	}//fin validar campos
	
	
	private void registrarEvento() {
		
		if(CronoDAO.registrarEvento(txfNombre.getText())) {
    		JOptionPane.showMessageDialog(null, "Evento Registrado.");
    		setCbxEvento();
		    borrarContenido();
    	}
		  			
	}//fin enviar contenido
	 

	private void borrarContenido() {
		txfNombre.setText("");	
		cbxEvento.setSelectedIndex(0);
	}
	

	private void deshabilitarEvento() {
		lec = new Lectura();
		 String codR="";
		 codR=lec.leerString("Ingrese C\u00F3digo Autorizaci\u00F3n");
		 if(Constantes.CODIGO_AUTORIZACION.equals(codR)) {
			if(cbxEvento.getSelectedIndex() != 0) {
				enviarDesh();
			}else {
				JOptionPane.showMessageDialog(null, Constantes.SELECCION_EVENTO,"ERROR", JOptionPane.ERROR_MESSAGE);
			}
		 }else {
			JOptionPane.showMessageDialog(null, Constantes.CODIGO_ERROR,"ERROR", JOptionPane.ERROR_MESSAGE);
		 }	
	}//fin deshabilitarEvento


	private void enviarDesh() {
		
		if(CronoDAO.deshabilitarEvento((String) cbxEvento.getSelectedItem())) {
    		JOptionPane.showMessageDialog(null, "Evento Deshabilitado.");
    		setCbxEvento();
		    borrarContenido();
    	}	 	  	
	}//fin enviarDesh
	
	
	public void salir() {
    	System.exit(0);
	}
	
}
