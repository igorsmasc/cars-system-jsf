package br.com.carssystem.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.com.carssystem.util.exception.ErrorSystem;

/**
 *
 * @author igors
 */
public class CarFactoryConnection{
    
    private static Connection con;
    private static final String URL_CONEXAO = "jdbc:mysql://localhost:3307/cars-system";
    private static final String USUARIO = "admin";
    private static final String SENHA = "sbanco2013";

    public static Connection getCon() throws ErrorSystem{
        if(con == null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(URL_CONEXAO, USUARIO, SENHA);
            } catch (SQLException ex) {
                throw new ErrorSystem("COULD NOT CONNECT TO DATABASE", ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CarFactoryConnection.class.getName()).log(Level.SEVERE, null, ex);
                throw new ErrorSystem("DATABASE DRIVER - NOT FOUND", ex);
            }
        }
        return con;
    }
    
    public static void closeCon() throws ErrorSystem{
        if(con != null){
            try {
                con.close();
                con = null;
            } catch (SQLException ex) {
                 throw new ErrorSystem("ERROR BY CLOSE CONNECTING TO THE DATABASE", ex);
            }
        }
    }
    
    
}
