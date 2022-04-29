package com.bridgelabzparkinglot;


import com.bridgelabzparkinglot.entity.AirportSecurity;
import com.bridgelabzparkinglot.entity.Car;
import com.bridgelabzparkinglot.entity.Owner;
import com.bridgelabzparkinglot.entity.ParkingLot;
import com.bridgelabzparkinglot.exception.ParkingLotException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

public class ParkingLotServiceTest {
    ParkingLot parkingLot = null;
    Owner owner = null;
    AirportSecurity security = null;

    @BeforeEach
    public void setUp() {
        parkingLot = new ParkingLot();
        owner = new Owner();
        security = new AirportSecurity();
    }

    /**
     * UC1- to park
     *
     * @throws ParkingLotException
     */
    @Test
    public void givenVehicle_WhenPark_ShouldReturnTrue() throws ParkingLotException {
        Car car = new Car("1", "MH-Swift");
        parkingLot.parkVehicle(car);
        boolean isParked = parkingLot.isParked(car);
        Assertions.assertTrue(isParked);
    }

    /**
     * UC-2 to Unpark
     *
     * @throws ParkingLotException
     */
    @Test
    public void givenVehicleIfParked_WhenUnParked_ShouldReturnTrue() throws ParkingLotException {
        Car car = new Car("1", "MH-Swift");
        parkingLot.parkVehicle(car);
        parkingLot.unParkVehicle(car);
        boolean isUnParked = parkingLot.isUnParked(car);
        Assertions.assertTrue(isUnParked);
    }

    @Test
    public void givenVehicleToUnPark_WhenNull_ShouldThrowException() {
        try {
            Car car = new Car("1", "MH-Swift");
            parkingLot.parkVehicle(car);
            parkingLot.unParkVehicle(null);
        } catch (ParkingLotException e) {
            System.out.println(e.type);
            Assertions.assertEquals(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, e.type);
        }
    }

    /**
     * UC3 -to show parking lot is full
     *
     * @throws ParkingLotException
     */
    @Test
    public void givenVehicleToPark_WhenOwner_ShouldInformInformLotFull() throws ParkingLotException {
        parkingLot.addMonitor(owner);
        Car car = new Car("1", "MH-Swift");
        Car car2 = new Car("2", "KA-Venu");
        parkingLot.parkVehicle(car);
        parkingLot.parkVehicle(car2);
        Assertions.assertEquals("Parking Lot Is Full", owner.getMessage());
    }

    /**
     * UC4 -to show parking lot is full - to security and owner
     *
     * @throws ParkingLotException
     */
    @Test
    public void givenVehicleToPark_WhenOwnerAndSecurity_ShouldInformInformLotFull() throws ParkingLotException {
        parkingLot.addMonitor(owner);
        parkingLot.addMonitor(security);
        Car car = new Car("1", "MH-Swift");
        Car car2 = new Car("2", "KA-Venu");
        parkingLot.parkVehicle(car);
        parkingLot.parkVehicle(car2);
        Assertions.assertEquals("Parking Lot Is Full", owner.getMessage());
        Assertions.assertEquals("Parking Lot Is Full", security.getMessage());
    }

    /**
     * UC5 - showing lot full when more vehicles get parked
     */
    @Test
    public void givenVehicleToPark_WhenMoreNumberOfVehicles_ShouldThrowException() {
        try {
            parkingLot.addMonitor(owner);
            Car car = new Car("1", "MH_Swift");
            Car car2 = new Car("2", "KA-Venu");
            Car car3 = new Car("3", "GA-polo");
            parkingLot.parkVehicle(car);
            parkingLot.parkVehicle(car2);
            parkingLot.parkVehicle(car3);
        } catch (ParkingLotException e) {
            Assertions.assertEquals(ParkingLotException.ExceptionType.LOT_FULL, e.type);
        }
    }

    /**
     * When mismatch vehicle is unparked
     */
    @Test
    public void givenVehicleToUnPark_WhenWrongVehicle_ShouldThrowException() {
        try {
            Car car = new Car("1", "MH-Swift");
            Car car2 = new Car("2", "KA-polo");
            parkingLot.parkVehicle(car);
            parkingLot.unParkVehicle(car2);
        } catch (ParkingLotException e) {
            System.out.println(e.type);
            Assertions.assertEquals(ParkingLotException.ExceptionType.VEHICLE_MISMATCH, e.type);
        }
    }
}
