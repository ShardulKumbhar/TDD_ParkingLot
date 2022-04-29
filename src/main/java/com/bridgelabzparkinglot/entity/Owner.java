package com.bridgelabzparkinglot.entity;

import com.bridgelabzparkinglot.interfac.IParkingObserver;

import java.util.Map;

public class Owner implements IParkingObserver {
    private String message;
    private int lotNumber;
    private String key;

    @Override
    public void updateMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;

    }

    public String generateKeyForLot(Map<String, Car> parkingMap) {
        if (parkingMap.size() == 0)
            this.key = String.valueOf(lotNumber);
        for (String key : parkingMap.keySet()) {
            this.key = key;
            if (!parkingMap.get(key).equals(key))
                this.key = String.valueOf(lotNumber);
        }
        lotNumber++;
        return key;
    }
}