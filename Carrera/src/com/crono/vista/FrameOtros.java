package com.crono.vista;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Panel;

import org.apache.log4j.Logger;

public class FrameOtros extends Frame{

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(FrameOtros.class);
	public static Panel ctpOtros;
	public static JTabbedPane tbpOtros;
	 
	public FrameOtros(){	 
		ctpOtros = new Panel();
		FramePrincipal.tbpPrincipal.addTab("Otros", null,ctpOtros, null);
		ctpOtros.setLayout(new BorderLayout(0, 0));
		tbpOtros = new JTabbedPane(JTabbedPane.TOP);
		ctpOtros.add(tbpOtros, BorderLayout.CENTER);
	
		setVisibilidad();
	}//fin constructor
	 
	private void setVisibilidad() {
		 new FrameDescalificar();
		 new FrameDiploma();
	} 

}
