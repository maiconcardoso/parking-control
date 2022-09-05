package com.api.parkingcontrol.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.repositories.ParkingSpotRepostitory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParkingSpotService {

    private final ParkingSpotRepostitory parkingSpotRepostitory;

    @Transactional
    public ParkingSpotModel save(ParkingSpotModel parkingSpotModel) {
        return parkingSpotRepostitory.save(parkingSpotModel);
    }

    public boolean existsByLicensePlateCar(String licensePlateCar) {
        return parkingSpotRepostitory.existsByLicensePlateCar(licensePlateCar);
    }

    public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
        return parkingSpotRepostitory.existsByParkingSpotNumber(parkingSpotNumber);
    }

    public boolean existsByApartmentAndBlock(String apartment, String block) {
        return parkingSpotRepostitory.existsByApartmentAndBlock(apartment, block);
    }

    public Page<ParkingSpotModel> findAll(Pageable pageable) {
        return parkingSpotRepostitory.findAll(pageable);
    }

    public Optional<ParkingSpotModel> getOneParkingSpot(UUID id) {
        return parkingSpotRepostitory.findById(id);
    }
 
    @Transactional
    public void deleteParkingSpot(UUID id) {
        ParkingSpotModel ParkingSpotForDeleted = parkingSpotRepostitory.findById(id).get();
        parkingSpotRepostitory.delete(ParkingSpotForDeleted);
    }

    
}
