package com.crono.vista;

import java.awt.Color;
import java.awt.Panel;
import java.awt.event.*;
import java.util.Calendar;
import java.util.List;
import javax.swing.*;
import org.apache.log4j.Logger;
import com.crono.modelo.dto.AtletaDTO;
import com.crono.modelo.administrar.usuario.*;
import com.crono.controlador.Control;
import com.crono.dao.CronoDAO;
import com.crono.util.*;

public class FrameRegistro extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(FrameRegistro.class);
    private JComboBox<String> cbxSexo, cbxYearB;
    private JLabel lblNombres, lblApellidos, lblCedula, lblSexo,lblYearB, lblTlf, lblEmail, lblLogo, lblDiscapacitado, lblBck;
    private JTextField txfNombres, txfApellidos, txfCedula, txfTlf, txfEmail;
    private JButton btnAceptar, btnBorrar, btnListar, btnSalir;
    private int cedula;
    private String msg2;
    private StringBuilder msg;
    private Panel ctpRegistro;
    private JRadioButton rdbtnNo, rdbtnSi;
    private ButtonGroup btgDisc;
    private AtletaDTO atleta;
    
    public FrameRegistro() {

		ctpRegistro = new Panel();	
		FramePrincipal.tbpPrincipal.addTab("Registro Atletas", null,ctpRegistro, null);
		ctpRegistro.setLayout(null);
		
		lblBck=new JLabel();
		lblBck.setIcon(new ImageIcon(getClass().getResource(Constantes.RUTA_BCK+"bck.png")));
		lblBck.setBounds(0, 0, 700, 525);
		
		lblNombres = new JLabel("Nombres");
		lblNombres.setFont(Fonts.FONT_LABEL);
		lblNombres.setBounds(60, 30, 70, 25);
		
		txfNombres = new JTextField(10);
		txfNombres.setBackground(new Color(255, 255, 255));
		txfNombres.setFont(Fonts.FONT_TEXT);
		txfNombres.setBounds(140, 28, 200, 28);
	
		lblApellidos = new JLabel("Apellidos");
		lblApellidos.setFont(Fonts.FONT_LABEL);
		lblApellidos.setBounds(60, 70, 70, 25);
		
		txfApellidos = new JTextField(10);
		txfApellidos.setBackground(new Color(255, 255, 255));
		txfApellidos.setFont(Fonts.FONT_TEXT);
		txfApellidos.setBounds(140, 68, 200, 29);
	
		lblCedula = new JLabel("Cedula");
		lblCedula.setFont(Fonts.FONT_LABEL);
		lblCedula.setBounds(60, 110, 70, 25);
		
		txfCedula = new JTextField(10);
		txfCedula.setFont(Fonts.FONT_TEXT);
		txfCedula.setBounds(140, 108, 120, 28);
		
		lblSexo = new JLabel("Sexo");
		lblSexo.setFont(Fonts.FONT_LABEL);
		lblSexo.setBounds(60, 150, 65, 25);
		
		cbxSexo = new JComboBox<String>();
		cbxSexo.setFont(Fonts.FONT_TEXT);
		cbxSexo.setBounds(140, 148, 120, 28);
		
		lblYearB = new JLabel("A\u00F1o Nac.");
		lblYearB.setFont(Fonts.FONT_LABEL);
		lblYearB.setBounds(60, 190, 70, 25);
		
		cbxYearB = new JComboBox<String>();
		cbxYearB.setFont(Fonts.FONT_TEXT);
		cbxYearB.setBounds(140, 188, 120, 28);
		
		lblTlf = new JLabel("Tel\u00E9fono");
		lblTlf.setFont(Fonts.FONT_LABEL);
		lblTlf.setBounds(60, 230, 70, 25);
	
		txfTlf = new JTextField(20);
		txfTlf.setFont(Fonts.FONT_TEXT);
		txfTlf.setBounds(140, 228, 120, 28);
		
		lblEmail = new JLabel("Email");
		lblEmail.setFont(Fonts.FONT_LABEL);
		lblEmail.setBounds(60, 270, 60, 25);
		
		txfEmail = new JTextField(20);
		txfEmail.setFont(Fonts.FONT_TEXT);
		txfEmail.setBounds(140, 268, 200, 28);	
		
		lblDiscapacitado = new JLabel("Discapacidad");
		lblDiscapacitado.setFont(Fonts.FONT_LABEL);
		lblDiscapacitado.setBounds(60, 310, 90, 25);
		
		rdbtnNo = new JRadioButton("No");
		rdbtnNo.setSelected(true);
		rdbtnNo.setFont(Fonts.FONT_TEXT);
		rdbtnNo.setBounds(180, 310, 50, 25);
			
		rdbtnSi = new JRadioButton("Si");
		rdbtnSi.setFont(Fonts.FONT_TEXT);
		rdbtnSi.setBounds(245, 310, 50, 25);
		
		btgDisc = new ButtonGroup();
		btgDisc.add(rdbtnNo);
		btgDisc.add(rdbtnSi);
	
		btnAceptar = new JButton("Guardar");
		btnAceptar.setFont(Fonts.FONT_BOTON);
		btnAceptar.setIcon(new ImageIcon(getClass().getResource(Constantes.RUTA_ICONOS+"guardar.png")));
		btnAceptar.setBounds(100, 355, 120, 50);
	
		btnBorrar = new JButton("Borrar");
		btnBorrar.setFont(Fonts.FONT_BOTON);
		btnBorrar.setIcon(new ImageIcon(getClass().getResource(Constantes.RUTA_ICONOS+"eraser.png")));
		btnBorrar.setBounds(250, 355, 120, 50);
		
		btnListar = new JButton("Listar #");
		btnListar.setFont(Fonts.FONT_BOTON);
		btnListar.setIcon(new ImageIcon(getClass().getResource(Constantes.RUTA_ICONOS+"list_user.png")));
		btnListar.setBounds(390, 355, 120, 50);
		
		btnSalir = new JButton("Salir");
		btnSalir.setFont(Fonts.FONT_BOTON);
		btnSalir.setIcon(new ImageIcon(getClass().getResource(Constantes.RUTA_ICONOS+"delete.png")));
		btnSalir.setBounds(565, 400, 120, 50);
	
		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(getClass().getResource(Constantes.RUTA_ICONOS+"logo.png")));
		lblLogo.setBounds(440, 90, 200, 200);
		
		setCbxSexo(); 	// añade valores al combobox sexo
		setCbxYearB(); 	// añade valores al combobox año nacimiento
	
		
		ctpRegistro.add(lblNombres);
		ctpRegistro.add(txfNombres);
		ctpRegistro.add(lblApellidos);
		ctpRegistro.add(txfApellidos);
		ctpRegistro.add(lblCedula);
		ctpRegistro.add(txfCedula);
		ctpRegistro.add(lblSexo);
		ctpRegistro.add(cbxSexo);
		ctpRegistro.add(lblYearB);
		ctpRegistro.add(cbxYearB);
		ctpRegistro.add(lblTlf);
		ctpRegistro.add(txfTlf);
		ctpRegistro.add(txfEmail);
		ctpRegistro.add(lblEmail);
		ctpRegistro.add(lblDiscapacitado);
		ctpRegistro.add(rdbtnNo);
		ctpRegistro.add(rdbtnSi);
		ctpRegistro.add(btnAceptar);
		ctpRegistro.add(btnBorrar);
		ctpRegistro.add(btnListar);
		ctpRegistro.add(btnSalir);
		ctpRegistro.add(lblLogo);
		ctpRegistro.add(lblBck);
	
		btnAceptar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	validarCampos();
		    }
		});
		
		btnAceptar.addKeyListener(new KeyAdapter() {
		    public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					validarCampos();
				}
		    }
		});	
	
		btnBorrar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	borrarContenido();
		    }
		});
		
		btnBorrar.addKeyListener(new KeyAdapter() {
		    public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					borrarContenido();
				}
		    }
		});
		
		btnListar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		    	new ListarAtletas();
		    }
		});
		
		btnListar.addKeyListener(new KeyAdapter() {
		    public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					new ListarAtletas();
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

    }// fin del constructor
    
     // ***********************************************************
    
    /**
     * a�ade valores al combobox sexo
     * 
     */
    private void setCbxSexo() {
    	List<String> result = CronoDAO.getSexos();
    	logger.info(result);
		for(String s: result)
			cbxSexo.addItem(s);
     }// fin setCbxSexo
   
    
    /**
     * a\u00F1ade valores al combobox a\u00F1o nacimiento
     * 
     */
    private void setCbxYearB() {	
		Calendar cal = Calendar.getInstance();
		cbxYearB.addItem("- ");
		int y = cal.get(Calendar.YEAR)-5; 
		
	    while (y > 1940) 
	    	cbxYearB.addItem(((Integer)y--).toString()); 	    
		    
     }// fin setCbxSexo
   
    
    
    private void validarCampos(){
    	colorText();
    	msg = new StringBuilder();
    	
    	if (txfNombres.getText().trim().length() < 2 || txfNombres.getText().trim().length() > 40) {
    		txfNombres.setBackground(Fonts.COLOR_ERROR);
    		msg.append(Constantes.E_NOMBRE);
    	}
    		
    	if (txfApellidos.getText().trim().length() < 2 || txfApellidos.getText().trim().length() > 40) {
    		txfApellidos.setBackground(Fonts.COLOR_ERROR);
    		msg.append(Constantes.E_APELLIDO);
    	}	
    	
    	if(txfCedula.getText().trim().length() < 6 || txfCedula.getText().trim().length() > 10) {
    		txfCedula.setBackground(Fonts.COLOR_ERROR);
    		msg.append(Constantes.E_CEDULA);
    	}
    	
    	if(txfTlf.getText().trim().length() < 7 || txfTlf.getText().trim().length() > 15) {
    		txfTlf.setBackground(Fonts.COLOR_ERROR);
    		msg.append(Constantes.E_TELEFONO);
    	}	
    	
    	if(txfEmail.getText().length() < 9 || txfEmail.getText().length() > 50) {
    		txfEmail.setBackground(Fonts.COLOR_ERROR);
    		msg.append(Constantes.E_EMAIL);
    	}
    	
    	if(cbxSexo.getSelectedIndex() == 0)
    		msg.append(Constantes.E_SEXO);
    		
    	if(cbxYearB.getSelectedIndex() == 0)
    		msg.append(Constantes.E_YEARB);
    	
    	msg2 = msg.toString();
    	if(!msg2.isEmpty()) {
    		JOptionPane.showMessageDialog(null, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
    	}else {
    		
    		if (validarCedula()) {
				if (CronoDAO.atletaExiste(txfCedula.getText().trim())) {
					txfCedula.setBackground(Fonts.COLOR_ERROR);
					JOptionPane.showMessageDialog(null,Constantes.CEDULA_REGISTRADA, "ERROR",JOptionPane.ERROR_MESSAGE);
				}else { 
					enviarContenido();
				}	
			}//fin if 
    	}
    }//fin validar campos
    
    
    private boolean validarCedula(){
    	try{
    		cedula=Integer.parseInt(txfCedula.getText().trim());
    		if(cedula < 1) {
    			JOptionPane.showMessageDialog(null,Constantes.CEDULA, "ERROR", JOptionPane.ERROR_MESSAGE);
        		return false;
    		}
    	}catch (NumberFormatException nfe){
    		JOptionPane.showMessageDialog(null, Constantes.CEDULA, "ERROR", JOptionPane.ERROR_MESSAGE);
    		return false;
    	}catch (Exception ex) {
    		JOptionPane.showMessageDialog(null, Constantes.CEDULA, "ERROR", JOptionPane.ERROR_MESSAGE);
    		logger.debug(ex);
    		return false;
    	}
    	 	return true;
    }//fin Validar cedula
    
    

    private void enviarContenido() {
    	
    	atleta = new AtletaDTO();
    	atleta.setNombres(txfNombres.getText().trim().toUpperCase());
    	atleta.setApellidos(txfApellidos.getText().trim().toUpperCase());
    	atleta.setCedula(txfCedula.getText().trim());
    	atleta.setFechaNacimiento(cbxYearB.getSelectedItem().toString());
    	atleta.setSexo(cbxSexo.getSelectedIndex());
    	atleta.setTelefono(txfTlf.getText().trim());
    	atleta.setEmail(txfEmail.getText().trim());
    	atleta.setDiscapacitado(rdbtnSi.isSelected() ? 1 : 0);
    	
    	logger.info(atleta);
    	if(CronoDAO.registrarAtleta(atleta)) {
    		JOptionPane.showMessageDialog(null, "Atleta Registrado.");
    		borrarContenido();
    	}
    	
    }//fin enviar contenido
    

    private void borrarContenido() {
    	txfNombres.setText("");
    	txfApellidos.setText("");
    	txfCedula.setText("");
    	cbxYearB.setSelectedIndex(0);
		cbxSexo.setSelectedIndex(0);
		txfTlf.setText("");
		txfEmail.setText("");
		rdbtnNo.setSelected(true);
		rdbtnSi.setSelected(false);
		colorText();
		
    }//fin borrar contenido
    
    
    private void colorText() {		
    	msg = null;
		msg2 = "";
		txfNombres.setBackground(Color.WHITE);
		txfApellidos.setBackground(Color.WHITE);
		txfCedula.setBackground(Color.WHITE);
		txfTlf.setBackground(Color.WHITE);
		txfEmail.setBackground(Color.WHITE);
    }
    
    public void salir() {
    	Control.cerrarApp();
    }
}
