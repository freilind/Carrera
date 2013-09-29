package com.crono.vista;

import java.awt.event.*;
import javax.swing.*;
import org.apache.log4j.Logger;

import com.crono.dao.CronoDAO;
import com.crono.util.*;

public class FrameDescalificar extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(FrameDescalificar.class);
    private Panel ctpDesc;
    private JLabel lblCedula, lblCambiarNmero, lblDescalificar, lblLogo;
    private JTextField txfEliminar,txfCambiar, txfDescalificar;
    private int aux, num;
    private JButton btnBorrar, btnSalir;
    
    public FrameDescalificar(){
		ctpDesc = new Panel();
		ctpDesc.setLayout(null);
		FrameOtros.tbpOtros.addTab("Descalificar", null,ctpDesc, null);
		
		
		lblCedula = new JLabel("Eliminar Atleta:");
		lblCedula.setFont(Fonts.FONT_LABEL);
		lblCedula.setBounds(38, 57, 110, 20);
			
		txfEliminar = new JTextField("Cedula", 10);
		txfEliminar.setFont(Fonts.FONT_TEXT);
		txfEliminar.setBounds(175, 51, 122, 28);
			
		lblCambiarNmero = new JLabel("Cambiar N\u00FAmero:");
		lblCambiarNmero.setFont(Fonts.FONT_LABEL);
		lblCambiarNmero.setBounds(38, 120, 120, 20);
		
		
		txfCambiar = new JTextField("Cedula",10);
		txfCambiar.setFont(Fonts.FONT_TEXT);
		txfCambiar.setBounds(175, 114, 122, 28);
			
		lblDescalificar = new JLabel("Descalificar:");
		lblDescalificar.setFont(Fonts.FONT_LABEL);
		lblDescalificar.setBounds(38, 177, 100, 20);
			
		txfDescalificar = new JTextField();
		txfDescalificar.setText("Numero");
		txfDescalificar.setFont(Fonts.FONT_TEXT);
		txfDescalificar.setBounds(175, 171, 122, 28);	
		txfDescalificar.setColumns(10);
		
		btnBorrar = new JButton("Borrar Tiempos");
		btnBorrar.setFont(Fonts.FONT_BOTON);
		btnBorrar.setIcon(new ImageIcon(Constantes.RUTA_ICONOS+"eraser.png"));
		btnBorrar.setToolTipText("Borra todos los resultados de la Base de Datos");
		btnBorrar.setBounds(245, 344, 142, 50);
		
		btnSalir = new JButton("Salir");
		btnSalir.setFont(Fonts.FONT_BOTON);
		btnSalir.setIcon(new ImageIcon(Constantes.RUTA_ICONOS+"delete.png"));
		btnSalir.setBounds(565, 371, 120, 50);
		
		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(Constantes.RUTA_ICONOS+"logo.png"));
		lblLogo.setBounds(440, 61, 200, 200);
		
		ctpDesc.add(lblCedula);
		ctpDesc.add(txfEliminar);
		ctpDesc.add(lblCambiarNmero);
		ctpDesc.add(txfCambiar);
		ctpDesc.add(lblDescalificar);
		ctpDesc.add(txfDescalificar);
		ctpDesc.add(btnBorrar);
		ctpDesc.add(btnSalir);
		ctpDesc.add(lblLogo);
		
	
		txfEliminar.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent evt) {
				txfEliminar.setText("");
			}
		});	
		
		
		txfEliminar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
				    eliminarAtleta();
				}
			}
		});
		
		
		txfCambiar.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent evt) {
				txfCambiar.setText("");
			}
		});
		
		
		txfCambiar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
				    cambiarNumero();
				}
			}
		});
		
		txfDescalificar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
				    descalificarAtleta();
				}
			}
		});
		
		txfDescalificar.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent evt) {
				txfDescalificar.setText("");
			}
		});
		
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				borrarTiempos();
			}
		});
		
		btnBorrar.addKeyListener(new KeyAdapter() {
		    public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					borrarTiempos();
				}
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
		
		
    }//fin contructor
		
    
    private void eliminarAtleta() {
    	if (validarCedula(txfEliminar.getText().trim())) {
			if (CronoDAO.atletaExiste(txfEliminar.getText().trim())) {
				CronoDAO.borrarAtleta(txfEliminar.getText().trim());
			} else {
				JOptionPane.showMessageDialog(null, Constantes.CEDULA_NO_REGISTRADA, "ERROR",JOptionPane.ERROR_MESSAGE);		
			}//fin if
		}
    }//fin eliminar atleta
       
    
    private void cambiarNumero() {
    	if (validarCedula(txfCambiar.getText().trim())) {
			if (CronoDAO.atletaExiste(txfCambiar.getText().trim()) && CronoDAO.validarParticipantexEvento(txfCambiar.getText().trim())) {
				int numero=0;
		    	Lectura le = new Lectura(); 	
		    	numero = le.leerInt("Nuevo N\u00FAmero");
		    	if(numero > 0){
					if(CronoDAO.isNumeroExiste(numero)) {
						JOptionPane.showMessageDialog(null, Constantes.NUMERO_REGISTRADO, "ERROR",JOptionPane.ERROR_MESSAGE);
					}else {
						CronoDAO.setNuevoNumero(numero, txfCambiar.getText().trim());
					}
		    	}else{
		    		JOptionPane.showMessageDialog(null, Constantes.NUMERO_POSITIVO, "ERROR",JOptionPane.ERROR_MESSAGE);
		    	}
			} else {
					JOptionPane.showMessageDialog(null, Constantes.CEDULA_NO_REGISTRADA, "ERROR",JOptionPane.ERROR_MESSAGE);		
			}//fin if
		}
    }//fin eliminar atleta
    
    
    private void  descalificarAtleta() {
    	if(validarNumero()){
	    	if(CronoDAO.isNumeroExiste(new Integer (txfDescalificar.getText().trim()))) {
	    		CronoDAO.descalificarAtleta(txfDescalificar.getText().trim());
	    	}else {
	    		JOptionPane.showMessageDialog(null, Constantes.NUMERO_NO_EXISTE, "ERROR",JOptionPane.ERROR_MESSAGE);
	    	}	
    	}
    }//fin descalificar atleta 
    
    
    private boolean validarCedula(String cedula){
    	aux=0;
    	try{
    		aux=Integer.parseInt(cedula);
    		if(aux < 1) {
    			JOptionPane.showMessageDialog(null, Constantes.CEDULA, "ERROR", JOptionPane.ERROR_MESSAGE);
        		return false;
    		}  			
    	}catch (Exception ex) {
    		JOptionPane.showMessageDialog(null,Constantes.CEDULA, "ERROR", JOptionPane.ERROR_MESSAGE);
    		logger.info(ex);
    		return false;
    	}
    	
    	return true;
    }//fin Validar cedula
     

    private boolean validarNumero(){
    	num=0;
    	try {
    		num=Integer.parseInt(txfDescalificar.getText().trim());
    		if(num < 1) {
    			JOptionPane.showMessageDialog(null, Constantes.NUMERO_POSITIVO, "ERROR", JOptionPane.ERROR_MESSAGE);
        		return false;
    		}  
    	}catch (Exception ex) {
			JOptionPane.showMessageDialog(null, Constantes.NUMERO_POSITIVO, "ERROR",JOptionPane.ERROR_MESSAGE);
			logger.info(ex);
			return false;
		}
    	
    	return true;
    }//fin validar numero
  
    
    private void borrarTiempos() {
    	Lectura lec = new Lectura();
		String codR="";
		codR=lec.leerString("Ingrese C\u00F3digo Borrado");
		if(Constantes.CODIGO_AUTORIZACION.equals(codR)){
			CronoDAO.borrarTiempos();
		 }//fin if
    		
    }//fin borrar tiempos

    
    public void salir() {
    	System.exit(0);
    }

}
