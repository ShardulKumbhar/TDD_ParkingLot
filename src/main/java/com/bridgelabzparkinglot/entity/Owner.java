package com.bridgelabzparkinglot.entity;

import com.bridgelabzparkinglot.interfac.IParkingObserver;

import java.util.Map;

public class Owner implements IParkingObserver {
    int key;
    int lotNumber = 1;
    public static String message;


    public void updateMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    /**
     * In this method we will check and generate the key to park the car.
     * We will return the key to the attendant to park the car.
     * We will increment the lot number and assign the key to it.
     */
    public Integer allocateSpaceToPark(Map<Integer, Car> parkingMap) {
        if (parkingMap.size() == 0)
            this.key = lotNumber;
        for (Integer key : parkingMap.keySet()) {
            this.key = key;
            if (!parkingMap.get(key).equals(key))
                this.key = lotNumber;
        }
        lotNumber++;
        return key;
    }

    /**
     * Method to update the key value for further implementations.
     */
    public void updateUnParkedVehicle(int key) {
        this.key = key;
    }
}