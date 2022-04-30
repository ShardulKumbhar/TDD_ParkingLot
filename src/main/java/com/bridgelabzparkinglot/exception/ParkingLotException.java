package com.bridgelabzparkinglot.exception;

public class ParkingLotException extends Exception {

    public String message;

    public ParkingLotException(String message) {

        this.message = message;
    }
}

