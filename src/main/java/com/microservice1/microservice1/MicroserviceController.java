package com.microservice1.microservice1;

import com.microservice1.microservice1.Model.Car;
import com.microservice1.microservice1.Service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/cars")
public class MicroserviceController {
    private static final Logger logger = LoggerFactory.getLogger(MicroserviceController.class);
    @Autowired
    private CarService carService;

    @GetMapping("/all")
    public List<Car> getAllCars() {//pridobim vse avte
        logger.info("Zahteva za pridobitev vseh avtomobilov prejeta");
        List<Car> cars = carService.getAllCars();
        logger.info("Pridobil {} avtov", cars.size());
        return cars;
    }

    @GetMapping("/{registerNumber}")//dobim avto glede na registrsko številko
    public ResponseEntity<Car> getCarByRegisterNumber(@PathVariable String registerNumber) {
        logger.info("Iscem avto z registrsko stevilko: {}", registerNumber);
        Optional<Car> carOptional = carService.getCarByRegisterNumber(registerNumber);
        if (carOptional.isPresent()) {
            logger.info("Avto najden");
            return ResponseEntity.ok(carOptional.get());
        } else {
            logger.info("Avto ni bil najden");
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/brand/{brand}") //dobim avte glede na znamko
    public ResponseEntity<List<Car>> getCarByBrand(@PathVariable String brand) {
        logger.info("Iscem avte z znamko: {}", brand);
        List<Car> cars = carService.getCarsByBrand(brand);
        if (!cars.isEmpty()) {
            logger.info("Avti z: {} znamko najdeni", brand);
            logger.info("Pridobil {} avtov", cars.size());
            return ResponseEntity.ok(cars);
        } else {
            logger.info("Ni avtomobilov znamke: {}", brand);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/color/{color}")//dobim avte glede na barvo
    public ResponseEntity<List<Car>> getCarByColor(@PathVariable String color) {
        logger.info("Iscem avte z barvo: {}", color);
        List<Car> cars = carService.getCarsByColor(color);
        if (!cars.isEmpty()) {
            logger.info("Avti {} barve najdeni", color);
            logger.info("Pridobil {} avtov", cars.size());
            return ResponseEntity.ok(cars);
        } else {
            logger.info("Ni bilo najdenih avtomobilov s to barvo");
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addCar")
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        logger.info("Zahteva za vstavljanje avta: {}", car);
        Car savedCar = carService.saveCar(car);
        logger.info("Avto uspesno dodan: {}", savedCar);
        return ResponseEntity.ok(savedCar);
    }


    @PutMapping("/{registerNumber}")
    public ResponseEntity<?> updateCar(@PathVariable String registerNumber, @RequestBody Car updatedCar) {
        logger.info("Dobljena zahteva za posodobitev avta z registrsko stevilko: {}", registerNumber);
        Optional<Car> existingCarOptional = carService.getCarByRegisterNumber(registerNumber);
        if (existingCarOptional.isPresent()) {
            Car existingCar = existingCarOptional.get();
            existingCar.setKilometersDriven(updatedCar.getKilometersDriven());
            carService.saveCar(existingCar);
            logger.info("Avto z registrsko številko {} uspesno posodobljen", registerNumber);
            return ResponseEntity.ok().build();
        } else {
            logger.info("Avto ni bil najden", registerNumber);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{registerNumber}")//brisanje glede na registrsko
    public ResponseEntity<?> deleteCar(@PathVariable String registerNumber) {
        logger.info("Dobljena zahteva za izbris avta z registrsko stevilko: {}", registerNumber);
        Optional<Car> existingCarOptional = carService.getCarByRegisterNumber(registerNumber);
        if (existingCarOptional.isPresent()) {
            carService.deleteCar(registerNumber);
            logger.info("Avto z registrsko stevilko: {} uspesno izbrisan.", registerNumber);
            return ResponseEntity.ok().body("Avto izbrisan");
        } else {
            logger.info("Avto ni bil najden", registerNumber);
            return ResponseEntity.notFound().build();
        }
    }

}
