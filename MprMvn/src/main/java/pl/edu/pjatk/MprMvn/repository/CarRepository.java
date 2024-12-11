package pl.edu.pjatk.MprMvn.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjatk.MprMvn.model.Car;

import java.util.List;

@Repository
//@Component
public interface CarRepository extends CrudRepository<Car, Long> {
    public List<Car> findByModel(String model);

    public List<Car> findByIndex(int index);
}
