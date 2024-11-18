package pl.edu.pjatk.MPRprojekt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pjatk.MPRprojekt.exception.CarAlreadyExists;
import pl.edu.pjatk.MPRprojekt.exception.CarNotFoundException;
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
        car.setIndex();
        List<Car> carIndexList = this.carRepository.findByIndex(car.getIndex());
        if (!carIndexList.isEmpty()) {
            throw new CarAlreadyExists();
        }
        carRepository.save(car);
     }

     public void deleteCarById(Long id) {
         carRepository.deleteById(id);
     }

     public Car getCarById(Long id) {
        Optional<Car> car = this.carRepository.findById(id);

        if (car.isEmpty()) {
            throw new CarNotFoundException();
        }
         return car.get();
     }

     public List<Car> getCarByIndex(int index) {
        List<Car> cars = this.carRepository.findByIndex(index);
        if (cars.isEmpty()) {
            throw new CarNotFoundException();
        }
        return cars;
     }

     public void updateCar(Car updatedCar) {
         carRepository.findById(updatedCar.getId()).ifPresent(c -> {
             c.setModel(updatedCar.getModel());
             c.setBrand(updatedCar.getBrand());
             c.setIndex();
             carRepository.save(c);
         });
     }
}
