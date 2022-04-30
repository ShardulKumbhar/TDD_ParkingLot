package com.bridgelabzparkinglot.Service;

import com.bridgelabzparkinglot.entity.AirportSecurity;
import com.bridgelabzparkinglot.entity.Attendant;
import com.bridgelabzparkinglot.entity.Car;
import com.bridgelabzparkinglot.entity.Owner;
import com.bridgelabzparkinglot.exception.ParkingLotException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ParkingLot {


    private final int PARKING_LOT_CAPACITY = 2;
    public final Map<Integer, Car> parkingMap = new HashMap<>();
    Owner owner = new Owner();
    Attendant attendant = new Attendant();

    /**
     * Method to park the Vehicle.
     * If array size is less than capacity then store id and car name
     * if array size is equals or gretter shows lot full Exception
     */
    public void parkVehicle(Car car) throws ParkingLotException {
        if (this.parkingMap.size() < PARKING_LOT_CAPACITY) {
            Integer key = attendant.parkVehicle(parkingMap);
            parkingMap.put(key, car);
            parkingTime(car);
            notifyToSystem("Space Available");
        } else if (this.parkingMap.size() >= PARKING_LOT_CAPACITY) {
            this.notifyToSystem("Parking Lot Is Full");
            throw new ParkingLotException("Parking Lot Is Full");
        }
    }

    /**
     * Method to Unpark the Vehicle.
     * if car=null shows empty parking(no such vehicle) throws Exception
     * if parking size is 0 lot is empty
     * if parked car and given car id is different shows mismatch exception
     */
    public void unParkVehicle(int key) throws ParkingLotException {
        if (key == 0)
            throw new ParkingLotException("No Such Vehicle");
        if (parkingMap.size() == 0)
            throw new ParkingLotException("Parking Lot Is Empty");
        if (!parkingMap.containsKey(key))
            throw new ParkingLotException("Wrong Vehicle");
        if (this.parkingMap.size() <= PARKING_LOT_CAPACITY - 1)
            notifyToSystem("Space Available");
        if (parkingMap.containsKey(key)) {
            owner.updateUnParkedVehicle(key);
            parkingMap.remove(key);
            notifyToSystem("Vehicle Unparked");
        }
    }

    /**
     * Method to check if the vehicle is parked or not.
     * If the Hash map key contains car id then the car is parked and will return true.
     */
    public boolean isParked(Car car) {
        return parkingMap.containsKey(car.getID());
    }

    /**
     * Method to check if the vehicle is Unparked or not.
     * If the Hash map key does not contains car id then the car is Unparked and will return true.
     */
    public boolean isUnParked(Car car) {
        return !parkingMap.containsKey(car.getID());
    }

    /**
     * Method to find the lot number of the vehicle if parked in the parking lot.
     * Iterating hashmap and equating each value of hashmap to the car if matched then return key as lot number of car.
     */
    public int getVehicle(Car car) {
        for (int key : parkingMap.keySet()) {
            if (parkingMap.get(key) == car)
                return key;
        }
        return 0;
    }

    /**
     * Method To Update Message To The Concern Entities
     */
    public void notifyToSystem(String message) {
        Owner owner = new Owner();
        AirportSecurity airportSecurity = new AirportSecurity();
        owner.updateMessage(message);
        airportSecurity.updateMessage(message);
    }

    /**
     * Method to get the time when the car was parked.
     */
    public LocalDateTime parkingTime(Car car) {
        LocalDateTime time;
        time = LocalDateTime.now();
        return time.withNano(0);
    }


}
