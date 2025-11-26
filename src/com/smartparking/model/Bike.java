package com.smartparking.model;

import java.time.LocalDateTime;

public final class Bike implements Vehicle {
    private final String regNumber;
    private final String ownerName;
    private final LocalDateTime entryTime;

    public Bike(String regNumber, String ownerName, LocalDateTime entryTime) {
        this.regNumber = regNumber;
        this.ownerName = ownerName;
        this.entryTime = entryTime;
    }

    @Override
    public String registration() { return regNumber; }
    public String ownerName() { return ownerName; }
    public LocalDateTime entryTime() { return entryTime; }
}