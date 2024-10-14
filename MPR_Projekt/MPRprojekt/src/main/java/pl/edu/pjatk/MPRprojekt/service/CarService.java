package pl.edu.pjatk.MPRprojekt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pjatk.MPRprojekt.model.Car;
import pl.edu.pjatk.MPRprojekt.repository.CarRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CarService {
    private CarRepository carRepository;

    @Autowired
    public CarService(CarRepository repository) {
         this.carRepository = repository;

         this.carRepository.save(new Car("BMW","M2"));
         this.carRepository.save(new Car("AUDI","RS6"));
         this.carRepository.save(new Car("MERCEDES","AMG GT"));
    }

    public List<Car> getCarByModel(String model) {
        return carRepository.findByModel(model);
    }
    public List<Car> getCarByBrand(String brand) {
        return carRepository.findByModel(brand);
    }

     public List<Car> getAllCars() {
         return (List<Car>) carRepository.findAll();
     }

     public void addCar(Car car) {
         carRepository.save(car);
     }

     public void deleteCarById(Long id) {
         carRepository.deleteById(id);
     }

     public Optional<Car> getCarById(Long id) {
         return carRepository.findById(id);
     }

     public void updateCar(Car updatedCar) {
         carRepository.findById(updatedCar.getId()).ifPresent(c -> {
             c.setModel(updatedCar.getModel());
             c.setBrand(updatedCar.getBrand());
             c.setIndex(updatedCar.getBrand(), updatedCar.getModel());
             carRepository.save(c);
         });
     }
}
