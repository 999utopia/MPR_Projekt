package pl.edu.pjatk.MPRprojekt.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.pjatk.MPRprojekt.exception.CarAlreadyExists;
import pl.edu.pjatk.MPRprojekt.model.Car;
import pl.edu.pjatk.MPRprojekt.repository.CarRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CarServiceTest {
    private CarRepository repository;
    private StringUtilsService stringUtilsService;
    private CarService carService;

    @BeforeEach
    void setUp() {
        repository = mock(CarRepository.class);
        stringUtilsService = new StringUtilsService();
        carService = new CarService(repository, stringUtilsService);
    }

//    @Test
//    void testAddCar() {
//        Car car = new Car("bmw", "m3");
//        when(repository.findByIndex(anyInt())).thenReturn(List.of());
//        carService.addCar(car);
//        verify(repository).save(any(Car.class));
//    }
//
//    @Test
//    void testGetCarById() {
//        Car car = new Car("BMW", "M3");
//        car.setId(1L);
//        when(repository.findById(1L)).thenReturn(Optional.of(car));
//
//        Car result = carService.getCarById(1L);
//        assertEquals("Bmw", result.getBrand());
//        assertEquals("M3", result.getModel());
//    }
//
//    @Test
//    void testAddCarThrowsExceptionWhenCarExists() {
//        Car car = new Car("BMW", "M3");
//        when(repository.findByIndex(anyInt())).thenReturn(List.of(car));
//        assertThrows(CarAlreadyExists.class, () -> carService.addCar(car));
//    }
}
