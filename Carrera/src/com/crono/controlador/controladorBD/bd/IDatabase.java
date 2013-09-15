package com.crono.controlador.controladorBD.bd;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDatabase {
    
    public boolean isConected();

    public void conectar() throws SQLException, ClassNotFoundException;

    public Connection getConnection();

    public void close() throws SQLException;
}
