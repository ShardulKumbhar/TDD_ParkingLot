package com.bridgelabzparkinglot;

public interface IParkingLot {
    void parkVehicle(Object vehicle) throws ParkingLotException;

    void unParkVehicle(Object vehicle) throws ParkingLotException;

    boolean isParked();

    boolean isUnParked();
}
