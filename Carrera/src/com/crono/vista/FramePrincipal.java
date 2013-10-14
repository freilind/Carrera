package com.crono.vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager.*;
import org.apache.log4j.Logger;
import com.crono.util.Constantes;

public class FramePrincipal extends JFrame {
	
	private static final Logger logger = Logger.getLogger(FramePrincipal.class);
	private static final long serialVersionUID = 1L;
	protected static int usrId;
    protected int usrNivel;
    public static JPanel ctpPrincipal;
    public static JTabbedPane tbpPrincipal;
    

    public FramePrincipal(int id, int rol) throws Exception {
	
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
		}
	
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(Constantes.RUTA_ICONOS+"logop.png")));
		setTitle("Sistema de Cron\u00F3metraje");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(700, 525);
		setLocationRelativeTo(null);
	
	
		ctpPrincipal = new JPanel();
		ctpPrincipal.setBorder(new EmptyBorder(3, 3, 3, 3));
		ctpPrincipal.setLayout(new BorderLayout(0, 0));
		setContentPane(ctpPrincipal);
		tbpPrincipal = new JTabbedPane(JTabbedPane.TOP);
		ctpPrincipal.add(tbpPrincipal, BorderLayout.CENTER);
	
		setVisibilidad();	   
	   

    }// fin del constructor **********************************************
    
    private void setVisibilidad(){
    	new FrameRegistro();
    	new FrameEvento();
    	new FrameCronometro();
    	new FrameResultados();
    	new FrameOtros();
    	
    }//fin visibilidad
    
  

}
