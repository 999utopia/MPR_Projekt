package pl.edu.pjatk.MprMvn.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.pjatk.MprMvn.exception.InvalidCarDataException;

@NoArgsConstructor
@Data
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String brand;
    private String model;
    private int index;

    public Car(String brand, String model) {
        validateParams(brand, model);
        this.brand = brand;
        this.model = model;
        setIndex();
    }

    private void validateParams (String... args) {
        for (String arg : args) {
            if (arg == null || arg.trim().isEmpty()) {
                throw new InvalidCarDataException();
            }
        }
    }

    public void setBrand(String brand) {
        validateParams(brand);
        this.brand = brand;
        setIndex();
    }

    public void setModel(String model) {
        validateParams(model);
        this.model = model;
        setIndex();
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
}
