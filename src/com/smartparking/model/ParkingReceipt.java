package com.smartparking.model;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Custom immutable type for receipts. Not a record to show manual immutability.
 */
public final class ParkingReceipt {
    private final String regNumber;
    private final LocalDateTime entryTime;
    private final LocalDateTime exitTime;
    private final double amount;

    public ParkingReceipt(String regNumber, LocalDateTime entryTime, LocalDateTime exitTime, double amount) {
        this.regNumber = regNumber;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.amount = amount;
    }

    public String regNumber() { return regNumber; }
    public LocalDateTime entryTime() { return entryTime; }
    public LocalDateTime exitTime() { return exitTime; }
    public double amount() { return amount; }

    public Duration duration() { return Duration.between(entryTime, exitTime); }

    @Override
    public String toString() {
        return String.format("Receipt[reg=%s, dur=%s, amt=%.2f]", regNumber, duration(), amount);
    }
}