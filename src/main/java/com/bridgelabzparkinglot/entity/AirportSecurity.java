package com.bridgelabzparkinglot.entity;

import com.bridgelabzparkinglot.interfac.IParkingObserver;

/**
 * Created class to notify the Airport Security when the parking lot is full.
 */
public class AirportSecurity implements IParkingObserver {
    public static String message;

    /**
     * Method to update the message passed.
     * @param message
     */
    public void updateMessage(String message) {
        this.message = message;
    }

    /**
     * Method to pass the message when called.
     * @return
     */
    public String getMessage() {
        return message;
    }
}