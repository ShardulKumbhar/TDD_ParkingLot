package com.bridgelabzparkinglot;

public class ParkingLotException extends Exception {

    public ExceptionType type;

    public ParkingLotException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public enum ExceptionType {
        NO_SUCH_VEHICLE, VEHICLE_MISMATCH, LOT_FULL;
    }
}
