package pl.edu.pjatk.MPRprojekt.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity // <- 1.
public class Car {
    @Id // <- 2.
    @GeneratedValue(strategy = GenerationType.AUTO) // <- 3.
    private Long  id;
    private String brand;
    private String model;
//    identyfikator czy coś
    private int index;

    public Car() {
    }

    public Car(String brand, String model) {
        this.brand = brand;
        this.model = model;
        setIndex();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex() {
        int result = 0;
        for (int i = 0; i < brand.length(); i++) {
            result += Character.getNumericValue(brand.charAt(i));
        }
        for (int i = 0; i < model.length(); i++) {
            result += Character.getNumericValue(model.charAt(i));
        }
        this.index = result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "ID=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
