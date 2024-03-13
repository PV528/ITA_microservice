package com.microservice1.microservice1.Repository;

import com.microservice1.microservice1.Model.Car;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepo extends MongoRepository<Car, String> {
    Optional<Car> findByregisterNumber(String registerNumber);

    List<Car> findByBrand(String brand); //posebne metode ustvarjene posebej

    List<Car> findByColor(String color);
}
