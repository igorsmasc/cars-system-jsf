/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.carssystem.dao;

import br.com.carssystem.entity.Car;
import br.com.carssystem.entity.User;
import br.com.carssystem.util.CarFactoryConnection;
import br.com.carssystem.util.exception.ErrorSystem;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author igors
 */
public class UserDAO implements CrudDAO<User>{

    @Override
    public void save(User entity) throws ErrorSystem {
        try {
            Connection con = CarFactoryConnection.getCon();
            PreparedStatement ps;
            if(entity.getId() == null){
            ps = con.prepareStatement("INSERT INTO `user` (`login`,`password`) VALUES (?,?)");
            } else {
                ps = con.prepareStatement("UPDATE usuario set login=?, password=? where id=?");    
                ps.setInt(3, entity.getId());
            }
            ps.setString(1, entity.getLogin());
            ps.setString(2, entity.getPassword());
            ps.execute();
            CarFactoryConnection.closeCon();
        } catch (SQLException ex) {
            throw new ErrorSystem("SAVE USER ERROR!", ex);
        }
    }

    @Override
    public void delete(User entity) throws ErrorSystem {
        try {
            Connection con = CarFactoryConnection.getCon();
            PreparedStatement ps = con.prepareStatement("delete from user where id = ?");
            ps.setInt(1, entity.getId());
            ps.execute();
        } catch (SQLException ex) {
            throw new ErrorSystem("DELETE CAR ERROR!", ex);
        }
    }

    @Override
    public List<User> findAll() throws ErrorSystem {
       try {
            Connection con = CarFactoryConnection.getCon();
            PreparedStatement ps = con.prepareStatement("select * from user");
            ResultSet resultSet = ps.executeQuery();
            List<User> users = new ArrayList<>();
                    //next na primeira vez acessa o primeiro dado, sempre q o while executar ele passa pro prox
                    // por ser boolean se não houver próx ele para a execução do while
            while(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
            CarFactoryConnection.closeCon();
            return users;
        } catch (SQLException ex) {
            throw new ErrorSystem("LIST USERS ERROR!", ex);
        }
    }
    
}
