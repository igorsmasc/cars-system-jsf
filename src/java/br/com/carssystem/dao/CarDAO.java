package br.com.carssystem.dao;

import br.com.carssystem.entity.Car;
import br.com.carssystem.util.CarFactoryConnection;
import br.com.carssystem.util.exception.ErrorSystem;
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
    
    public void saveCar(Car car) throws ErrorSystem{
        try {
            Connection con = CarFactoryConnection.getCon();
            PreparedStatement ps;
            if(car.getId() == null){
            ps = con.prepareStatement("INSERT INTO `car` (`model`,`brand`,`color`,`year`) VALUES (?,?,?,?)");
            } else {
                ps = con.prepareStatement("UPDATE car set model=?, brand=?, color=?, year=? where id=?");    
                ps.setInt(5, car.getId());
            }
            ps.setString(1, car.getModel());
            ps.setString(2, car.getBrand());
            ps.setString(3, car.getColor());
            ps.setDate(4, new Date(car.getYear().getTime()));
            ps.execute();
            CarFactoryConnection.closeCon();
        } catch (SQLException ex) {
            throw new ErrorSystem("SAVE CAR ERROR!", ex);
        }
        
    }
    
    public void deleteCar(Integer idCar) throws ErrorSystem{
        try {
            Connection con = CarFactoryConnection.getCon();
            PreparedStatement ps = con.prepareStatement("delete from car where id = ?");
            ps.setInt(1, idCar);
            ps.execute();
        } catch (SQLException ex) {
            throw new ErrorSystem("DELETE CAR ERROR!", ex);
        }
    }
    
        public List<Car> findAll() throws ErrorSystem{
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
                car.setColor(resultSet.getString("color"));
                car.setYear(resultSet.getDate("year"));
                cars.add(car);
            }
            CarFactoryConnection.closeCon();
            return cars;
        } catch (SQLException ex) {
            throw new ErrorSystem("LIST CAR ERROR!", ex);
        }
        }
    
    
}
