package com.crono.util;

import java.awt.Color;
import java.awt.Font;

public class Fonts {

	public static Font FONT_LISTA, FONT_LABEL, FONT_TEXT, FONT_BOTON, FONT_RELOJ;
	public static Color COLOR_ERROR;
	
	public Fonts() {
		FONT_LISTA = new Font("Verdana", Font.PLAIN, 11);
		FONT_LABEL = new Font("Verdana", Font.BOLD, 12);
		FONT_TEXT = new Font("Verdana", Font.PLAIN, 12);
		FONT_BOTON = new Font("Verdana", Font.BOLD, 12);
		FONT_RELOJ = new Font("Verdana", Font.BOLD, 50);
		
		COLOR_ERROR = new Color(240, 128, 128);
	}
}
