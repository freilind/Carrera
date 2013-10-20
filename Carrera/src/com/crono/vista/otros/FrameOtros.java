package com.crono.vista.otros;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Panel;
import org.apache.log4j.Logger;
import com.crono.vista.FramePrincipal;

public class FrameOtros{

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
		 new FrameBD();
	} 

}
