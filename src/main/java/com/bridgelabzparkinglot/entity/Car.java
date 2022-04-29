package com.bridgelabzparkinglot.entity;

public class Car {
    private String id;
    private String regNumber;

    /*
    constructor
     */
    public Car(String id, String regNumber) {
        this.regNumber = regNumber;
        this.id = id;
    }

    public String getID()
    {
        return id;
    }
}
