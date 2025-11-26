package com.smartparking.service;

import com.smartparking.model.CarType;

public interface FeeCalculator {
    double calculateFee(long minutes, CarType type);

    default double calculateForHours(long hours, CarType type) {
        return calculateFee(hours * 60, type);
    }

    private static double baseRate(CarType type) {
        return switch (type) {
            case ELECTRIC -> 1.0;
            case SUV -> 2.0;
            default -> 1.5;
        };
    }

    static FeeCalculator simple() {
        return (minutes, type) -> {
            double rate = baseRate(type);
            return Math.ceil(minutes / 60.0) * rate * 10;
        };
    }
}