package pl.edu.pjatk.MPRprojekt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pl.edu.pjatk.MPRprojekt.model.Car;
import pl.edu.pjatk.MPRprojekt.service.CarService;

import java.util.List;
import java.util.Optional;

@RestController
public class     MyRestController {
    private CarService carService;

    @Autowired
    public MyRestController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping("car/all")
    public ResponseEntity<List<Car>> getAll() {
        this.carService.getAllCars();
        return new ResponseEntity<>(this.carService.getAllCars(), HttpStatus.OK);
    }

    @PostMapping ("car/add")
    public ResponseEntity<Void> create(@RequestBody Car car) {
        this.carService.addCar(car);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("car/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.carService.deleteCarById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("car/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        this.carService.getCarById(id);
        return new ResponseEntity<>(this.carService.getCarById(id), HttpStatus.OK);
    }

    @GetMapping("car/model/{model}")
    public ResponseEntity<List<Car>> getCarByModel(@PathVariable String model) {
        this.carService.getCarByModel(model);
        return new ResponseEntity<>(this.carService.getAllCars(), HttpStatus.OK);
    }

    @GetMapping("car/brand/{brand}")
    public ResponseEntity<List<Car>> getCarByBrand(@PathVariable String brand) {
        this.carService.getCarByBrand(brand);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("car/update")
    public ResponseEntity<Void> updateCar(@RequestBody Car updatedCar) {
        this.carService.updateCar(updatedCar);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
