package com.api.parkingcontrol.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.parkingcontrol.dtos.ParkingSpotDto;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.services.ParkingSpotService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
@RequiredArgsConstructor
public class ParkingSpotController {

    private final ParkingSpotService parkingSpotService;

    @PostMapping
    public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto) {
        if (parkingSpotService.existsByLicensePlateCar(parkingSpotDto.getLicensePlateCar())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLICT: License Plate Car is already in use! ");
        }

        if (parkingSpotService.existsByParkingSpotNumber(parkingSpotDto.getParkingSpotNumber())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLICT: Parking Spot Number is already in use!");
        }

        if (parkingSpotService.existsByApartmentAndBlock(parkingSpotDto.getApartment(), parkingSpotDto.getBlock())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("CONFLICT: Parking Spot already registered for this apartment/block");
        }

        var parkingSpotModel = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
        parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel));
    }

    @GetMapping
    public ResponseEntity<List<ParkingSpotModel>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneParkingSpot(@PathVariable(value = "id") UUID id) {
        Optional<ParkingSpotModel> parkingSpotModel = parkingSpotService.getOneParkingSpot(id);
        if (!parkingSpotModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot Not Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModel.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteParkingSpot(@PathVariable(value = "id") UUID id) {
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.getOneParkingSpot(id);
        if (!parkingSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot Not Found");
        }
        parkingSpotService.deleteParkingSpot(parkingSpotModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body("Parking Spot Deleted successfully");
    }

    // @PutMapping("/{id}")
    // public ResponseEntity<Object> updateParkingSpot(@PathVariable(value = "id")
    // UUID id, @RequestBody @Valid ParkingSpotDto parkingSpotDto) {
    // ParkingSpotModel parkingSpotModelForUpdated =
    // parkingSpotService.getOneParkingSpot(id).get();

    // parkingSpotModelForUpdated.setApartment(parkingSpotDto.getApartment());
    // parkingSpotModelForUpdated.setBlock(parkingSpotDto.getBlock());
    // parkingSpotModelForUpdated.setBrandCar(parkingSpotDto.getBrandCar());
    // parkingSpotModelForUpdated.setColorCar(parkingSpotDto.getColorCar());
    // parkingSpotModelForUpdated.setLicensePlateCar(parkingSpotDto.getLicensePlateCar());
    // parkingSpotModelForUpdated.setModelCar(parkingSpotDto.getModelCar());
    // parkingSpotModelForUpdated.setParkingSpotNumber(parkingSpotDto.getParkingSpotNumber());
    // parkingSpotModelForUpdated.setResponsibleName(parkingSpotDto.getResponsibleName());

    // return
    // ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.save(parkingSpotModelForUpdated));
    // }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateParkingSpot(@PathVariable(value = "id") UUID id,
            @RequestBody @Valid ParkingSpotDto parkingSpotDto) {

        Optional<ParkingSpotModel> parkingSpotById = parkingSpotService.getOneParkingSpot(id);
        if (!parkingSpotById.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot Not Found");
        }

        var parkingSpotModel = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
        parkingSpotModel.setId(parkingSpotById.get().getId());
        parkingSpotModel.setRegistrationDate(parkingSpotById.get().getRegistrationDate());
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.save(parkingSpotModel));
    }

}
