package com.crono.vista;

import java.awt.event.*;
import javax.swing.*;
import org.apache.log4j.Logger;

import com.crono.resultados.*;
import com.crono.util.*;

public class FrameResultados {
	
	private static final Logger logger = Logger.getLogger(FrameResultados.class);
	private Panel ctpResultados;
	private JButton btnGeneral, btnMasculino, btnFemenino, btnCategorias, btnInscritos, btnSalir;
	private JLabel lblLogo;
	
	public FrameResultados() {
		
		ctpResultados = new Panel();
		FramePrincipal.tbpPrincipal.addTab("Resultados", null,ctpResultados, null);
		ctpResultados.setLayout(null);
		
		btnGeneral = new JButton("General");
		btnGeneral.setToolTipText("Resultados Generales");
		btnGeneral.setIcon(new ImageIcon(Constantes.RUTA_ICONOS+"medal.png"));
		btnGeneral.setFont(Fonts.FONT_BOTON);
		btnGeneral.setBounds(80, 50, 134, 50);
		
		btnMasculino = new JButton("Masculino");
		btnMasculino.setIcon(new ImageIcon(Constantes.RUTA_ICONOS+"hombre.png"));
		btnMasculino.setToolTipText("Resultados Masculino");
		btnMasculino.setFont(Fonts.FONT_BOTON);
		btnMasculino.setBounds(80, 170, 134, 50);
			
		btnFemenino = new JButton("Femenino");
		btnFemenino.setIcon(new ImageIcon(Constantes.RUTA_ICONOS+"mujer.png"));
		btnFemenino.setToolTipText("Resultados Femenino");
		btnFemenino.setFont(Fonts.FONT_BOTON);
		btnFemenino.setBounds(280, 170, 134, 50);
			
		btnCategorias = new JButton("Categorias");
		btnCategorias.setIcon(new ImageIcon(Constantes.RUTA_ICONOS+"gpie.png"));
		btnCategorias.setToolTipText("Resultados Categor\u00EDa");
		btnCategorias.setFont(Fonts.FONT_BOTON);
		btnCategorias.setBounds(280, 50, 134, 50);
		
		btnInscritos = new JButton("Inscritos");
		btnInscritos.setIcon(new ImageIcon(Constantes.RUTA_ICONOS+"list_user.png"));
		btnInscritos.setToolTipText("Listado de Inscritos");
		btnInscritos.setFont(Fonts.FONT_BOTON);
		btnInscritos.setBounds(80, 290, 134, 50);
		
		btnSalir = new JButton("Salir");
		btnSalir.setFont(Fonts.FONT_BOTON);
		btnSalir.setIcon(new ImageIcon(Constantes.RUTA_ICONOS+"delete.png"));
		btnSalir.setBounds(565, 400, 120, 50);
	
		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(Constantes.RUTA_ICONOS+"logo.png"));
		lblLogo.setBounds(440, 90, 200, 200);
		
		ctpResultados.add(btnGeneral);
		ctpResultados.add(btnMasculino);
		ctpResultados.add(btnFemenino);
		ctpResultados.add(btnCategorias);
		ctpResultados.add(btnInscritos);
		ctpResultados.add(btnSalir);
		ctpResultados.add(lblLogo);	
		
		btnGeneral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			   	new General();
			}
		});
	
		btnMasculino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    new Masculino();    
			}
		});
			
		btnFemenino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    new Femenino();
			}
		});
		
		btnCategorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    new Categorias();
			}
		});
		
		btnInscritos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Inscritos();
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
	
	public void salir() {
    	System.exit(0);
    }
}
