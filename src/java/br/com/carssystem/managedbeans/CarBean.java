package br.com.carssystem.managedbeans;

import br.com.carssystem.dao.CarDAO;
import br.com.carssystem.entity.Car;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class CarBean {
    
    private Car car = new Car();
    private List<Car> cars = new ArrayList<>();
    private CarDAO carDAO = new CarDAO();
    
    public void addCar(){
        new CarDAO().saveCar(car);
        car= new Car();
    }

    public void listCar(){
        cars = carDAO.findAll();
    }
    
    public void edit(Car c){
        car = c;
    }
    
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCarros(List<Car> carros) {
        this.cars = cars;
    }

}
