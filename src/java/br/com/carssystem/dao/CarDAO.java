package br.com.carssystem.dao;

import br.com.carssystem.entity.Car;
import br.com.carssystem.util.CarFactoryConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Igor Santos
 */
public class CarDAO {
    
    public void saveCar(Car car){
        try {
            Connection con = CarFactoryConnection.getCon();
            PreparedStatement ps = con.prepareCall("INSERT INTO `carro` (`modelo`,`fabricante`,`cor`,`ano`) VALUES (?,?,?,?)");
            ps.setString(1, car.getModel());
            ps.setString(2, car.getBrand());
            ps.setString(3, car.getColor());
            ps.setDate(4, new Date(car.getYear().getTime()));
            ps.execute();
            CarFactoryConnection.closeCon();
        } catch (SQLException ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
