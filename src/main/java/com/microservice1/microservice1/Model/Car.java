package com.microservice1.microservice1.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Car")
public class Car {
    @Id
    private String registerNumber;
    private String brand;
    private String model;
    private String color;
    private int year;
    private int kilometersDriven;
    private int pricePerDay;

}
