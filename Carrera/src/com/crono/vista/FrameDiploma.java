package com.crono.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.log4j.Logger;

import com.crono.controlador.controladorBD.bd.ControladorBD;
import com.crono.modelo.dto.AtletaDTO;
import com.crono.resultados.Diploma;
import com.crono.util.Constantes;
import com.crono.util.Fonts;
import com.crono.util.Panel;

public class FrameDiploma extends Thread{
	
	private static final Logger logger = Logger.getLogger(FrameDiploma.class);
	private JButton btnSalir, btnDiploma, btnEmail;
	private JLabel lblLogo;
	private Panel ctpDiploma;
	
	public FrameDiploma(){
		ctpDiploma = new Panel();
		ctpDiploma.setLayout(null);
		FrameOtros.tbpOtros.addTab("Diploma", null,ctpDiploma, null);
		
		
		btnDiploma = new JButton("Diplomas");
		btnDiploma.setFont(Fonts.FONT_BOTON);
		btnDiploma.setIcon(new ImageIcon(Constantes.RUTA_ICONOS+"diploma.png"));
		btnDiploma.setToolTipText("Generar los Diplomas");
		btnDiploma.setBounds(80, 50, 145, 50);
		
		btnEmail = new JButton("Enviar Email");
		btnEmail.setFont(Fonts.FONT_BOTON);
		btnEmail.setIcon(new ImageIcon(Constantes.RUTA_ICONOS+"mail.png"));
		btnEmail.setToolTipText("Enviar email masivo con los resultados");
		btnEmail.setBounds(80, 150, 145, 50);	
		
		btnSalir = new JButton("Salir");
		btnSalir.setFont(Fonts.FONT_BOTON);
		btnSalir.setIcon(new ImageIcon(Constantes.RUTA_ICONOS+"delete.png"));
		btnSalir.setBounds(565, 371, 120, 50);
		
		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(Constantes.RUTA_ICONOS+"logo.png"));
		lblLogo.setBounds(440, 61, 200, 200);
		
