package com.bridgelabzparkinglot;

import com.bridgelabzparkinglot.interfac.IParkingObserver;

public enum Notifications {

    PARKING_LOT_IS_FULL("Parking Lot Is Full"),
    HAVE_SPACE_TO_PARK("Have Space To Park");

    public String message;

    Notifications(String message) {
        this.message = message;
    }
}
