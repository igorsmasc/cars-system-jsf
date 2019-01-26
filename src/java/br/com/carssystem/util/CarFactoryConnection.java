package br.com.carssystem.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author igors
 */
public class CarFactoryConnection{
    
    private static Connection con;
    private static final String URL_CONEXAO = "jdbc:mysql://localhost/car-system";
    private static final String USUARIO = "admin";
    private static final String SENHA = "sbanco2013";

    public static Connection getCon() {
        if(con == null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(URL_CONEXAO, USUARIO, SENHA);
            } catch (SQLException ex) {
                Logger.getLogger(CarFactoryConnection.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CarFactoryConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return con;
    }
    
    public static void closeCon(){
        if(con != null){
            try {
                con.close();
                con = null;
            } catch (SQLException ex) {
                Logger.getLogger(CarFactoryConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
