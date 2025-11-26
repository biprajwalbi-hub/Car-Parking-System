package com.smartparking.model;

import java.time.LocalDateTime;

public record Car(String regNumber, String ownerName, CarType carType, LocalDateTime entryTime) implements Vehicle {
    public String registration() { return regNumber; }
}