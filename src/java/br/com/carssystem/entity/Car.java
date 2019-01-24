
package br.com.carssystem.entity;

/**
 *
 * @author igors
 */
public class Car {
    private String model;
    private String brand;
    private String color;
    private Integer year; //O valor de Integer nunca será null, inicia com 0

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
   
}
