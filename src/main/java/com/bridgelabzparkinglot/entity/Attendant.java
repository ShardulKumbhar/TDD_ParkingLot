package com.bridgelabzparkinglot.entity;


import java.util.Map;

/**
 * Class attendant is taken as entity which follows the instruction of the owner to park the vehicle.
 */
public class Attendant {
    Owner owner = new Owner();

    /**
     * Method park vehicle will call the owner class to get the key number.
     * @param parkingMap - Passing the hash map
     * @return - will return the key generated from the owner.
     */
    public Integer parkVehicle(Map<Integer, Car> parkingMap) {
        return owner.allocateSpaceToPark(parkingMap);
    }
}