package com.crono.vista;

import java.awt.Panel;
import java.awt.Toolkit;
import javax.swing.JFrame;

import org.apache.log4j.Logger;

import com.crono.util.Constantes;
import com.crono.util.Fonts;

public class FrameTabla extends JFrame{
	
	private static final Logger logger = Logger.getLogger(FrameTabla.class);
	private static final long serialVersionUID = 1L;
	private JFrame FrameTabla;
    
    public FrameTabla(Panel ctpListar) {
    	
    	FrameTabla = new JFrame();
		
		FrameTabla.setFont(Fonts.FONT_LISTA);
		FrameTabla.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		FrameTabla.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(Constantes.RUTA_ICONOS+"logop.png")));
		FrameTabla.setSize(810, 400);
		setLocationRelativeTo(null);
		FrameTabla.add(ctpListar);	
		FrameTabla.setVisible(true);
		
    }

}
