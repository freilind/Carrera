package com.crono.controlador.controladorBD.util;

import java.util.ResourceBundle;
import org.apache.log4j.Logger;
import com.crono.controlador.encriptar.Encriptar;

public class LeerPropiedades {
	
	private static final Logger logger = Logger.getLogger(LeerPropiedades.class);
    public static String archivoRecurso = "";
    private static ResourceBundle resource = null;

    public static String leeID(String id) {
	resource = ResourceBundle.getBundle(archivoRecurso);

	return resource.getString(id);
    }
}
