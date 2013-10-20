package com.crono.vista.otros;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

import org.apache.log4j.Logger;

import com.crono.controlador.Control;
import com.crono.dao.CronoDAO;
import com.crono.util.Constantes;
import com.crono.util.Fonts;
import com.crono.util.Lectura;
import com.crono.vista.evento.FrameEventoInscripcion;

public class FrameBD {
	
	private static final Logger logger = Logger.getLogger(FrameBD.class);
	private Panel ctpBD;
	private JButton btnBorrarTiempo, btnBorrarCategoria, btnCrearCategoria, btnSalir;
	private JLabel lblLogo, lblBck;
	private Lectura lec;
	
	public FrameBD(){
		ctpBD = new Panel();
		ctpBD.setLayout(null);
		FrameOtros.tbpOtros.addTab("Base Datos", null,ctpBD, null);
		
		lec = new Lectura();
		
		lblBck=new JLabel();
		lblBck.setIcon(new ImageIcon(getClass().getResource(Constantes.RUTA_BCK+"bck.png")));
		lblBck.setBounds(0, 0, 700, 525);
		
		btnBorrarTiempo = new JButton("Borrar Tiempos");
		btnBorrarTiempo.setFont(Fonts.FONT_BOTON);
		btnBorrarTiempo.setIcon(new ImageIcon(getClass().getResource(Constantes.RUTA_ICONOS+"eraser.png")));
		btnBorrarTiempo.setToolTipText("Borra Todos los Tiempos de la Base de Datos");
		btnBorrarTiempo.setBounds(60, 50, 160, 50);
		
		btnBorrarCategoria = new JButton("Borrar Categor\u00EDa");
		btnBorrarCategoria.setFont(Fonts.FONT_BOTON);
		btnBorrarCategoria.setIcon(new ImageIcon(getClass().getResource(Constantes.RUTA_ICONOS+"eraser.png")));
		btnBorrarCategoria.setToolTipText("Borra Todos las Categor\u00EDias de la Base de Datos");
		btnBorrarCategoria.setBounds(60, 120, 160, 50);
		
		btnCrearCategoria = new JButton("Crear Categor\u00EDa");
		btnCrearCategoria.setFont(Fonts.FONT_BOTON);
		btnCrearCategoria.setIcon(new ImageIcon(getClass().getResource(Constantes.RUTA_ICONOS+"eraser.png")));
		btnCrearCategoria.setToolTipText("Crea Una Categor\u00EDia en la Base de Datos");
		btnCrearCategoria.setBounds(240, 120, 160, 50);
		
		btnSalir = new JButton("Salir");
		btnSalir.setFont(Fonts.FONT_BOTON);
		btnSalir.setIcon(new ImageIcon(getClass().getResource(Constantes.RUTA_ICONOS+"delete.png")));
		btnSalir.setBounds(565, 371, 120, 50);
		
		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(getClass().getResource(Constantes.RUTA_ICONOS+"logo.png")));
		lblLogo.setBounds(440, 61, 200, 200);
		
		ctpBD.add(btnBorrarTiempo);
		ctpBD.add(btnBorrarCategoria);
		ctpBD.add(btnCrearCategoria);
		ctpBD.add(btnSalir);
		ctpBD.add(lblLogo);
		ctpBD.add(lblBck);
		
		
		btnBorrarTiempo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				borrarTiempos();
			}
		});
		
		btnBorrarTiempo.addKeyListener(new KeyAdapter() {
		    public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					borrarTiempos();
				}
		    }
		});
		
		btnBorrarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				borrarCategorias();
			}
		});
		
		btnBorrarCategoria.addKeyListener(new KeyAdapter() {
		    public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					borrarCategorias();
				}
		    }
		});
		
		btnCrearCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrarCategoria();
			}
		});
		
		btnCrearCategoria.addKeyListener(new KeyAdapter() {
		    public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					registrarCategoria();
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
	}//fin constructor
	
	
	private void borrarTiempos() {
		String codR="";
		codR=lec.leerCodigo("Ingrese C\u00F3digo Borrado");
		if(Constantes.CODIGO_AUTORIZACION.equals(codR)){
			CronoDAO.borrarTiempos();
		 }//fin if		
    }//fin borrar tiempos

	
	private void borrarCategorias() {
		String codR="";
		codR=lec.leerCodigo("Ingrese C\u00F3digo Borrado");
		if(Constantes.CODIGO_AUTORIZACION.equals(codR)){
			CronoDAO.borrarCategorias();
		 }//fin if	
    }//fin borrar categorias
	
	
	private void registrarCategoria() {
		String codR="";
		codR=lec.leerString("Ingrese Categor\u00EDa. "
						  + "\nEn Formato [00-99]");
		
		Pattern p = Pattern.compile("[0-9]{2}[\\-]{1}[0-9]{2}");	// Establecer el patron
    	Matcher m = p.matcher(codR); // Asociar el string al patron
		if(m.matches()){
			CronoDAO.registrarCategoria(codR);
		}else{
			JOptionPane.showMessageDialog(null, Constantes.CATEGORIA_INVALIDA, "ERROR", JOptionPane.ERROR_MESSAGE);
		}
    }//fin borrar categorias
	    
    public void salir() {
    	Control.cerrarApp();
    }
}
