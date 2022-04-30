package com.bridgelabzparkinglot;


import com.bridgelabzparkinglot.Service.ParkingLot;
import com.bridgelabzparkinglot.entity.AirportSecurity;
import com.bridgelabzparkinglot.entity.Car;
import com.bridgelabzparkinglot.entity.Owner;
import com.bridgelabzparkinglot.exception.ParkingLotException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParkingLotServiceTest {

    ParkingLot parkingLot;
    Owner owner;
    AirportSecurity airportSecurity;

    /**
     * Created object of class car
     */
    public static Car car1 = new Car(1, "MH-12-7744");
    public static Car car2 = new Car(2, "MH-89-7896");
    public static Car car3 = new Car(3, "KA-20-4587");
    public static Car car4 = new Car(4, "GA-30-7895");

    @BeforeEach
    public void setUp() {
        parkingLot = new ParkingLot();
        airportSecurity = new AirportSecurity();
        owner = new Owner();
    }


    /**
     * UC1- to park
     *
     */
    @Test
    public void givenVehicle_WhenPark_ShouldReturnTrue() throws ParkingLotException {
        parkingLot.parkVehicle(car1);
        boolean isParked = parkingLot.isParked(car1);
        assertTrue(isParked);
    }


    /**
     * UC-2 to Unpark
     *
     * @throws ParkingLotException
     */
    @Test
    public void givenVehicleIfParked_WhenUnParked_ShouldReturnTrue() throws ParkingLotException {
        try {
            parkingLot.parkVehicle(car1);
            int key = parkingLot.getVehicle(car1);
            parkingLot.unParkVehicle(key);
        } catch (ParkingLotException e) {
            boolean isUnParked = parkingLot.isUnParked(car1);
            assertTrue(isUnParked);
        }
    }

    /**
     * UC3 -to show parking lot is full
     *
     * @throws ParkingLotException
     */
    @Test
    public void givenParkingLot_IfFull_ShouldThrowException_LotIsFull() {
        try {
            parkingLot.parkVehicle(car1);
            parkingLot.parkVehicle(car2);
            parkingLot.parkVehicle(car3);
        } catch (ParkingLotException e) {
            System.out.println(e.message);
            assertEquals("Parking Lot Is Full", e.message);
        }
    }

    /**
     * UC4 -to show parking lot is full - to Airportsecurity
     *
     * @throws ParkingLotException
     */
    @Test
    public void givenVehicleToPark_WhenLotFull_ShouldNotifyAirportSecurity() {
        try {
            parkingLot.parkVehicle(car1);
            parkingLot.parkVehicle(car2);
            parkingLot.parkVehicle(car3);
        } catch (ParkingLotException e) {
            assertEquals("Parking Lot Is Full", airportSecurity.getMessage());
        }
    }

    /**
     * UC 5 : Notify the owner to take in Full sign.
     */
    @Test
    public void givenWhenSpaceAvailable_ShouldNotifyOwner_ToTakeInTheFullSign() throws ParkingLotException {
        try {
            parkingLot.parkVehicle(car1);
            parkingLot.parkVehicle(car2);
            int key = parkingLot.getVehicle(car1);
            parkingLot.unParkVehicle(key);
            int key1 = parkingLot.getVehicle(car2);
            parkingLot.unParkVehicle(key1);
            parkingLot.parkVehicle(car3);
        } catch (ParkingLotException e) {
            assertEquals("Space Available", owner.getMessage());
        }
    }

    /**
     * UC 6 : As a parking lot Owner I want a parking attendant to park cars
     */
    @Test
    public void givenAttendant_WhenInstructed_ShouldParkCars_AndReturnTrue() throws ParkingLotException {
        parkingLot.parkVehicle(car1);
        parkingLot.parkVehicle(car2);
        boolean isParked = parkingLot.isParked(car2);
        assertTrue(isParked);
    }
    /**
     * UC 7 : As a driver I want to find the car
     */
    @Test
    public void givenDriven_WhenFoundTheCar_shouldReturnLotNumber() throws ParkingLotException {
        parkingLot.parkVehicle(car1);
        parkingLot.parkVehicle(car2);
        int lotNumber = parkingLot.getVehicle(car2);
        assertEquals(2, lotNumber);
    }

    /**
     * UC 8 :know the parking Time so that I can charge lot users.
     */
    @Test
    public void givenVehicle_whenParked_ShouldReturnParkingTime() throws ParkingLotException {
        parkingLot.parkVehicle(car1);
        LocalDateTime time;
        time = LocalDateTime.now();
        System.out.println(time.withNano(0));
        assertEquals(time.withNano(0), parkingLot.parkingTime(car1));
    }
}
