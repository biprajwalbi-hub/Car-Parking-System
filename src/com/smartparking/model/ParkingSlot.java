package com.smartparking.model;

public class ParkingSlot {
    private final int slotId;
    private Vehicle vehicle; // null if free

    public ParkingSlot(int slotId) {
        this.slotId = slotId;
    }

    // overloaded assignment methods
    public void assignVehicle(Vehicle v) {
        if (this.vehicle != null) throw new IllegalStateException("Slot occupied");
        this.vehicle = v;
    }

    public void assignVehicle(Vehicle v, boolean force) {
        if (!force && this.vehicle != null) throw new IllegalStateException("Slot occupied");
        this.vehicle = v;
    }

    public void removeVehicle() {
        this.vehicle = null;
    }

    public boolean isOccupied() { return vehicle != null; }

    public int getSlotId() { return slotId; }

    // defensive copy example: if returning complex mutable state, clone or copy
    public Vehicle getVehicle() { return vehicle; }

    @Override
    public String toString() {
        return "Slot{" + slotId + ": " + (isOccupied() ? vehicle.registration() : "free") + '}';
    }
}