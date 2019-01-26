package br.com.carssystem.managedbeans;

import br.com.carssystem.dao.CarDAO;
import br.com.carssystem.entity.Car;
import br.com.carssystem.util.exception.ErrorSystem;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class CarBean {
    
    private Car car = new Car();
    private List<Car> cars = new ArrayList<>();
    private CarDAO carDAO = new CarDAO();
    
    public void addCar(){
        try {
            new CarDAO().saveCar(car);
            car= new Car();
            addMessage("SAVED!", "CAR SAVED WITH SUCCESS!", FacesMessage.SEVERITY_INFO);
        } catch (ErrorSystem ex) {
            addMessage(ex.getMessage(), ex.getCause().getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void listCar(){
        try {
            cars = carDAO.findAll();
            if(cars.isEmpty()){
                addMessage("NO DATA FOUND!", "YOUR SEARCH RETURNED NO CARS!", FacesMessage.SEVERITY_WARN);
            }
        } catch (ErrorSystem ex) {
            addMessage(ex.getMessage(), ex.getCause().getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void deleteCar(Car c){
        try {
            carDAO.deleteCar(c.getId());
            addMessage("DELETED!", "CAR DELETED WITH SUCCESS!", FacesMessage.SEVERITY_INFO);
        } catch (ErrorSystem ex) {
            addMessage(ex.getMessage(), ex.getCause().getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void edit(Car c){
        car = c;
    }
    
    public void addMessage(String summary,String detail, FacesMessage.Severity errorType){
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(errorType, summary, detail);
        context.addMessage(null, message);
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
