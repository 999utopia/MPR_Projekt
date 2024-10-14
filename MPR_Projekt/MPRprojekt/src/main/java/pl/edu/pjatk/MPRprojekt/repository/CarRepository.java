package pl.edu.pjatk.MPRprojekt.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjatk.MPRprojekt.model.Car;

import java.util.List;

@Repository
//@Component
public interface CarRepository extends CrudRepository<Car, Long> {
    public List<Car> findByModel(String model);
}
