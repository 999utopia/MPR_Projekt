package pl.edu.pjatk.MPRprojekt.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pjatk.MPRprojekt.exception.CarAlreadyExists;
import pl.edu.pjatk.MPRprojekt.exception.CarNotFoundException;
import pl.edu.pjatk.MPRprojekt.model.Car;
import pl.edu.pjatk.MPRprojekt.repository.CarRepository;

import java.io.File;
import java.io.IOException;
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

        this.carRepository.save(new Car("BMW", "M2"));
        this.carRepository.save(new Car("AUDI", "RS6"));
        this.carRepository.save(new Car("MERCEDES", "AMG GT"));
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

    public String getCarByIdToPdf(Long id) {
        Car car = getCarById(id);
        String filePath = "";
        try (PDDocument document = new PDDocument();
             PDPageContentStream contentStream = new PDPageContentStream(document, new PDPage())) {
            document.addPage(new PDPage());

            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("Car Details:");
            contentStream.newLine();
            contentStream.showText("Brand: " + car.getBrand());
            contentStream.newLine();
            contentStream.showText("Model: " + car.getModel());
            contentStream.newLine();
            contentStream.showText("Index: " + car.getIndex());
            contentStream.endText();

            filePath = "car-details-" + car.getId() + ".pdf";
            document.save(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }
}