		ctpDiploma.add(btnDiploma);
		ctpDiploma.add(btnEmail);
		ctpDiploma.add(btnSalir);
		ctpDiploma.add(lblLogo);
		
		
		btnDiploma.addKeyListener(new KeyAdapter() {
		    public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					generarPdf();
					btnDiploma.setEnabled(false);
				}
		    }
		});
		
		
		btnDiploma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				generarPdf();
				btnDiploma.setEnabled(false);
			}
		});
		
		
		btnEmail.addKeyListener(new KeyAdapter() {
		    public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					start();
					btnEmail.setEnabled(false);
				}
		    }
		});
		
		
		btnEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				start();
				btnEmail.setEnabled(false);
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
	
	
    public void run(){
    	enviarEmail();
    }
    
    
    public void generarPdf(){
    	Thread hilo2 = new Thread() {
		public void run() { 
			
			List<AtletaDTO> listAtletas = buscarAtletas();
			
			for(Iterator<AtletaDTO> iterator = listAtletas.iterator(); iterator.hasNext();){
    			AtletaDTO a = iterator.next();
    			new Diploma(a);
    		}
			btnDiploma.setEnabled(true);		
		}
		
		};
		hilo2.start();
    }//generarPdf
    
    
  private void enviarEmail(){
    	
    	List<String> listEmail = getEmail();
    	List<String> listEmail2 = validarEmail(listEmail);
    	List<String> listEnviados = new ArrayList<String>();
    	
    	//JavaMail email = new JavaMail();
    	try{
    		for(Iterator<String> iterator = listEmail2.iterator(); iterator.hasNext();){
    			String mail = iterator.next();
    			String cedula = buscarCedula(mail);
    			if (!cedula.isEmpty()){
    				//email.send(mail,"Esto es una prueba masiva","Este correo fue enviado usando JavaMail e hilos", cedula+".pdf");
        			listEnviados.add(mail);
    			}
    		}
    		
    		if(!listEnviados.isEmpty()){
    			JTextArea textArea = new JTextArea(10, 25);
    			String aux = "";
    			for(Iterator<String> iterator = listEnviados.iterator(); iterator.hasNext();){
        			String mail = iterator.next();
        			aux+=mail+"\n";
        		}
    		    
    			textArea.setText(aux);
    		    textArea.setEditable(false);
    		    JScrollPane scrollPane = new JScrollPane(textArea);
    			
    			
    			JOptionPane.showMessageDialog(null, scrollPane, "Emails Enviados", JOptionPane.INFORMATION_MESSAGE);
    		}
    	}catch(Exception ex){
    		JOptionPane.showMessageDialog(null, "Ha ocurrido un Error Enviando los Email.","ERROR", JOptionPane.ERROR_MESSAGE);
    		logger.debug(ex);
    	}finally{
    		btnEmail.setEnabled(true);
    	}

    }//fin enviarEmail
    
    
    private List<String> getEmail(){
    	String sql = "SELECT email FROM atleta";
    	List<String> result = new ArrayList<String>();
    	
    	try {
    	    ControladorBD.addSql(sql);
    	    ControladorBD.execute();

    	    while (ControladorBD.finRecordSet()) {
		    	result.add(ControladorBD.getString("email"));
	 	    }
    	} catch (Exception ex) {
    	    JOptionPane.showMessageDialog(null, "Ha ocurrido un error inesperado.","ERROR", JOptionPane.ERROR_MESSAGE);
    	    logger.debug(ex);
    	}
    	return result;
    }
    
    
    private List<String> validarEmail(List<String> listEmail){
    	
    	List<String> result = new ArrayList<String>();
    	
        Pattern p = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");	// Establecer el patron
        
        for(Iterator<String> iterator = listEmail.iterator(); iterator.hasNext();){
        	String mail = iterator.next();
        	Matcher m = p.matcher(mail); // Asociar el string al patron
        	
        	if(m.matches()){// Comprobar si encaja
        		result.add(mail);
        	}
        }
        return result;    	
    }//fin validarEmail
    
    
    private String buscarCedula(String mail){
    	
    	String sql = "SELECT cedula FROM atleta WHERE email = '" + mail+ "'";
    	String result = "";
    	
    	try {
    	    ControladorBD.addSql(sql);
    	    ControladorBD.execute();

    	    while (ControladorBD.finRecordSet()) {
		    	result = (ControladorBD.getString("cedula"));
	 	    }
    	} catch (Exception ex) {
    	    JOptionPane.showMessageDialog(null, "Ha ocurrido un error inesperado.","ERROR", JOptionPane.ERROR_MESSAGE);
    	    logger.debug(ex);
    	}
    	return result;
    	
    }//fin buscarCedula
    
    
    private List<AtletaDTO> buscarAtletas(){
       	
    	List<AtletaDTO> result = new ArrayList<AtletaDTO>();
    	
	    String sql = "SELECT nombre, apellido, cedula, (SELECT tiempo FROM tiempo WHERE num =  numero)  FROM atleta WHERE id < 1000 ";
	    
	    try {
    		ControladorBD.addSql(sql);
    		ControladorBD.execute();
    		 while(ControladorBD.finRecordSet()) {
    			 	AtletaDTO a = new AtletaDTO();
	    		    a.setNombres(ControladorBD.getString("nombre"));
	    		    a.setApellidos(ControladorBD.getString("apellido"));
	    		   // a.setCedula(ControladorBD.getInt("cedula"));
	    		  //  a.setTiempo(ControladorBD.getString("(SELECT tiempo FROM tiempo WHERE num =  numero)"));
	    		    result.add(a);
    		}//fin while
	    } catch (Exception ex) {
    		JOptionPane.showMessageDialog(null,"Ha Ocurrido un Error Inesperado.", "ERROR",JOptionPane.ERROR_MESSAGE);
    		logger.debug(ex);
	    }
	    return result;
    }//fin buscarAtletas
	
    
	public void salir() {
    	System.exit(0);
    }

}
