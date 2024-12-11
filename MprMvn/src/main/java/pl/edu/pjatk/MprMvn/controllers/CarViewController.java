package pl.edu.pjatk.MprMvn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.MprMvn.model.Car;
import pl.edu.pjatk.MprMvn.service.CarService;

@Controller
@RequestMapping("/view/car")
public class CarViewController {

    private final CarService carService;

    @Autowired
    public CarViewController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/all")
    public String getAllCars(Model model) {
        model.addAttribute("cars", carService.getAllCars());
        return "car-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("car", new Car());
        return "car-add";
    }

    @PostMapping("/add")
    public String addCar(@ModelAttribute Car car) {
        carService.addCar(car);
        return "redirect:/view/car/all";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Car car = carService.getCarById(id);
        model.addAttribute("car", car);
        return "car-update";
    }

    @PostMapping("/update")
    public String updateCar(@ModelAttribute Car car) {
        carService.updateCar(car);
        return "redirect:/view/car/all";
    }

    @GetMapping("/delete/{id}")
    public String showDeleteForm(@PathVariable Long id, Model model) {
        Car car = carService.getCarById(id);
        model.addAttribute("car", car);
        return "car-delete";
    }

    @PostMapping("/delete")
    public String deleteCar(@RequestParam Long id) {
        carService.deleteCarById(id);
        return "redirect:/view/car/all";
    }
}
