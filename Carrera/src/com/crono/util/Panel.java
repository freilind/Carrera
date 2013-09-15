package com.crono.util;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Panel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private ImageIcon imagen;
	private String ruta;
	
	public Panel() {
		ruta = Constantes.RUTA_ICONOS+"/../bck.png";		
	}
	
	public void paint (Graphics g) {
		Dimension dim = getSize();
		imagen = new ImageIcon(ruta);
		g.drawImage(imagen.getImage(), 0, 0, dim.width, dim.height, null);
		setOpaque(false);
		super.paint(g);
	}

}
