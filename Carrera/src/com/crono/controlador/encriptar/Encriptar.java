/**
 * @author Freilin Manzano
 * @email freilind@gmail.com
 * @date 30-sep-2011
 */
package com.crono.controlador.encriptar;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

import com.crono.util.Constantes;

public class Encriptar {
	
	private static final Logger logger = Logger.getLogger(Encriptar.class);
    public static String SHA1 = "SHA-1";

    /***
     * Convierte un arreglo de bytes a String usando valores hexadecimales
     * 
     * @param digest arreglo de bytes a convertir
     * @return String creado a partir de <code>digest</code>
     */
    private static String toHexadecimal(byte[] digest) {
		String hash = "";
		for (byte aux : digest) {
		    hash += String.format("%02x", aux & 0xff);
		}
		logger.info(hash);
		return hash;
    }

    /***
     * Encripta un mensaje de texto mediante algoritmo de resumen de mensaje.
     * 
     * @param message
     *            texto a encriptar
     * @return mensaje encriptado
     */
    public static String SHA1(char[] clave) {

		String clave1 = new String(clave);
		
		byte[] digest = null;
		byte[] buffer = clave1.getBytes();
		clave1=null;
		
		try {
		    MessageDigest md = MessageDigest.getInstance(SHA1); 
		    md.update(buffer);
		    digest = md.digest();
		    md.reset();
		} catch (NoSuchAlgorithmException nsae) {
		    JOptionPane.showMessageDialog(null, Constantes.ERROR_ENCRIPTANDO,"ERROR", JOptionPane.ERROR_MESSAGE);
		    logger.debug(nsae);
		}catch (Exception ex) {
		    JOptionPane.showMessageDialog(null, Constantes.EXCEPTION,"ERROR", JOptionPane.ERROR_MESSAGE);
		    logger.debug(ex);
		}
		return toHexadecimal(digest);
    }

}
