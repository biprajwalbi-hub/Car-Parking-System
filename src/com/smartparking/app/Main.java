package com.smartparking.app;

import com.smartparking.model.*;
import com.smartparking.service.*;
import com.smartparking.util.TimeUtils;
import com.smartparking.exceptions.*;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var feeCalc = FeeCalculator.simple();
        var lot = new ParkingLot(5, feeCalc);
        var scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> doPark(lot, scanner);
                case "2" -> doExit(lot, scanner);
                case "3" -> doView(lot);
                case "4" -> doAdmin(lot);
                case "0" -> running = false;
                default -> System.out.println("Invalid option");
            }
        }
        System.out.println("Exiting...");
    }

    private static void printMenu() {
        System.out.println("\nSmartParking - Menu:\n1) Park  2) Exit  3) View slots  4) Admin  0) Quit");
        System.out.print("Choose: ");
    }

    private static void doPark(ParkingLot lot, Scanner scanner) {
        try {
            System.out.print("Enter reg number: ");
            var reg = scanner.nextLine().trim();
            System.out.print("Owner name: ");
            var owner = scanner.nextLine().trim();
            var car = new Car(reg, owner, CarType.SEDAN, LocalDateTime.now());
            int slot = lot.parkVehicle(car);
            System.out.println("Parked at slot: " + slot);
        } catch (ParkingLotFullException e) {
            System.out.println("Cannot park: " + e.getMessage());
        }
    }

    private static void doExit(ParkingLot lot, Scanner scanner) {
        System.out.print("Enter reg number: ");
        var reg = scanner.nextLine().trim();
        try {
            var receipt = lot.exitVehicle(reg, LocalDateTime.now());
            System.out.println("Receipt: " + receipt);
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void doView(ParkingLot lot) {
        System.out.println("Free slots:");
        lot.freeSlots().forEach(System.out::println);
        System.out.println("Occupied slots:");
        lot.occupiedSlots().forEach(System.out::println);
    }

    private static void doAdmin(ParkingLot lot) {
        System.out.println("Receipts:");
        lot.getReceipts().forEach(System.out::println);
    }
}