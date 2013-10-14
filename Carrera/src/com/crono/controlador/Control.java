package com.crono.controlador;

import java.io.*;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.apache.log4j.Logger;

import com.crono.util.Constantes;
import com.crono.vista.FrameLogin;

public class Control {

	private static final Logger logger = Logger.getLogger(FrameLogin.class);
    private static String appPath = System.getProperties().getProperty("user.dir");//fichero TMP
    private static File fichero = new File( appPath + "\\check.tmp");    
    //tiempo en que se actualiza el fichero TMP
    private int segundos = 60;

    
    public Control(){
    	try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
				    UIManager.setLookAndFeel(info.getClassName());
				    break;
				}
		    }
		} catch (Exception ex) {
		    JOptionPane.showMessageDialog(null, Constantes.LOOK_FEEL,"ERROR", JOptionPane.ERROR_MESSAGE);
		    logger.info(ex);
		    // si Nimbus no esta disponible se puede escojer otro look and feel
		}
    };

    /**
     * Comprueba que archivo TMP exista, sino lo crea e inicia valores
     */
    public boolean comprobar(){           
        try {
			if (fichero.exists()) {
				long tiempo = leer();
				long res = restarTiempo(tiempo);
				if (res < segundos) {
					JOptionPane.showMessageDialog(null,Constantes.PROGRAMA_EJECUTANDOSE, "ERROR",JOptionPane.ERROR_MESSAGE);
					return false;
				} else {
					programarTarea();
					return true;
				}
			} else {//fichero no existe
				crearTMP();
				programarTarea();
				return true;
			}
		} catch (IOException ioe) {
			logger.debug(ioe);
			return false;
		} catch (Exception e) {
			logger.debug(e);
			return false;
		}        
    }

    /**
     * Lee el archivo TMP y retorna su valor 
     * @return LONG cantidad de milisegundos 
     * @throws IOException 
     */
    public long leer() throws IOException{
        String linea = "0";        
        BufferedReader bufferedReader=null;
        try {
            bufferedReader = new BufferedReader(new FileReader(fichero));            
            while(bufferedReader.ready()){
                linea = bufferedReader.readLine();            
            }
        }catch (IOException ioe) {
        	logger.debug(ioe);
        }finally{
        	bufferedReader.close();
        }
        return Long.valueOf(linea).longValue();
    }

    
    /**
     * Programa un proceso que se repite cada cierto tiempo
     */
    public void programarTarea() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate( new Runnable() {
            @Override
            public void run() {                   
                crearTMP(); 
            }
          }, 1000, segundos * 1000 , TimeUnit.MILLISECONDS ); //comienza dentro de 1 segundo y luego se repite cada N segundos
    }

    
    /**
     * Crea un archivo TMP con un unico valor, el tiempo en milisegundos
     */
    public void crearTMP(){
        Date fecha=new Date();        
        try {            
            BufferedWriter writer = new BufferedWriter(new FileWriter(fichero));                        
            writer.write(  String.valueOf( fecha.getTime() ) );                        
            writer.close();            
        } catch (IOException ioe) {
        	logger.debug(ioe);
        }        
    }

    
    /**
     * Resta el tiempo expresado en milisegundos
     * @param tiempoActual el tiempo actual del sistema expresado en milisegundos
     * @return tiempo el resultado expresado en segundos
     */
    public long restarTiempo( long tiempoActual ){
        Date date =new Date();        
        long tiempoTMP = date.getTime();        
        long tiempo = tiempoTMP - tiempoActual;        
        tiempo = tiempo /1000;        
        return tiempo;
    }

    
    /**
     * Elimina el fichero TMP si es que existe
     */
    public static void cerrarApp() {   
        if (fichero.exists()) { 
        	fichero.delete(); 
        }
        System.exit(0);
    }

}//--> fin clase
