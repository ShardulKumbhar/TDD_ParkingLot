package com.bridgelabzparkinglot.interfac;

import com.bridgelabzparkinglot.entity.Car;
import com.bridgelabzparkinglot.exception.ParkingLotException;

public interface IParkingLot {
    void parkVehicle(Car car) throws ParkingLotException;

    void unParkVehicle(Car car) throws ParkingLotException;

    void notifyToMonitor();

    boolean isParked(Car car);

    void addMonitor(IParkingObserver monitor);


    void notifyToObserver(String message);

    void addObserver(IParkingObserver monitor);

    boolean isUnParked(Car car);

    String getVehicle(Car car2);
}
