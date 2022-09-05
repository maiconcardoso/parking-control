package com.api.parkingcontrol.configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.repositories.ParkingSpotRepostitory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConfigDataBase{

    private final ParkingSpotRepostitory parkingSpotRepostitory;

    @Bean
    private CommandLineRunner execute() {
        return args -> {
            ParkingSpotModel parkingSpotModel = new ParkingSpotModel();
            parkingSpotModel.setParkingSpotNumber("205");
            parkingSpotModel.setApartment("202");
            parkingSpotModel.setBlock("9");
            parkingSpotModel.setBrandCar("HONDA");
            parkingSpotModel.setColorCar("WHITE");
            parkingSpotModel.setModelCar("HRV");
            parkingSpotModel.setLicensePlateCar("RRS856");
            parkingSpotModel.setResponsibleName("Carlos Daniel");

            ParkingSpotModel newParkingSpotModel = new ParkingSpotModel();
            newParkingSpotModel.setParkingSpotNumber("232H");
            newParkingSpotModel.setApartment("45");
            newParkingSpotModel.setBlock("A");
            newParkingSpotModel.setBrandCar("Chevrolet");
            newParkingSpotModel.setColorCar("gray");
            newParkingSpotModel.setModelCar("TRAKER");
            newParkingSpotModel.setLicensePlateCar("RFG5454");
            newParkingSpotModel.setResponsibleName("Maicon Cardoso");

            parkingSpotRepostitory.save(parkingSpotModel);
            parkingSpotRepostitory.save(newParkingSpotModel);
        };
    }

    
    
    
}
