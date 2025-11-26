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

## Features demonstrated
See `report.pdf` for a mapping of required language features to files.

## Commits
Make sequential commits: `init project`, `add models`, `add service`, `add exceptions`, `add app main`, `fix fee calc & defensive copy`, `final cleanup`.

## Submission
Include `report.pdf/docx`, `src/`, `screencast.mp4`.
