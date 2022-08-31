package com.api.parkingcontrol.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "TB_PARKING_SPOT")
@Data
public class ParkingSpotModel implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "PARKING_SPOT_NUMBER", nullable = false, unique = true, length = 10)
    private String parkingSpotNumber;

    @Column(name = "LICENSE_PLATE_CAR", nullable = false, unique = true, length = 7)
    private String licensePlateCar;

    @Column(name = "BRAND_CAR", nullable = false, length = 70)
    private String brandCar;

    @Column(name = "MODEL_CAR", nullable = false, length = 70)
    private String modelCar;

    @Column(name = "COLOR_CAR", nullable = false, length = 70)
    private String colorCar;

    @Column(name = "REGISTRATION_DATE", nullable = false)
    private LocalDateTime registrationDate;

    @Column(name = "RESPONSIBLE_NAME", nullable = false, length = 130)
    private String responsibleName;

    @Column(name = "APARTMENT", nullable = false, length = 30)
    private String apartment;

    @Column(name = "BLOCK", nullable = false, length = 30)
    private String block;
}
