package com.crono.vista.evento;

import java.awt.*;
import javax.swing.*;
import org.apache.log4j.Logger;
import com.crono.vista.FramePrincipal;

public class FrameEvento {
	
	private static final Logger logger = Logger.getLogger(FrameEvento.class);
	public static Panel ctpEvento;
	public static JTabbedPane tbpEvento;
	 
	public FrameEvento(){	 
		ctpEvento = new Panel();
		FramePrincipal.tbpPrincipal.addTab("Eventos", null,ctpEvento, null);
		ctpEvento.setLayout(new BorderLayout(0, 0));
		tbpEvento = new JTabbedPane(JTabbedPane.TOP);
		ctpEvento.add(tbpEvento, BorderLayout.CENTER);
	
		setVisibilidad();
	}//fin constructor
	 
	private void setVisibilidad() {
		 new FrameEventoInscripcion();
		 new FrameEventoValida();
	}

}
