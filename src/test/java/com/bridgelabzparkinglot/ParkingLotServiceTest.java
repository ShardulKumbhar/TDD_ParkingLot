package com.bridgelabzparkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotServiceTest {
    ParkingLot parkingLot = null;
    Object vehicle = null;
    Object vehicle2 = null;

    @BeforeEach
    public void setUp() {
        parkingLot = new ParkingLot();
        vehicle = new Object();
        vehicle2 = new Object();
    }

    /**
     * UC1-to park the vehicle return true
     *
     * @throws ParkingLotException
     */
    @Test
    public void givenVehicle_WhenPark_ShouldReturnTrue() throws ParkingLotException {
        parkingLot.parkVehicle(vehicle);
        boolean isParked = parkingLot.isParked();
        Assertions.assertTrue(isParked);
    }

    /**
     * UC2-UnPark Vehicle retur true
     *
     * @throws ParkingLotException
     */
    @Test
    public void givenVehicleIfParked_WhenUnParked_ShouldReturnTrue() throws ParkingLotException {
        parkingLot.parkVehicle(vehicle);
        parkingLot.unParkVehicle(vehicle);
        boolean isUnParked = parkingLot.isUnParked();
        Assertions.assertTrue(isUnParked);
    }

    /**
     * @throws ParkingLotException
     */
    @Test
    public void givenVehicle_WhenAlreadyParked_ShouldReturnFalse() throws ParkingLotException {
        parkingLot.parkVehicle(vehicle);
        boolean parkingStatus = parkingLot.isUnParked();
        Assertions.assertFalse(parkingStatus);
    }

    @Test
    public void givenVehicleIfParked_AndGivenDifferentVehicleToUnPark_ShouldReturnFalse() throws ParkingLotException {
        parkingLot.parkVehicle(vehicle);
        boolean parkingStatus = parkingLot.isUnParked();
        Assertions.assertFalse(parkingStatus);
    }

    /**
     * UC -3 Check for Parking lot is full
     */
    @Test
    public void givenVehicleToPark_IfLotFull_ShouldThrowException() {
        try {
            parkingLot.parkVehicle(vehicle);
            parkingLot.parkVehicle(vehicle2);
        } catch (ParkingLotException e) {
            System.out.println(e.type);
            Assertions.assertEquals(e.type, ParkingLotException.ExceptionType.LOT_FULL);
        }
    }

    @Test
    public void givenVehicleToUnPark_WhenNull_ShouldThrowException() {
        try {
            parkingLot.parkVehicle(vehicle);
            parkingLot.unParkVehicle(null);
        } catch (ParkingLotException e) {
            System.out.println(e.type);
            Assertions.assertEquals(e.type, ParkingLotException.ExceptionType.NO_SUCH_VEHICLE);
        }
    }

}
