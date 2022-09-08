package com.api.parkingcontrol.configs;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.repositories.ParkingSpotRepostitory;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ConfigDataBase{

    private final ParkingSpotRepostitory parkingSpotRepostitory;

    // @Bean
    // public CommandLineRunner execute() {
    //     return args -> {
    //         ParkingSpotModel parkingSpotModel = new ParkingSpotModel();
    //         parkingSpotModel.setParkingSpotNumber("205");
    //         parkingSpotModel.setApartment("202");
    //         parkingSpotModel.setBlock("9");
    //         parkingSpotModel.setBrandCar("HONDA");
    //         parkingSpotModel.setColorCar("WHITE");
    //         parkingSpotModel.setModelCar("HRV");
    //         parkingSpotModel.setLicensePlateCar("RRS856");
    //         parkingSpotModel.setResponsibleName("Carlos Daniel");
    //         parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));

    //         ParkingSpotModel otherParkingSpotModel = new ParkingSpotModel();
    //         otherParkingSpotModel.setParkingSpotNumber("232H");
    //         otherParkingSpotModel.setApartment("45");
    //         otherParkingSpotModel.setBlock("A");
    //         otherParkingSpotModel.setBrandCar("Chevrolet");
    //         otherParkingSpotModel.setColorCar("gray");
    //         otherParkingSpotModel.setModelCar("TRAKER");
    //         otherParkingSpotModel.setLicensePlateCar("RFG5454");
    //         otherParkingSpotModel.setResponsibleName("Maicon Cardoso");
    //         otherParkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));

    //         List<ParkingSpotModel> parkingSpotModelList = new ArrayList<>();
    //         parkingSpotModelList.add(parkingSpotModel);
    //         parkingSpotModelList.add(otherParkingSpotModel);

    //         parkingSpotRepostitory.saveAll(parkingSpotModelList);
    //     };
    // }

    
    
    
}
