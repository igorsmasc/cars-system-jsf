package br.com.carssystem.managedbeans;

import br.com.carssystem.dao.CarDAO;
import br.com.carssystem.entity.Car;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class CarBean extends CrudBean<Car, CarDAO>{

    private CarDAO carDAO = new CarDAO();
    
    @Override
    public CarDAO getDao() {
        if(carDAO == null){
        carDAO = new CarDAO();
    } 
        return carDAO;
    }

    @Override
    public Car createNewEntity() {
        return new Car();
    }



}
