package com.bridgelabzparkinglot;

public interface IParkingLot {
    void parkVehicle(Car car) throws ParkingLotException;

    void unParkVehicle(Car car) throws ParkingLotException;

    boolean isParked(Car car);

    boolean isUnParked(Car car);

    void notifyToMonitor();

    void addMonitor(IParkingMonitor monitor);
}
