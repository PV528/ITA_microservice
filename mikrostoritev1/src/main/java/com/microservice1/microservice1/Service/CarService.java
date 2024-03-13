package com.microservice1.microservice1.Service;
import com.microservice1.microservice1.Model.Car;
import com.microservice1.microservice1.Repository.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CarService {

    @Autowired
    private CarRepo carRepo;

    public List<Car> getAllCars() {//dobim vse
        return carRepo.findAll();
    }

    public Optional<Car> getCarByRegisterNumber(String registerNumber) {//glede na registrsko stevilko
        return carRepo.findByregisterNumber(registerNumber);
    }

    public List<Car> getCarsByBrand(String brand) {//glede na znamko
        return carRepo.findByBrand(brand);
    }

    public List<Car> getCarsByColor(String color) {//da pridobim avte glede na barvo
        return carRepo.findByColor(color);
    }

    public Car saveCar(Car car) {//za create in update
        return carRepo.save(car);
    }

    public void deleteCar(String registerNumber){
         carRepo.deleteById(registerNumber);
    }


}
