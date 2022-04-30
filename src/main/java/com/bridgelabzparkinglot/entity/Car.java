package com.bridgelabzparkinglot.entity;

/**
 * We have defined this entity as class car with their properties.
 */
public class Car {
    private int id;
    private String regNumber;

    public Car(int id, String regNumber) {
        this.regNumber = regNumber;
        this.id = id;
    }

    public int getID() {
        return id;
    }
}
