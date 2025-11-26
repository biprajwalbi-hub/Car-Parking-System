package com.smartparking.service;

import com.smartparking.model.*;
import com.smartparking.exceptions.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class ParkingLot {
    private final List<ParkingSlot> slots;
    private final FeeCalculator feeCalculator;
    private final List<ParkingReceipt> receipts = new ArrayList<>();

    public ParkingLot(int capacity, FeeCalculator feeCalculator) {
        this.feeCalculator = Objects.requireNonNull(feeCalculator);
        this.slots = new ArrayList<>();
        for (var i = 1; i <= capacity; i++) this.slots.add(new ParkingSlot(i));
    }

    public List<ParkingSlot> getSlots() { return new ArrayList<>(slots); }

    public synchronized int parkVehicle(Vehicle v) throws ParkingLotFullException {
        var free = slots.stream().filter(s -> !s.isOccupied()).findFirst();
        if (free.isEmpty()) throw new ParkingLotFullException("Parking is full");
        var slot = free.get();
        slot.assignVehicle(v);
        return slot.getSlotId();
    }

    public synchronized ParkingReceipt exitVehicle(String regNumber, LocalDateTime exitTime) {
        var slotOpt = slots.stream().filter(s -> s.isOccupied() && s.getVehicle().registration().equals(regNumber)).findFirst();
        if (slotOpt.isEmpty()) throw new InvalidSlotException("Vehicle not found");
        var slot = slotOpt.get();
        var vehicle = slot.getVehicle();
        LocalDateTime entry;
        if (vehicle instanceof Car c) entry = c.entryTime();
        else if (vehicle instanceof Bike b) entry = b.entryTime();
        else entry = LocalDateTime.now().minusHours(1);

        long minutes = java.time.Duration.between(entry, exitTime).toMinutes();
        if (minutes <= 0) minutes = 1;
        var type = (vehicle instanceof Car car) ? car.carType() : CarType.HATCHBACK;
        double amt = feeCalculator.calculateFee(minutes, type);
        var receipt = new ParkingReceipt(vehicle.registration(), entry, exitTime, amt);
        receipts.add(receipt);
        slot.removeVehicle();
        return receipt;
    }

    public List<ParkingSlot> freeSlots() {
        return slots.stream().filter(s -> !s.isOccupied()).collect(Collectors.toList());
    }

    public List<ParkingSlot> occupiedSlots() {
        return slots.stream().filter(ParkingSlot::isOccupied).collect(Collectors.toList());
    }

    public void logEvents(String... messages) {
        for (var m : messages) System.out.println(m);
    }

    public List<ParkingReceipt> getReceipts() { return List.copyOf(receipts); }
}