# SmartParking (OOP1) — Java 21

Simple console-based parking lot manager demonstrating Java 21 features required for OOP1.

## How to run
- JDK 21 required.
- From project root:
  - Compile: `javac -d out $(find src -name "*.java")`
  - Run: `java -cp out com.smartparking.app.Main`

Or import in Eclipse and run the `Main` class.

## Structure
- `src/com/smartparking/model` — domain types (Car, ParkingSlot, ParkingReceipt)
- `src/com/smartparking/service` — ParkingLot, FeeCalculator
- `src/com/smartparking/exceptions` — custom exceptions
- `src/com/smartparking/app` — Main UI

