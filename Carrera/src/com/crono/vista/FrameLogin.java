package com.crono.vista;

import javax.swing.*;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.UIManager.*;
import org.apache.log4j.Logger;

import com.crono.login.*;
import com.crono.util.*;


public class FrameLogin extends JFrame {

    private static final Logger logger = Logger.getLogger(FrameLogin.class);
	private static final long serialVersionUID = 1L;
    private Panel ctpEntrada; 
    private JTextField txfUsuario;
    private JPasswordField pwdClave;
    private JButton btnEntrar, btnSalir, btnConfigurarBD;
    private JLabel lblUsuario, lblClave;

    
    public FrameLogin() {
    	
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
				    UIManager.setLookAndFeel(info.getClassName());
				    break;
				}
		    }
		} catch (Exception ex) {
		    JOptionPane.showMessageDialog(null, Constantes.LOOK_FEEL,"ERROR", JOptionPane.ERROR_MESSAGE);
		    logger.debug(ex);
		    // si Nimbus no esta disponible se puede escojer otro look and feel
		}
	
		setIconImage(Toolkit.getDefaultToolkit().getImage(Constantes.RUTA_ICONOS+"logop.png"));
		setTitle("Ingreso al Sistema Cronometraje");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(310, 220);
		setLocationRelativeTo(null);
		setVisible(true);

		ctpEntrada = new Panel();
    	ctpEntrada.setLayout(null);
    	setContentPane(ctpEntrada);
    	
		lblUsuario = new JLabel("Usuario");
		lblUsuario.setIcon(new ImageIcon(Constantes.RUTA_ICONOS+"user.png"));
		lblUsuario.setFont(Fonts.FONT_LABEL);
		lblUsuario.setBounds(30, 20, 80, 25);
	
		txfUsuario = new JTextField();
		txfUsuario.setFont(Fonts.FONT_TEXT);
		txfUsuario.setBounds(130, 18, 125, 28);
		txfUsuario.setColumns(20);
		
		lblClave = new JLabel("Clave");
		lblClave.setIcon(new ImageIcon(Constantes.RUTA_ICONOS+"keys.png"));
		lblClave.setFont(Fonts.FONT_LABEL);
		lblClave.setBounds(30, 55, 75, 25);
		
		pwdClave = new JPasswordField();
		pwdClave.setBounds(130, 53, 125, 28);
		pwdClave.setColumns(20);
	
		btnEntrar = new JButton("Entrar");
		btnEntrar.setFont(Fonts.FONT_BOTON);
		btnEntrar.setIcon(new ImageIcon(Constantes.RUTA_ICONOS+"check.png"));
		btnEntrar.setBounds(55, 100, 90, 40);
	
		btnSalir = new JButton("Salir");
		btnSalir.setFont(Fonts.FONT_BOTON);
		btnSalir.setIcon(new ImageIcon(Constantes.RUTA_ICONOS+"delete.png"));
		btnSalir.setBounds(165, 100, 90, 40);
		
		btnConfigurarBD = new JButton("Configurar Base Datos");
		btnConfigurarBD.setFont(Fonts.FONT_BOTON);
		btnConfigurarBD.setIcon(new ImageIcon(Constantes.RUTA_ICONOS+"db.png"));
		btnConfigurarBD.setForeground(Color.BLUE);
		btnConfigurarBD.setBounds(58, 149, 175, 30);	
	
		
		ctpEntrada.add(lblUsuario);
		ctpEntrada.add(lblClave);
		ctpEntrada.add(txfUsuario);
		ctpEntrada.add(pwdClave);
		ctpEntrada.add(btnEntrar);
		ctpEntrada.add(btnSalir);
		//ctpEntrada.add(btnConfigurarBD);
		
		
		// Eventos de boton y de tecla.
		btnEntrar.addKeyListener(new KeyAdapter() {
		    public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
				    validarDatos();
				}
		    }
		});
		
	
		btnEntrar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	validarDatos();
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
		
	
		pwdClave.addKeyListener(new KeyAdapter() {
		    public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
				    validarDatos();
				}
		    }
		});
	
    }// fin constructor frmlogin
    

    private void validarDatos() {
		AccesoUsuario au = new AccesoUsuario();
		try {
		    au.setDatos(txfUsuario.getText().trim(), pwdClave.getPassword());
		} catch (Exception ex) {
		    JOptionPane.showMessageDialog(null, Constantes.EXCEPTION,"ERROR", JOptionPane.ERROR_MESSAGE);
		    logger.debug(ex);
		}
    } // envia los datos para validarlos
    

    public void salir() {
    	System.exit(0);
    }
    
    
}
