package com.crono.controlador.controladorBD.sqlCommand;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Logger {
	
    public void logln(String message) {

	SimpleDateFormat simpleD = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	Calendar calendario = Calendar.getInstance();

	String fecha = simpleD.format(calendario.getTime());
	System.out.println(fecha + ", " + message);

    }

    public void log(String message) {

	SimpleDateFormat simpleD = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	Calendar calendario = Calendar.getInstance();

	String fecha = simpleD.format(calendario.getTime());
	System.out.print("<b>" + fecha + "</b>, " + message);

    }

    public void loglnSinFecha(String message) {

	System.out.println(" " + message);

    }
}
