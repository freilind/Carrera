package com.crono.controlador.controladorBD.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import com.crono.login.Main;

public class DatabaseODBC implements IDatabase {
	
	private static final Logger logger = Logger.getLogger(DatabaseODBC.class);
	
    private Connection con = null;
    private String url;
    private String usr;
    private String pass;
    private String driver;
    private boolean isConected = false;

    // ---------------------------------------------------------------------------------
    public DatabaseODBC(String url, String usr, String pass, String driver) {
		this.url = url;
		this.usr = usr;
		this.pass = pass;
		this.driver = driver;
    }

    // ---------------------------------------------------------------------------------
    public void conectar() throws SQLException, ClassNotFoundException {
		Class.forName(this.driver);
		System.out.println("-->" + url + " - " + usr + " - " + pass);
		con = DriverManager.getConnection(url, usr, pass);
		isConected = true;
		System.out.println("Conexion realizada con exito!!");
    }

    // ---------------------------------------------------------------------------------
    public boolean isConected() {
    	return isConected;
    }

    // ---------------------------------------------------------------------------------
    public Connection getConnection() {
    	return con;
    }

    // ----------------------------------------------------------------------------------
    public void close() throws SQLException {
    	con.close();
    }
}
