package br.com.carssystem.dao;

import br.com.carssystem.entity.Car;
import br.com.carssystem.util.CarFactoryConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
            PreparedStatement ps = con.prepareStatement("INSERT INTO `car` (`model`,`brand`,`color`,`year`) VALUES (?,?,?,?)");
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
    
        public List<Car> findAll(){
        try {
            Connection con = CarFactoryConnection.getCon();
            PreparedStatement ps = con.prepareStatement("select * from car");
            ResultSet resultSet = ps.executeQuery();
            List<Car> cars = new ArrayList<>();
                    //next na primeira vez acessa o primeiro dado, sempre q o while executar ele passa pro prox
                    // por ser boolean se não houver próx ele para a execução do while
            while(resultSet.next()){
                Car car = new Car();
                car.setId(resultSet.getInt("id"));
                car.setModel(resultSet.getString("model"));
                car.setBrand(resultSet.getString("brand"));
                car.setYear(resultSet.getDate("year"));
                cars.add(car);
            }
            return cars;
        } catch (SQLException ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        }
    
    
}
