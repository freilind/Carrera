package com.crono.vista;

import java.awt.Color;
import java.awt.Panel;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

import org.apache.log4j.Logger;

import com.crono.dao.CronoDAO;
import com.crono.util.*;

public class FrameCronometro implements Runnable, ActionListener{
	
	private static final Logger logger = Logger.getLogger(FrameCronometro.class);
	private Panel ctpCrono;
	private JButton btnIniciar, btnBorrar;
	private JLabel lblTiempo, lblNumeroAtleta, lblLogo, lblBck;
	private Thread hilo;
	private boolean cronometroActivo;
	private Lectura lec;
	private JTextField txfNumero;
	private int numero;
	private SimpleDateFormat tmpSDF;
	private long tiempoActual, tiempoInicio;
	
	public FrameCronometro() {
		ctpCrono = new Panel();	
		FramePrincipal.tbpPrincipal.addTab("Cron\u00F3metro", null,ctpCrono, null);
		ctpCrono.setLayout(null);
		
		lblBck=new JLabel();
		lblBck.setIcon(new ImageIcon(getClass().getResource(Constantes.RUTA_BCK+"bck.png")));
		lblBck.setBounds(0, 0, 700, 525);
		
		lblTiempo = new JLabel( "00:00:00:000" );
		lblTiempo.setFont(Fonts.FONT_RELOJ);
		lblTiempo.setHorizontalAlignment( JLabel.CENTER );
		lblTiempo.setForeground( Color.BLACK );
		lblTiempo.setBackground( Color.WHITE );
		lblTiempo.setBounds(100, 30, 400, 80);
		lblTiempo.setOpaque(true);
		
		lblNumeroAtleta  = new JLabel("N\u00FAmero Atleta");
		lblNumeroAtleta.setFont(Fonts.FONT_LABEL);
		lblNumeroAtleta.setBounds(150, 202, 100, 25);
		
		txfNumero = new JTextField();
		txfNumero.setFont(Fonts.FONT_TEXT);
		txfNumero.setBounds(150, 230, 132, 28);	
		txfNumero.setColumns(10);
		txfNumero.setEnabled(false);
		
		//Boton iniciar
	    btnIniciar = new JButton( "Iniciar" );
	    btnIniciar.setFont(Fonts.FONT_BOTON);
		btnIniciar.setIcon(new ImageIcon(getClass().getResource(Constantes.RUTA_ICONOS+"check.png")));
		btnIniciar.setBounds(150, 300, 120, 50);
		btnIniciar.addActionListener( this );
		
	 
		//Boton reiniciar inicia nuevamente desde 0
		btnBorrar = new JButton( "Borrar" );
		btnBorrar.setFont(Fonts.FONT_BOTON);
		btnBorrar.setIcon(new ImageIcon(getClass().getResource(Constantes.RUTA_ICONOS+"eraser.png")));
		btnBorrar.setBounds(350, 300, 120, 50);
		btnBorrar.addActionListener( this );
		
		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(getClass().getResource(Constantes.RUTA_ICONOS+"logo.png")));
		lblLogo.setBounds(440, 110, 200, 200);
		
		ctpCrono.add( lblTiempo );
		ctpCrono.add( btnIniciar );
		ctpCrono.add( btnBorrar );		
		ctpCrono.add(lblNumeroAtleta);
		ctpCrono.add(txfNumero);
		ctpCrono.add(lblLogo);
		ctpCrono.add(lblBck);
		
		txfNumero.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
				    registrarNumero();
				}
			}
		});
		
	}//fin constructor
	

	public void setLabelTiempoTranscurrido(JLabel lblTiempo) {
		this.lblTiempo = lblTiempo;
	}

	
	public void run(){
		
		if(CronoDAO.isEventoFecha()){
			tiempoInicio = System.currentTimeMillis();
			CronoDAO.setEventoFecha(tiempoInicio);
		}else{
			tiempoInicio = CronoDAO.getEventoFecha();
		}
		
		txfNumero.setEnabled(true);
		try{
			while( cronometroActivo ){		
				Thread.sleep(40);
				tiempoActual = System.currentTimeMillis();
				setTiempoCronometro(tiempoActual - tiempoInicio - 72_000_000);
			}//fin del while
			
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, Constantes.EXCEPTION, "ERROR",JOptionPane.ERROR_MESSAGE);
			logger.debug(ex);
		}//fin try
		 
		 lblTiempo.setText( "00:00:00:000" );
	}//fin run
	
	
	private void setTiempoCronometro(long aTiempo)  {
		tmpSDF = new SimpleDateFormat("HH:mm:ss:SSS");
		String tmpText = tmpSDF.format(new Date(aTiempo));
		lblTiempo.setText(tmpText);
	}//fin poner tiempo
	
	
	 //Esto es para el boton iniciar y reiniciar
	 public void actionPerformed( ActionEvent evt ){
		  Object o = evt.getSource();
		  if( o instanceof JButton ){
		   JButton btn = (JButton)o;
		   if( btn.getText().equals("Iniciar") ) iniciarCronometro();
		   if( btn.getText().equals("Borrar") )  pararCronometro();
		  }
	 }//fin action
	 
	  
	 //Iniciar el cronometro poniendo cronometroActivo 
	 //en verdadero para que entre en el while
	public void iniciarCronometro(){
		
		if (CronoDAO.getEvento().length() > 2){
			cronometroActivo = true;
			btnIniciar.setEnabled(false);
			hilo = new Thread( this );
			hilo.start();
		}else{
			JOptionPane.showMessageDialog(null,Constantes.NO_HAY_EVENTO, "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	 }//fin iniciarCronometro
	 
	  
	 //Esto es para parar el cronometro
	public void pararCronometro(){
		lec = new Lectura();
		String codR="";
		codR=lec.leerString("Ingrese C\u00F3digo Reinicio");
		if(codR.equals(Constantes.CODIGO_AUTORIZACION)) {
			cronometroActivo = false;
			txfNumero.setText("");
			txfNumero.setEnabled(false);
		 	btnIniciar.setEnabled(true);
		}else {
			JOptionPane.showMessageDialog(null, Constantes.CODIGO_ERROR,"ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}//fin parar cronometro


	public void registrarNumero() {
		boolean flag=false;
		numero=0;
		try {
			numero=Integer.parseInt(txfNumero.getText().trim());	 
			if(numero > 0) {
				flag=true;
			}else {
				JOptionPane.showMessageDialog(null, Constantes.NUMERO_POSITIVO,"ERROR", JOptionPane.ERROR_MESSAGE);
				flag=false;
				txfNumero.setText("");
			}
		}catch (Exception ex) {
			flag=false;
			logger.debug(ex);
			JOptionPane.showMessageDialog(null, Constantes.NUMERO_POSITIVO,"ERROR", JOptionPane.ERROR_MESSAGE);
			txfNumero.setText("");
		}//fin try
		 
		if (flag) {
			if (CronoDAO.isNumeroExiste(new Integer(txfNumero.getText().trim())) && !CronoDAO.isNumeroRegistrado(txfNumero.getText().trim())) {
				CronoDAO.registrarTiempo(txfNumero.getText().trim(), tiempoActual, lblTiempo.getText());
				txfNumero.setText("");
			}//fin if numeroExiste
		}//fin if flag
		 
	}//fin registrar numero
	 
	 
}//fin clase
