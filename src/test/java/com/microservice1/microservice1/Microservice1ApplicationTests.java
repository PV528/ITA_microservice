package com.microservice1.microservice1;

import com.microservice1.microservice1.Model.Car;
import com.microservice1.microservice1.Service.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import java.util.Optional;


@SpringBootTest
class Microservice1ApplicationTests {

	@Mock
	private CarService carService;

	@InjectMocks
	private MicroserviceController microserviceController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	//Testi si vedno sledijo v vrstnem redu:
	// Priprava testnih podatkov
	// Definiranje vedenja "mock" objekta (v našem primeru mockamo CarService in jo "vstavimo - injectamo" v MicroserviceController
	// Klic metode, ki jo testiramo
	// Preverjanje rezultatov

	@Test
	public void testGetAllCars() {
		Car car1 = new Car("ABC123", "Toyota", "Corolla", "Red", 2010, 100000, 50);// Priprava testnih podatkov
		Car car2 = new Car("XYZ789", "Honda", "Civic", "Blue", 2015, 80000, 50);
		List<Car> expectedCars = Arrays.asList(car1, car2);
		when(carService.getAllCars()).thenReturn(expectedCars);// Definiranje vedenja "mock" objekta
		List<Car> actualCars = microserviceController.getAllCars();	// Klic metode, ki jo testiramo
		assertEquals(expectedCars, actualCars);		// Preverjanje rezultatov
	}

	@Test
	void testGetCarByRegisterNumber() {
		String registerNumber = "ABC123";
		Car expectedCar = new Car(registerNumber, "Toyota", "Corolla", "Red", 2010, 100000, 50);
		when(carService.getCarByRegisterNumber(registerNumber)).thenReturn(Optional.of(expectedCar));
		ResponseEntity<Car> response = microserviceController.getCarByRegisterNumber(registerNumber);
		assertEquals(expectedCar, response.getBody());
		assertEquals(200, response.getStatusCodeValue()); // Assuming OK status
	}

	@Test
	void testGetCarByBrand() {
		String brand = "Toyota";
		Car car1 = new Car("ABC123", brand, "Corolla", "Red", 2010, 100000, 50);
		Car car2 = new Car("XYZ789", brand, "Camry", "Blue", 2015, 80000, 50);
		List<Car> expectedCars = Arrays.asList(car1, car2);
		when(carService.getCarsByBrand(brand)).thenReturn(expectedCars);
		ResponseEntity<List<Car>> response = microserviceController.getCarByBrand(brand);
		assertEquals(expectedCars, response.getBody());
		assertEquals(200, response.getStatusCodeValue()); // Assuming OK status
	}

	@Test
	public void testGetCarsByColor() {
		String color = "Red";
		Car car1 = new Car("ABC123", "Toyota", "Corolla", color, 2010, 100000, 50);
		List<Car> expectedCars = Arrays.asList(car1);
		when(carService.getCarsByColor(color)).thenReturn(expectedCars);
		ResponseEntity<List<Car>> responseEntity = microserviceController.getCarByColor(color);
		assertEquals(expectedCars, responseEntity.getBody());
	}

	@Test
	public void	testaddCar(){
		Car carToAdd = new Car("ABC123", "Toyota", "Corolla", "Red", 2010, 100000, 50);
		when(carService.saveCar(any(Car.class))).thenReturn(carToAdd);
		ResponseEntity<Car> responseEntity = microserviceController.addCar(carToAdd);
		assertEquals(carToAdd, responseEntity.getBody());
	}

	@Test
	void testUpdateCar() {
		String registerNumber = "ABC123";
		Car existingCar = new Car(registerNumber, "Toyota", "Corolla", "Red", 2010, 100000, 50);
		Car updatedCar = new Car(registerNumber, "Toyota", "Corolla", "Red", 2010, 120000, 50);
		when(carService.getCarByRegisterNumber(registerNumber)).thenReturn(Optional.of(existingCar));
		when(carService.saveCar(existingCar)).thenReturn(existingCar);
		ResponseEntity<?> response = microserviceController.updateCar(registerNumber, updatedCar);
		assertEquals(200, response.getStatusCodeValue()); // Assuming OK status
	}

	@Test
	void testDeleteCar() {
		String registerNumber = "ABC123";
		Car existingCar = new Car(registerNumber, "Toyota", "Corolla", "Red", 2010, 100000, 50);
		when(carService.getCarByRegisterNumber(registerNumber)).thenReturn(Optional.of(existingCar));
		ResponseEntity<?> response = microserviceController.deleteCar(registerNumber);
		assertEquals(200, response.getStatusCodeValue());
		assertEquals("Avto izbrisan", response.getBody());
		verify(carService, times(1)).deleteCar(registerNumber);
	}
}

