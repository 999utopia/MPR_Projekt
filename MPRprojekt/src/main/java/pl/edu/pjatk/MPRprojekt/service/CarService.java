package pl.edu.pjatk.MPRprojekt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pjatk.MPRprojekt.exception.CarAlreadyExists;
import pl.edu.pjatk.MPRprojekt.exception.CarNotFoundException;
import pl.edu.pjatk.MPRprojekt.model.Car;
import pl.edu.pjatk.MPRprojekt.repository.CarRepository;

import java.util.List;
import java.util.Optional;

@Component
public class CarService {
    private CarRepository carRepository;
    private StringUtilsService stringUtilsService;

    @Autowired
    public CarService(CarRepository repository, StringUtilsService stringUtilsService) {
         this.carRepository = repository;
         this.stringUtilsService = stringUtilsService;

         this.carRepository.save(new Car("BMW","M2"));
         this.carRepository.save(new Car("AUDI","RS6"));
         this.carRepository.save(new Car("MERCEDES","AMG GT"));
    }

    public List<Car> getCarByModel(String model) {
        return formatCars(carRepository.findByModel(model));
    }
    public List<Car> getCarByBrand(String brand) {
        return formatCars(carRepository.findByModel(brand));
    }

     public List<Car> getAllCars() {
         return formatCars((List<Car>) carRepository.findAll());
     }

     public void addCar(Car car) {
        car.setBrand(stringUtilsService.toUpperCase(car.getBrand()));
        car.setModel(stringUtilsService.toUpperCase(car.getModel()));
        car.setIndex();
        List<Car> carIndexList = this.carRepository.findByIndex(car.getIndex());
        if (!carIndexList.isEmpty()) {
            throw new CarAlreadyExists();
        }
        carRepository.save(car);
     }

     public void deleteCarById(Long id) {
        getCarById(id);
        carRepository.deleteById(id);
     }

     public Car getCarById(Long id) {
        Optional<Car> car = this.carRepository.findById(id);

        if (car.isEmpty()) {
            throw new CarNotFoundException();
        }
         return formatCar(car.get());
     }

     public List<Car> getCarByIndex(int index) {
        List<Car> cars = this.carRepository.findByIndex(index);
        if (cars.isEmpty()) {
            throw new CarNotFoundException();
        }
        return formatCars(cars);
     }

    public void updateCar(Car updatedCar) {
        Optional<Car> carOptional = carRepository.findById(updatedCar.getId());

        if (carOptional.isEmpty()) {
            throw new CarNotFoundException();
        }

        Car car = carOptional.get();
        car.setModel(stringUtilsService.toUpperCase(updatedCar.getModel()));
        car.setBrand(stringUtilsService.toUpperCase(updatedCar.getBrand()));
        car.setIndex();
        carRepository.save(car);
    }

    private Car formatCar(Car car) {
        car.setBrand(stringUtilsService.toCamelCase(car.getBrand()));
        car.setModel(stringUtilsService.toCamelCase(car.getModel()));
        return car;
    }

    private List<Car> formatCars(List<Car> cars) {
        cars.forEach(this::formatCar);
        return cars;
    }
}
