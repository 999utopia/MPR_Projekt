package pl.edu.pjatk.MPRprojekt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pl.edu.pjatk.MPRprojekt.model.Car;
import pl.edu.pjatk.MPRprojekt.service.CarService;

import java.util.List;
import java.util.Optional;

@RestController
public class MyRestController {
    private CarService carService;

    @Autowired
    public MyRestController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("car/all")
    public List<Car> getAll() {
        return this.carService.getAllCars();
    }

    @PostMapping ("car/add")
    public void create(@RequestBody Car car) {
        this.carService.addCar(car);
    }

    @DeleteMapping("car/delete/{id}")
    public void delete(@PathVariable Long id) {
        this.carService.deleteCarById(id);
    }

    @GetMapping("car/{id}")
    public Optional<Car> getCarById(@PathVariable Long id) {
        return this.carService.getCarById(id);
    }

    @GetMapping("car/model/{model}")
    public List<Car> getCarByModel(@PathVariable String model) {
        return this.carService.getCarByModel(model);
    }

    @GetMapping("car/brand/{brand}")
    public List<Car> getCarByBrand(@PathVariable String brand) {
        return this.carService.getCarByBrand(brand);
    }

    @PutMapping("car/update")
    public void updateCar(@RequestBody Car updatedCar) {
        this.carService.updateCar(updatedCar);
    }

}
